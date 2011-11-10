package net.sozinsoft.tokenlab;


import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ResourceManager {
    static final String RES_TOKEN_LAB_MACRO_SET_MTMACSET = "res/TokenLabMacroSet.mtmacset";
    static final String WEAPONS_CACHE                    = "res/Weapons.txt";
    private static ResourceManager resourceManager = new ResourceManager();

     public static File getMacroSet() throws IOException {
         return new File(resourceManager.getClass().getResource(RES_TOKEN_LAB_MACRO_SET_MTMACSET).getFile() );
     }

     public static File getWeapons() throws IOException {
         URL resource = resourceManager.getClass().getResource( WEAPONS_CACHE );
         File f = new File( resource.getFile() );
         return f;
    }
}
