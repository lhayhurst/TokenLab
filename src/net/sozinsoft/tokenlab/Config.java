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
    public static final Predicate IMAGE_EXTENSION_PREDICATE = new Predicate() {
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

    private HashMap<String, ConfigEntry> configs;
    private String xmlFileLocation;
	private String configFileName;
    private transient Preferences prefs;


    public Config( Preferences prefs ) throws IOException {
        this(prefs, prefs.get(TOKENLAB_CHARACTER_MAPPINGS, ""));
    }

    /**
     * Mostly for testing migrations from older config file versions.
     */
    public Config( Preferences prefs, String configFileLocation) throws IOException {
        this.prefs = prefs;
        xmlFileLocation = configFileLocation;
        XStream xstream = new XStream();
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

        postProcessConfigEntriesAfterLoad();

        System.out.println("Loaded " + configs.size() + " Config Entries.");
    }

    /**
     * This handles two issues loading from the config file:  
     *     1)  XStream loaded ConfigEntries will not have their reference to the parent config set.
     *     2)  Migration from prior versions of a config file will be broken, as XStream (per Mr. Walnes)
     *      does not handle model changes elegantly.  See ConfigEntry.postProcessAfterLoad.
     */
    private void postProcessConfigEntriesAfterLoad() {
        for (ConfigEntry entry : configs.values()) {
            entry.postProcessAfterLoad(this);
        }
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

	public ConfigEntry addConfigEntry( String name, String pogPath, String portraitPath, String tokenPath) {
        ConfigEntry ce = new ConfigEntry(this);
        ce.setCharacterName(name);
        ce.setPogFilePath(pogPath);
        ce.setPortraitFilePath(portraitPath);
        ce.setTokenFilePath(tokenPath);
		configs.put( name, ce);
        return ce;
	}

    // TODO: is this actually used by something?
	public void parseConfigFile() throws IOException, SAXException {
        Digester d = new Digester();
        d.push( this );

        d.addCallMethod("config/token", "addConfigEntry", 4 );
        d.addCallParam("config/token", 0, "name" );
        d.addCallParam("config/token", 1, "pogFileDirectory" );
        d.addCallParam("config/token", 2, "portraitFileDirectory" );
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

    public void populateCharacterWithDefaults(String characterName, boolean forceDefault) {
        ConfigEntry entry = getOrCreate(characterName);

        entry.populateWithDefaultValues(forceDefault);
    }



    public class ConfigEntry {
		
    	private String characterName;
    	private DefaultableFilePath pogPath;
    	private DefaultableFilePath portraitPath;
        private DefaultableFilePath tokenPath;

        // Don't want this serialized/deserialized with the config
        private transient Config config;

        // Old Attributes - these can eventually be deleted.
        // Here to prevent crashing during deserialization from previous structure
        private String CharacterName;
        private String imageFilePath;
        private String portraitFilePath;
        private String tokenFileDirectory;
        private String tokenFileName;
        private String outputTokenTo;
       // END Old Attributes


        ConfigEntry(Config config) {
            this.config = config;
            
            pogPath = new DefaultableFilePath();
            portraitPath = new DefaultableFilePath();
            tokenPath = new DefaultableFilePath();
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

        public String getPogFilePath() {
            return pogPath.getFilePath();
        }

        public void setPogFilePath(String path) {
            pogPath.setFilePath(path);
        }

        public String getPogDirectory() {
            return pogPath.getDirectory();
        }

        public String getPortraitFilePath() {
            return portraitPath.getFilePath();
        }

        public void setPortraitFilePath(String path) {
            portraitPath.setFilePath(path);
        }

        public String getPortraitDirectory() {
            return portraitPath.getDirectory();
        }

		public String getOutputTokenPath() {
            return tokenPath.getFilePath();
		}

        public boolean isOk() {
            return getOutputTokenPath() != null && getPogFilePath() != null && getPortraitFilePath() != null;
        }

        public String getTokenFileName() {
            if (tokenPath.getFileName() == null) {
                tokenPath.setFileName(generateDefaultTokenFileName());
            }

            return tokenPath.getFileName();
        }

        private String generateDefaultFileName() {
            return generateCleanFileName(characterName);
        }

        private String generateCleanFileName(String name) {
            if (name == null || name.length() == 0) {
                return null;
            }

            return name.replaceAll("[ ,'._]", "");
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

            tokenPath.setFileName(tokenFileName);
        }

        public String getTokenFileDirectory() {
            return tokenPath.getDirectory();
        }

        public String getTokenFilePath() {
            return tokenPath.getFilePath();
        }
        public void setTokenFilePath(String path) {
            tokenPath.setFilePath(path);
        }

        public void defaultTokenPath() {
            tokenPath.setFilePath(config.getOutputTokenDirectory(), generateDefaultTokenFileName(), false);
        }

        private void populateWithDefaultValues(boolean forceDefault) {
            System.out.println("Attempting to default " + characterName);
            if (forceDefault || !tokenPath.isOverridden()) {
                defaultTokenPath();
                System.out.println("    Token will output to: " + getOutputTokenPath());
            }

            String defaultFileName = generateDefaultFileName();
            if (forceDefault || !portraitPath.isOverridden()) {
                File portraitFile = findImageFile(config.getPortraitDirectory(), defaultFileName);

                if (portraitFile != null) {
                    portraitPath.setFilePath(portraitFile, false);
                    System.out.println("    Using Portrait File: " + getPortraitFilePath());
                }

                System.out.println("    No Portrait File Found");
            }

            if (forceDefault || !pogPath.isOverridden()) {
                File pogFile = findImageFile(config.getPogDirectory(), defaultFileName);

                if (pogFile != null) {
                    pogPath.setFilePath(pogFile, false);
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

        /**
         * Ugly little method to handle XStream migrations and avoid the crashy.
         * Once we settle in on a 1.0 and stabilize the Config structure, this can all hopefully go away.
         */
        public void postProcessAfterLoad(Config config) {
            this.config = config;
            if (characterName == null) {
                characterName = CharacterName;
            }

            portraitPath = checkDefaultablePath(portraitPath, portraitFilePath, null);
            pogPath = checkDefaultablePath(pogPath, imageFilePath, null);
            tokenPath = checkDefaultablePath(tokenPath, tokenFileDirectory, tokenFileName);
            
            // Null out old attributes - these should never again be serialized
            CharacterName = null;
            imageFilePath = null;
            portraitFilePath = null;
            tokenFileDirectory = null;
            tokenFileName = null;
            outputTokenTo = null;
        }

        /**
         * Also an artifact of config migration.  Can go away once configs are locked in.
         */
        private DefaultableFilePath checkDefaultablePath(DefaultableFilePath oldDFP, String filePath, String fileName) {
            DefaultableFilePath newDFP = oldDFP; 
            if (newDFP == null) {
                newDFP = new DefaultableFilePath();
                if (filePath != null) {
                    if (fileName != null) {
                        newDFP.setFileName(fileName);
                        newDFP.setDirectory(filePath);
                    } else {
                        newDFP.setFilePath(filePath);
                    }
                }
            }
            
            return newDFP;
        }

        public Config getConfig() {
            return config;
        }
    }
    
    private class DefaultableFilePath {
        private String directory;
        private String fileName;
        private boolean overridden;
        
        String getFilePath() {
            if (directory != null && fileName != null) {
                return new File(directory, fileName).getPath();
            }

            return null;
        }

        void setFilePath(String path) {
            if (path != null) {
                File fullPath = new File(path);

                setFilePath(fullPath, true);
            }
        }
        
        void setFilePath(File fullPath, boolean isOverride) {
            setFilePath(fullPath.getParent(), fullPath.getName(), isOverride);
        }
        
        void setFilePath(String directory, String file, boolean isOverride) {
            this.directory = directory;
            fileName = file;
            overridden = isOverride;
        }

        String getFileName() {
            return fileName;
        }

        void setFileName(String fileName) {
            this.fileName = fileName;
        }

        String getDirectory() {
            return directory;
        }

        boolean isOverridden() {
            return overridden;
        }

        void setDirectory(String directory) {
            this.directory = directory;
        }
    }
}


