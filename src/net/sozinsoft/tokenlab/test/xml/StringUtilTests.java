package net.sozinsoft.tokenlab.test.xml;

import net.sozinsoft.tokenlab.StringUtils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: lhayhurst
 * Date: 1/2/12
 * Time: 6:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtilTests {
    @Test
    public void testSpellXN() {
        String spellName = "Cure Light Wounds (x2)";
        int numRepeats = StringUtils.isXN( spellName );
        assertEquals( 2, numRepeats );

        spellName = "Cure Light Wounds (x3)";
        numRepeats = StringUtils.isXN( spellName );
        assertEquals( 3, numRepeats );

        spellName = "Cure Light Wounds";
        numRepeats = StringUtils.isXN( spellName );
        assertEquals( 0, numRepeats );
    }
    
    @Test
    public void testRemoveXN() {
        String spellName = "Cure Light Wounds (x2)";
        spellName = StringUtils.removeXN( spellName );
        assertEquals( "Cure Light Wounds", spellName );
        spellName = "Cure Light Wounds (x3)";
        assertEquals( "Cure Light Wounds", StringUtils.removeXN( spellName ));
    }
}
