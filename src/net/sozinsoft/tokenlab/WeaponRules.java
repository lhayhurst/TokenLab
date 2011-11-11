package net.sozinsoft.tokenlab;

import java.util.regex.Pattern;

public class WeaponRules {

    public static final String DEX_BONUS = "DexBonus";
    public static final String STR_BONUS1_5 = "StrBonus1.5";
    public static final String STR_BONUS = "StrBonus";
    public static final String INT_BONUS = "IntBonus";

    String abilityBonus = null;
    String basicName = null;
    int hasWeaponFocus = 0;
    int hasWeaponSpecialization = 0;
    private String baseAttackBonus;
    private int twoWeaponFightingPenalty = 0;
    private String name;
    private Weapon weapon;
    private int hasWeaponProficiency = 0;
    private int enhancementBonus = 0;
    private int isMasterwork = 0;

    public WeaponRules( Weapon w ) {
        weapon = w;
    }

    public boolean offHandWeaponIsLightOrUnarmed( Character c, WeaponCache cache ) {
           //get the offhand weapon
            Weapon offHand = null;
            for ( Weapon w  : c.getWeapons().values()) {
                if ( isWieldedOffhand() ) {
                   offHand = w;
                   break;
                }
            }

            if ( offHand == null ) {
                return false;
            }

            if ( isUnarmedStrike() ) {
                return true;
            }

            WeaponCache.Entry entry = cache.get( offHand.name );
            if ( entry == null ) {
                entry = cache.get( offHand.basicName );
            }

            if ( entry.isLightWeapon() ) {
                return true;
            }
            return false;
        }

    private String extractBasicWeaponName( String name, Character c ) {
        Pattern regex = Pattern.compile("^.*?\\s+(\\w+)$");
        java.util.regex.Matcher matcher = regex.matcher(name);
        if (matcher.find()) {
            return matcher.group(1);
        }
        regex=Pattern.compile("^.*?\\s+(\\w+),\\s+Composite");   //edge case (UGH): maybe its a composite ranged weapon?
        matcher = regex.matcher(name);
        if (matcher.find() ) {
            return matcher.group(1);
        }
        else {  //edge case2 Some sort of natural weapon, like 'Constrict (Ochre Jelly)'
            String pattern = "^(\\w+)\\s+\\(" + c.getName() + "\\)";
            regex=Pattern.compile( pattern );
            matcher = regex.matcher(name);
            if (matcher.find() ) {
                return matcher.group(1);
            }
        }

        return name;
    }

    //see http://www.d20pfsrd.com/equipment---final/weapons for how this stuff all works.
    public void inferAbilityBonus(Character c, WeaponCache cache) {


        basicName = extractBasicWeaponName(weapon.name, c );

        if ( c.hasWeaponFocus( weapon.name ) ) {
            hasWeaponFocus = 1;
        }

        if ( c.hasWeaponSpecialization( weapon.name )) {
            hasWeaponSpecialization = 1;
        }

        if( c.hasWeaponFinesseFeat() && c.hasShieldEquipped() && isWieldedMainhand() ) {
            System.out.println("You have weapon finesse weapon " +  weapon.name + " AND a shield equipped; please be advised that " +
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

        WeaponCache.Entry weaponEntry = cache.get( weapon.name );

        if ( weaponEntry == null ) {
            //try to get the generic name
            weaponEntry = cache.get( this.basicName );
            if ( weaponEntry == null ) {
                //special case handling for natural attacks.  sigh.
                System.out.println( "Unable to determine information about weapon " + this.name +
                                        ", please file a bug report at githib"); //TODO: handle more gracefully
                return;
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
        else if ( weaponEntry.isNaturalWeapon()) {
            abilityBonus = STR_BONUS;
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

    private void setWeaponProficiency(Character c, WeaponCache.Entry weaponEntry) {

        if ( isUnarmedStrike()  ) {  //All characters are proficient with unarmed strikes
            hasWeaponProficiency = 1;
        }
        else if ( weaponEntry.isNaturalWeapon() ) {
            hasWeaponProficiency = 1;
        }
        else if ( weaponEntry.isSimpleWeapon() ) {
            if( c.hasSimpleWeaponProficiency( weapon) ) {
                this.hasWeaponProficiency = 1;
            }
        } else if ( weaponEntry.isMartialWeapon() ) {
            if ( c.hasMartialWeaponProficiency(weapon) ) {
                this.hasWeaponProficiency = 1;
            }
        } else if ( weaponEntry.isExoticWeapon() ) {
            if ( c.hasExoticWeaponProficiency( weapon ) ) {
                this.hasWeaponProficiency = 1;
            }
        }
    }

    private int getTwoWeaponFightingPenalty( Character c, WeaponCache cache ) {

        int primaryHandPenalty = -6;
        int offHandPenalty = -10;

        if( offHandWeaponIsLightOrUnarmed(c, cache) ) {
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

        //this method is a bit of a hack as herolabs doesn't provide an actual enhancement bonus in its xml
    //TODO: http://forums.wolflair.com/showthread.php?p=65056&posted=1#post65056 thread asking for it
    private void parseEnhancementBonus(String name) {
        Pattern regex = Pattern.compile("\\+(\\d+)\\s+");
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


public boolean isUnarmedStrike() {
        return this.name.equals(Character.HEROLABS_UNARMED_STRIKE);
    }
    private boolean isEquipped() {
        return isWieldedTwoHandedWeapon() || isWieldedMainhand() || isWieldedOffhand();
    }

    public boolean isWieldedMainhand() {
        return weapon.equipped != null && ! weapon.equipped.isEmpty() && weapon.equals(Character.HEROLABS_WEAPON_MAINHAND);
    }

    public boolean isWieldedOffhand() {
        return weapon.equipped != null && ! weapon.equipped.isEmpty() && weapon.equipped.equals( Character.HEROLABS_WEAPON_OFFHAND );
    }

    public boolean isWieldedTwoHandedWeapon() {
        return weapon.equipped != null && ! weapon.equipped.isEmpty() && weapon.equipped.equals( Character.HEROLABS_WEAPON_TWOHAND );
    }

    private boolean isFirearm() {
        return weapon.category != null && ! weapon.category.isEmpty() && weapon.category.equals( Character.HEROLABS_FIREARM_PROJECTILE_WEAPON );
    }

    private boolean isProjectileWeapon() {
        return weapon.category != null && ! weapon.category.isEmpty() && weapon.category.equals(Character.HEROLABS_PROJECTILE_WEAPON);
    }

    private boolean isMeleeWeapon() {
        return weapon.category != null && ! weapon.category.isEmpty() && weapon.category.indexOf(Character.HEROLABS_MELEE_WEAPON ) == 0;
    }


}
