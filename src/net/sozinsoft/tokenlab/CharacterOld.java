package net.sozinsoft.tokenlab;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class CharacterOld {

    public static final String HEROLABS_FIREARM_PROJECTILE_WEAPON = "Firearm, Projectile Weapon";
    public static final String HEROLABS_PROJECTILE_WEAPON = "Projectile Weapon";
    public static final String HEROLABS_MELEE_WEAPON = "Melee Weapon";
    public static final String HEROLABS_WEAPON_OFFHAND = "offhand";
    public static final String HEROLABS_WEAPON_MAINHAND = "mainhand";
    public static final String HEROLABS_WEAPON_TWOHAND = "bothhands";
    public static final String HEROLABS_NPC = "npc";
    public static final String HEROLABS_CONSTITUTION = "Constitution";
    public static final String HEROLABS_WEAPON_FINESSE_FEAT = "Weapon Finesse";
    public static final String HEROLABS_WEAPON_FOCUS_FEAT = "Weapon Focus";
    public static final String HEROLABS_WEAPON_SPECIALIZATION_FEAT = "Weapon Specialization";
    public static final String HEROLABS_BOOLEAN_YES = "yes";
    public static final String HEROLABS_UNARMED_STRIKE = "Unarmed Strike";

    public static final String SIMPLE_WEAPON_PROFICIENCY = "Simple Weapon Proficiency";
    public static final String HEROLABS_SIMPLE_WEAPON_PROFICIENCY_ALL_FEAT =
            SIMPLE_WEAPON_PROFICIENCY + " - All";
    public static final String MARTIAL_WEAPON_PROFICIENCY = "Martial Weapon Proficiency";
    public static final String MARTIAL_WEAPON_PROFICIENCY_ALL_FEAT =
            MARTIAL_WEAPON_PROFICIENCY + " - All";
    public static final String EXOTIC_WEAPON_PROFICIENCY = "Exotic Weapon Proficiency";
    public static final String TWO_WEAPON_FIGHTING_FEAT = "Two-weapon Fighting";

    private String _name;
    private String _race;
    private String _hitpoints;
    private String _role;

    public boolean isNPC() {
        return getRole().equals(HEROLABS_NPC);
    }

    private final HashMap<String, CharacterAttribute> cattributes = new HashMap<String, CharacterAttribute>();
    private String _level;
    private String _classSummaryAbbreviation;
    private String _size;
    private String _deity;
    private String _playername;
    private String _alignment;
    private String _gender;
    private String _age;
    private String _weight;
    private String _height;
    private String _speed;

    public String toString() {
        return this._name;
    }

    public HashMap<String, CharacterAttribute> getAttributes() {
        return cattributes;
    }

    public String getRace() {
        return _race;
    }

    public String getHitpoints() {
        return _hitpoints;
    }

    public String getRole() {
        return _role;
    }

    public String getLevel() {
        return _level;
    }

    public String getClassSummaryAbbreviation() {
        return _classSummaryAbbreviation;
    }

    public String getSize() {
        return _size;
    }

    public String getDeity() {
        return _deity;
    }

    public String getPlayername() {
        return _playername;
    }

    public String getAlignment() {
        return _alignment;
    }

    public String getGender() {
        return _gender;
    }

    public String getAge() {
        return _age;
    }

    public String getWeight() {
        return _weight;
    }

    public String getHeight() {
        return _height;
    }

    public String getSpeed() {
        return _speed;
    }

    public Initiative getInitiative() {
        return _initiative;
    }

    public String getBaseAttackBonus() {
        return _baseAttackBonus;
    }

    public void set_baseAttackBonus(String _baseAttackBonus) {
        this._baseAttackBonus = _baseAttackBonus;
    }



    private class CharacterClass {
        public String ClassName;
        public String ClassLevel;
    }

    public void setLevel(String level, String classSummaryAbbreviation) {
        this._level = level;
        this._classSummaryAbbreviation = classSummaryAbbreviation;
    }

    private final LinkedList<CharacterClass> classes = new LinkedList<CharacterClass>();

    public void addClass(String name, String level) {
        CharacterClass c = new CharacterClass();
        c.ClassName = name;
        c.ClassLevel = level;
        classes.add(c);
    }

    public class Save {
        public String name;
        public String base;
        public String frommisc;
        public String fromattr;
        public String fromresist;
        public String save;
        public String abbr;
    }

    private HashMap<String, Save> saves = new HashMap<String, Save>();

    public HashMap<String, Save> getSaves() {
        return saves;
    }

    public void addSave(String name, String abbr, String base, String frommisc,
                        String fromresist, String fromattr, String save) {
        Save s = new Save();
        s.name = name;
        s.abbr = abbr;
        s.base = massageBonusValue(base);
        s.frommisc = massageBonusValue(frommisc);
        s.fromresist = massageBonusValue(fromresist);
        s.fromattr = massageBonusValue(fromattr);
        s.save = massageBonusValue(save);
        saves.put(s.name, s);
    }

    public class ArmorClass {
        public String touchAC;
        public String flatFootedAC;
        public String AC;
        public String frommisc;
        public String fromdodge;
        public String fromdeflect;
        public String fromnatural;
        public String fromsize;
        public String fromdexterity;
        public String fromshield;
        public String fromarmor;
    }

    private ArmorClass _armorClass;

    public CharacterOld.ArmorClass getArmorClass() {
        return _armorClass;
    }

    private String _baseAttackBonus;


    public boolean hasSimpleWeaponProficiency( Weapon w) {
        return _feats.containsKey(HEROLABS_SIMPLE_WEAPON_PROFICIENCY_ALL_FEAT) ||
               _feats.containsKey(SIMPLE_WEAPON_PROFICIENCY + ": " + w.basicName );
    }

    public boolean hasMartialWeaponProficiency( Weapon w ) {
        return _feats.containsKey(MARTIAL_WEAPON_PROFICIENCY_ALL_FEAT) ||
               _feats.containsKey(MARTIAL_WEAPON_PROFICIENCY + ": " + w.basicName );
    }

    public boolean hasExoticWeaponProficiency( Weapon w ) {
        return _feats.containsKey(EXOTIC_WEAPON_PROFICIENCY + ": " + w.basicName );
    }

    public boolean hasTwoWeaponFightingFeat( ) {
        return _feats.containsKey(TWO_WEAPON_FIGHTING_FEAT);
    }


    public boolean hasWeaponFinesseFeat() {
        return _feats.containsKey(HEROLABS_WEAPON_FINESSE_FEAT);
    }

    public boolean hasWeaponFocus(String weaponName) {
        for (String f : this._feats.keySet()) {
            if (hasWeaponFeat(f, HEROLABS_WEAPON_FOCUS_FEAT, weaponName)) { //TODO - this is a bit of a hack...
                return true;
            }
        }
        return false;
    }

    public boolean hasWeaponSpecialization(String weaponName) {
        for (String f : this._feats.keySet()) {
            if (hasWeaponFeat(f, HEROLABS_WEAPON_SPECIALIZATION_FEAT, weaponName)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasWeaponFeat(String feat, String featName, String weaponName) {

        Pattern regex = Pattern.compile("^.*?(\\w+)$");
        java.util.regex.Matcher matcher = regex.matcher(weaponName);
        if (matcher.find()) {
            String endOfWeaponName = matcher.group(1);
            return feat.indexOf(featName) >= 0 && feat.indexOf(endOfWeaponName) > 0;
        }
        return false;
    }

    public CharacterAttribute getConstitution() {
        return cattributes.get(HEROLABS_CONSTITUTION);
    }


    public void setAttackBonus(String bab) {
        this.set_baseAttackBonus(CharacterAttribute.replacePlus(bab));
    }

    public void addAttribute(CharacterAttribute ca) {
        //for some reason it is always reusing the same instance of the ca ... so a little cheaty hack here
        CharacterAttribute copy = new CharacterAttribute(ca);
        cattributes.put(ca.getName(), copy);
    }

    public class Feat {
        String featName;
        String useradded;
        String profgroup;
        String categorytext;
        String description;
        String featCategory;

        public Feat(String featName, String useradded, String profgroup, String categorytext, String description, String featCategory) {
            this.featName = featName;
            this.useradded = useradded;
            this.profgroup = profgroup;
            this.categorytext = categorytext;
            this.description = description;
            this.featCategory = featCategory;
        }
    }

    private HashMap<String, Feat> _feats = new HashMap<String, Feat>();

    public HashMap<String, Feat> getFeats() {
        return _feats;
    }

    public void addFeat(String featName, String useradded, String profgroup, String categorytext, String description, String featCategory) {
        Feat f = new Feat(featName, useradded, profgroup, categorytext, description, featCategory);
        _feats.put(featName, f);
    }

    public class Armor {

        public static final String SHIELD = "Shield";
        String name;
        String ac;
        String equipped;
        String natural;
        String useradded;

        public Armor(String name, String ac, String equipped, String natural, String useradded) {
            this.name = name;
            this.ac = ac;
            this.equipped = equipped;
            this.natural = natural;
            this.useradded = useradded;
        }

        public boolean isShield() {
            return name.indexOf(SHIELD) >= 0;    //TODO: this won't work for shields that don't have 'shield' in the name, alas
        }

        public boolean isEquipped() {
            return equipped != null && !equipped.isEmpty() && equipped.equals(HEROLABS_BOOLEAN_YES);
        }

        public boolean isNatural() {
            return natural != null && !natural.isEmpty() && natural.equals(HEROLABS_BOOLEAN_YES);
        }
    }

    private HashMap<String, Armor> _armors = new HashMap<String, Armor>();

    public void addArmor(String name, String ac, String equipped, String natural, String useradded) {
        Armor a = new Armor(name, ac, equipped, natural, useradded);
        _armors.put(name, a);
    }

    public boolean hasShieldEquipped() {
        for (Armor a : _armors.values()) {
            if (a.isShield() && a.isEquipped()) {
                return true;
            }
        }
        return false;
    }

    private HashMap<String, Weapon> weapons = new HashMap<String, Weapon>();

    public HashMap<String, Weapon> getWeapons() {
        return weapons;
    }


    public void addWeapon(String name, String damage, String categorytext, String crit,
                          String attackBonus, String equipped, String weaponType, String description) {
        Weapon w = new Weapon(name, damage, categorytext, crit, attackBonus, equipped, weaponType, description);
        weapons.put(w.name, w);
    }

    public void setArmorClassBasics(String touchAC, String flatFootedAC, String armorClass) {
        if (_armorClass == null) {
            this._armorClass = new ArmorClass();
        }
        _armorClass.AC = armorClass;
        _armorClass.touchAC = touchAC;
        _armorClass.flatFootedAC = flatFootedAC;
    }

    public void setArmorClassFroms(String frommisc, String fromdodge, String fromdeflect, String fromnatural, String fromsize,
                                   String fromdexterity, String fromshield, String fromarmor) {
        if (_armorClass == null) {
            this._armorClass = new ArmorClass();
        }
        _armorClass.frommisc = massageBonusValue(frommisc);
        _armorClass.fromdodge = massageBonusValue(fromdodge);
        _armorClass.fromdeflect = massageBonusValue(fromdeflect);
        _armorClass.fromnatural = massageBonusValue(fromnatural);
        _armorClass.fromsize = massageBonusValue(fromsize);
        _armorClass.fromdexterity = massageBonusValue(fromdexterity);
        _armorClass.fromshield = massageBonusValue(fromshield);
        _armorClass.fromarmor = massageBonusValue(fromarmor);
    }


    private String massageBonusValue(String frommisc) {
        return frommisc.isEmpty() ? "0" : CharacterAttribute.replacePlus(frommisc);
    }


    public void setName(String _name) {
        this._name = _name;
    }

    public String getName() {
        return _name;
    }

    public void setRace(String race) {
        this._race = race;
    }

    public void setHitpoints(String hitpoints) {
        this._hitpoints = hitpoints;
    }

    public void setRole(String role) {
        this._role = role;
    }

    public class Skill {
        private static final String YES = "yes";
        String skillName;
        String value;
        String attrName;
        String attrBonus;
        String ranks;
        String description;
        boolean isClassSkill = false;
        boolean useTrainedOnly = false;

        public Skill(String skillName, String value, String attrName,
                     String attrBonus, String ranks, String classSkill, String trainedOnly, String description) {
            this.skillName = skillName;
            this.value = value;
            this.attrName = attrName;
            this.attrBonus = attrBonus;
            this.ranks = ranks;
            this.description = description;
            if (classSkill != null && !classSkill.isEmpty() && classSkill.equals(YES)) {
                isClassSkill = true;
            }
            if (trainedOnly != null && !trainedOnly.isEmpty() && trainedOnly.equals(YES)) {
                useTrainedOnly = true;
            }

        }

    }

    private HashMap<String, Skill> skills = new HashMap<String, Skill>();

    public HashMap<String, Skill> getSkills() {
        return skills;
    }

    public void addSkill(String skillName, String value, String attrName, String attrBonus,
                         String ranks, String isClassSkill, String useTrainedOnly, String description) {
        Skill s = new Skill(skillName, value, attrName, attrBonus, ranks, isClassSkill, useTrainedOnly, description);
        skills.put(skillName, s);
    }

    public class Initiative {
        String total;
        String attrName;
        String misctext;
        String attrtext;

        public Initiative(String total, String attrName, String misctext, String attrtext) {
            this.total = total;
            this.attrName = attrName;
            this.misctext = misctext;
            this.attrtext = attrtext;
        }

        public int initModifier() {
            int itotal = Integer.parseInt(CharacterAttribute.replacePlus((total)));
            int imisc = Integer.parseInt(CharacterAttribute.replacePlus(misctext));
            int iattr = Integer.parseInt(CharacterAttribute.replacePlus(attrtext));
            if (imisc > 0 || iattr > 0) {
                return itotal - iattr - imisc;
            } else return 0;
        }
    }

    private Initiative _initiative = null;

    public void addInitiative(String total, String attrName, String misctext, String attrtext) {
        _initiative = new Initiative(total, attrName, misctext, attrtext);
    }

    public class Penalty {

        public String name;
        public String value;
        public String text;

        public Penalty(String name, String value, String text) {
            this.name = name;
            this.value = value;
            this.text = text;
        }
    }

    private HashMap<String, Penalty> penalties = new HashMap<String, Penalty>();

    public Penalty getPenalty(String penaltyName) {
        return penalties.get(penaltyName);
    }

    public void addPenalty(String name, String value, String text) {
        Penalty p = new Penalty(name, value, text);
        penalties.put(name, p);
    }

    public void addSize(String name) {
        this._size = name;
    }

    public void addDeity(String name) {
        this._deity = name;
    }

    public void addPlayerName(String playername) {
        this._playername = playername;
    }

    public String getPlayerName() {
        return _playername;
    }

    public void addAlignment(String alignment) {
        this._alignment = alignment;
    }

    public void addPersonal(String gender, String age, String height, String weight) {
        this._gender = gender;
        this._age = age;
        this._height = height;
        this._weight = weight;
    }

    public void addMovement(String speed) {
        this._speed = speed;
    }


    private HashMap< String, SpellClass > _spellClasses = new HashMap<String, SpellClass>();
    public void addSpellClass( String name, String spells, String maxSpellLevel,
                               String level, String used, String unlimited, String maxcasts) {

        SpellClass sc = new SpellClass(name, spells, Integer.parseInt(maxSpellLevel) );

        int iLevel = Integer.parseInt(level);
        int iUsed  = Integer.parseInt(used);
        boolean isUnlimited = unlimited != null && unlimited.equals(HEROLABS_BOOLEAN_YES) ? true : false;
        int maxCasts = maxcasts != null ? Integer.parseInt(maxcasts) : -1;
        SpellLevel sl = new SpellLevel( iLevel, iUsed,  isUnlimited, maxCasts );
        sc.addSpellLevel ( sl );
        _spellClasses.put( sc.spellClassName, sc );


    }
    //spells
    public class SpellLevel {
        public int level;
        public int used;
        public boolean isUnlimited;
        public int maxCasts;

        public SpellLevel( int level, int used, boolean isUnlimited, int maxCasts) {
            this.level = level;
            this.used = used;
            this.isUnlimited = isUnlimited;
            this.maxCasts = maxCasts;
        }
    }
    public class SpellClass {
        public String spellClassName;
        public String spellCasterType;
        public int maxSpellLevel;
        public HashMap<Integer, SpellLevel> spellLevels = new HashMap<Integer, SpellLevel>();

        public SpellClass(String spellClassName, String spellCasterType, int maxSpellLevel ) {
            this.spellCasterType = spellCasterType;
            this.spellClassName = spellClassName;
            this.maxSpellLevel = maxSpellLevel;
        }

        public void addSpellLevel( SpellLevel sl ) {
            spellLevels.put( sl.level, sl );

        }
    }

    class Spell {
          String name;
          int level;
          int casterlevel;
          String save;
          boolean spontaneous;
          String descriptortext;
          String subschooltext;
          String schooltext;
          String componenttext;
          int difficultyClass;
          String resist;
          String duration;
          String effect;
          String area;
          String target;
          String range;
          String casttime;
          String spellclass;
          String description;

        public Spell(String name, int level, int casterlevel, String save, boolean spontaneous,
                     String descriptortext, String subschooltext, String schooltext, String componenttext,
                     int dc, String resist, String duration, String effect, String area, String target,
                     String range, String casttime, String spellclass, String description) {
            this.name = name;
            this.level = level;
            this.casterlevel = casterlevel;
            this.save = save;
            this.spontaneous = spontaneous;
            this.descriptortext = descriptortext;
            this.subschooltext = subschooltext;
            this.schooltext = schooltext;
            this.componenttext = componenttext;
            this.difficultyClass = dc;
            this.resist = resist;
            this.duration = duration;
            this.effect = effect;
            this.area = area;
            this.target = target;
            this.range = range;
            this.casttime = casttime;
            this.spellclass = spellclass;
            this.description = description;

        }


    }

    public HashMap<String, Spell> spells = new HashMap<String, Spell>();
    public void addSpell( String name, String level, String casterlevel, String save, String spontaneous,
                          String descriptortext, String subschooltext, String schooltext, String componenttext,
                          String dc,String resist,  String duration, String effect, String area , String target,
                          String range, String casttime, String spellclass, String description ) throws Exception {
        boolean isSpontaneous = spontaneous != null && spontaneous.equals(HEROLABS_BOOLEAN_YES);
        Spell spell = new Spell( name,
                                 Integer.parseInt(level),
                                 Integer.parseInt( casterlevel ),
                                 save,
                                 isSpontaneous,
                                 descriptortext,
                                 subschooltext,
                                 schooltext,
                                 componenttext,
                                 Integer.parseInt(dc),
                                 resist,
                                 duration,
                                 effect,
                                 area,
                                 target,
                                 range,
                                 casttime,
                                 spellclass,
                                 description );

        spells.put( spell.name, spell );

    }


}
