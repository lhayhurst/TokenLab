package net.sozinsoft.tokenlab;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import net.rptools.maptool.model.Token;
import net.rptools.maptool.util.PersistenceUtil;
import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;


public class HerolabsDigester {

    /**
     * Main method : entry point for running this example program.
     * <p/>
     * Usage: java Example example.xml
     */


    private LinkedList<Character> characters = new LinkedList<Character>();

    public LinkedList<Character> getCharacters() {
        return characters;
    }

    public void addCharacter(Character c) {
        characters.add(c);
    }


    public static void main(String[] args) {
        if (args.length != 2) {
            usage();
            System.exit(-1);
        }

        String herolabsXMLFile = args[0];
        String configFileName = args[1];

        int exitStatus = 0;

        //Process the config file
        Config config = new Config(configFileName);
        try {
            config.parseConfigFile();
        } catch (java.io.IOException ioe) {
            System.out.println("Error reading input file:" + ioe.getMessage());
            System.exit(-1);
        } catch (org.xml.sax.SAXException se) {
            System.out.println("Error parsing input file:" + se.getMessage());
            System.exit(-1);
        }

        try {
            HerolabsDigester digester = new HerolabsDigester();
            digester.parse(new File(herolabsXMLFile));
            digester.processCharacters(config);
        } catch (org.xml.sax.SAXException se) {
            System.out.println("Error parsing input file:" + se.getMessage());
            se.printStackTrace();
            exitStatus = -1;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            exitStatus = -1;
        }  finally {
            ResourceManager.cleanupTmpFiles();
        }

        System.exit(exitStatus);
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
        PathfinderToken pt = new PathfinderToken(c, null);
        Token t = pt.asToken(ce);
        System.out.println( "Saving maptools token to output directory " + ce.getOutputTokenTo());
        PersistenceUtil.saveToken(t, ce.getOutputTokenTo(), true);
    }


