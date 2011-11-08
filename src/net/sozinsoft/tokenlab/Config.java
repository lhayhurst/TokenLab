package net.sozinsoft.tokenlab;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

public class Config {
	
	private HashMap<String, ConfigEntry> configs = new HashMap<String, ConfigEntry>();
	
	private String configFileName;
	
	public Config( String configFileName ) {
		this.configFileName = configFileName;
	}
	
	public void addConfigEntry( String name, String imagePath, String portraitPath, String outputTokenPath ) {
		ConfigEntry ce = new ConfigEntry( name, imagePath, portraitPath, outputTokenPath);
		configs.put( name, ce);
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

	class ConfigEntry {
		
    	private String CharacterName;
    	private String imageFilePath;
    	private String portraitFilePath;
    	private String outputTokenTo;
    	
    	public ConfigEntry( String name, String imagePath, String portraitPath, String outputTokenPath ) {
    		this.CharacterName    = name;
    		this.imageFilePath    = imagePath;
    		this.portraitFilePath = portraitPath;
    		this.outputTokenTo    = outputTokenPath;
    	}
		public String getCharacterName() {
			return CharacterName;
		}
		public String getImageFilePath() {
			return imageFilePath;
		}
		public String getPortraitFilePath() {
			return portraitFilePath;
		}

		public String getOutputTokenTo() {
			return outputTokenTo;
		}
	}

	public ConfigEntry get(String name) {
		return this.configs.get(name);
	}
}


