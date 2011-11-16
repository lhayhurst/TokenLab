package net.sozinsoft.tokenlab;


import java.util.HashMap;

public interface IPathfinderCharacter {

    //background and personal
    public String getName();
    public String getPlayer();
    public String getRace();
    public String getAlignment();
    public String getDeity();
    public String getGender();
    public Integer getAge();
    public String getHeight();
    public String getWeight();
    public Integer getSpeed();
    public Integer getLevel();
    public String getClassAbbreviation(); //example: 'Ftr 18/Rog 2'

    //hp
    public Integer getClassHitpoints();

    public enum IAttribute { Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma };
    public enum IAttributeAbbreviated { STR, DEX, CON,  INT, WIS, CHA };
    public Integer getBaseAbilityScore( IAttribute attribute );   //18
    public Integer getBaseAbilityBonus(IAttribute iattribute); //4
    public Integer getAbilityEnhancement( IAttribute attribute );
    public Integer getAbilityDamage( IAttribute attribute );

    //saving throws
    public enum ISavingThrow { Fort, Ref, Will }  ;
    public Integer getSavingThrowClassBonus( ISavingThrow ist );
    public Integer getResistanceSavingThrowBonus( ISavingThrow ist );
    public Integer getMiscellaneousSavingThrowBonus( ISavingThrow ist );

    //armor class
    public Integer getACArmorBonus();
    public Integer getACFromShield();
    public Integer getACFromDeflect();
    public Integer getACFromDodge();
    public Integer getACFromnNatural();
    public Integer getACFromSize();
    public Integer getACMisc();


}
