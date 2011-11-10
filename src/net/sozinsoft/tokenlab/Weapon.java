package net.sozinsoft.tokenlab;

import java.util.LinkedList;
import java.util.regex.Pattern;

class Weapon {
    public static final String DEX_BONUS = "DexBonus";
    public static final String STR_BONUS1_5 = "StrBonus1.5";
    public static final String STR_BONUS = "StrBonus";
    public static final String INT_BONUS = "IntBonus";
    String name;
    String basicName;
    String baseAttackBonus;
    Damage damage;
    String damageDice;
    String category;
    int critFloor;
    int critMultiplier;
    int enhancementBonus = 0;
    int isMasterwork = 0;
    String equipped;
    int numFullAttacks = 0;
    String weaponType;
    String abilityBonus;
    int hasWeaponProficiency = 0;
    int hasWeaponFocus = 0;
    int hasWeaponSpecialization = 0;
    private int twoWeaponFightingPenalty = 0;
    int shieldAttackPenalty = 0;
    String description;

    private LinkedList<String> attacks = new LinkedList<String>();


    public Weapon(String name, String damage, String category, String crit,
                  String attackBonus, String equipped, String weaponType, String description) {
        this.name = name;
        basicName = extractBasicWeaponName(name);
        parseEnhancementBonus(name);
        this.damage = new Damage(damage);
        this.damageDice = this.damage.asExpression();
        this.category = category;
        parseCrit(crit);
        parseAttackBonus(attackBonus);
        numFullAttacks = attacks.size();
        this.equipped = equipped;
        this.weaponType = weaponType;
        this.description = description;
    }


    //see http://www.d20pfsrd.com/equipment---final/weapons for how this stuff all works.
    public void inferAbilityBonus(Character c, WeaponCache cache) {

        if ( c.hasWeaponFocus( this.name ) ) {
            hasWeaponFocus = 1;
        }

        if ( c.hasWeaponSpecialization( this.name )) {
            hasWeaponSpecialization = 1;
        }

        if( c.hasWeaponFinesseFeat() && c.hasShieldEquipped() && isWieldedMainhand() ) {
            System.out.println("You have weapon finesse weapon " +  this.name + " AND a shield equipped; please be advised that " +
            "a) the shield penalty applies to your attack bonus, and " +
            "b) the curent state of herolabs xml output doesn't allow method to infer exactly what that " +
            "penalty is.  Please be an honest chap and edit the shieldAttackProperty of your weapon to be the " +
            "armor check penalty of your shield in Maptool." );
        }

        this.baseAttackBonus = c.getBaseAttackBonus();

        //entire section of code is a bit too rules specific for my taste, but the herolabs export leaves something
        //to be had - for example, if I knew what type of weapon it was (light, heavy, etc) I wouldn't have to do
        //specific checks on unarmed strike.

        //because of this I am using an export of pfsrd weapons list.

        WeaponCache.Entry weaponEntry = cache.get( this.name );

        if ( weaponEntry == null ) {
            //try to get the generic name
            weaponEntry = cache.get( this.basicName );
            if ( weaponEntry == null ) {
                //special case handling for natural attacks.  sigh.
                if ( isNaturalAttack()) {
                    abilityBonus = STR_BONUS;
                    hasWeaponProficiency = 1;
                    return;
                }
                else {
                    System.out.println( "Unable to determine information about weapon " + this.name +
                                        ", please file a bug report at githib"); //TODO: handle more gracefully
                    return;
                }
            }
        }

        this.twoWeaponFightingPenalty = getTwoWeaponFightingPenalty(c, cache );

        setWeaponProficiency(c, weaponEntry);

        if ( weaponEntry.isRangedWeapon() ) {
            abilityBonus = DEX_BONUS; //ignoring certain wisdom feats now that make this wisdom based...
        }

        if ( weaponEntry.isTwoHandedMeleeWeapon() ) {
             abilityBonus = STR_BONUS;
        }
        else if ( isUnarmedStrike() ) {
            abilityBonus = DEX_BONUS;
        }
        else if ( weaponEntry.isLightWeapon() ) {
            if ( c.hasWeaponFinesseFeat()) {
                abilityBonus = DEX_BONUS;
            }
            else {
                abilityBonus = STR_BONUS;
            }
        }
        else if ( weaponEntry.isOneHandedMeleeWeapon() ) {
            if ( c.hasWeaponFinesseFeat()) {
                //special case for rapier, whip, and spiked chain ... of course :-)
                if( weaponEntry.isFinessableOneHandedMeleeWeapon() ) {
                     abilityBonus = DEX_BONUS;
                }
                else {
                     abilityBonus = STR_BONUS;
                }
            } else {
                abilityBonus = STR_BONUS;
            }
        }
        else if ( isFirearm() ) {
            abilityBonus = INT_BONUS;
        }
        else {
            if ( abilityBonus == null ) {
                System.out.println("Unable to figure out attack ability bonus for weapon " + this.name +
                        ", please fill a bug report"); //TODO
            }
        }
	}

