package net.sozinsoft.tokenlab.test;

import net.sozinsoft.tokenlab.HerolabsDigester;
import net.sozinsoft.tokenlab.IPathfinderCharacter;
import net.sozinsoft.tokenlab.dtd.Character;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.sozinsoft.tokenlab.PathfinderToken;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

public class PathfinderTokenTests {

    HerolabsDigester dig;
    @Before
    public void setUp() throws JAXBException {
        dig = new HerolabsDigester();
        dig.parse( new File( "src/net/sozinsoft/tokenlab/test/xml/Children_Of_Steel.xml") );
    }

    @Test
	public void testBasic() throws JAXBException {

        List<net.sozinsoft.tokenlab.dtd.Character> characters = dig.getCharacters();
        Character dss = characters.get(0);

        PathfinderToken ptok = new PathfinderToken( dss );
        assertEquals("Derrak Stoneskull", ptok.getName());
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
    public void testAbilities() throws JAXBException {

        List<net.sozinsoft.tokenlab.dtd.Character> characters = dig.getCharacters();
        Character dss = characters.get(0);

        PathfinderToken ptok = new PathfinderToken( dss );

        assertEquals( 20, ptok.getBaseAbilityScore( IPathfinderCharacter.IAttribute.Strength ).intValue() );
        assertEquals( 3,  ptok.getAbilityEnhancement(IPathfinderCharacter.IAttribute.Strength).intValue());
        assertEquals( 0, ptok.getAbilityDamage( IPathfinderCharacter.IAttribute.Strength).intValue() );

        assertEquals( 13, ptok.getBaseAbilityScore(IPathfinderCharacter.IAttribute.Dexterity ).intValue() );
        assertEquals( 0,  ptok.getAbilityEnhancement(IPathfinderCharacter.IAttribute.Dexterity).intValue());
        assertEquals( 0, ptok.getAbilityDamage( IPathfinderCharacter.IAttribute.Dexterity).intValue() );

        assertEquals( 16, ptok.getBaseAbilityScore(IPathfinderCharacter.IAttribute.Constitution ).intValue() );
        assertEquals( 3,  ptok.getAbilityEnhancement(IPathfinderCharacter.IAttribute.Constitution).intValue());
        assertEquals( 0, ptok.getAbilityDamage( IPathfinderCharacter.IAttribute.Constitution).intValue() );

        assertEquals( 10, ptok.getBaseAbilityScore(IPathfinderCharacter.IAttribute.Intelligence ).intValue() );
        assertEquals( 0,  ptok.getAbilityEnhancement(IPathfinderCharacter.IAttribute.Intelligence).intValue());
        assertEquals( 0, ptok.getAbilityDamage( IPathfinderCharacter.IAttribute.Intelligence).intValue() );

        assertEquals( 14, ptok.getBaseAbilityScore(IPathfinderCharacter.IAttribute.Wisdom ).intValue() );
        assertEquals( 0,  ptok.getAbilityEnhancement(IPathfinderCharacter.IAttribute.Wisdom).intValue());
        assertEquals( 0, ptok.getAbilityDamage( IPathfinderCharacter.IAttribute.Wisdom).intValue() );

        assertEquals( 6, ptok.getBaseAbilityScore(IPathfinderCharacter.IAttribute.Charisma ).intValue() );
        assertEquals( 0,  ptok.getAbilityEnhancement(IPathfinderCharacter.IAttribute.Charisma).intValue());
        assertEquals( 0, ptok.getAbilityDamage( IPathfinderCharacter.IAttribute.Charisma).intValue() );

    }
}
