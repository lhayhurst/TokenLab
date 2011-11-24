package net.sozinsoft.tokenlab;


public class PFSRDSpell {

    public String name;
    public String school;
    public String subschool;
    public String descriptor;
    public String spell_level;
    public String casting_time;
    public String components;
    public String costly_components;
    public String range;
    public String area;
    public String effect;
    public String targets;
    public String duration;
    public String dismissible;
    public String shapeable;
    public String saving_throw;
    public String spell_resistence;
    public String description;
    //public String description_formated;
    public String source;
   // public String full_text;
    public String verbal;
    public String somatic;
    public String material;
    public String focus;
    public String divine_focus;
    public String sor;
    public String wiz;
    public String cleric;
    public String druid;
    public String ranger;
    public String bard;
    public String paladin;
    public String alchemist;
    public String summoner;
    public String witch;
    public String inquisitor;
    public String oracle;
    public String antipaladin;
    public String magus;
    public String deity;
    public String SLA_Level;
    public String domain;
    public String short_description;

    //the below two attributes are mashups from herolabs.
    //TODO: if the below ever gets annoying, refactor all of this code into a smarter class.
    public String spellDC;
    public String casterLevel;

    public PFSRDSpell( String[] a ) {
        name = a[0]; school = a[1]; subschool = a[2]; descriptor = a[3]; spell_level = a[4]; casting_time = a[5];
        components = a[6]; costly_components = a[7]; range = a[8]; area = a[9]; effect = a[10];
        targets = a[11]; duration = a[12]; dismissible = a[13]; shapeable = a[14]; saving_throw = a[15];
        spell_resistence = a[16]; description = a[17];
        //description_formated = a[18];
        source = a[19];
        //full_text = a[20];
        verbal = a[21]; somatic = a[22]; material = a[23]; focus = a[24]; divine_focus = a[25]; sor = a[26];
        wiz = a[27]; cleric = a[28]; druid = a[29]; ranger = a[30]; bard = a[31]; paladin = a[32]; alchemist = a[33];
        summoner = a[34]; witch =a[35]; inquisitor = a[36]; oracle = a[37]; antipaladin = a[38]; magus = a[39];
        deity = a[40]; SLA_Level = a[41]; domain = a[42]; short_description = a[43];

        short_description = short_description.replace( "�", "");

    }
}
