package net.sozinsoft.tokenlab.test;

import net.rptools.maptool.model.Token;
import net.sozinsoft.tokenlab.*;
import net.sozinsoft.tokenlab.dtd.Character;
import net.sozinsoft.tokenlab.dtd.Feat;
import net.sozinsoft.tokenlab.dtd.Special;
import org.junit.Before;
import org.junit.Test;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class PathfinderTokenTests {

    HerolabsDigester dig;
    @Before
    public void setUp() throws JAXBException {
        dig = new HerolabsDigester();
        dig.parse( new File( "src/net/sozinsoft/tokenlab/test/xml/Children_Of_Steel.xml") );
    }

    @Test
    public void testDamageRegex() {
        Damage d = null;
        try {
            d = new Damage("6");
            assertEquals(0, d.getNumDamageDice());
            assertEquals(0, d.getDamageDice());
            assertEquals(6, d.getBonusDamage());
            d = new Damage("1d6");
            assertEquals(1, d.getNumDamageDice());
            assertEquals(6, d.getDamageDice());
            assertEquals(0, d.getBonusDamage());
            d = new Damage("2d8+3");
            assertEquals(2, d.getNumDamageDice());
            assertEquals(8, d.getDamageDice());
            assertEquals(3, d.getBonusDamage());
            d = new Damage("12d10+3 Acid");
            assertEquals(12, d.getNumDamageDice());
            assertEquals(10, d.getDamageDice());
            assertEquals(3, d.getBonusDamage());
            assertEquals("Acid", d.getDamageType());
            d = new Damage("1d3-1");
            assertEquals(1, d.getNumDamageDice());
            assertEquals(3, d.getDamageDice());
            assertEquals(-1, d.getBonusDamage());

        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
	public void testBasic() throws Exception {

        List<net.sozinsoft.tokenlab.dtd.Character> characters = dig.getCharacters();
        Character dss = characters.get(0);

        PathfinderToken ptok = new PathfinderToken( dss );
        assertEquals("Derrak Stoneskull", ptok.getName());
        assertEquals( 274, ptok.getClassHitpoints().intValue());
        assertEquals("Dwarf", ptok.getRace() );
        assertEquals("Neutral Evil", ptok.getAlignment() );
        assertEquals( "Rovagug", ptok.getDeity());
        assertEquals( 51, ptok.getAge().intValue());
        assertEquals( "4' 2\"", ptok.getHeight() );
        assertEquals( "185lb.", ptok.getWeight() );
        assertEquals( 20, ptok.getSpeed().intValue());
        assertEquals( 20, ptok.getLevel().intValue() );
        assertEquals( "Ftr 20", ptok.getClassAbbreviation());

        dss = characters.get(3);
        ptok = new PathfinderToken( dss );
        assertEquals("Wotywina Turncoin", ptok.getName());
        assertEquals( 183, ptok.getClassHitpoints().intValue());
        assertEquals("Halfling", ptok.getRace() );
        assertEquals("Chaotic Evil", ptok.getAlignment() );
        assertTrue(ptok.getDeity().isEmpty()) ;
        assertEquals(25, ptok.getAge().intValue());
        assertEquals( "2' 11\"", ptok.getHeight() );
        assertEquals( "30lb.", ptok.getWeight() );
        assertEquals( 35, ptok.getSpeed().intValue());
        assertEquals( 20, ptok.getLevel().intValue() );
        assertEquals( "Rog 20", ptok.getClassAbbreviation());

	}

    @Test
    public void testCombat() throws Exception {
        List<net.sozinsoft.tokenlab.dtd.Character> characters = dig.getCharacters();
        Character dss = characters.get(0);

        PathfinderToken ptok = new PathfinderToken( dss );
        assertEquals( 20, ptok.getBaseAttackBonus().intValue() );
    }

    @Test
    public void testAbilities() throws Exception {

        List<net.sozinsoft.tokenlab.dtd.Character> characters = dig.getCharacters();
        Character dss = characters.get(0);

        PathfinderToken ptok = new PathfinderToken( dss );

        assertEquals( 20, ptok.getBaseAbilityScore( IPathfinderCharacter.IAttribute.Strength ).intValue() );
        assertEquals( 5, ptok.getBaseAbilityModifier( IPathfinderCharacter.IAttribute.Strength ).intValue() );
        assertEquals( 26,  ptok.getBonusAbilityScore(IPathfinderCharacter.IAttribute.Strength).intValue());
        assertEquals( 8,  ptok.getBonusAbilityModifier(IPathfinderCharacter.IAttribute.Strength).intValue());
        assertEquals( 0, ptok.getAbilityDamage( IPathfinderCharacter.IAttribute.Strength).intValue() );

        assertEquals( 13, ptok.getBaseAbilityScore( IPathfinderCharacter.IAttribute.Dexterity ).intValue() );
        assertEquals( 1, ptok.getBaseAbilityModifier( IPathfinderCharacter.IAttribute.Dexterity ).intValue() );
        assertEquals( 13,  ptok.getBonusAbilityScore(IPathfinderCharacter.IAttribute.Dexterity).intValue());
        assertEquals( 1,  ptok.getBonusAbilityModifier(IPathfinderCharacter.IAttribute.Dexterity).intValue());
        assertEquals( 0, ptok.getAbilityDamage( IPathfinderCharacter.IAttribute.Dexterity).intValue() );

        assertEquals( 16, ptok.getBaseAbilityScore( IPathfinderCharacter.IAttribute.Constitution ).intValue() );
        assertEquals( 3, ptok.getBaseAbilityModifier( IPathfinderCharacter.IAttribute.Constitution ).intValue() );
        assertEquals( 22,  ptok.getBonusAbilityScore(IPathfinderCharacter.IAttribute.Constitution).intValue());
        assertEquals( 6,  ptok.getBonusAbilityModifier(IPathfinderCharacter.IAttribute.Constitution).intValue());
        assertEquals( 0, ptok.getAbilityDamage( IPathfinderCharacter.IAttribute.Constitution).intValue() );

        assertEquals( 14, ptok.getBaseAbilityScore( IPathfinderCharacter.IAttribute.Wisdom ).intValue() );
        assertEquals( 2, ptok.getBaseAbilityModifier( IPathfinderCharacter.IAttribute.Wisdom ).intValue() );
        assertEquals( 14,  ptok.getBonusAbilityScore(IPathfinderCharacter.IAttribute.Wisdom).intValue());
        assertEquals( 2,  ptok.getBonusAbilityModifier(IPathfinderCharacter.IAttribute.Wisdom).intValue());
        assertEquals( 0, ptok.getAbilityDamage( IPathfinderCharacter.IAttribute.Wisdom).intValue() );

        assertEquals( 10, ptok.getBaseAbilityScore( IPathfinderCharacter.IAttribute.Intelligence ).intValue() );
        assertEquals( 0, ptok.getBaseAbilityModifier( IPathfinderCharacter.IAttribute.Intelligence ).intValue() );
        assertEquals( 10,  ptok.getBonusAbilityScore(IPathfinderCharacter.IAttribute.Intelligence).intValue());
        assertEquals( 0,  ptok.getBonusAbilityModifier(IPathfinderCharacter.IAttribute.Intelligence).intValue());
        assertEquals( 0, ptok.getAbilityDamage( IPathfinderCharacter.IAttribute.Intelligence).intValue() );

        assertEquals( 6, ptok.getBaseAbilityScore( IPathfinderCharacter.IAttribute.Charisma ).intValue() );
        assertEquals( -2, ptok.getBaseAbilityModifier( IPathfinderCharacter.IAttribute.Charisma ).intValue() );
        assertEquals( 6,  ptok.getBonusAbilityScore(IPathfinderCharacter.IAttribute.Charisma).intValue());
        assertEquals( -2,  ptok.getBonusAbilityModifier(IPathfinderCharacter.IAttribute.Charisma).intValue());
        assertEquals( 0, ptok.getAbilityDamage( IPathfinderCharacter.IAttribute.Charisma).intValue() );
    }

    @Test
    public void testSaves() throws Exception {
        List<net.sozinsoft.tokenlab.dtd.Character> characters = dig.getCharacters();
        Character dss = characters.get(0);

        PathfinderToken ptok = new PathfinderToken( dss );

        //Derrak Stoneskull
        //fort
        assertEquals( 12, ptok.getSavingThrowClassBonus( IPathfinderCharacter.ISavingThrow.Fort ).intValue() );
        assertEquals( 4, ptok.getResistanceSavingThrowBonus(IPathfinderCharacter.ISavingThrow.Fort).intValue() );
        assertEquals( 0, ptok.getMiscellaneousSavingThrowBonus(IPathfinderCharacter.ISavingThrow.Fort).intValue() );

        assertEquals( 6, ptok.getSavingThrowClassBonus( IPathfinderCharacter.ISavingThrow.Ref ).intValue() );
        assertEquals( 4, ptok.getResistanceSavingThrowBonus( IPathfinderCharacter.ISavingThrow.Ref ).intValue() );
        assertEquals( 2, ptok.getMiscellaneousSavingThrowBonus( IPathfinderCharacter.ISavingThrow.Ref ).intValue() );

        assertEquals( 6, ptok.getSavingThrowClassBonus( IPathfinderCharacter.ISavingThrow.Will ).intValue() );
        assertEquals( 4, ptok.getResistanceSavingThrowBonus( IPathfinderCharacter.ISavingThrow.Will ).intValue() );
        assertEquals( 2, ptok.getMiscellaneousSavingThrowBonus( IPathfinderCharacter.ISavingThrow.Will ).intValue() );

    }

    @Test
    public void testSpecialAbilities() throws Exception {
        List<net.sozinsoft.tokenlab.dtd.Character> characters = dig.getCharacters();
        Character dss = characters.get(1);
        PathfinderToken echean = new PathfinderToken( dss );
        echean.setSpecialAbilities();

        SortedMap< String, TreeMap<String, Special>> specials = echean.getSpecialAbilities();
        assertNotNull( specials );

        TreeMap<String, Special> immunities = specials.get( PathfinderToken.IMMUNITIES );
        assertNotNull( immunities );
        Special sleepImmunity = immunities.get( "Elven Immunities - Sleep");
        assertNotNull( sleepImmunity);
        assertEquals( "Elven Immunities - Sleep", sleepImmunity.getName() );

    }

    @Test
    public void testFeats() throws Exception {
        List<net.sozinsoft.tokenlab.dtd.Character> characters = dig.getCharacters();
        Character dss = characters.get(1);
        PathfinderToken echean = new PathfinderToken( dss );
        echean.setFeats();
        HashMap<String, Feat> feats = echean.getFeats();
        assertNotNull( feats );
        assertEquals( 17, feats.size() );
        Feat combatCasting = feats.get("Combat Casting");
        assertNotNull( combatCasting );
        assertEquals(  "Combat Casting", combatCasting.getName());
    }

    @Test
    public void testVision() throws Exception {
        dig = new HerolabsDigester();
        dig.parse( new File( "src/net/sozinsoft/tokenlab/test/xml/vision_monsters.xml") );
        List<net.sozinsoft.tokenlab.dtd.Character> characters = dig.getCharacters();

        Character dss = characters.get(0);
        PathfinderToken bernard = new PathfinderToken( dss );
        assertEquals("Bernard the Guard", bernard.getName());
        assertEquals( PathfinderToken.MT_NORMAL_VISION, bernard.getVision());

        dss = characters.get(1);
        PathfinderToken wwt = new PathfinderToken( dss );
        assertEquals("Purple Worm", wwt.getName());
        assertEquals( PathfinderToken.MT_DARKVISION, wwt.getVision());

        dss = characters.get(2);
        PathfinderToken shark = new PathfinderToken( dss );
        assertEquals("Shark", shark.getName());
        assertEquals( PathfinderToken.MT_BLINDSIGHT, shark.getVision());

        dss = characters.get(3);
        PathfinderToken tendrilicos = new PathfinderToken( dss );
        assertEquals("Tendriculos", tendrilicos.getName());
        assertEquals( PathfinderToken.MT_LOWLIGHT_VISION, tendrilicos.getVision());


    }

    @Test
    public void testSpells() throws Exception {
        List<net.sozinsoft.tokenlab.dtd.Character> characters = dig.getCharacters();
        Character dss = characters.get(1);

        PathfinderToken echean = new PathfinderToken( dss );
        assertEquals( "Echean Ansolandi", echean.getName() );
        echean.setSpells();
        HashMap< String, PFSRDSpell > spells = echean.getSpellsByClass("Wizard")  ;
        assertNotNull(spells);
        PFSRDSpell arcaneMark = spells.get( "Arcane Mark");
        assertNotNull( arcaneMark );
        assertEquals( "universal", arcaneMark.school );
        PFSRDSpell treasureMap = spells.get("Create Treasure Map");
        assertNotNull( treasureMap);
        assertEquals( treasureMap.name, "Create Treasure Map");

        SortedMap< Integer, HashMap<String, PFSRDSpell>> spellsByLevel = echean.getSpellsByClassAndLevel("Wizard");
        //first assert that they are ordered 0-9
        List<Integer> levels = new ArrayList<Integer>(spellsByLevel.keySet());
        assertTrue( levels.size() == 10 );
        assertEquals( 0, levels.get(0).intValue() );
        assertEquals( 1, levels.get(1).intValue() );
        assertEquals( 2, levels.get(2).intValue() );
        assertEquals( 3, levels.get(3).intValue() );
        assertEquals( 4, levels.get(4).intValue() );
        assertEquals( 5, levels.get(5).intValue() );
        assertEquals( 6, levels.get(6).intValue() );
        assertEquals( 7, levels.get(7).intValue() );
        assertEquals( 8, levels.get(8).intValue() );
        assertEquals( 9, levels.get(9).intValue() );

        //next verify that spells show up where they should.
        PFSRDSpell readMagic = spellsByLevel.get(0).get("Read Magic");
        assertNotNull( readMagic );
        assertEquals( "Read Magic", readMagic.name);


        PFSRDSpell shield = spellsByLevel.get(1).get("Shield");
        assertNotNull( shield );
        assertEquals( "Shield", shield.name);

        PFSRDSpell scorchingRay = spellsByLevel.get(2).get("Scorching Ray");
        assertNotNull( scorchingRay );
        assertEquals( "Scorching Ray", scorchingRay.name);

        PFSRDSpell fireBall = spellsByLevel.get(3).get("Fireball");
        assertNotNull( fireBall );
        assertEquals( "Fireball", fireBall.name);
        assertEquals( "22", fireBall.spellDC);

        PFSRDSpell timeStop = spellsByLevel.get(9).get("Time Stop");
        assertNotNull( timeStop );
        assertEquals( "Time Stop", timeStop.name);

        dss = characters.get(2);
        PathfinderToken inaris = new PathfinderToken( dss );
        assertEquals( "Inaris Jerveel", inaris.getName() );
        inaris.setSpells();
        spells = inaris.getSpellsByClass("Cleric") ;
        assertNotNull(spells);
        PFSRDSpell bane = spells.get("Bane");
        assertEquals( "20", bane.casterLevel);
        assertEquals( "20", bane.spellDC);
        assertEquals( "Bane", bane.name);


    }

    @Test
    public void testMultiClassPC() throws Exception {
        dig = new HerolabsDigester();
        dig.parse( new File( "src/net/sozinsoft/tokenlab/test/xml/Fortune_Teller.xml") );
        List<net.sozinsoft.tokenlab.dtd.Character> characters = dig.getCharacters();
        Character dss = characters.get(0);
        PathfinderToken ft = new PathfinderToken( dss );
        assertEquals("Fortune Teller", ft.getName());

        //first check his classes
        HashMap<String, PFSRDSpell> sorcererSpells = ft.getSpellsByClass("Sorcerer");
        assertNotNull( sorcererSpells);
        assertTrue( sorcererSpells.containsKey("Mage Armor"));
        PFSRDSpell ma = sorcererSpells.get("Mage Armor");
        assertEquals( "3", ma.casterLevel);

        HashMap<String, PFSRDSpell> bardSpells = ft.getSpellsByClass("Bard");
        assertNotNull( bardSpells );
        assertTrue( bardSpells.containsKey("Silent Image"));
        PFSRDSpell si = bardSpells.get("Silent Image");
        assertEquals( "Silent Image", si.name );


    }

    @Test
    public void testAC() throws Exception {

        List<net.sozinsoft.tokenlab.dtd.Character> characters = dig.getCharacters();
        Character dss = characters.get(0);

        PathfinderToken ptok = new PathfinderToken( dss );

        //Derrak Stoneskull
        assertEquals( 14, ptok.getACArmorBonus().intValue() );  //full plate +5
        assertEquals( 0, ptok.getACFromShield().intValue() );
        assertEquals( 2, ptok.getACFromDeflect().intValue() );  //ring of protection +2
        assertEquals( 1, ptok.getACFromDodge().intValue() ); //he has dodge
        assertEquals( 2, ptok.getACFromnNatural().intValue() ); //he is wearing a ammy of nat armor +2
        assertEquals( 0, ptok.getACFromSize().intValue() ) ;
        assertEquals( 0, ptok.getACMisc().intValue() ) ;

        dss = characters.get(3);
        ptok = new PathfinderToken( dss );

        //Wotywina Turncoin
        assertEquals( 5, ptok.getACArmorBonus().intValue() );  //bracers of armor +5
        assertEquals( 0, ptok.getACFromShield().intValue() );
        assertEquals( 3, ptok.getACFromDeflect().intValue() );  //ring of protection +3
        assertEquals( 1, ptok.getACFromDodge().intValue() ); //she has dodge
        assertEquals( 0, ptok.getACFromnNatural().intValue() );
        assertEquals( 1, ptok.getACFromSize().intValue() ) ; //she's small
        assertEquals( 0, ptok.getACMisc().intValue() ) ;

    }

    @Test
    public void testTokenCreation() throws Exception, SAXException {
        Config config = new Config();
        config.addConfigEntry( "Derrak Stoneskull", "src/net/sozinsoft/tokenlab/test/img/derrakStoneSkullPog.png",
                               "src/net/sozinsoft/tokenlab/test/img/derrakStoneskullPortrait.jpg",
                               "src/net/sozinsoft/tokenlab/test/tokens/derrakStoneSkull.rptok" );
        config.addConfigEntry( "Echean Ansolandi", "src/net/sozinsoft/tokenlab/test/img/echeanPog.png",
                               "src/net/sozinsoft/tokenlab/test/img/echeanPortrait.jpg",
                               "src/net/sozinsoft/tokenlab/test/tokens/echean.rptok" );
        config.addConfigEntry( "Inaris Jerveel", "src/net/sozinsoft/tokenlab/test/img/inarisJarveelPog.png",
                               "src/net/sozinsoft/tokenlab/test/img/inarisJarveelPortrait.jpg",
                               "src/net/sozinsoft/tokenlab/test/tokens/inarisJarveel.rptok" );
        config.addConfigEntry( "Wotywina Turncoin", "src/net/sozinsoft/tokenlab/test/img/wotywinaTurncoinPog.png",
                               "src/net/sozinsoft/tokenlab/test/img/wotywinaTurncoinPortrait.jpg",
                               "src/net/sozinsoft/tokenlab/test/tokens/wotywinaTurncoin.rptok" );


        List<net.sozinsoft.tokenlab.dtd.Character> characters = dig.getCharacters();
        HerolabsDigester dig = new HerolabsDigester();
        for ( Character c : characters ) {
            PathfinderToken pt = new PathfinderToken(c);
            Token token = pt.asToken( config.get( c.getName() ) );
            dig.saveCharacter( config, c );
        }
    }
}
