package net.sozinsoft.tokenlab;


import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.LinkedList;

public class ResourceManager {
    static final String RES_TOKEN_LAB_MACRO_SET_MTMACSET = "res/TokenLabMacroSet.mtmacset";
    static final String WEAPONS_CACHE                    = "res/Weapons.txt";
    static final String WEAPONS_MACRO                    = "res/AttackMacroBasic.txt";

    static LinkedList<File> _tmpFiles = new LinkedList<File>();

    private static ResourceManager resourceManager = new ResourceManager();
    public static final String RES_MACROS_MACROS_XML = "res/macros/macros.xml";

    public static File getMacroSet() throws IOException {
         return getResouceByName(RES_TOKEN_LAB_MACRO_SET_MTMACSET);
     }

    public static void cleanupTmpFiles() {
        for ( File f : _tmpFiles ) {
            f.delete();
        }
    }

    public static File getResouceByName( String name ) throws IOException {

        URL url = resourceManager.getClass().getResource(name);
        //when you run in a jar file, you get a strange jar path out, so ... alas ...
        //do some temp file sillyness.
        InputStream is = url.openStream();
        File tmpFile = File.createTempFile("tokenlab", ".tmp");
        OutputStream out = new FileOutputStream(tmpFile);
        byte buf[] = new byte[1024];
        int len;
        while ((len = is.read(buf)) > 0)
            out.write(buf, 0, len);
        out.close();
        is.close();

        _tmpFiles.add(tmpFile);

        return tmpFile.getAbsoluteFile();
    }

    public static File getWeapons() throws IOException {
         return getResouceByName(WEAPONS_CACHE);
    }

     public static File getWeaponMacro() throws IOException {
         return getResouceByName(WEAPONS_MACRO);
     }

     public static File getMacroConfigFile() throws IOException {
         return getResouceByName(RES_MACROS_MACROS_XML);
     }
}
