package net.sozinsoft.tokenlab;

import java.util.*;
import java.util.regex.Pattern;

public class WeaponImpl {

    String name;
    String basicName;
    Damage damage;
    String damageDice;
    String category;
    int critFloor = 20;
    int critMultiplier;
    String equipped;
    int numFullAttacks = 0;
    String weaponType;
    String description;
    int temporaryAttackModifier = 0;
    int temporaryDamageModifier = 0;

    private HashMap<Integer, Integer> attacks = new HashMap<Integer,Integer>();

    public List<Integer> sortedAttacks() {
        List<Integer> v = new ArrayList<Integer>(attacks.keySet());
        Collections.sort( v );
        return v;
    }

    public WeaponImpl(String name, String damage, String category, String crit,
                      String attackBonus, String equipped, String weaponType, String description) throws Exception {



        //sometimes weapon names can look wonky, for example
        //"+1 Adamantine Keen Scimitar, elven Adamantine:\n\nThis ability doubles the threat range of a weapon.
        // Only piercing or slashing"
        //so, strip out everything after the first newline
        //and then the first comma
        this.name = name.replaceAll( "\\n.*$", "");
        this.name = this.name.replaceAll("\\s+$", "" );
     //   this.name = this.name.replaceAll(",\\s+.*$", "");  this regex was messing up perfectly good weapon names (Sword, Bastard for ex)


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



    private void parseCrit(String crit) {
        Pattern regex = Pattern.compile("^(\\d+).*(\\d+)$");
        java.util.regex.Matcher matcher = regex.matcher(crit);
        if (matcher.matches()) {
            critFloor = Integer.parseInt(matcher.group(1));
            critMultiplier = Integer.parseInt(matcher.group(2));
        }
        else {
            //version 4 of Herolab changed the crit format so that, if its a weapon that
            //crits on 20, the crit string is simply "x2" or "x3" (instead of 20/x2)
            regex = Pattern.compile("^x(\\d+)$");
            matcher = regex.matcher(crit);
            if (matcher.matches()) {
                critFloor = 20;
                critMultiplier = Integer.parseInt( matcher.group(1));
            }
            else {    //hmm, a very strange weapon...
                critFloor = 20;
                critMultiplier = 2; //TODO: probably throw an exception here
            }

        }
    }

    private void parseAttackBonus(String attackBonus) {
        Pattern regex = Pattern.compile("\\d+");
        java.util.regex.Matcher matcher = regex.matcher(attackBonus);
        int count = 1;
        while (matcher.find()) {
            String ab = matcher.group();
            int multiplier = 1;
            if ( attackBonus.startsWith("-")) {
                multiplier = -1;
            }
            attacks.put(new Integer(count++), multiplier * Integer.parseInt(ab));
        }
    }


}