    public void parse(File xmlSourceFile) throws IOException, SAXException {
        // Create a Digester instance
        Digester d = new Digester();
        d.setValidating(false);
        d.push(this);

        d.addObjectCreate("document/public/character", Character.class);
        d.addSetProperties("document/public/character");

        //race
        d.addCallMethod("document/public/character/race", "setRace", 1);
        d.addCallParam("document/public/character/race", 0, "name");

        //role
        d.addCallMethod("document/public/character", "setRole", 1);
        d.addCallParam("document/public/character", 0, "role");

        //playername
        d.addCallMethod("document/public/character", "addPlayerName", 1);
        d.addCallParam("document/public/character", 0, "playername");

        //alignment
        d.addCallMethod("document/public/character/alignment", "addAlignment", 1);
        d.addCallParam("document/public/character/alignment", 0, "name");

        //personal stuff
        d.addCallMethod("document/public/character/personal", "addPersonal", 4);
        d.addCallParam("document/public/character/personal", 0, "gender");
        d.addCallParam("document/public/character/personal", 1, "age");
        d.addCallParam("document/public/character/personal/charheight", 2, "text");
        d.addCallParam("document/public/character/personal/charweight", 3, "text");

        //movement
        d.addCallMethod("document/public/character/movement", "addMovement", 1);
        d.addCallParam("document/public/character/movement/speed", 0, "value");


        //hitpoints
        d.addCallMethod("document/public/character/health", "setHitpoints", 1);
        d.addCallParam("document/public/character/health", 0, "hitpoints");

        //attack bonus
        d.addCallMethod("document/public/character/attack", "setAttackBonus", 1);
        d.addCallParam("document/public/character/attack", 0, "baseattack");


        //armor class
        d.addCallMethod("document/public/character/armorclass", "setArmorClassFroms", 8);
        d.addCallParam("document/public/character/armorclass", 0, "frommisc");
        d.addCallParam("document/public/character/armorclass", 1, "fromdodge");
        d.addCallParam("document/public/character/armorclass", 2, "fromdeflect");
        d.addCallParam("document/public/character/armorclass", 3, "fromnatural");
        d.addCallParam("document/public/character/armorclass", 4, "fromsize");
        d.addCallParam("document/public/character/armorclass", 5, "fromdexterity");
        d.addCallParam("document/public/character/armorclass", 6, "fromshield");
        d.addCallParam("document/public/character/armorclass", 7, "fromarmor");
//        d.addCallMethod( "document/public/character/armorclass", "setArmorClassBasics", 3);
        //       d.addCallParam( "document/public/character/armorclass", 0, "touch" );
        //       d.addCallParam( "document/public/character/armorclass", 1, "flatfooted" );
        //       d.addCallParam( "document/public/character/armorclass", 2, "ac" );


        //defenses
        d.addCallMethod( "document/public/character/defenses/armor", "addArmor", 5);
        d.addCallParam( "document/public/character/defenses/armor", 0, "name");
        d.addCallParam( "document/public/character/defenses/armor", 1, "ac");
        d.addCallParam( "document/public/character/defenses/armor", 2, "equipped");
        d.addCallParam( "document/public/character/defenses/armor", 3, "natural");
        d.addCallParam( "document/public/character/defenses/armor", 4, "useradded");

        //attributes
        d.addSetNext("document/public/character/attributes/attribute", "addAttribute");
        d.addObjectCreate("document/public/character/attributes", "net.sozinsoft.tokenlab.CharacterAttribute");
        d.addCallMethod("document/public/character/attributes/attribute", "setName", 1);
        d.addCallParam("document/public/character/attributes/attribute", 0, "name");
        d.addCallMethod("document/public/character/attributes/attribute/attrvalue", "setValue", 3);
        d.addCallParam("document/public/character/attributes/attribute/attrvalue", 0, "base");
        d.addCallParam("document/public/character/attributes/attribute/attrvalue", 1, "modified");
        d.addCallParam("document/public/character/attributes/attribute/attrvalue", 2, "text");
        d.addCallMethod("document/public/character/attributes/attribute/attrbonus", "setBonus", 2);
        d.addCallParam("document/public/character/attributes/attribute/attrbonus", 0, "base");
        d.addCallParam("document/public/character/attributes/attribute/attrbonus", 1, "modified");


        //level
        d.addCallMethod("document/public/character/classes", "setLevel", 2);
        d.addCallParam("document/public/character/classes", 0, "level");
        d.addCallParam("document/public/character/classes", 1, "summaryabbr");
        // d.addCallMethod( "document/public/character/classes/class", "addClass", 2 );
        //d.addCallParam( "document/public/character/classes/class", 0, "name");
        //d.addCallParam( "document/public/character/classes/class", 1, "level");

        //saves
        //<saves><save name="Fortitude Save" base="+2" frommisc="" fromresist="" fromattr="+1" save="+3" abbr="Fort"/>
        d.addCallMethod("document/public/character/saves/save", "addSave", 7);
        d.addCallParam("document/public/character/saves/save", 0, "name");
        d.addCallParam("document/public/character/saves/save", 1, "abbr");
        d.addCallParam("document/public/character/saves/save", 2, "base");
        d.addCallParam("document/public/character/saves/save", 3, "frommisc");
        d.addCallParam("document/public/character/saves/save", 4, "fromresist");
        d.addCallParam("document/public/character/saves/save", 5, "fromattr");
        d.addCallParam("document/public/character/saves/save", 6, "save");

        //basic attack
       // d.addCallMethod("document/public/character/attack", "addBaseAttack", 1);
        //d.addCallParam("document/public/character/attack", 0, "baseattack");

        //melee weapons
        d.addCallMethod("document/public/character/melee/weapon", "addWeapon", 8);
        d.addCallParam("document/public/character/melee/weapon", 0, "name");
        d.addCallParam("document/public/character/melee/weapon", 1, "damage");
        d.addCallParam("document/public/character/melee/weapon", 2, "categorytext");
        d.addCallParam("document/public/character/melee/weapon", 3, "crit");
        d.addCallParam("document/public/character/melee/weapon", 4, "attack");
        d.addCallParam("document/public/character/melee/weapon", 5, "equipped");
        d.addCallParam("document/public/character/melee/weapon/weptype", 6);
        d.addCallParam("document/public/character/melee/weapon/description", 7);


        //ranged weapons

        d.addCallMethod("document/public/character/ranged/weapon", "addWeapon", 8);
        d.addCallParam("document/public/character/ranged/weapon", 0, "name");
        d.addCallParam("document/public/character/ranged/weapon", 1, "damage");
        d.addCallParam("document/public/character/ranged/weapon", 2, "categorytext");
        d.addCallParam("document/public/character/ranged/weapon", 3, "crit");
        d.addCallParam("document/public/character/ranged/weapon", 4, "attack");
        d.addCallParam("document/public/character/ranged/weapon", 5, "equipped");
        d.addCallParam("document/public/character/ranged/weapon/weptype", 6);
        d.addCallParam("document/public/character/ranged/weapon/description", 7);


        //skills
        d.addCallMethod("document/public/character/skills/skill", "addSkill", 8);
        d.addCallParam("document/public/character/skills/skill", 0, "name");
        d.addCallParam("document/public/character/skills/skill", 1, "value");
        d.addCallParam("document/public/character/skills/skill", 2, "attrname");
        d.addCallParam("document/public/character/skills/skill", 3, "attrbonus");
        d.addCallParam("document/public/character/skills/skill", 4, "ranks");
        d.addCallParam("document/public/character/skills/skill", 5, "classskill");
        d.addCallParam("document/public/character/skills/skill", 6, "trainedonly");
        d.addCallParam("document/public/character/skills/skill/description", 7);

        //initiative
        d.addCallMethod("document/public/character/initiative", "addInitiative", 4);
        d.addCallParam("document/public/character/initiative", 0, "total");
        d.addCallParam("document/public/character/initiative", 1, "attrname");
        d.addCallParam("document/public/character/initiative", 2, "misctext");
        d.addCallParam("document/public/character/initiative", 3, "attrtext");

        //armor check penalty
        d.addCallMethod("document/public/character/penalties/penalty", "addPenalty", 3);
        d.addCallParam("document/public/character/penalties/penalty", 0, "name");
        d.addCallParam("document/public/character/penalties/penalty", 1, "value");
        d.addCallParam("document/public/character/penalties/penalty", 2, "text");

        //size
        d.addCallMethod("document/public/character/size", "addSize", 1);
        d.addCallParam("document/public/character/size", 0, "name");

        //deity
        d.addCallMethod("document/public/character/deity", "addDeity", 1);
        d.addCallParam("document/public/character/deity", 0, "name");

        //feats
        d.addCallMethod("document/public/character/feats/feat", "addFeat", 6);
        d.addCallParam("document/public/character/feats/feat", 0, "name");
        d.addCallParam("document/public/character/feats/feat", 1, "useradded");
        d.addCallParam("document/public/character/feats/feat", 2, "profgroup");
        d.addCallParam("document/public/character/feats/feat", 3, "categorytext");
        d.addCallParam("document/public/character/feats/feat/description", 4);
        d.addCallParam("document/public/character/feats/feat/featcategory", 5);


        d.addSetNext("document/public/character", "addCharacter", "net.rptools.maptool.util.herolabs.Character");
        d.parse(xmlSourceFile);
    }

    private static void usage() {
        System.out.println("Usage: java HerolabsDigester input.xml config.xml");
    }

}
