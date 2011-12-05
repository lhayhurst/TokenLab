package net.sozinsoft.tokenlab;

import net.sozinsoft.tokenlab.d20_dtd.*;
import net.sozinsoft.tokenlab.d20_dtd.Character;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lhayhurst
 * Date: 12/5/11
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroLabD20Digester extends HeroLabDigester {

    private List<net.sozinsoft.tokenlab.d20_dtd.Character> characters;

    public List<Character> getCharacters() {
        return characters;
    }


    public void parse(File xmlFile ) throws JAXBException {
         Document c = (Document)this.parse( xmlFile, net.sozinsoft.tokenlab.d20_dtd.Document.class );
         characters = c.getPublic().getCharacter();
    }


    public void saveCharacter(Config config, Character c) throws Exception {
        D20Token d = new D20Token( c );
        this.saveCharacter( config, d );
    }

}
