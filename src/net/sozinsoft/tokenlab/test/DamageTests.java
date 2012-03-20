package net.sozinsoft.tokenlab.test;

import net.sozinsoft.tokenlab.Damage;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: lhayhurst
 * Date: 3/20/12
 * Time: 8:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class DamageTests {

    @Test
    public void testDamages() throws Exception {
        String basicDamage = "1d6";
        Damage d = new Damage( basicDamage);
        assertEquals( 1, d.getNumDamageDice()) ;
        assertEquals( 6, d.getDamageDice());

        d = new Damage("6");
        assertEquals( 0, d.getNumDamageDice()) ;
        assertEquals( 0, d.getDamageDice());
        assertEquals( 6, d.getBonusDamage());

        d = new Damage("--");
        assertEquals( 0, d.getNumDamageDice()) ;
        assertEquals( 0, d.getDamageDice());
        assertEquals( 0, d.getBonusDamage());

        d = new Damage("RustMonster");
        assertEquals( 0, d.getNumDamageDice()) ;
        assertEquals( 0, d.getDamageDice());
        assertEquals( 0, d.getBonusDamage());


        d = new Damage( "2d8+6");
        assertEquals( 2, d.getNumDamageDice()) ;
        assertEquals( 8, d.getDamageDice());
        assertEquals( 6, d.getBonusDamage());
        
        d = new Damage("2d6+5 Fire");
        assertEquals( 2, d.getNumDamageDice()) ;
        assertEquals( 6, d.getDamageDice());
        assertEquals( 5, d.getBonusDamage());
        assertEquals( "Fire", d.getDamageType());

        d = new Damage("2d6+5 Fire + Confusion");
        assertEquals( 2, d.getNumDamageDice()) ;
        assertEquals( 6, d.getDamageDice());
        assertEquals( 5, d.getBonusDamage());
        assertEquals( "Fire + Confusion", d.getDamageType());


    }
}
