package net.sozinsoft.tokenlab;


import java.io.*;
import java.util.HashMap;

public class WeaponCache {

    public class Entry {


        public boolean isSimpleWeapon() {
            return WeaponType.equals( "Simple");
        }

        public boolean isMartialWeapon() {
            return WeaponType.equals( "Martial");
        }

        public boolean isExoticWeapon() {
            return WeaponType.equals( "Exotic");
        }

        public boolean isNaturalWeapon() {
            return WeaponType.equals("Natural");
        }

        public boolean isLightWeapon() {
            return this.WeaponEffort.equals( "Light Melee Weapons");
        }

        public boolean isOneHandedMeleeWeapon() {
            return WeaponEffort.equals("One-Handed Melee Weapons");
        }

        public boolean isTwoHandedMeleeWeapon() {
            return WeaponEffort.equals("Two-Handed Melee Weapons");
        }

        public boolean isRangedWeapon() {
            return WeaponEffort.equals("Ranged Weapons");
        }


        public boolean isAmmunition() {
            return WeaponEffort.equals("Ammunition");
        }

        public boolean isFinessableOneHandedMeleeWeapon() {
            return this.finessable.equals("1");
        }

        String WeaponType;
        String WeaponEffort;
        String Weapon;
        String finessable;

        public Entry(String WeaponType , String WeaponEffort, String Weapon, String finessable ) {
            this.WeaponType = WeaponType;
            this.WeaponEffort = WeaponEffort;
            this.Weapon = Weapon;
            this.finessable = finessable;
        }
    }

    private HashMap<String, Entry> cache = new HashMap<String, Entry>();
    public Entry get( String key ) {
        return cache.get( key.toLowerCase() );
    }

    public WeaponCache( String cacheFileName ) throws IOException {
        FileInputStream fstream = new FileInputStream( cacheFileName );
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        //skip the header
        String strLine = br.readLine();

        while ((strLine = br.readLine()) != null)   {
            String[] entries = strLine.split("\\t");
            Entry e = new Entry( entries[0], entries[1], entries[2], entries[3] );
            cache.put( e.Weapon.toLowerCase(), e );
        }

        in.close();
    }
}
