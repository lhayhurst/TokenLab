package net.sozinsoft.tokenlab;


import net.rptools.maptool.model.*;
import net.rptools.maptool.util.PersistenceUtil;
import net.rptools.maptool.util.TokenUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PathfinderToken {

    private static final String CLASS_HP = "ClassHP";
    public static final String MISC_HP = "MiscHP";
    private Character _character;

    public PathfinderToken( Character character ) {
        _character = character;
    }

    public Token asToken( Config.ConfigEntry configEntry ) throws IOException {

        Token t = createToken(configEntry);
        loadMacros(t);

        setCoreProperties(t);


        setCharacterAttributes(t);
        setPenalties(t);

        setHitPoints(t);
        setArmorClass(t);


        t.setProperty("BaseAttackBonus", _character.getBaseAttackBonus());
		t.setProperty( "InitMod", Integer.toString( _character.getInitiative().initModifier() ) );

        setSavingThrows(t);
        setSkills(t);

        return t;
    }

    private void setCoreProperties(Token t) {
        t.setPropertyType("Pathfinder");
        t.setProperty("Character", _character.getName());
        t.setProperty("Race", _character.getRace());
        t.setProperty("Player", _character.getPlayerName());
        t.setProperty("Alignment", _character.getAlignment() );
        t.setProperty( "Deity", _character.getDeity());
        t.setProperty("Class", _character.getClassSummaryAbbreviation());
        t.setProperty("Level", _character.getLevel());
        t.setProperty( "Gender", _character.getGender());
        t.setProperty( "Age", _character.getAge());
        t.setProperty( "Height", _character.getHeight());
        t.setProperty( "Weight", _character.getWeight());
        t.setProperty( "Speed", _character.getSpeed() );

        if (_character.isNPC()) {
            t.setType(Token.Type.NPC);
        } else {
            t.setType(Token.Type.PC);
        }

    }

    private void setSkills(Token t) {
        for( Character.Skill skill: _character.getSkills().values()) {
            t.setProperty( skill.skillName + "Ranks", skill.ranks );
            t.setProperty( skill.skillName + "Bonus", skill.attrBonus);
            if ( skill.isClassSkill == true ) {
                t.setProperty( skill.skillName + "ClassSkill", "1");
            }
        }
    }

    private void setSavingThrows(Token t) {
        HashMap<String, Character.Save> saves = _character.getSaves();
        for (String saveName : saves.keySet()) {
            Character.Save s = saves.get(saveName);
            t.setProperty(s.abbr + "ClassBonus", s.base);
            t.setProperty(s.abbr + "FeatBonus", s.frommisc);
            t.setProperty(s.abbr + "EnhBonus", s.fromresist);
            String miscBonus1 = s.abbr + "MiscBonus1";
            if (t.getProperty(miscBonus1) == null) {
                t.setProperty(miscBonus1, "0");
            }
            String miscBonus2 = s.abbr + "MiscBonus2";
            if (t.getProperty(miscBonus2) == null) {
                t.setProperty(miscBonus2, "0");
            }
        }
    }

    private void setArmorClass(Token t) {
        Character.ArmorClass AC = _character.getArmorClass();
        t.setProperty("ACArmorBonus", AC.fromarmor);
        t.setProperty("ACShieldBonus", AC.fromshield);
        //t.setProperty("DexBonus", AC.fromdexterity);
        t.setProperty("ACClassBonus", "0"); //TODO: does herolabs ever define this?
        t.setProperty("ACFeatBonus", AC.fromdodge); //TODO: anything other than dodge here?
        t.setProperty("ACMiscBonus1", AC.frommisc);
        t.setProperty("ACMiscBonus2", "0");
        t.setProperty("ACTempBonus", "0");
        t.setProperty("SizeMod", AC.fromsize);

        //finally do the enhancement bonus
        int enhancementbonus = Integer.parseInt(AC.fromdeflect) +
                Integer.parseInt(AC.fromnatural);
        t.setProperty("ACEnhBonus", Integer.toString(enhancementbonus));
    }

	private void setHitPoints(Token t) {
		CharacterAttribute con = _character.getConstitution();
		int level = Integer.parseInt( _character.getLevel() );
		int hp = Integer.parseInt( _character.getHitpoints() );
		int conBonus = con.getBonus();
		int baseHitpoints = hp - conBonus * level;
		t.setProperty( CLASS_HP, Integer.toString( baseHitpoints ) );
		t.setProperty(MISC_HP, Integer.toString( hp - baseHitpoints ) );
	}

    private void setPenalties(Token t) {
        //penalties
        Character.Penalty dexPenalty = _character.getPenalty("Max Dex Bonus"); //penalties.get( "Max Dex Bonus");
        if ( dexPenalty != null ) {
            t.setProperty( "ArmorMaxDexBonus", dexPenalty.value);
        }
        Character.Penalty armorCheckPenalty = _character.getPenalty("Armor Check Penalty");
        if ( armorCheckPenalty != null ) {
            t.setProperty( "ArmorCheckPenalty", armorCheckPenalty.value);
        }
    }

    private void setCharacterAttributes(Token t) {
        for (CharacterAttribute ca : _character.getAttributes().values()) {
            t.setProperty(ca.getName(), ca.getValue());
            String enhancementProperty = CharacterAttribute.getShortName(ca.getName()) + "Enhancement";
            t.setProperty(enhancementProperty, Integer.toString((int) Math.floor(ca.getEnhancementBonus() / 2))); //TODO: this is ugly
            String damageProperty = CharacterAttribute.getShortName(ca.getName()) + "Damage";
            t.setProperty(damageProperty, "0");
            if (ca.isVoid()) { //void attributes mess up saves, so...
                String saveShortName = CharacterAttribute.getSaveShortName(ca.getName());
                if (saveShortName != null) {
                    String attrib = saveShortName + "MiscBonus1";
                    t.setProperty(attrib, Integer.toString(ca.getModifiedEnhancementBonus()));
                    attrib = saveShortName + "MiscBonus2";
                    t.setProperty(attrib, "-1"); //TODO: this is a bit of a hack to work around the PF ruleset.
                }
            }
        }
    }

    private Token createToken(Config.ConfigEntry configEntry) throws IOException {
        Asset tokenImage = null;
        File file = new File( configEntry.getImageFilePath());
        tokenImage = AssetManager.createAsset(file);
        AssetManager.putAsset( tokenImage );
        Token t = new Token( _character.getName(), tokenImage.getId());

        t.setImageAsset(tokenImage.getName());
        t.setImageAsset(tokenImage.getName(), tokenImage.getId());

        //set the image shape
        Image image = ImageIO.read(file);
        t.setShape(TokenUtil.guessTokenType(image));


		//set the token size
        String characterSize = _character.getSize();
		if ( characterSize != null && ! characterSize.isEmpty()) {
			SquareGrid grid = new SquareGrid();
			for (TokenFootprint footprint : grid.getFootprints()) {
				if ( characterSize.equals( footprint.getName() ) ) {
					t.setFootprint( grid, footprint );
				}
			}
		}

        //set the other image assets (portrait, charsheet image)
        Asset portrait = AssetManager.createAsset( new File( configEntry.getPortraitFilePath()) );
        AssetManager.putAsset(portrait);
        t.setPortraitImage(portrait.getId());
        t.setCharsheetImage(portrait.getId());

        return t;
    }


    private void loadMacros(Token t) throws IOException {
        List<MacroButtonProperties> macroButtonSet = PersistenceUtil.loadMacroSet(
                new File(this.getClass().getResource("res/TokenLabMacroSet.mtmacset").getFile()));
        t.getMacroNextIndex(); //TODO: this is a hack to create the underlying macroPropertiesMap hash table
        t.replaceMacroList(macroButtonSet);

	}
}
