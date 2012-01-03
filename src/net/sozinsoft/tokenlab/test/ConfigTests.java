package net.sozinsoft.tokenlab.test;

import net.rptools.lib.FileUtil;
import net.sozinsoft.tokenlab.Config;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

public class ConfigTests {

    /**
     * @return New Config with mocked out prefs file.
     */
    private Config newConfig() {
        Preferences mockPrefs = Preferences.userNodeForPackage(this.getClass());

        Config config = null;
        try {
            config = new Config(mockPrefs);
        } catch (IOException exception) {
            fail("IO Exception - shouldn't be trying to load the file, however.");
        }
        return config;
    }
    
    private String cleanPath(String pathString) {
        return FilenameUtils.separatorsToSystem(pathString);
    }

    @Test
    public void shouldGenerateValidTokenFileNames() {

        Map<String, String> filenames = new HashMap<String, String>() {{
           put(null, null);
           put("", null);
           put("Jur Revicious", "JurRevicious.rptok");
           put("Jacen Salem", "JacenSalem.rptok");
           put("Dragon, Ancient Black", "DragonAncientBlack.rptok");
           put("Kuroshi O'Koye", "KuroshiOKoye.rptok");
           put("James T. Kirk", "JamesTKirk.rptok");
           put("Name_With_Underscores", "NameWithUnderscores.rptok");

            // Some cases not handled yet, to handle later
//           put("Dragon, Ancient Black", "AncientBlackDragon.rptok");
//           put("Drüd", "Drud.rptok");
        }};

        Config config = newConfig();
        Config.ConfigEntry entry;
        for(String characterName : filenames.keySet()) {
            entry = config.addConfigEntry(characterName);
            assertEquals(filenames.get(characterName), entry.getTokenFileName());
        }
    }

    @Test
    public void shouldAppendTokenSuffixToFileName() {
        Config config = newConfig();
        Config.ConfigEntry entry = config.addConfigEntry("Jacen Salem");

        entry.setTokenFileName("JacenSalem");
        assertEquals("JacenSalem.rptok", entry.getTokenFileName());

        entry.setTokenFileName("JurRevicious.rptok");
        assertEquals("JurRevicious.rptok", entry.getTokenFileName());

        // Very limited error checking right now - this is what will happen currently
        entry.setTokenFileName("JurRevicious.txt");
        assertEquals("JurRevicious.txt.rptok", entry.getTokenFileName());
        entry.setTokenFileName("JurRevicious.rptok.txt");
        assertEquals("JurRevicious.rptok.txt.rptok", entry.getTokenFileName());
    }

    @Test
    public void shouldCorrectlyDefaultTokenFilePath() {
        Config config = newConfig();
        Config.ConfigEntry entry = config.addConfigEntry("Jacen Salem");

        entry.defaultTokenPath();
        
        assertEquals("JacenSalem.rptok", entry.getTokenFileName());
        assertEquals(cleanPath(config.getOutputTokenDirectory()), cleanPath(entry.getTokenFileDirectory()));
    }

    @Test
    public void shouldBeOKWhenTokenPogAndPortraitAreSet() {
        Config config = newConfig();
        Config.ConfigEntry entry = config.addConfigEntry("Jacen Salem");

        assertFalse(entry.isOk());

        entry.defaultTokenPath();
        assertFalse(entry.isOk());

        entry.setPogFilePath("/Somewhere/Else");
        assertFalse(entry.isOk());

        entry.setPortraitFilePath("/Also/Somewhere/Else");
        assertTrue(entry.isOk());
    }

    @Test
    public void shouldCorrectlyUpdateOutputEntriesWithDefaultValues() {
        ArrayList<String> characterNames = new ArrayList<String>() {{
           add("Jur Revicious");
           add("Jacen Salem");
           add("Dragon, Ancient Black");
           add("Kuroshi O'Koye");
           add("James T. Kirk");
        }};

        Config config = newConfig();

        String outputDirectory = "/Test/Tokens/";
        String portraitDirectory = "/Test/Portraits/";
        String pogDirectory = "/Test/Pogs/";
        config.setOutputTokenDirectory(outputDirectory);
        config.setPortraitDirectory(portraitDirectory);
        config.setPogDirectory(pogDirectory);

        assertEquals(0, config.getEntries().size());

        for (String characterName : characterNames ) {
            config.populateCharacterWithDefaults(characterName, false);
            Config.ConfigEntry entry = config.get(characterName);

            assertEquals(cleanPath(outputDirectory + entry.getTokenFileName()), entry.getOutputTokenPath());
            // Right now not testing image lookups
//            assertEquals(portraitDirectory + entry.getPortraitFileName(), entry.getPortraitFilePath());
//            assertEquals(pogDirectory + entry.getPogFileName(), entry.getPortraitFilePath());
        }
        
        assertEquals(characterNames.size(), config.getEntries().size());
    }

    @Test
    public void shouldCorrectlyRemoveEntries() {
       final String entryToRemove = "James T. Kirk";
       ArrayList<String> characters = new ArrayList<String>() {{
            add("Jur Revicious");
            add("Jacen Salem");
            add("Dragon, Ancient Black");
            add("Kuroshi O'Koye");
            add(entryToRemove);
       }};

        Config config = newConfig();
        Config.ConfigEntry entry;
        for(String characterName : characters) {
            entry = config.addConfigEntry(characterName);
        }

        assertEquals(characters.size(), config.getEntries().size());
        assertEquals(entryToRemove, config.get("James T. Kirk").getCharacterName());

        config.remove("James T. Kirk");
        assertEquals(characters.size()-1, config.getEntries().size());
        assertEquals(null, config.get("James T. Kirk"));

        config.remove("Grolatta");
        assertEquals(characters.size()-1, config.getEntries().size());
    }
    
    @Test
    public void shouldSuccessfullyLoadV1ConfigFile() {
        Preferences mockPrefs = Preferences.userNodeForPackage(this.getClass());

        Config config = null;
        try {
            config = new Config(mockPrefs, "src/net/sozinsoft/tokenlab/test/xml/configV1.xml");
        } catch (IOException exception) {
            fail("Failed to load test config file: \n" + exception.toString());
        }

        for (Config.ConfigEntry entry : config.getEntries()) {
            assertEquals(config, entry.getConfig());
        }

        ArrayList<String> characters = new ArrayList<String>() {{
            add("Bunyip");
            add("Bruthazmus");
        }};

        for (String character : characters) {
            Config.ConfigEntry entry = config.get(character);
            assertEquals(character, entry.getCharacterName());
            assertEquals(cleanPath("/Some/Portrait/Directory/") + character + ".png", entry.getPortraitFilePath());
            assertEquals(cleanPath("/Some/Pog/Directory/") + character + ".png", entry.getPogFilePath());
        }
    }
}
