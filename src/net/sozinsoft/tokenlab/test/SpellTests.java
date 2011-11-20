package net.sozinsoft.tokenlab.test;


import net.sozinsoft.tokenlab.PFSRDSpell;
import net.sozinsoft.tokenlab.ResourceManager;
import net.sozinsoft.tokenlab.SpellDB;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

public class SpellTests {

    @Test
    public void testLoadPFSRDSpells() throws IOException {
        File spells = ResourceManager.getSpells();
        SpellDB db = new SpellDB( spells.getAbsolutePath() );
        PFSRDSpell acidArrow = db.getSpell( "Acid Arrow");
        assertEquals( "Acid Arrow", acidArrow.name );
        assertEquals( "no", acidArrow.spell_resistence);
        assertEquals( "2", acidArrow.sor);
        assertEquals( "Ranged touch attack; 2d4 damage for 1 round + 1 round/three levels.", acidArrow.short_description);

        PFSRDSpell wreathOfBlades = db.getSpell( "Wreath of Blades");
        assertEquals( "abjuration", wreathOfBlades.school);
        assertEquals("1 standard action", wreathOfBlades.casting_time);
        assertEquals("Reflex half (special, see below)", wreathOfBlades.saving_throw);
        assertEquals( "4", wreathOfBlades.magus);
        assertEquals( "Four mithral daggers speed around you, attacking nearby creatures and protecting your spellcasting from attacks of opportunity.",
                      wreathOfBlades.short_description);
    }

}
