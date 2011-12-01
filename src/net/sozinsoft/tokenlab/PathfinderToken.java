package net.sozinsoft.tokenlab;


import com.google.gson.*;
import net.rptools.maptool.model.*;
import net.rptools.maptool.util.TokenUtil;
import net.sozinsoft.tokenlab.dtd.*;
import net.sozinsoft.tokenlab.dtd.Character;
import net.sozinsoft.tokenlab.dtd.Weapon;
import org.xml.sax.SAXException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class PathfinderToken implements IPathfinderCharacter {

    private static final String CLASS_HP = "ClassHP";
    private static final String SIZE_MOD = "SizeMod";
    private static final String AC_TEMP_BONUS = "ACTempBonus";
    private static final String AC_MISC_BONUS_2 = "ACMiscBonus2";
    private static final String AC_MISC_BONUS_1 = "ACMiscBonus1";
    private static final String AC_FROM_DEFLECT = "ACDeflectBonus";
    private static final String AC_FROM_DODGE = "ACDodgeBonus";
    private static final String AC_SHIELD_BONUS = "ACShieldBonus";
    private static final String AC_ARMOR_BONUS = "ACArmorBonus";
    public static  final String AC_FROM_NATURAL = "ACNaturalBonus";
    private static final String ARMOR_MAX_DEX_BONUS = "ArmorMaxDexBonus";
    private static final String ARMOR_CHECK_PENALTY = "ArmorCheckPenalty";
    private static final String MISC_BONUS = "MiscBonus";
    private static final String DAMAGE = "damage";
    private static final String ENHANCEMENT = "Enhancement";
    private static final String CLASS_BONUS = "ClassBonus";
    private static final String RESIST_BONUS = "ResistBonus";
    private static final String CLASS_SKILL = "ClassSkill";
    private static final String PATHFINDER = "Pathfinder";
    private static final String CHARACTER = "Character";
    private static final String RACE = "Race";
    private static final String PLAYER = "Player";
    private static final String ALIGNMENT = "Alignment";
    private static final String DEITY = "Deity";
    private static final String CLASS = "Class";
    private static final String LEVEL = "Level";
    private static final String GENDER = "Gender";
    private static final String AGE = "Age";
    private static final String HEIGHT = "Height";
    private static final String WEIGHT = "Weight";
    private static final String SPEED = "Speed";
    private static final String BASE_ATTACK_BONUS = "BaseAttackBonus";
    private static final String INIT_MOD = "InitMod";
    private static MacroDigester macroDigester = null;
    public static final String VALUE = "value";
    public static final String VALUE_MODIFIER = "valueModifier";
    public static final String BONUS = "bonus";
    public static final String BONUS_MODIFIER = "bonusModifier";
    public static final String TEMP_MODIFIER = "tempModifier";
    public static final String PROFESSION = "Profession";
    public static final String YES = "yes";
    public static final String RANKS = "Ranks";
    public static final String WEAPON_JSON = "WeaponJSON";
    public static final String SPELLS_JSON = "SpellJSON";
    public static final String FEATS_JSON = "FeatJSON";
    public static final String SPELLBOOK = "Spellbook";
    public static final String MEMORIZED = "Memorized";
    public static final String SPONTANEOUS = "Spontaneous";
    public static final String SPECIAL_ABILITIES_JSON = "SpecialAbilitiesJSON";
    public static final String SENSES = "Senses";
    public static final String AURAS = "Auras";
    public static final String HEALTH = "Health";
    public static final String DAMAGE_REDUCTION = "Damage Reduction";
    public static final String DEFENSIVE = "Defensive";
    public static final String IMMUNITIES = "Immunities";
    public static final String WEAKNESSES = "Weaknesses";
    public static final String MOVEMENT = "Movement";
    public static final String SKILL_ABILITIES = "Skill Abilities";
    public static final String ATTACK = "Attack";
    public static final String SPELL_LIKE = "Spell-like";
    public static final String OTHER = "Other";
    public static final String TRAITS_JSON = "TraitsJSON";
    public static final String SKILLS_JSON = "SkillsJSON";


    private Character _character;
    private Token _token;
    private HashMap<String, Object> _propertyMap = new HashMap<String, Object>();

    public PathfinderToken(Character character ) throws Exception {
        _character = character;
        setCoreProperties();
        setAbilities();
        setSavingThrows();
        setHitpoints();
        setArmorClass();
        setSkills();
        setWeapons();
        setSpells();
        setFeats();
        setSpecialAbilities();
        setVision();
        setInitiative();

        _propertyMap.put( BASE_ATTACK_BONUS, Integer.parseInt( replacePlus( _character.getAttack().getBaseattack() ) ) );
    }

    private HashMap<String, WeaponImpl> _weapons = new HashMap<String, WeaponImpl>();
    private void setWeapons() throws Exception {
        for( Weapon weapon: this._character.getMelee().getWeapon() ) {

            WeaponImpl wimpl = new WeaponImpl(weapon.getName(), weapon.getDamage(), weapon.getCategorytext(),
                    weapon.getCrit(), weapon.getAttack(), weapon.getEquipped(),
                    weapon.getCategorytext(), weapon.getDescription());
            _weapons.put( wimpl.name, wimpl);

        }

        for( Weapon weapon: this._character.getRanged().getWeapon() ) {

            WeaponImpl wimpl = new WeaponImpl(weapon.getName(), weapon.getDamage(), weapon.getCategorytext(),
                    weapon.getCrit(), weapon.getAttack(), weapon.getEquipped(),
                    weapon.getCategorytext(), weapon.getDescription());
            _weapons.put( wimpl.name, wimpl);

        }

    }

    public HashMap<String, PFSRDSpell> getSpellsByClass( String className) {

        ClassSpells cs = _spells.get(className);
        HashMap<String, PFSRDSpell > ret = cs.getAllSpells();
        return ret;
    }

    public SortedMap< Integer, HashMap<String, PFSRDSpell>> getSpellsByClassAndLevel( String className ) {
        ClassSpells cs = _spells.get(className);
        SortedMap<Integer, HashMap<String, PFSRDSpell >> ret = cs.getAllSpellsByLevel();
        return ret;
    }


    private HashMap< String, Feat > _feats = new HashMap<String, Feat>();
    private HashMap< String, Trait> _traits = new HashMap<String, Trait>();
    public HashMap< String, Feat > getFeats() {
        return _feats;
    }
    public void setFeats() {
        for( Feat f : _character.getFeats().getFeat() ) {
            //replace newlines with html breaks for nicer printing
            _feats.put(f.getName().replaceAll(",", ""), f);
        }
        for ( Trait t : _character.getTraits().getTrait()) {
            _traits.put( t.getName().replaceAll(",", ""), t );
        }
    }

    private SortedMap< String, TreeMap<String, Special >> _specials = new TreeMap<String, TreeMap<String, Special>>();

    public  SortedMap< String, TreeMap<String, Special >> getSpecialAbilities() {
        return _specials;
    }
    public void setSpecial( String specialName, Special special ) {
        TreeMap< String, Special > smap = _specials.get( specialName );
        if ( smap == null ) {
            smap = new TreeMap<String, Special>();
            _specials.put( specialName, smap );
        }
        smap.put( special.getName(), special );
    }

    private String _vision;
    public void setVision() {
        Vision v = new Vision( _character.getSenses().getSpecial() );
        _vision = v.getVision();
    }

    private String _initMod;
    public void setInitiative() {
        String total    = replacePlus(_character.getInitiative().getTotal());
        String fromAttr = replacePlus(_character.getInitiative().getAttrtext());
        int iTotal      = Integer.parseInt(total);
        int iFromAttr   = Integer.parseInt(fromAttr);
        int iMod        = iTotal - iFromAttr;
        _initMod        = new Integer(iMod).toString();

    }

    public void setSpecialAbilities() {

        for ( Special s: _character.getSenses().getSpecial() ) {
            setSpecial(SENSES, s );
        }
        for( Special s: _character.getAuras().getSpecial() ) {
            setSpecial(AURAS, s );
        }
        for( Special s: _character.getHealth().getSpecial() ) {
            setSpecial(HEALTH, s );
        }
        for( Special s: _character.getDamagereduction().getSpecial()) {
            setSpecial(DAMAGE_REDUCTION, s );
        }
        for( Special s: _character.getDefensive().getSpecial()) {
            setSpecial(DEFENSIVE, s );
        }
        for( Special s: _character.getImmunities().getSpecial() ) {
            setSpecial(IMMUNITIES, s );
        }
        for( Special s: _character.getWeaknesses().getSpecial() ) {
            setSpecial(WEAKNESSES, s );
        }
        for( Special s: _character.getMovement().getSpecial()) {
            setSpecial(MOVEMENT, s );
        }
        for( Special s: _character.getSkillabilities().getSpecial()) {
            setSpecial(SKILL_ABILITIES, s );
        }
        for( Special s: _character.getAttack().getSpecial() ) {
            setSpecial(ATTACK, s );
        }
        for( Special s: _character.getSpelllike().getSpecial() ) {
            setSpecial(SPELL_LIKE, s );
        }
        for( Special s: _character.getOtherspecials().getSpecial()) {
            setSpecial(OTHER, s );
        }
    }

    private HashMap<String, ClassSpells > _spells= new HashMap<String, ClassSpells>();

    public void setSpells() {

        for(Spellclass sc : _character.getSpellclasses().getSpellclass() ) {
            ClassSpells cs = null;
            if ( _spells.containsKey(sc.getName())) {
                cs = _spells.get(sc.getName());
            }
            else {
                cs = new ClassSpells(sc.getName());
                _spells.put( sc.getName(), cs );
            }

            if ( sc.getSpells().equals(SPELLBOOK) ) {
                for ( Spell s : _character.getSpellbook().getSpell() ) {
                    cs.addSpell(s);
                }
            }
            else if ( sc.getSpells().equals(MEMORIZED)) {
                for ( Spell s : _character.getSpellsmemorized().getSpell() ) {
                    cs.addSpell(s);
                }
            }
            else if ( sc.getSpells().equals(SPONTANEOUS)) {
                for ( Spell s: _character.getSpellsknown().getSpell()) {
                    cs.addSpell(s);
                }
            }
            else {
                System.out.println("Unknown spell class " + sc.getSpells() );
            }
        }
    }



    private static HashMap<IAttribute, IAttributeAbbreviated > attributeAbbreviations =
            new HashMap<IAttribute, IAttributeAbbreviated>();
    static {
        attributeAbbreviations.put( IAttribute.Strength, IAttributeAbbreviated.STR );
        attributeAbbreviations.put( IAttribute.Dexterity, IAttributeAbbreviated.DEX );
        attributeAbbreviations.put( IAttribute.Constitution, IAttributeAbbreviated.CON );
        attributeAbbreviations.put( IAttribute.Wisdom, IAttributeAbbreviated.WIS );
        attributeAbbreviations.put( IAttribute.Intelligence, IAttributeAbbreviated.INT );
    }
    private static IAttributeAbbreviated getAbbreviatedAttribute( IAttribute ia ) {
        return attributeAbbreviations.get(ia);
    }

    private HashMap< IAttribute, HashMap<String, Object> > _attributes = new HashMap<IAttribute, HashMap<String, Object>>();

    private void setAbilities() {

        for( Attribute attribute : _character.getAttributes().getAttribute() ) {
            HashMap<String, Object> attribJSON = new HashMap<String, Object>();
            _attributes.put( IAttribute.valueOf( attribute.getName() ), attribJSON );
            //_propertyMap.put( attribute.getName(), Integer.parseInt( attribute.getAttrvalue().getBase()) );

            attribJSON.put(VALUE, Integer.parseInt( attribute.getAttrvalue().getBase() ) );
            attribJSON.put(VALUE_MODIFIER, Integer.parseInt( replacePlus(attribute.getAttrbonus().getBase() ) ) );

            int attrBonusModified = Integer.parseInt( replacePlus( attribute.getAttrvalue().getModified() ) );
            int attrBonusBase =       Integer.parseInt( replacePlus( attribute.getAttrbonus().getModified() ) );

            if ( attrBonusModified == attrBonusBase ) {
                attribJSON.put(BONUS, new Integer( 0 ) );
                attribJSON.put(BONUS_MODIFIER, new Integer( 0 ) );
              //  _propertyMap.put( getAbbreviatedEnhancementBonusName(attribute),
                //                  new Integer( 0  ) );

            }
            else {
                attribJSON.put( BONUS, new Integer( attrBonusModified ) );
                attribJSON.put( BONUS_MODIFIER, new Integer( replacePlus( attribute.getAttrbonus().getModified())) );

                //_propertyMap.put( getAbbreviatedEnhancementBonusName(attribute),
                  //                new Integer( attrBonusModified  ) );
            }
            //_propertyMap.put(getAttributeDamageKeyName(attribute), new Integer(damage) );
            attribJSON.put( DAMAGE, new Integer( 0 ) );
            attribJSON.put(TEMP_MODIFIER, new Integer( 0 ) );
        }

    }

    public Integer getBaseAbilityScore(IAttribute iattribute) {
        //return (Integer)getTokenProperties(attribute.name() );
        HashMap<String, Object> attribute = _attributes.get( iattribute );
        return  (Integer)attribute.get(VALUE);
    }

    public Integer getBaseAbilityModifier(IAttribute iattribute) {
        //return (Integer)getTokenProperties(attribute.name() );
        HashMap<String, Object> attribute = _attributes.get( iattribute );
        return  (Integer)attribute.get(VALUE_MODIFIER);
    }


    public Integer getBonusAbilityScore(IAttribute iattribute) {
        HashMap<String, Object> attribute = _attributes.get( iattribute );
        return  (Integer)attribute.get(BONUS);
    }

    public Integer getBonusAbilityModifier(IAttribute iattribute) {
        HashMap<String, Object> attribute = _attributes.get( iattribute );
        return  (Integer)attribute.get(BONUS_MODIFIER);
    }

    public Integer getAbilityDamage(IAttribute iattribute ) {
        HashMap<String, Object> attribute = _attributes.get( iattribute );
        return  (Integer)attribute.get(DAMAGE);
    }


    private static String replacePlus(String value) {
		if ( value != null && value.length() > 0 && value.charAt(0) == '+') {
			String tmp = value.replace("+", "");
			return tmp;
		}
		return value;
	}

    private void setArmorClass() {
        Armorclass ac = _character.getArmorclass();
        _propertyMap.put( AC_ARMOR_BONUS,  ac.getFromarmor().length() > 0 ?
                                           Integer.parseInt( replacePlus( ac.getFromarmor() ) ) :
                                           new Integer( 0 ) );
        _propertyMap.put( AC_SHIELD_BONUS, ac.getFromshield().length() > 0 ?
                                           Integer.parseInt( replacePlus( ac.getFromshield() ) ) :
                                           new Integer( 0 ) );
        _propertyMap.put( AC_FROM_DEFLECT, ac.getFromdeflect().length() > 0 ?
                                           Integer.parseInt( replacePlus( ac.getFromdeflect() ) ) :
                                           new Integer( 0 ) );
        _propertyMap.put( AC_FROM_DODGE,  ac.getFromdodge().length() > 0 ?
                                           Integer.parseInt( replacePlus( ac.getFromdodge() ) ) :
                                           new Integer( 0 ) );
        _propertyMap.put( AC_FROM_NATURAL, ac.getFromnatural().length() > 0 ?
                                           Integer.parseInt( replacePlus( ac.getFromnatural() ) ) :
                                           new Integer( 0 ) );
        _propertyMap.put( SIZE_MOD,        ac.getFromsize().length() > 0 ?
                                           Integer.parseInt( replacePlus( ac.getFromsize() ) ) :
                                           new Integer( 0 ) );
        _propertyMap.put( AC_MISC_BONUS_1, ac.getFrommisc().length() > 0 ?
                                           Integer.parseInt( replacePlus( ac.getFrommisc() ) ) :
                                           new Integer( 0 ) );
        _propertyMap.put( AC_MISC_BONUS_2, new Integer( 0  ) );
        _propertyMap.put( AC_TEMP_BONUS,   new Integer( 0 ) );

        for ( Penalty p : _character.getPenalties().getPenalty() ) {
            if (p.getName().equals( "Armor Check Penalty") ) {
                _propertyMap.put( ARMOR_CHECK_PENALTY, Integer.parseInt( p.getValue() ) );
            }
            else if ( p.getName().equals( "Max Dex Bonus") ) {
                _propertyMap.put( ARMOR_MAX_DEX_BONUS, Integer.parseInt(p.getValue()) );
            }
        }
    }

    private void setHitpoints() {
       int     hp    = Integer.parseInt(_character.getHealth().getHitpoints());
       _propertyMap.put( CLASS_HP, hp );
    }

    private void setCoreProperties() {

        _propertyMap.put( CHARACTER, _character.getName() );
        _propertyMap.put( RACE, _character.getRace().getName());
        _propertyMap.put( ALIGNMENT, _character.getAlignment().getName());
        _propertyMap.put( PLAYER, _character.getPlayername());
        _propertyMap.put( DEITY, _character.getDeity().getName() == null ? "" :  _character.getDeity().getName() );
        _propertyMap.put( GENDER, _character.getPersonal().getGender());
        _propertyMap.put( AGE, Integer.parseInt(_character.getPersonal().getAge()) );
        _propertyMap.put( HEIGHT, _character.getPersonal().getCharheight().getText());
        _propertyMap.put( WEIGHT, _character.getPersonal().getCharweight().getText() );
        _propertyMap.put( SPEED, Integer.parseInt( _character.getMovement().getSpeed().getValue() ) );
        _propertyMap.put( LEVEL, Integer.parseInt( _character.getClasses().getLevel() ) );
        _propertyMap.put( CLASS, _character.getClasses().getSummaryabbr());



    }

    private void setSavingThrows() {
        for(Save s : _character.getSaves().getSave() ) {
            _propertyMap.put( s.getAbbr() + CLASS_BONUS,  s.getBase().length() > 0 ?
                                                          Integer.parseInt( replacePlus( s.getBase() ) ) :
                                                          new Integer( 0 ) );
            _propertyMap.put( s.getAbbr() + RESIST_BONUS, s.getFromresist().length() > 0 ?
                                                          Integer.parseInt( replacePlus( s.getFromresist() ) ) :
                                                          new Integer( 0 ) );
            _propertyMap.put( s.getAbbr() + MISC_BONUS,  s.getFrommisc().length() > 0 ?
                                                          Integer.parseInt( replacePlus( s.getFrommisc() ) ) :
                                                          new Integer( 0 ) );
        }
    }

    //IPathfinderCharacter methods
    public String getName() {
        return (String)getTokenProperties( CHARACTER );
    }

    public String getPlayer() {
        return (String)getTokenProperties( PLAYER );
    }

    public String getRace() {
        return (String)getTokenProperties( RACE );
    }

    public String getAlignment() {
        return (String)getTokenProperties( ALIGNMENT );
    }

    public String getDeity() {
        return (String)getTokenProperties( DEITY );
    }

    public String getGender() {
        return (String)getTokenProperties( GENDER );
    }

    public Integer getAge() {
        return (Integer)getTokenProperties( AGE );
    }

    public String getHeight() {
        return (String)getTokenProperties( HEIGHT );
    }

    public String getWeight() {
        return (String)getTokenProperties( WEIGHT );
    }

    public Integer getSpeed() {
        return (Integer)getTokenProperties( SPEED );
    }

    public Integer getLevel() {
        return (Integer)getTokenProperties( LEVEL );
    }

    public String getClassAbbreviation() {
        return (String)getTokenProperties( CLASS );
    }

    public Integer getClassHitpoints() {
        return (Integer)getTokenProperties( CLASS_HP );
    }


    public Integer getSavingThrowClassBonus(ISavingThrow ist) {
        return (Integer)_propertyMap.get( ist.toString() + CLASS_BONUS );
    }

    public Integer getMiscellaneousSavingThrowBonus(ISavingThrow ist) {
        return (Integer)_propertyMap.get( ist.toString() + MISC_BONUS );
    }

    public Integer getResistanceSavingThrowBonus(ISavingThrow ist) {
        return (Integer)_propertyMap.get( ist.toString() + RESIST_BONUS );
    }

    public Integer getACArmorBonus() {
        return (Integer)_propertyMap.get( AC_ARMOR_BONUS );
    }

    public Integer getACFromShield() {
        return (Integer)_propertyMap.get( AC_SHIELD_BONUS );
    }

    public Integer getACFromDeflect() {
        return (Integer)_propertyMap.get( AC_FROM_DEFLECT );
    }

    public Integer getACFromDodge() {
        return (Integer)_propertyMap.get( AC_FROM_DODGE );
    }

    public Integer getACFromnNatural() {
        return (Integer)_propertyMap.get( AC_FROM_NATURAL );
    }

    public Integer getACFromSize() {
        return (Integer)_propertyMap.get( SIZE_MOD );
    }

    public Integer getACMisc() {
        return (Integer)_propertyMap.get( AC_MISC_BONUS_1 );
    }

    public Integer getBaseAttackBonus() {
        return (Integer)_propertyMap.get( BASE_ATTACK_BONUS );
    }

    public String getVision() {
        return _vision;  //To change body of implemented methods use File | Settings | File Templates.
    }


    private Object getTokenProperties( String key ) {
        return _propertyMap.get(key);
    }

    private class IntegerSerializer implements JsonSerializer<Integer>  {
        public JsonElement serialize(Integer integer, java.lang.reflect.Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(integer.toString() );
        }
    }

    private class SpellSerializer implements JsonSerializer<PFSRDSpell> {
       public JsonElement serialize(PFSRDSpell spell, java.lang.reflect.Type type, JsonSerializationContext jsonSerializationContext) {
            return spell.jsonFields();
        }
    }

    public Token asToken( Config.ConfigEntry configEntry ) throws IOException, SAXException {

        _token = createToken(configEntry);

        loadMacros(_token);

        //set all the token properties.
        _token.setPropertyType(PATHFINDER);

        //PC or NPC
        if ( _character.getRole().equals("npc") ) {
            _token.setType(Token.Type.NPC);
        }
        else {
            _token.setType( Token.Type.PC );
        }


        //do the attributes
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter( Integer.class, new IntegerSerializer() ) ;
        gson.registerTypeAdapter( PFSRDSpell.class, new SpellSerializer());
        Gson gjson = gson.create();
        for( IAttribute ia : IAttribute.values() ) {
            String json = gjson.toJson(_attributes.get(ia) );
            _token.setProperty( ia.toString(), json );
        }

        for( String propKey : _propertyMap.keySet() ) {
            _token.setProperty( propKey, _propertyMap.get( propKey ).toString());
        }


        _token.setProperty( FEATS_JSON, gjson.toJson(_feats ) );

        _token.setProperty(TRAITS_JSON, gjson.toJson( _traits ));

        _token.setProperty(WEAPON_JSON, gjson.toJson(_weapons));

        _token.setProperty(SKILLS_JSON, gjson.toJson(_skills));

        //set the spells.  this needs to basically happen by class.

        HashMap< String, SortedMap< Integer, HashMap<String, PFSRDSpell>>> characterSpells =
                new HashMap<String, SortedMap<Integer, HashMap<String, PFSRDSpell>>>();
        for(Spellclass sc : _character.getSpellclasses().getSpellclass() ) {
            SortedMap< Integer, HashMap<String, PFSRDSpell>> spellsByLevel = this.getSpellsByClassAndLevel( sc.getName() );
            characterSpells.put( sc.getName(), spellsByLevel );
        }

        _token.setProperty(SPELLS_JSON, gjson.toJson(characterSpells));

        _token.setProperty(SPECIAL_ABILITIES_JSON, gjson.toJson( _specials ));

        //init mod
        _token.setProperty(INIT_MOD, _initMod );

        //vision
        _token.setHasSight(true);
        _token.setSightType( _vision );

        return _token;
    }


    private HashMap<String, HashMap<String, String >> _skills = new HashMap<String, HashMap<String, String>>() ;

    private void setSkills() {
        for(  Skill s :  _character.getSkills().getSkill() ) {
            String mungedSkillName = mungeSkillName( s.getName() );
            HashMap<String, String> skillMap = new HashMap<String, String>();
            skillMap.put( "value", s.getValue());
            skillMap.put( "ranks", s.getRanks() );
            skillMap.put( "description", s.getDescription());
            skillMap.put( "skillBonus", "0" );
            skillMap.put( "attrBonus", s.getAttrbonus());
            skillMap.put( "attrName", s.getAttrname());
            skillMap.put( "classSkill", s.getClassskill() != null && s.getClassskill().equals(YES) ? "1" : "0");
            skillMap.put( "armorCheckPenalty", s.getArmorcheck() != null && s.getArmorcheck().equals(YES) ? "1" : "0");
            skillMap.put( "useTrainedOnly", s.getTrainedonly() != null && s.getTrainedonly().equals(YES) ? "1" : "0" );
            skillMap.put( "usable", s.getUsable() != null && s.getUsable().equals(YES) ? "1" : "0" );
            skillMap.put( "tools", s.getTools()  != null && s.getTools().equals(YES) ? "1" : "0" );
            _skills.put( mungedSkillName, skillMap );
        }
    }


    private static boolean isClassSkill(Skill s) {
        return s.getClassskill() != null && s.getClassskill().equals( YES ) == true;
    }


    private Token createToken(Config.ConfigEntry configEntry) throws IOException {
        Asset tokenImage = null;
        File file = new File( configEntry.getImageFilePath());
        tokenImage = AssetManager.createAsset(file);
        AssetManager.putAsset( tokenImage );
        _token = new Token( _character.getName(), tokenImage.getId());


        _token.setImageAsset(tokenImage.getName());
        _token.setImageAsset(tokenImage.getName(), tokenImage.getId());

        //set the image shape
        Image image = ImageIO.read(file);
        _token.setShape(TokenUtil.guessTokenType(image));


		//set the token size
        String characterSize = _character.getSize().getName();
		if ( characterSize != null && ! characterSize.isEmpty()) {
			SquareGrid grid = new SquareGrid();
			for (TokenFootprint footprint : grid.getFootprints()) {
				if ( characterSize.equals( footprint.getName() ) ) {
					_token.setFootprint( grid, footprint );
				}
			}
		}

        //set the other image assets (portrait, charsheet image)
        Asset portrait = AssetManager.createAsset( new File( configEntry.getPortraitFilePath()) );
        AssetManager.putAsset(portrait);
        _token.setPortraitImage(portrait.getId());
        _token.setCharsheetImage(portrait.getId());

        return _token;
    }

    private void loadMacros(Token t) throws IOException, SAXException {

        if ( macroDigester == null ) {
            macroDigester = new MacroDigester( ResourceManager.getMacroConfigFile().getAbsolutePath() );
            macroDigester.parseConfigFile();
        }

        List<MacroButtonProperties> macroButtonSet = new ArrayList<MacroButtonProperties>();

        //do the generic macros first.


        int index = 1;

        index = buildGenericMacros(macroButtonSet, index);
        index = buildWeaponMacros(macroButtonSet, index);
        index = buildSpecialAbilityMacros(macroButtonSet, index);
        index = buildSkillMacros(macroButtonSet, index);
        buildSubMacros( macroButtonSet, index );


        t.getMacroNextIndex(); //TODO: this is a hack to create the underlying macroPropertiesMap hash table
        t.replaceMacroList(macroButtonSet);

	}

    private int buildSubMacros(List<MacroButtonProperties> macroButtonSet, int index) throws IOException {
        HashMap<String, MacroDigester.MacroEntry > submacros = macroDigester.getGroup( "SUBMACROS");

        IMacroReplacer defaultReplacer = new DefaultReplacer();
        for( MacroDigester.MacroEntry macroEntry : submacros.values()) {

            //this is kind of a hack.
            if ( macroEntry.name.indexOf("spell") == 0 ) {
                if (isSpellCaster()) {
                    continue;
                }
            }

            MacroButtonProperties properties = macroEntry.getMacroButtonProperties( index++, defaultReplacer );
            macroButtonSet.add( properties );
        }
        return index  ;
    }

    private String mungeSkillName( String skillName ) {
        return skillName.replaceAll( "\\s|\\(|\\)", "");
    }

    private int buildSkillMacros(List<MacroButtonProperties> macroButtonSet, int index) throws IOException {

       HashMap<String, MacroDigester.MacroEntry > skillMacros = macroDigester.getGroup( "Skills");

       for ( Skill s : _character.getSkills().getSkill() ) {

           String skillName = s.getName();
           String mungedSkillName = mungeSkillName( skillName);

            String attributeName   = s.getAttrname();
            String attribShortName = CharacterAttribute.getShortName( attributeName );
            SkillReplacer replacer = new SkillReplacer( mungedSkillName, attribShortName  );
            MacroDigester.MacroEntry macroEntry = skillMacros.get("Skill Check");

           boolean disableButton = false;

            if ( isClassSkill(s) ) {
                macroEntry.buttonColor = "yellow";
            }
            else if ( s.getTrainedonly().equals(YES ) ) {
                macroEntry.buttonColor = "darkgray";
                if ( s.getRanks().length() == 0 || s.getRanks().equals("0")) {
                    disableButton = true;
                }

            } else {
                macroEntry.buttonColor = "white";
            }

            //todo: refactor the below into the replacer interface if I ever do it somewhere else.
            if ( ! disableButton ) {
                macroEntry.toolTip = "[r:" + s.getRanks() + "]";
                macroEntry.name = skillName;
                MacroButtonProperties properties = macroEntry.getMacroButtonProperties( index++, replacer );
                macroButtonSet.add( properties );
            }
        }

        return index;

    }

    private int buildGenericMacros(List<MacroButtonProperties> macroButtonSet, int index) throws IOException {
        index = buildMacros(macroButtonSet, "Generic", index);
        return index;
    }

    private int buildSpecialAbilityMacros(List<MacroButtonProperties> macroButtonSet, int index) throws IOException {
        index = buildMacros(macroButtonSet, "Special Abilities", index);
        return index;
    }

    private int buildMacros(List<MacroButtonProperties> macroButtonSet, String groupName, int index) throws IOException {
        HashMap<String, MacroDigester.MacroEntry > genericMacros = macroDigester.getGroup( groupName );
        IMacroReplacer defaultReplacer = new DefaultReplacer();
        for( MacroDigester.MacroEntry macroEntry : genericMacros.values()) {

            //kind of a small hack, special for spells
            //if the character doesn't have spells, don't bother creating a spell button
            //TODO: probably a smoother way to do this
            if ( macroEntry.name.equals("Spells") ) {
                if (isSpellCaster()) {
                    continue;
                }
            }
            MacroButtonProperties properties = macroEntry.getMacroButtonProperties( index++, defaultReplacer );
            macroButtonSet.add( properties );
        }
        return index;
    }

    private boolean isSpellCaster() {
        return _character.getSpellclasses().getSpellclass().size() == 0;
    }


    private int buildWeaponMacros(List<MacroButtonProperties> macroButtonSet, int index) throws IOException {

        //next do the power macros
        int sortPrefix = 0;
        HashMap<String, MacroDigester.MacroEntry > powerMacros = macroDigester.getGroup("Powers");
        for( WeaponImpl wimpl : _weapons.values()) {

            MacroDigester.MacroEntry macroEntry = powerMacros.get( "Standard Attack");

            if ( sortPrefix == 0 ) {
                sortPrefix = Integer.parseInt(macroEntry.sortPrefix); //bootstrap the sortPrefix
            } else {
                ++sortPrefix;
                macroEntry.sortPrefix = new Integer( sortPrefix ).toString();
            }

            macroEntry.name = wimpl.name;
            MacroButtonProperties properties = macroEntry.getMacroButtonProperties( index++,
                                                                                    new WeaponNameReplacer( wimpl.name, new Integer(1) ) );
            macroButtonSet.add( properties );

            //and set all the full attack stuff

            if ( wimpl.numFullAttacks > 1 ) {
                for ( Integer attackCount : wimpl.sortedAttacks() ) {
                    MacroDigester.MacroEntry fullAttackMacroEntry = powerMacros.get( "Attack - Full");
                    ++sortPrefix;
                    fullAttackMacroEntry.sortPrefix = new Integer( sortPrefix ).toString();
                    fullAttackMacroEntry.name = attackCount.toString();
                    MacroButtonProperties faProperties = fullAttackMacroEntry.getMacroButtonProperties( index++,
                                                                                    new WeaponNameReplacer( wimpl.name, attackCount ) );
                    macroButtonSet.add( faProperties );

                }
            }
        }

        return index;
    }


}
