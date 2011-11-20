package net.sozinsoft.tokenlab;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SpellDB {

    private HashMap<String, PFSRDSpell> cache = new HashMap<String, PFSRDSpell>();
    public SpellDB( String spellDBFilePath ) throws IOException {
        FileInputStream fstream = new FileInputStream( spellDBFilePath );
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        //skip the header
        String strLine = br.readLine();

        while ((strLine = br.readLine()) != null)   {
            String[] entries = strLine.split("\\t");

            int i = 0;
            for ( String e : entries ) {
                String noQuote = entries[i].replaceAll("\"", "" );
                entries[i] = noQuote;
                i++;
            }

            PFSRDSpell e = new PFSRDSpell( entries );
            cache.put( e.name.toLowerCase(), e );
        }

        in.close();
    }

    public PFSRDSpell getSpell( String name ) {
        return cache.get(name.toLowerCase());
    }
}
