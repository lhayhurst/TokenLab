package net.sozinsoft.tokenlab;


import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import net.sozinsoft.tokenlab.dtd.Character;
import net.rptools.maptool.model.Token;
import net.rptools.maptool.util.PersistenceUtil;
import net.sozinsoft.tokenlab.dtd.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class HerolabsDigester {

    /**
     * Main method : entry point for running this example program.
     * <p/>
     * Usage: java Example example.xml
     */


    private List<Character> characters;

    public List<Character> getCharacters() {
        return characters;
    }

    public void addCharacter(Character c) {
        characters.add(c);
    }

    public void parse(File xmlFile ) throws JAXBException {
         JAXBContext ctx = JAXBContext.newInstance(new java.lang.Class[] {net.sozinsoft.tokenlab.dtd.Document.class});
         Unmarshaller um = ctx.createUnmarshaller();
         Document c = (Document) um.unmarshal( xmlFile );
         characters = c.getPublic().getCharacter();
    }


    public void processCharacters(Config config) throws IOException, SAXException {

        //WeaponCache cache = new WeaponCache( ResourceManager.getWeapons().getAbsolutePath() );
        for (Character c : this.characters) {
            processCharacter(config, c);
        }
    }

    public void processCharacter(Config config, Character c) throws IOException, SAXException {
        System.out.println( "Processing character " + c.getName() );
        Config.ConfigEntry ce = config.get(c.getName());

        if (ce == null) {
            System.out.println("Couldn't find " + c.getName() + " in config file!");
            return;
        }

        System.out.println( "Creating token for character " + c.getName() );
        PathfinderToken pt = new PathfinderToken(c);
        Token t = pt.asToken(ce);
        System.out.println( "Saving maptools token to output directory " + ce.getOutputTokenTo());
        PersistenceUtil.saveToken(t, ce.getOutputTokenTo(), true);
    }



}
