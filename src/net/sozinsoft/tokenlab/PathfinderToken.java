package net.sozinsoft.tokenlab;


import net.rptools.maptool.model.*;
import net.rptools.maptool.util.TokenUtil;
import net.sozinsoft.tokenlab.dtd.*;
import net.sozinsoft.tokenlab.dtd.Character;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PathfinderToken implements IPathfinderCharacter {

    private static final String CLASS_HP = "ClassHP";
    public static final String MISC_HP = "MiscHP";
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
    private static final String MISC_BONUS1 = "MiscBonus1";
    private static final String MISC_BONUS2 = "MiscBonus2";
    private static final String DAMAGE = "Damage";
    private static final String ENHANCEMENT = "Enhancement";
    private static final String CLASS_BONUS = "ClassBonus";
    private static final String RESIST_BONUS = "ResistBonus";
    private static final String RANKS = "Ranks";
    private static final String BONUS = "Bonus";
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
    private Character _character;
    private Token _token;
    private HashMap<String, Object> _propertyMap = new HashMap<String, Object>();

    public PathfinderToken(Character character ) {
        _character = character;
        setCoreProperties();
        setAbilities();
        setSavingThrows();
        setHitpoints();
        setArmorClass();
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

    private HashMap< IAttribute, Attribute > _attributes = new HashMap<IAttribute, Attribute>();
    private void setAbilities() {

        for( Attribute attribute : _character.getAttributes().getAttribute() ) {
            _attributes.put( IAttribute.valueOf(attribute.getName() ), attribute );
            _propertyMap.put( attribute.getName(), Integer.parseInt( attribute.getAttrvalue().getBase()) );
            int bonus =       Integer.parseInt( replacePlus( attribute.getAttrbonus().getModified() ) ) -
                              Integer.parseInt( replacePlus( attribute.getAttrbonus().getBase() ) );
            _propertyMap.put( getAbbreviatedEnhancementBonusName(attribute),
                              new Integer(bonus ) );
            int damage = 0; //TODO - is there something I can do here?
            _propertyMap.put(getAttributeDamageKeyName(attribute), new Integer(damage) );
        }

    }

    private static String getAttributeDamageKeyName(Attribute attribute) {
        return getAbbreviatedEnhancementBonusName( IAttribute.valueOf(attribute.getName()) ).toString() +
                                                              DAMAGE;
    }

    private static String getAttributeDamageKeyName(IAttribute attribute) {
        return getAbbreviatedEnhancementBonusName( attribute ).toString() + DAMAGE;
    }

    private static String getAbbreviatedEnhancementBonusName(Attribute attribute) {
        return getAbbreviatedAttribute(IAttribute.valueOf(attribute.getName())) + ENHANCEMENT;
    }

    private static String getAbbreviatedEnhancementBonusName(IAttribute attribute) {
        return getAbbreviatedAttribute(attribute)  + ENHANCEMENT;
    }

    private static String replacePlus(String value) {
		if ( value.charAt(0) == '+') {
			String tmp = value.replace("+", "");
			return tmp;
		}
		return value;
	}

    //armor class

    //AC	[r:10+ACArmorBonus+ACShieldBonus+DexBonus+ACClassBonus+
    //            ACFeatBonus+ACEnhBonus+ACMiscBonus1+ACMiscBonus2+ACTempBonus+SizeMod]
    // 10 + AC_ARMOR_BONUS + AC_SHIELD_BONUS + AC_FROM_DEFLECT + AC_FROM_DODGE + AC_FROM_NATURAL +
    //        SIZE_MOD  + AC_MISC_BONUS_1 + AC_MISC_BONUS_2 + ACTempBonus

    private void setArmorClass() {
        Armorclass ac = _character.getArmorclass();
        _propertyMap.put( AC_ARMOR_BONUS, Integer.parseInt( replacePlus( ac.getFromarmor() ) ) );
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


    }

    private void setHitpoints() {
       Integer level = this.getLevel();
       int     hp    = Integer.parseInt(_character.getHealth().getHitpoints());
       int  conBonus = this.getBaseAbilityBonus( IAttribute.Constitution );
       int  baseHp   = hp - conBonus * level;
       _propertyMap.put( CLASS_HP, baseHp );
    }

    private void setCoreProperties() {

        _propertyMap.put( CHARACTER, _character.getName() );
        _propertyMap.put( RACE, _character.getRace().getName());
        _propertyMap.put( ALIGNMENT, _character.getAlignment().getName());
        _propertyMap.put( PLAYER, _character.getPlayername());
        _propertyMap.put( DEITY, _character.getDeity().getName() );
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
            _propertyMap.put( s.getAbbr() + CLASS_BONUS, Integer.parseInt( replacePlus( s.getBase() ) ) );
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

    public Integer getBaseAbilityScore(IAttribute attribute) {
        return (Integer)getTokenProperties(attribute.name() );
    }

    public Integer getBaseAbilityBonus(IAttribute iattribute) {
        Attribute attribute = _attributes.get( iattribute );
        return  Integer.parseInt(replacePlus(attribute.getAttrbonus().getBase()));
    }

    public Integer getAbilityEnhancement(IAttribute iattribute) {
        return (Integer)_propertyMap.get( getAbbreviatedEnhancementBonusName(iattribute) );
    }

    public Integer getAbilityDamage(IAttribute attribute) {
        return (Integer)_propertyMap.get(getAttributeDamageKeyName(attribute));
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


    private Object getTokenProperties( String key ) {
        return _propertyMap.get(key);
    }

    public Token asToken( Config.ConfigEntry configEntry ) throws IOException, SAXException {

        _token = createToken(configEntry);

        loadMacros(_token);

        //set all the token properties.
        _token.setPropertyType(PATHFINDER);
        for( String propKey : _propertyMap.keySet() ) {
            _token.setProperty( propKey, _propertyMap.get( propKey ));
        }

        //setPenalties(_token);



        //_token.setProperty(BASE_ATTACK_BONUS, _character.getAttack().getBaseattack());
		//_token.setProperty(INIT_MOD, Integer.toString(CreateInitModProperty(_character.getInitiative())));

        //setSkills(_token);

        //Gson gson = new Gson();

     //   t.setProperty( "Feats", gson.toJson(_character.getFeats().values()));

      //  t.setProperty( "WeaponJSON", gson.toJson( _character.getWeapons() ) );

        return _token;
    }

    private static int CreateInitModProperty( Initiative i ) {
        int itotal = Integer.parseInt(CharacterAttribute.replacePlus(i.getTotal() ) );
        int imisc = Integer.parseInt(CharacterAttribute.replacePlus(i.getMisctext() ) );
        int iattr = Integer.parseInt(CharacterAttribute.replacePlus(i.getAttrtext() ) );
        if (imisc > 0 || iattr > 0) {
            return itotal - iattr - imisc;
        } else return 0;
    }



    private void setSkills(Token t) {
        /*
        for( CharacterOld.Skill skill: _character.getSkills().values()) {
            String mungedSkillName = mungeSkillName( skill.skillName );
            if ( mungedSkillName.indexOf("Profession") >= 0) {
                continue; //skipping professions for now, TODO:
            }
            t.setProperty( mungedSkillName + RANKS, skill.ranks );
            //t.setProperty( mungedSkillName + BONUS, skill.attrBonus);
            if ( skill.isClassSkill == true ) {
                t.setProperty( mungedSkillName + CLASS_SKILL, "1");
            }
        } */
    }





    private void setPenalties(Token t) {
        /*
        //penalties
        CharacterOld.Penalty dexPenalty = _character.getPenalty("Max Dex Bonus"); //penalties.get( "Max Dex Bonus");
        if ( dexPenalty != null ) {
            t.setProperty(ARMOR_MAX_DEX_BONUS, dexPenalty.value);
        }
        CharacterOld.Penalty armorCheckPenalty = _character.getPenalty("Armor Check Penalty");
        if ( armorCheckPenalty != null ) {
            t.setProperty(ARMOR_CHECK_PENALTY, armorCheckPenalty.value);
        }
        */
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
        index = buildPowerMacros(macroButtonSet, index);
        index = buildSkillMacros(macroButtonSet, index);
        index = buildSubMacros( macroButtonSet, index );


        t.getMacroNextIndex(); //TODO: this is a hack to create the underlying macroPropertiesMap hash table
        t.replaceMacroList(macroButtonSet);

	}

    private int buildSubMacros(List<MacroButtonProperties> macroButtonSet, int index) throws IOException {
        HashMap<String, MacroDigester.MacroEntry > submacros = macroDigester.getGroup( "SUBMACROS");

        IMacroReplacer defaultReplacer = new DefaultReplacer();
        for( MacroDigester.MacroEntry macroEntry : submacros.values()) {

            MacroButtonProperties properties = macroEntry.getMacroButtonProperties( index++, defaultReplacer );
            macroButtonSet.add( properties );
        }
        return index  ;
    }

    private String mungeSkillName( String skillName ) {
        return skillName.replaceAll( "\\s|\\(|\\)", "");
    }

    private int buildSkillMacros(List<MacroButtonProperties> macroButtonSet, int index) throws IOException {
        /*
       HashMap<String, MacroDigester.MacroEntry > skillMacros = macroDigester.getGroup( "Skills");

       HashMap<String, CharacterOld.Skill> skills = _character.getSkills();

        for (String skillName : skills.keySet() ) {

            if ( skillName.indexOf("Profession") >= 0) {
                continue; //skipping professions for now, TODO:
            }
            CharacterOld.Skill skill  = skills.get(skillName);
            String attributeName   = skill.attrName;
            String attribShortName = CharacterAttribute.getShortName( attributeName );
            SkillReplacer replacer = new SkillReplacer( skillName, attribShortName, attribShortName + "Bonus", mungeSkillName( skillName )  );
            MacroDigester.MacroEntry macroEntry = skillMacros.get("Skill Check");

            if ( skill.isClassSkill ) {
                macroEntry.buttonColor = "yellow";
            }
            else if ( skill.useTrainedOnly ) {
                macroEntry.buttonColor = "darkgray";
            } else {
                macroEntry.buttonColor = "white";
            }

            //todo: refactor the below into the replacer interface if I ever do it somewhere else.
            macroEntry.toolTip = "[r:" + replacer.skillRanks + "]";

            macroEntry.name = skillName;
            MacroButtonProperties properties = macroEntry.getMacroButtonProperties( index++, replacer );
            macroButtonSet.add( properties );
        }
        */
        return index;

    }

    private int buildGenericMacros(List<MacroButtonProperties> macroButtonSet, int index) throws IOException {
        HashMap<String, MacroDigester.MacroEntry > genericMacros = macroDigester.getGroup( "Generic");
        IMacroReplacer defaultReplacer = new DefaultReplacer();
        for( MacroDigester.MacroEntry macroEntry : genericMacros.values()) {

            MacroButtonProperties properties = macroEntry.getMacroButtonProperties( index++, defaultReplacer );
            macroButtonSet.add( properties );
        }
        return index;
    }

    private int buildPowerMacros(List<MacroButtonProperties> macroButtonSet, int index) throws IOException {
        /*
        //next do the power macros
        int sortPrefix = 0;
        HashMap<String, MacroDigester.MacroEntry > powerMacros = macroDigester.getGroup("Powers");
        for( Weapon weapon : this._character.getWeapons().values()) {

            MacroDigester.MacroEntry macroEntry = powerMacros.get( "Standard Attack");

            if ( sortPrefix == 0 ) {
                sortPrefix = Integer.parseInt(macroEntry.sortPrefix); //bootstrap the sortPrefix
            } else {
                ++sortPrefix;
                macroEntry.sortPrefix = new Integer( sortPrefix ).toString();
            }

            macroEntry.name = weapon.name;
            MacroButtonProperties properties = macroEntry.getMacroButtonProperties( index++,
                                                                                    new WeaponNameReplacer( weapon.name, new Integer(1) ) );
            macroButtonSet.add( properties );

            //and set all the full attack stuff

            if ( weapon.numFullAttacks > 1 ) {
                for ( Integer attackCount : weapon.sortedAttacks() ) {
                    MacroDigester.MacroEntry fullAttackMacroEntry = powerMacros.get( "Attack - Full");
                    ++sortPrefix;
                    fullAttackMacroEntry.sortPrefix = new Integer( sortPrefix ).toString();
                    fullAttackMacroEntry.name = attackCount.toString();
                    MacroButtonProperties faProperties = fullAttackMacroEntry.getMacroButtonProperties( index++,
                                                                                    new WeaponNameReplacer( weapon.name, attackCount ) );
                    macroButtonSet.add( faProperties );

                }
            }
        }
        */
        return index;
    }
}
