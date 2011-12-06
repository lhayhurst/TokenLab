package net.sozinsoft.tokenlab;


import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sozinsoft.tokenlab.dtd.Character;
import net.rptools.maptool.model.Token;
import net.rptools.maptool.util.PersistenceUtil;
import net.sozinsoft.tokenlab.dtd.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class HeroLabPathfinderDigester extends HeroLabDigester {


    private List<Character> characters;

    public List<Character> getCharacters() {
        return characters;
    }


    public void parse(File xmlFile ) throws JAXBException {
         Document c = (Document)this.parse( xmlFile, net.sozinsoft.tokenlab.dtd.Document.class );
         characters = c.getPublic().getCharacter();
    }


    public void saveCharacter(Config config, Character c) throws Exception {
        PathfinderToken pt = new PathfinderToken(c);
        this.saveCharacter( config, pt );
    }



}
