package net.sozinsoft.tokenlab;


import net.rptools.maptool.model.Token;
import net.rptools.maptool.util.PersistenceUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.Class;

public class HeroLabDigester {

    public Object parse(File xmlFile, Class documentClass ) throws JAXBException {
         JAXBContext ctx = JAXBContext.newInstance(new java.lang.Class[] { documentClass });
         Unmarshaller um = ctx.createUnmarshaller();
         Object   o = um.unmarshal( xmlFile );
         return o;
    }


    public void saveCharacter(Config config, ITokenizable tokenizable) throws Exception {
        System.out.println("Processing character " + tokenizable.getName());
        Config.ConfigEntry ce = config.get(tokenizable.getName());

        if (ce == null) {
            System.out.println("Couldn't find " + tokenizable.getName() + " in config file!");
            return;
        }

        System.out.println( "Creating token for character " + tokenizable.getName() );
        Token t = tokenizable.asToken(ce);
        System.out.println( "Saving maptools token to output directory " + ce.getOutputTokenTo());
        PersistenceUtil.saveToken(t, ce.getOutputTokenTo(), true);
    }



}

