package net.sozinsoft.tokenlab;

import java.io.*;
import java.util.HashMap;
import java.util.prefs.Preferences;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

public class Config {

    public static final String TOKENLAB_CHARACTER_MAPPINGS = "TOKENLAB_CHARACTER_MAPPINGS";
    private HashMap<String, ConfigEntry> configs;
    private Preferences prefs;
    private String xmlFileLocation;
    private String outputTokenDirectory;
	private String configFileName;

    public Config( Preferences prefs ) throws IOException {
        this.prefs = prefs;
        XStream xstream = new XStream();
        xmlFileLocation = this.prefs.get(TOKENLAB_CHARACTER_MAPPINGS, "");
        File f = new File(xmlFileLocation);
        if ( xmlFileLocation != null && ! xmlFileLocation.isEmpty() && f.exists() ) {
            configs = (HashMap<String, ConfigEntry>) xstream.fromXML(f);
        }
        else {
            configs = new HashMap<String, ConfigEntry>();
        }
    }

    public Config() {
        configs = new HashMap<String, ConfigEntry>();
    }

    public void save() {
        try {

            if ( configs.size() == 0) {
                return;
            }

            if ( xmlFileLocation.isEmpty() ) {
                xmlFileLocation = "config.xml";
                prefs.put( TOKENLAB_CHARACTER_MAPPINGS, xmlFileLocation );
            }
            FileOutputStream os = new FileOutputStream(xmlFileLocation);
            XStream xstream = new XStream();
            xstream.toXML( configs, os );
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add an empty ConfigEntry other than the character's name.
     */
    public ConfigEntry addConfigEntry( String name) {
        return addConfigEntry(name, null, null, null);
    }

	public ConfigEntry addConfigEntry( String name, String imagePath, String portraitPath, String tokenFileDirectory ) {
        ConfigEntry ce = new ConfigEntry();
        ce.setCharacterName(name);
        ce.setImageFilePath(imagePath);
        ce.setPortraitFilePath(portraitPath);
        ce.setTokenFileDirectory(tokenFileDirectory);
        ce.resetDefaultTokenFilename();
		configs.put( name, ce);
        return ce;
	}
	
	public void parseConfigFile() throws IOException, SAXException {
        Digester d = new Digester(); 
        d.push( this );
        
        d.addCallMethod( "config/token", "addConfigEntry", 4 );
        d.addCallParam( "config/token", 0, "name" );
        d.addCallParam( "config/token", 1, "imageFilePath" );
        d.addCallParam( "config/token", 2, "portraitFilePath" );
        d.addCallParam( "config/token", 3, "outputTokenTo" );

        d.parse( new File( configFileName ) );
	}

    public String getOutputTokenDirectory() {
        return outputTokenDirectory;
    }

    public void setOutputTokenDirectory(String outputTokenDirectory) {
        this.outputTokenDirectory = outputTokenDirectory;
    }

    public class ConfigEntry {
		
    	private String CharacterName;
    	private String imageFilePath;
    	private String portraitFilePath;
        private String tokenFileName;
        private String tokenFileDirectory;

        public ConfigEntry() {

        }

    	//public ConfigEntry( String name, String imagePath, String portraitPath, String outputTokenPath ) {
    	//	this.CharacterName    = name;
    	//	this.imageFilePath    = imagePath;
    	//	this.portraitFilePath = portraitPath;
    	//	this.outputTokenTo    = outputTokenPath;
    	//}

        public String getCharacterName() {
			return CharacterName;
		}

        public void setCharacterName( String name) {
            this.CharacterName = name;

        }
		public String getImageFilePath() {
			return imageFilePath;
		}

        public void setImageFilePath( String path ) {
            imageFilePath = path;
        }

        public String getPortraitFilePath() {
			return portraitFilePath;
		}

        public void setPortraitFilePath(String path) {
			portraitFilePath = path;
		}

		public String getOutputTokenTo() {
            if (tokenFileDirectory != null && tokenFileName != null) {
                return new File(getTokenFileDirectory(), getTokenFileName()).toString();
            }

            return null;
		}

        public boolean isOk() {
            return getOutputTokenTo() != null && imageFilePath != null;
        }

        public String getTokenFileName() {
            if (tokenFileName == null) {
                tokenFileName = generateDefaultTokenFileName();
            }

            return tokenFileName;
        }

        private String generateDefaultTokenFileName() {
            if (CharacterName == null || CharacterName.length() == 0) {
                return null;
            }

            String filename = CharacterName.replaceAll("[ ,'.]", "");

            return filename + ".rptok";
        }

        public void setTokenFileName(String tokenFileName) {
            if ( tokenFileName != null && ! tokenFileName.endsWith(".rptok")) {
                tokenFileName = tokenFileName + ".rptok";
            }

            this.tokenFileName = tokenFileName;
        }

        public String getTokenFileDirectory() {
            return tokenFileDirectory;
        }

        public void setTokenFileDirectory(String tokenFileDirectory) {
            this.tokenFileDirectory = tokenFileDirectory;
        }

        public void resetDefaultTokenFilename() {
            setTokenFileName(generateDefaultTokenFileName());
        }
    }

	public ConfigEntry get(String name) {
		return this.configs.get(name);
	}
}


