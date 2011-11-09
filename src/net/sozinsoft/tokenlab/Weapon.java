package net.sozinsoft.tokenlab;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.regex.Pattern;

class Weapon {
    String name;
    Damage damage;
    String damageDice;
    String category;
    int critFloor;
    int critMultiplier;
    int enhancementBonus = 0;
    String equipped;
    int numFullAttacks = 0;
    String weaponType;
    String abilityBonus;

    private LinkedList<String> attacks = new LinkedList<String>();

    public Weapon(String name, String damage, String category, String crit,
                  String attackBonus, String equipped, String weaponType) {
        this.name = name;
        parseEnhancementBonus(name);
        this.damage = new Damage(damage);
        this.damageDice = this.damage.asExpression();
        this.category = category;
        parseCrit(crit);
        parseAttackBonus(attackBonus);
        numFullAttacks = attacks.size();
        this.equipped = equipped;
        this.weaponType = weaponType;

    }

    public void asGson() {
        Gson gson = new Gson();
        System.out.println( gson.toJson( this ));
    }


    //see http://www.d20pfsrd.com/equipment---final/weapons for how this stuff all works.
    public void inferAbilityBonus( Character c) {
		if ( isMeleeWeapon()) {
			if ( c.hasWeaponFinesseFeat() ) {
                abilityBonus = "DexBonus";
            }
            else {
                if ( isWieldedTwoHandedWeapon() )
                {
				    abilityBonus =  "StrBonus1.5";
                }
                else {
                    abilityBonus = "StrBonus";
                }
            }
		} else if (isProjectileWeapon()) {
			abilityBonus = "DexBonus";
		} else if (isFirearm()) {
			abilityBonus = "IntBonus";
		}
		else {
			System.out.println( "Unable to infer weapon bonus from weapon " + name + " with category " + category);
			abilityBonus =  "StrBonus"; //TODO: as good as a default as any
		}
	}

    private boolean isWieldedTwoHandedWeapon() {
        return equipped != null && ! equipped.isEmpty() && equipped.equals( Character.HEROLABS_WEAPON_TWOHAND );
    }

    private boolean isFirearm() {
        return category != null && ! category.isEmpty() && category.equals( Character.HEROLABS_FIREARM_PROJECTILE_WEAPON );
    }

    private boolean isProjectileWeapon() {
        return category != null && ! category.isEmpty() && category.equals(Character.HEROLABS_PROJECTILE_WEAPON);
    }

    private boolean isMeleeWeapon() {
        return category != null && ! category.isEmpty() && category.equals(Character.HEROLABS_MELEE_WEAPON );
    }

    //this method is a bit of a hack as herolabs doesn't provide an actual enhancement bonus in its xml
    //TODO: http://forums.wolflair.com/showthread.php?p=65056&posted=1#post65056 thread asking for it
    private void parseEnhancementBonus(String name) {
        Pattern regex = Pattern.compile("^\\+(\\d+)");
        java.util.regex.Matcher matcher = regex.matcher(name);
        if (matcher.find()) {
            this.enhancementBonus = Integer.parseInt(matcher.group(1));
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
