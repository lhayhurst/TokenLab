package net.sozinsoft.tokenlab;

public class WeaponNameReplacer implements IMacroReplacer {
    private Integer attackNumber = 0;
    private String weaponName;
    private boolean isNPC;
    private static final String SUBSTITUTE_SELF = "$SELF";
    private static final String SUBSTITUTE_NAME_OF_WEAPON_HERE = "$SUBSTITUTE_NAME_OF_WEAPON_HERE";
    private static final String SUBSTITUTE_ATTACK_NUMBER_HERE = "$SUBSTITUTE_ATTACK_NUMBER_HERE";

    public WeaponNameReplacer(boolean isNPC, String weaponName, Integer attackNumber) {
        this.attackNumber = attackNumber;
        this.weaponName = weaponName;
        this.isNPC = isNPC;
    }

    public String replace(String target) {
        String one = target.replace(SUBSTITUTE_NAME_OF_WEAPON_HERE, weaponName);
        String two = one.replace(SUBSTITUTE_ATTACK_NUMBER_HERE, attackNumber.toString() );
        String ret;
        if ( this.isNPC == true )
        {
            ret = two.replace( SUBSTITUTE_SELF, "/self");
        }
        else
        {
            ret = two.replace( SUBSTITUTE_SELF, "");
        }
        return ret;
    }
}