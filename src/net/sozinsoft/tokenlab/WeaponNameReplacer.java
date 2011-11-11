package net.sozinsoft.tokenlab;

public class WeaponNameReplacer implements IMacroReplacer {
    private Integer attackNumber = 0;
    private String weaponName;
    private static final String SUBSTITUTE_NAME_OF_WEAPON_HERE = "$SUBSTITUTE_NAME_OF_WEAPON_HERE";
    private static final String SUBSTITUTE_ATTACK_NUMBER_HERE = "$SUBSTITUTE_ATTACK_NUMBER_HERE";

    public WeaponNameReplacer(String weaponName, Integer attackNumber) {
        this.attackNumber = attackNumber;
        this.weaponName = weaponName;
    }

    public String replace(String target) {
        String one = target.replace(SUBSTITUTE_NAME_OF_WEAPON_HERE, weaponName);
        String ret = one.replace(SUBSTITUTE_ATTACK_NUMBER_HERE, attackNumber.toString() );
        return ret;
    }
}