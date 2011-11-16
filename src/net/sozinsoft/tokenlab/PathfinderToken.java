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
    private static final String AC_ENH_BONUS = "ACEnhBonus";
    private static final String SIZE_MOD = "SizeMod";
    private static final String AC_TEMP_BONUS = "ACTempBonus";
    private static final String AC_MISC_BONUS_2 = "ACMiscBonus2";
    private static final String AC_MISC_BONUS_1 = "ACMiscBonus1";
    private static final String AC_FEAT_BONUS = "ACFeatBonus";
    private static final String AC_CLASS_BONUS = "ACClassBonus";
    private static final String AC_SHIELD_BONUS = "ACShieldBonus";
    private static final String AC_ARMOR_BONUS = "ACArmorBonus";
    private static final String ARMOR_MAX_DEX_BONUS = "ArmorMaxDexBonus";
    private static final String ARMOR_CHECK_PENALTY = "ArmorCheckPenalty";
    private static final String MISC_BONUS1 = "MiscBonus1";
    private static final String MISC_BONUS2 = "MiscBonus2";
    private static final String DAMAGE = "Damage";
    private static final String ENHANCEMENT = "Enhancement";
    private static final String CLASS_BONUS = "ClassBonus";
    private static final String FEAT_BONUS = "FeatBonus";
    private static final String ENH_BONUS = "EnhBonus";
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

    private void setAbilities() {


        for( Attribute attribute : _character.getAttributes().getAttribute() ) {
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

    private void setCoreProperties() {
       //TODO: // t.setPropertyType(PATHFINDER);
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

       // t.setProperty(CLASS, _character.getClassSummaryAbbreviation());
     //   t.setProperty(LEVEL, _character.getClass()



        //if (_character.isNPC()) {
          //  t.setType(Token.Type.NPC);
        //} else {
          //  t.setType(Token.Type.PC);
        //}

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

    public Integer getBaseAbilityScore(IAttribute attribute) {
        return (Integer)getTokenProperties(attribute.name() );
    }

    public Integer getAbilityEnhancement(IAttribute iattribute) {
        return (Integer)_propertyMap.get( getAbbreviatedEnhancementBonusName(iattribute) );
    }

    public Integer getAbilityDamage(IAttribute attribute) {
        return (Integer)_propertyMap.get(getAttributeDamageKeyName(attribute));
    }


    private Object getTokenProperties( String key ) {
        return _propertyMap.get(key);
    }

    public Token asToken( Config.ConfigEntry configEntry ) throws IOException, SAXException {

        _token = createToken(configEntry);

        //loadMacros(_token);

        //setCoreProperties(_token);


        //setCharacterAttributes(_token);
        //setPenalties(_token);

        //setHitPoints(_token);
        //setArmorClass(_token);


        //_token.setProperty(BASE_ATTACK_BONUS, _character.getAttack().getBaseattack());
		//_token.setProperty(INIT_MOD, Integer.toString(CreateInitModProperty(_character.getInitiative())));

        //setSavingThrows(_token);
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

    private void setSavingThrows(Token t) {
        /*
        HashMap<String, CharacterOld.Save> saves = _character.getSaves();
        for (String saveName : saves.keySet()) {
            CharacterOld.Save s = saves.get(saveName);
            t.setProperty(s.abbr + CLASS_BONUS, s.base);
            t.setProperty(s.abbr + FEAT_BONUS, s.frommisc);
            t.setProperty(s.abbr + ENH_BONUS, s.fromresist);
            String miscBonus1 = s.abbr + MISC_BONUS1;
            if (t.getProperty(miscBonus1) == null) {
                t.setProperty(miscBonus1, "0");
            }
            String miscBonus2 = s.abbr + MISC_BONUS2;
            if (t.getProperty(miscBonus2) == null) {
                t.setProperty(miscBonus2, "0");
            }
        }  */
    }

    private void setArmorClass(Token t) {
        /*
        CharacterOld.ArmorClass AC = _character.getArmorClass();
        t.setProperty(AC_ARMOR_BONUS, AC.fromarmor);
        t.setProperty(AC_SHIELD_BONUS, AC.fromshield);
        t.setProperty(AC_CLASS_BONUS, "0"); //TODO: does herolabs ever define this?
        t.setProperty(AC_FEAT_BONUS, AC.fromdodge); //TODO: anything other than dodge here?
        t.setProperty(AC_MISC_BONUS_1, AC.frommisc);
        t.setProperty(AC_MISC_BONUS_2, "0");
        t.setProperty(AC_TEMP_BONUS, "0");
        t.setProperty(SIZE_MOD, AC.fromsize);

        //finally do the enhancement bonus
        int enhancementbonus = Integer.parseInt(AC.fromdeflect) +
                Integer.parseInt(AC.fromnatural);
        t.setProperty(AC_ENH_BONUS, Integer.toString(enhancementbonus));
        */
    }

	private void setHitPoints(Token t) {
        /*
		CharacterAttribute con = _character.getConstitution();
		int level = Integer.parseInt(_character.getLevel());
		int hp = Integer.parseInt( _character.getHitpoints() );
		int conBonus = con.getBonus();
		int baseHitpoints = hp - conBonus * level;
		t.setProperty( CLASS_HP, Integer.toString( baseHitpoints ) );
		//t.setProperty(MISC_HP, Integer.toString( hp - baseHitpoints ) );
		*/
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


    private void setCharacterAttributes(Token t) {
        /*
        for (CharacterAttribute ca : _character.getAttributes().values()) {
            t.setProperty(ca.getName(), ca.getBase());
            String enhancementProperty = CharacterAttribute.getShortName(ca.getName()) + ENHANCEMENT;
            t.setProperty(enhancementProperty, Integer.toString( ca.getEnhancement() ) );
            String damageProperty = CharacterAttribute.getShortName(ca.getName()) + DAMAGE;
            t.setProperty(damageProperty, "0");
            if (ca.isVoid()) { //void attributes mess up saves, so...
                String saveShortName = CharacterAttribute.getSaveShortName(ca.getName());
                if (saveShortName != null) {
                    String attrib = saveShortName + MISC_BONUS1;
                    t.setProperty(attrib, Integer.toString(ca.getModifiedBonus()));
                    attrib = saveShortName + MISC_BONUS2;
                    t.setProperty(attrib, "-1"); //TODO: this is a bit of a hack to work around the PF ruleset.
                }
            }
        } */
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
        /*
        String characterSize = _character.getSize();
		if ( characterSize != null && ! characterSize.isEmpty()) {
			SquareGrid grid = new SquareGrid();
			for (TokenFootprint footprint : grid.getFootprints()) {
				if ( characterSize.equals( footprint.getName() ) ) {
					t.setFootprint( grid, footprint );
				}
			}
		}  */

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
