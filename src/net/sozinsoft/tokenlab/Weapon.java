package net.sozinsoft.tokenlab;

import java.util.LinkedList;
import java.util.regex.Pattern;

class Weapon {

    String name;
    String basicName;
    Damage damage;
    String damageDice;
    String category;
    int critFloor;
    int critMultiplier;
    String equipped;
    int numFullAttacks = 0;
    String weaponType;
    String description;
    int temporaryAttackModifier = 0;
    int temporaryDamageModifier = 0;

    private LinkedList<String> attacks = new LinkedList<String>();


    public Weapon(String name, String damage, String category, String crit,
                  String attackBonus, String equipped, String weaponType, String description) {
        this.name = name;
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



    //see http://www.d20pfsrd.com/gamemastering/combat#TOC-Two-Weapon-Fighting for how this works.
    //TODO: refactor this into a decision table.





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
