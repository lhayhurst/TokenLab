package net.sozinsoft.tokenlab;


import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ResourceManager {
    static final String RES_TOKEN_LAB_MACRO_SET_MTMACSET = "res/TokenLabMacroSet.mtmacset";
    static final String WEAPONS_CACHE                    = "res/Weapons.txt";
    static final String WEAPONS_MACRO                    = "res/AttackMacroBasic.txt";

    private static ResourceManager resourceManager = new ResourceManager();
    public static final String RES_MACROS_MACROS_XML = "res/macros/macros.xml";

    public static File getMacroSet() throws IOException {
         return getResouceByName(RES_TOKEN_LAB_MACRO_SET_MTMACSET);
     }

    public static File getResouceByName( String name ) {
        return new File(resourceManager.getClass().getResource(name).getFile() );
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
