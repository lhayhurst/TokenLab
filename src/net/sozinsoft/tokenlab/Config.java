package net.sozinsoft.tokenlab;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.HashMap;
import java.util.prefs.Preferences;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

public class Config {

    public static final String TOKENLAB_CHARACTER_MAPPINGS = "TOKENLAB_CHARACTER_MAPPINGS";
    private HashMap<String, ConfigEntry> configs;
    private Preferences prefs;
    private String xmlFileLocation;
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

	public ConfigEntry addConfigEntry( String name, String imagePath, String portraitPath, String outputTokenPath ) {

        ConfigEntry ce = new ConfigEntry();
        ce.setCharacterName( name );
        ce.setImageFilePath(imagePath);
        ce.setPortraitFilePath(portraitPath);
        ce.setOutputTokenTo(outputTokenPath);
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

	public class ConfigEntry {
		
    	private String CharacterName;
    	private String imageFilePath;
    	private String portraitFilePath;
    	private String outputTokenTo;

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
			return outputTokenTo;
		}

		public void setOutputTokenTo( String path) {
            if ( path != null && ! path.endsWith(".rptok")) {
                path = path + ".rptok";
            }
			outputTokenTo = path;
		}

        public boolean isOk() {
            return outputTokenTo != null && imageFilePath != null;

        }
    }

	public ConfigEntry get(String name) {
		return this.configs.get(name);
	}
}