    //see http://www.d20pfsrd.com/gamemastering/combat#TOC-Two-Weapon-Fighting for how this works.
    //TODO: refactor this into a decision table.
    private int getTwoWeaponFightingPenalty( Character c, WeaponCache cache ) {

        int primaryHandPenalty = -6;
        int offHandPenalty = -10;

        if( c.offHandWeaponIsLightOrUnarmed(cache) ) {
            primaryHandPenalty += 2;
            offHandPenalty += 2;
        }

        if ( c.hasTwoWeaponFightingFeat() ) {
            primaryHandPenalty += 2;
            offHandPenalty += 6;
        }

        if( this.isWieldedMainhand() ) {
            return primaryHandPenalty;
        }
        else if ( this.isWieldedOffhand() ) {
            return offHandPenalty;
        }
        else {
            return 0;
        }
    }
    private void setWeaponProficiency(Character c, WeaponCache.Entry weaponEntry) {

        if ( isUnarmedStrike()  ) {  //All characters are proficient with unarmed strikes
            hasWeaponProficiency = 1;
        }
        else if ( this.isNaturalAttack() ) {
            hasWeaponProficiency = 1;
        }
        else if ( weaponEntry.isSimpleWeapon() ) {
            if( c.hasSimpleWeaponProficiency( this) ) {
                this.hasWeaponProficiency = 1;
            }
        } else if ( weaponEntry.isMartialWeapon() ) {
            if ( c.hasMartialWeaponProficiency(this) ) {
                this.hasWeaponProficiency = 1;
            }
        } else if ( weaponEntry.isExoticWeapon() ) {
            if ( c.hasExoticWeaponProficiency( this ) ) {
                this.hasWeaponProficiency = 1;
            }
        }
    }

    private boolean isNaturalAttack() {
        //TODO: this is kind of a hack, but since it isn't in the herolabs xml, we're
        //stuck with what we have
        return this.description!= null &&
               !this.description.isEmpty() &&
               this.description.indexOf(" natural ") >= 0 ||
               this.description.indexOf(" Natural ") >= 0;
    }

    public boolean isUnarmedStrike() {
        return this.name.equals(Character.HEROLABS_UNARMED_STRIKE);
    }
    private boolean isEquipped() {
        return isWieldedTwoHandedWeapon() || isWieldedMainhand() || isWieldedOffhand();
    }

    public boolean isWieldedMainhand() {
        return equipped != null && ! equipped.isEmpty() && equipped.equals(Character.HEROLABS_WEAPON_MAINHAND);
    }

    public boolean isWieldedOffhand() {
        return equipped != null && ! equipped.isEmpty() && equipped.equals( Character.HEROLABS_WEAPON_OFFHAND );
    }

    public boolean isWieldedTwoHandedWeapon() {
        return equipped != null && ! equipped.isEmpty() && equipped.equals( Character.HEROLABS_WEAPON_TWOHAND );
    }

    private boolean isFirearm() {
        return category != null && ! category.isEmpty() && category.equals( Character.HEROLABS_FIREARM_PROJECTILE_WEAPON );
    }

    private boolean isProjectileWeapon() {
        return category != null && ! category.isEmpty() && category.equals(Character.HEROLABS_PROJECTILE_WEAPON);
    }

    private boolean isMeleeWeapon() {
        return category != null && ! category.isEmpty() && category.indexOf(Character.HEROLABS_MELEE_WEAPON ) == 0;
    }

    //this method is a bit of a hack as herolabs doesn't provide an actual enhancement bonus in its xml
    //TODO: http://forums.wolflair.com/showthread.php?p=65056&posted=1#post65056 thread asking for it
    private void parseEnhancementBonus(String name) {
        Pattern regex = Pattern.compile("\\+(\\d+)");
        java.util.regex.Matcher matcher = regex.matcher(name);
        if (matcher.find()) {
            this.enhancementBonus = Integer.parseInt(matcher.group(1));
        }
        else {
            if ( name.indexOf( "Masterwork") >= 0 ) { //TODO: another hack ... sigh
                this.isMasterwork = 1;
            }
        }
    }

    private String extractBasicWeaponName( String name ) {
        Pattern regex = Pattern.compile("^.*?\\s+(\\w+)$");
        java.util.regex.Matcher matcher = regex.matcher(name);
        if (matcher.find()) {
            return matcher.group(1);
        }
        else {
            return name;
        }
    }

    private void parseCrit(String crit) {
        Pattern regex = Pattern.compile("^(\\d+).*(\\d+)$");
        java.util.regex.Matcher matcher = regex.matcher(crit);
        if (matcher.matches()) {
            critFloor = Integer.parseInt(matcher.group(1));
            critMultiplier = Integer.parseInt(matcher.group(2));
        }
    }

    private void parseAttackBonus(String attackBonus) {
        Pattern regex = Pattern.compile("\\d+");
        java.util.regex.Matcher matcher = regex.matcher(attackBonus);
        while (matcher.find()) {
            String ab = matcher.group();
            attacks.add(ab);
        }
    }

    public class Damage {
		private int numDamageDice = 1;
		private int damageDice = 1;
		private int bonusDamage = 0;

        public int getBonusDamage() {
            return bonusDamage;
        }

        public String asExpression() {
            return Integer.toString(numDamageDice) + "d" + Integer.toString(damageDice);
        }

		public Damage( String expression ) {
			Pattern regex = Pattern.compile("(\\d+)d*(\\d*)\\+*(\\d*)" );
			java.util.regex.Matcher matcher = regex.matcher( expression );
			if ( matcher.matches() )
			{
				String ndd = matcher.group(1);
				String dd  = matcher.group(2);
				String bd  = matcher.group(3);
				if ( ndd != null && ! ndd.isEmpty()) {
					numDamageDice = Integer.parseInt(ndd);
				}
				if ( dd != null  && ! dd.isEmpty() ) {
					damageDice = Integer.parseInt(dd);
				}
				if ( bd != null && ! bd.isEmpty()) {
					bonusDamage = Integer.parseInt( bd );
				}
			}
			else
			{
				//TODO: throw exception, invalid damage expression
			}
		}
	}
}
