package net.sozinsoft.tokenlab;


import java.io.*;
import java.util.HashMap;

public class WeaponCache {

    public class Entry {
        String WeaponType;

        public boolean isSimpleWeapon() {
            return WeaponType.equals( "Simple");
        }

        public boolean isMartialWeapon() {
            return WeaponType.equals( "Martial");
        }

        public boolean isExoticWeapon() {
            return WeaponType.equals( "Exotic");
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

        String WeaponEffort;
        String Weapon;
        String finessable;
        String Cost;
        String DamageSmall;
        String DamageMedium;
        String crit;
        String range;
        String Weight;
        String Type;
        String Special;
        String Source;

        public Entry(String WeaponType , String WeaponEffort, String Weapon, String finessable, String Cost,
                     String DamageSmall, String DamageMedium, String crit, String range, String Weight,
                     String Type , String Special , String Source ) {
            this.WeaponType = WeaponType;
            this.WeaponEffort = WeaponEffort;
            this.Weapon = Weapon;
            this.finessable = finessable;
            this.Cost = Cost;
            this.DamageSmall = DamageSmall;
            this.DamageMedium = DamageMedium;
            this.crit = crit;
            this.range = range;
            this.Weight = Weight;
            this.Type = Type;
            this.Special = Special;
            this.Source = Source;
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
            if( entries[7] == null  ) {
                continue;
            }
            Entry e = new Entry( entries[0], entries[1], entries[2], entries[3], entries[4], entries[5],
                                 entries[6], entries[7], null, null, null, null, null );
            cache.put( e.Weapon.toLowerCase(), e );
        }

        in.close();
    }
}
