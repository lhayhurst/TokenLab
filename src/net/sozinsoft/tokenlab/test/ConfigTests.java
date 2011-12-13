package net.sozinsoft.tokenlab.test;

import net.sozinsoft.tokenlab.Config;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

public class ConfigTests {

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

            // Some cases not handled yet, to handle later
//           put("Dragon, Ancient Black", "AncientBlackDragon.rptok");
//           put("Drüd", "Drud.rptok");
        }};

        Config config = new Config();
        Config.ConfigEntry entry;
        for(String characterName : filenames.keySet()) {
            entry = config.addConfigEntry(characterName);
            assertEquals(filenames.get(characterName), entry.getTokenFileName());
        }
    }

    @Test
    public void shouldAppendTokenSuffixToFileName() {
        Config config = new Config();
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
    public void shouldCorrectlyCombineTokenNameAndPathInConfigEntry() {
        Config config = new Config();
        Config.ConfigEntry entry = config.addConfigEntry("Jacen Salem");

        entry.setTokenFileName(entry.getTokenFileName()); // Should just set it to the default
        assertEquals("JacenSalem.rptok", entry.getTokenFileName());
        entry.setTokenFileDirectory("/dev/null");
        assertEquals( File.separator +  "dev" + File.separator + "null" + File.separator + "JacenSalem.rptok", entry.getOutputTokenTo());

        entry.setTokenFileDirectory("/dev/null/");
        assertEquals( File.separator +  "dev" + File.separator + "null" + File.separator + "JacenSalem.rptok", entry.getOutputTokenTo());

        entry.setTokenFileName("JurRevicious");
        assertEquals(File.separator +  "dev" + File.separator + "null" + File.separator + "JurRevicious.rptok", entry.getOutputTokenTo());
    }

    @Test
    public void shouldBeOKWhenPathAndImageAreSet() {
        Config config = new Config();
        Config.ConfigEntry entry = config.addConfigEntry("Jacen Salem");

        assertFalse(entry.isOk());

        entry.resetDefaultTokenFilename();
        assertFalse(entry.isOk());

        entry.setTokenFileDirectory("something");
        assertFalse(entry.isOk());

        entry.setImageFilePath("something else");
        assertTrue(entry.isOk());
    }

    @Test
    public void shouldCorrectlyUpdateUnconfiguredOutputEntries() {
        Map<String, String> filenames = new HashMap<String, String>() {{
           put("Jur Revicious", "JurRevicious.rptok");
           put("Jacen Salem", "JacenSalem.rptok");
           put("Dragon, Ancient Black", "DragonAncientBlack.rptok");
           put("Kuroshi O'Koye", "KuroshiOKoye.rptok");
           put("James T. Kirk", "JamesTKirk.rptok");
        }};

        Preferences mockPrefs = Preferences.userNodeForPackage(this.getClass());

        Config config = null;
        try {
            config = new Config(mockPrefs);
        } catch (IOException exception) {
            fail("IO Exception - shouldn't be trying to load the file, however.");
        }

        String outputDirectory = "/Test/Tokens/";
        String portraitDirectory = "/Test/Portraits/";
        String pogDirectory = "/Test/Pogs/";
        config.setOutputTokenDirectory(outputDirectory);
        config.setPortraitDirectory(portraitDirectory);
        config.setPogDirectory(pogDirectory);
        for(String characterName : filenames.keySet()) {
            Config.ConfigEntry entry = config.addConfigEntry(characterName);
            assertEquals(null, entry.getOutputTokenTo());
        }

        config.defaultConfigEntries();

        for (Config.ConfigEntry entry : config.getEntries() ) {
            assertEquals(outputDirectory + entry.getTokenFileName(), entry.getOutputTokenTo());
//            assertEquals(portraitDirectory + entry.getPortraitFileName(), entry.getPortraitFilePath());
        }
    }


}
