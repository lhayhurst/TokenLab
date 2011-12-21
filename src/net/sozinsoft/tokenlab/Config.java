package net.sozinsoft.tokenlab;

import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.prefs.Preferences;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import org.apache.commons.collections.Predicate;
import org.apache.commons.digester3.Digester;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.xml.sax.SAXException;

public class Config {

    public static final String TOKENLAB_CHARACTER_MAPPINGS = "TOKENLAB_CHARACTER_MAPPINGS";
    public static final String TOKEN_DIR = "TOKEN_DIR";
    public static final String IMAGE_DIR = "IMAGE_DIR";
    private static final String POG_DIR = "POG_DIR";
    public static final String TOKEN_FILE_EXTENSION = ".rptok";
    
    
    private static final String[] IMAGE_EXTENSIONS = new String[] { "tiff", "tif", "gif", "bmp", "jpg", "jpeg", "png" };
    private static final Predicate IMAGE_EXTENSION_PREDICATE = new Predicate() {
        public boolean evaluate(Object o) {
            String fileName = (String) o;
            for (String extension: IMAGE_EXTENSIONS) {
                if (fileName.endsWith(extension)) {
                    return true;
                }
            }
            return false;
        }
    };
    private static final FileFilter IMAGE_FILE_FILTER = new FileFilter() {
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            } else {
                String name = f.getName();
                return IMAGE_EXTENSION_PREDICATE.evaluate(name);
            }
        }

        public String getDescription() {
            return "Image files";
        }
    };
    
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
            try {
                configs = (HashMap<String, ConfigEntry>) xstream.fromXML(f);
            } catch (StreamException e) {
                System.out.println("Exception deserializing config :\n" + e.toString());
            }
        }
        else {
            configs = new HashMap<String, ConfigEntry>();
        }

        postLoadConfigs();

        System.out.println("Loaded " + configs.size() + " Config Entries.");
    }

    private void postLoadConfigs() {
        for (ConfigEntry entry : configs.values()) {
            // XStream loaded ConfigEntries will not have their reference to the parent config set.
            entry.setConfig(this);
        }
    }

    public Config() {
        configs = new HashMap<String, ConfigEntry>();
    }

    public void save() {
        try {

            // Need to save with 0 config now, since it is possible to delete configurations.
//            if ( configs.size() == 0) {
//                return;
//            }
//
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
        ConfigEntry ce = new ConfigEntry(this);
        ce.setCharacterName(name);
        ce.setPogFilePath(imagePath);
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
        d.addCallParam( "config/token", 1, "pogFileDirectory" );
        d.addCallParam( "config/token", 2, "portraitFileDirectory" );
        d.addCallParam( "config/token", 3, "outputTokenTo" );

        d.parse( new File( configFileName ) );
	}

    public String getOutputTokenDirectory() {
        return prefs.get(TOKEN_DIR, "");
    }

    public void setOutputTokenDirectory(String outputTokenDirectory) {
        prefs.put(TOKEN_DIR, outputTokenDirectory);
    }

    public String getPogDirectory() {
        return prefs.get(POG_DIR, "");
    }

    public void setPogDirectory(String pogDirectory) {
        prefs.put(POG_DIR, pogDirectory);
    }

    public String getPortraitDirectory() {
        return prefs.get(IMAGE_DIR, "");
    }

    public void setPortraitDirectory(String portraitDirectory) {
        prefs.put(IMAGE_DIR, portraitDirectory);
    }

    public Collection<ConfigEntry> getEntries() {
        return Collections.unmodifiableCollection(configs.values());
    }

    public ConfigEntry get(String name) {
        return this.configs.get(name);
    }

    public ConfigEntry getOrCreate(String name) {
        ConfigEntry entry = get(name);
        if (entry == null) {
            entry = addConfigEntry(name);
        }

        return entry;
    }

    public void remove(String characterName) {
        configs.remove(characterName);
    }

    public void populateCharacterWithDefaults(String characterName) {
        ConfigEntry entry = getOrCreate(characterName);

        entry.populateWithDefaultValues();
    }

    public class ConfigEntry {
		
    	private String characterName;
    	private String pogFileDirectory;
    	private String pogFileName;
    	private String portraitFileDirectory;
    	private String portraitFileName;
        private String tokenFileDirectory;
        private String tokenFileName;

        private Config config;

        // Old Attributes - these can eventually be deleted.
        // Here to prevent crashing during deserialization from previous structure
        private transient String CharacterName;
        private transient String imageFilePath;
        private transient String portraitFilePath;
        // END Old Attributes


        ConfigEntry(Config config) {
            this.config = config;
        }

        void setConfig(Config config) {
            this.config = config;
        }

        public String getCharacterName() {
			return characterName;
		}

        public void setCharacterName( String name) {
            this.characterName = name;

        }

		private String getPogFileDirectory() {
			return pogFileDirectory;
		}

        private void setPogFileDirectory(String directory) {
            pogFileDirectory = directory;
        }

        private String getPortraitFileDirectory() {
            return portraitFileDirectory;
        }

        private void setPortraitFileDirectory(String path) {
            portraitFileDirectory = path;
        }

        private void setPortraitFileName(String name) {
            portraitFileName = name;
        }

        private String getPortraitFileName() {
            return portraitFileName;
        }

        public String getPogFilePath() {
            if (pogFileDirectory != null && pogFileName != null) {
                return new File(pogFileDirectory, pogFileName).getPath();
            }
            
            return null;
        }

        public void setPogFilePath(String path) {
            if (path != null) {
                File fullPath = new File(path);
    
                pogFileDirectory = fullPath.getParent();
                pogFileName = fullPath.getName();
            }
        }

        public String getPortraitFilePath() {
            if (portraitFileDirectory != null && portraitFileName != null) {
                return new File(portraitFileDirectory, portraitFileName).getPath();
            }

            return null;
        }

        public void setPortraitFilePath(String path) {
            if (path != null) {
                File fullPath = new File(path);

                portraitFileDirectory = fullPath.getParent();
                portraitFileName = fullPath.getName();
            }
        }

		public String getOutputTokenTo() {
            if (tokenFileDirectory != null && tokenFileName != null) {
                return new File(getTokenFileDirectory(), getTokenFileName()).toString();
            }

            return null;
		}

        public boolean isOk() {
            return getOutputTokenTo() != null && getPogFilePath() != null && getPortraitFilePath() != null;
        }

        public String getTokenFileName() {
            if (tokenFileName == null) {
                tokenFileName = generateDefaultTokenFileName();
            }

            return tokenFileName;
        }

        private String generateDefaultFileName() {
            return generateCleanFileName(characterName);
        }

        private String generateCleanFileName(String name) {
            if (name == null || name.length() == 0) {
                return null;
            }

            return name.replaceAll("[ ,'.]", "");
        }

        private String generateDefaultTokenFileName() {
            String cleanName =  generateCleanFileName(characterName);
            
            if (cleanName != null) {
                return cleanName + TOKEN_FILE_EXTENSION;
            }

            return null;
        }

        public void setTokenFileName(String tokenFileName) {
            if ( tokenFileName != null && ! tokenFileName.endsWith(TOKEN_FILE_EXTENSION)) {
                tokenFileName = tokenFileName + TOKEN_FILE_EXTENSION;
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

        private void populateWithDefaultValues() {
            System.out.println("Attempting to default " + characterName);
            if (getTokenFileDirectory() == null) {
                setTokenFileDirectory(config.getOutputTokenDirectory());
                resetDefaultTokenFilename();

                System.out.println("    Token will output to: " + getOutputTokenTo());
            }

            String defaultFileName = generateDefaultFileName();
            if (getPortraitFileDirectory() == null) {
                File portraitFile = findImageFile(config.getPortraitDirectory(), defaultFileName);

                if (portraitFile != null) {
                    setPortraitFilePath(portraitFile.getPath());
                    System.out.println("    Using Portrait File: " + getPortraitFilePath());
                }

                System.out.println("    No Portrait File Found");
            }

            if (getPogFileDirectory() == null) {
                File pogFile = findImageFile(config.getPogDirectory(), defaultFileName);

                if (pogFile != null) {
                    setPogFilePath(pogFile.getPath());
                    System.out.println("    Using Pog File: " + getPogFilePath());
                }
                System.out.println("    No Pog File Found");
            }
        }

        private File findImageFile(final String directory, final String defaultFileName) {
            File searchDirectory = new File(directory);
            
            if(searchDirectory.isDirectory()) {
                Iterator<File> files = FileUtils.iterateFiles(
                        new File(directory),
                        new IOFileFilter() {
                            private boolean nameMatches(String fileName) {
                                if (IMAGE_EXTENSION_PREDICATE.evaluate(fileName)) {
                                    String[] components = fileName.split("\\.");
                                    if (components.length > 0) {
                                        String cleanFileName = generateCleanFileName(components[0]);
                                        return defaultFileName.toLowerCase().equals(cleanFileName.toLowerCase());
                                    }
                                }

                                return false;
                            }

                            public boolean accept(File file) {
                                return nameMatches(file.getName());
                            }

                            public boolean accept(File file, String fileName) {
                                return nameMatches(fileName);
                            }
                        },
                        null
                );

                if (files.hasNext()) {
                    return files.next();
                }
            }

            return null;
        }

        public String getPogFileName() {
            return pogFileName;
        }
    }
}


