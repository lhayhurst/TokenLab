package net.sozinsoft.tokenlab;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import net.rptools.maptool.model.Asset;
import net.rptools.maptool.model.AssetManager;
import net.rptools.maptool.model.MacroButtonProperties;
import net.rptools.maptool.model.SquareGrid;
import net.rptools.maptool.model.Token;
import net.rptools.maptool.model.TokenFootprint;
import net.rptools.maptool.model.Token.Type;
import net.rptools.maptool.util.PersistenceUtil;
import net.rptools.maptool.util.TokenUtil;
import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Character {
		
	private static final String HEROLABS_FIREARM_PROJECTILE_WEAPON = "Firearm, Projectile Weapon";
	private static final String HEROLABS_PROJECTILE_WEAPON = "Projectile Weapon";
	private static final String HEROLABS_MELEE_WEAPON = "Melee Weapon";
	private static final String HEROLABS_WEAPON_OFFHAND = "offhand";
	private static final String HEROLABS_WEAPON_MAINHAND = "mainhand";
	private static final String CLASS_HP = "ClassHP";
	private String _name;
	private String _race;
	private String _hitpoints;
	private String _role;
	private final HashMap<String, CharacterAttribute> cattributes = new HashMap<String,CharacterAttribute>();
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
	
	private class CharacterClass {
		public String ClassName;
		public String ClassLevel;
	}
	
	public void setLevel( String level, String classSummaryAbbreviation ) {
		this._level = level;
		this._classSummaryAbbreviation = classSummaryAbbreviation;
	}

	private final LinkedList<CharacterClass> classes = new LinkedList<CharacterClass>();

	public void addClass( String name, String level ) {
		CharacterClass c = new CharacterClass();
		c.ClassName = name;
		c.ClassLevel = level;
		classes.add( c );
	}
	
	private class Save {
		public String name;
		public String base;
		public String frommisc;
		public String fromattr;
		public String fromresist;
		public String save;
		public String abbr;		
	}
	
	private HashMap <String, Save> saves = new HashMap<String, Save>();
	
	public void addSave( String name, String abbr, String base, String frommisc ,
			             String fromresist ,String fromattr ,String save )
	{
		Save s = new Save();
		s.name = name;
		s.abbr = abbr;
		s.base = massageBonusValue(base);
		s.frommisc = massageBonusValue(frommisc);
		s.fromresist = massageBonusValue(fromresist);
		s.fromattr = massageBonusValue(fromattr);
		s.save = massageBonusValue(save);
		saves.put( s.name, s);
	}
	
	private class ArmorClass {
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
	private String _baseAttackBonus;

	public Token asToken( Config.ConfigEntry configEntry ) {
		
		
		Asset tokenImage = null;
		File file = new File( configEntry.getImageFilePath());
		try {
			tokenImage = AssetManager.createAsset( file  );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		AssetManager.putAsset( tokenImage );
		Token t = new Token( this._name, tokenImage.getId());
		
		loadMacros(t);

		t.setPropertyType( "Pathfinder");
		t.setProperty("Race", _race );
		t.setProperty( "Player", this._playername);
		t.setProperty( "Alignment", this._alignment);
		t.setImageAsset( tokenImage.getName() );
		t.setImageAsset( tokenImage.getName(), tokenImage.getId());        
        setCharacterAttributes(t);
        
        if (_role.equals("npc")) {
        	t.setType( Type.NPC);
        }
        else {
        	t.setType( Type.PC);
        }
        

        setTokenShape(file, t); 
        setHitPoints(t);
        setArmorClass(t);
		setImageAssets( new File( configEntry.getPortraitFilePath()), t);

		t.setProperty("Class", this._classSummaryAbbreviation);
		t.setProperty("Level", this._level);
		
		//penalties
		Penalty dexPenalty = penalties.get( "Max Dex Bonus");
		if ( dexPenalty != null ) {
			t.setProperty( "ArmorMaxDexBonus", dexPenalty.value);
		}
		Penalty armorCheckPenalty = penalties.get( "Armor Check Penalty");
		if ( armorCheckPenalty != null ) {
			t.setProperty( "ArmorCheckPenalty", armorCheckPenalty.value);
		}
		
		t.setProperty("BaseAttackBonus", this._baseAttackBonus);
		
		t.setProperty("Character", this._name);
		
		t.setProperty( "Gender", _gender);
		t.setProperty( "Age", _age);
		t.setProperty( "Height", _height);
		t.setProperty( "Weight", _weight);
		t.setProperty( "Speed", _speed ); 
				
		for ( String saveName : saves.keySet()) {
		    Save s = saves.get(saveName);
		    t.setProperty( s.abbr + "ClassBonus", s.base);
	        t.setProperty( s.abbr + "FeatBonus", s.frommisc);
		    t.setProperty( s.abbr + "EnhBonus", s.fromresist);
		    String miscBonus1 = s.abbr + "MiscBonus1";
		    if ( t.getProperty(miscBonus1) == null ) {
		    	t.setProperty(  miscBonus1, "0");
		    }
		    String miscBonus2 = s.abbr + "MiscBonus2";
		    if ( t.getProperty(miscBonus2) == null ) {
		    	t.setProperty( miscBonus2, "0");
		    }
		}
		
		//weapons
		setWeaponProperties( HEROLABS_WEAPON_MAINHAND, t);
		setWeaponProperties( HEROLABS_WEAPON_OFFHAND, t);
	
		//skills
		for( Skill skill: this.skills.values()) {
			t.setProperty( skill.skillName + "Ranks", skill.ranks );
			t.setProperty( skill.skillName + "Bonus", skill.attrBonus);
			if ( skill.isClassSkill == true ) { 
				t.setProperty( skill.skillName + "ClassSkill", "1"); //TODO: for now, all skills are class skills, but this isn't really true
			}
		}
		
		//init
		t.setProperty( "InitMod", Integer.toString( initiative.initModifier()));
		
		//size
		//TODO: ask on maptool forums how to set this.  looks like the guids live in squareGridFootprints.xml
		if ( this._size != null && ! this._size.isEmpty()) {
			SquareGrid grid = new SquareGrid(); 
			for (TokenFootprint footprint : grid.getFootprints()) {
				if ( this._size.equals( footprint.getName() ) ) {
					t.setFootprint( grid, footprint );
				}
			}
		}
		
		//deity
		t.setProperty( "Deity", this._deity);
		
		return t;
		
	}


	private void setWeaponProperties(String targetWeaponHand, Token t) {
		Weapon targetWeapon = null;
		for( Weapon w: this.weapons.values()) {
			if ( targetWeaponHand.equals(w.equipped) )
			{
				targetWeapon = w;
				break;
			}
		}
		if ( targetWeapon != null ) {
			String prefix = null;
			String abilityBonusPropertyName = null;
			if( targetWeaponHand == HEROLABS_WEAPON_MAINHAND ) {
				prefix = "Weapon"; //TODO refactor constant to PF  Macro set constants land
				abilityBonusPropertyName = "WepAbilBonus"; //TODO refactor constant to PF  Macro set constants land
			}
			else //offhand
			{
				prefix = "Offhand";
			    abilityBonusPropertyName = "OffhAbilBonus"; //TODO refactor constant to PF  Macro set constants land
			}
			setWeaponProperties( t, targetWeapon, prefix, abilityBonusPropertyName );
		}
		
	}


	private void setWeaponProperties(Token t, Weapon w, String prefix, String abilityBonusPropertyName) {
		t.setProperty( prefix + "Name", w.name);
		t.setProperty( abilityBonusPropertyName, inferAbilityBonus(w)); //TODO: dynamically figure this one out
		t.setProperty( prefix + "Dice", w.damage.asExpression() );
		t.setProperty( prefix + "DamageType", w.weaponType);
		t.setProperty( prefix + "EnhBonus", Integer.toString( w.enhancementBonus) );
		t.setProperty( prefix + "MiscDmgBonus", Integer.toString( w.damage.bonusDamage));
		t.setProperty( prefix + "MiscAtkBonus", "0" ); //TODO: should this be something?
		t.setProperty( prefix + "CritsOn", Integer.toString( w.critFloor ) );
	}


	private String inferAbilityBonus(Weapon w) {
		if ( w.categorytext.equals(HEROLABS_MELEE_WEAPON)) {
			if ( ! hasWeaponFinesseFeat() ) {
				return "StrBonus"; //TODO: check for greatswords/etc, which are 1.5  ...
			} else {
				return "DexBonus";
			}
		} else if ( w.categorytext.equals(HEROLABS_PROJECTILE_WEAPON)) {
			return "DexBonus";
		} else if ( w.categorytext.equals( HEROLABS_FIREARM_PROJECTILE_WEAPON )) {
			return "IntBonus";
		}
		else {
			System.out.println( "Unable to infer weapon bonus from weapon " + w.name + " with category " + w.categorytext);
			return "StrBonus"; //TODO: as good as a default as any
		}
	}

	private boolean hasWeaponFinesseFeat() {
		return false; //TODO: implement me
	}

	private void loadMacros(Token t) {
		//load the macro set
		try {
			List<MacroButtonProperties> macroButtonSet = PersistenceUtil.loadMacroSet( 
					new File ("C:/Users/lhayhurst/Documents/My Dropbox/Maptools/macros/PF/PF-NewMacroSet.mtmacset"));
			t.getMacroNextIndex(); //TODO: this is a hack to create the underlying macroPropertiesMap hash table
			t.replaceMacroList( macroButtonSet );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void setCharacterAttributes(Token t) {
		for( CharacterAttribute ca : cattributes.values() ) {
        	t.setProperty(ca.getName(), ca.getValue() );
        	String enhancementProperty = CharacterAttribute.getShortName( ca.getName() ) + "Enhancement";
        	t.setProperty(enhancementProperty, Integer.toString( (int)Math.floor( ca.getEnhancementBonus() / 2 ) ) ); //todo: this is ugly
        	String damageProperty = CharacterAttribute.getShortName( ca.getName() ) + "Damage";
        	t.setProperty(damageProperty, "0");
        	if ( ca.isVoid()) { //void attributes mess up saves, so...
        		String saveShortName = CharacterAttribute.getSaveShortName( ca.getName() );
        		if ( saveShortName != null ) {
        		    String attrib = saveShortName + "MiscBonus1";
        		    t.setProperty( attrib, Integer.toString( ca.getModifiedEnhancementBonus() ) );
        		    attrib = saveShortName + "MiscBonus2";
        		    t.setProperty( attrib, "-1" ); //TODO: this is a bit of a hack to work around the PF ruleset.
        		}
        	}     	
        }
	}

	

	private void setHitPoints(Token t) {
		CharacterAttribute con = cattributes.get("Constitution");
		int level = Integer.parseInt( this._level );
		int hp = Integer.parseInt( this._hitpoints );
		int conBonus = con.getBonus();
		int baseHitpoints = hp - conBonus * level;
		t.setProperty( CLASS_HP, Integer.toString( baseHitpoints ) );
		t.setProperty( "MiscHP", Integer.toString( hp - baseHitpoints ) );
	}
	
	
	private void setArmorClass(Token t) {
		t.setProperty( "ACArmorBonus", this._armorClass.fromarmor ) ;
		t.setProperty( "ACShieldBonus",this._armorClass.fromshield) ;
		t.setProperty( "DexBonus", this._armorClass.fromdexterity) ;
		t.setProperty( "ACClassBonus", "0"); //TODO: does herolabs ever define this?
		t.setProperty( "ACFeatBonus", this._armorClass.fromdodge) ; //TODO: anything other than dodge here?
		t.setProperty( "ACMiscBonus1", this._armorClass.frommisc) ;
		t.setProperty( "ACMiscBonus2", "0");
		t.setProperty( "ACTempBonus", "0");
		t.setProperty( "SizeMod", this._armorClass.fromsize) ;
		
		//finally do the enhancement bonus
		int enhancementbonus = Integer.parseInt( this._armorClass.fromdeflect) +
		                       Integer.parseInt( this._armorClass.fromnatural);
		t.setProperty("ACEnhBonus", Integer.toString( enhancementbonus ));

	}


	private void setImageAssets( File file, Token t) {
		try {
			Asset portrait = AssetManager.createAsset(file );
			AssetManager.putAsset( portrait );
		    t.setPortraitImage(portrait.getId()); 
			t.setCharsheetImage(portrait.getId());

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void setTokenShape(File file, Token t) {
		Image image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		t.setShape( TokenUtil.guessTokenType( image ) );
	}
	
	public void setAttackBonus( String bab ) {
		this._baseAttackBonus = CharacterAttribute.replacePlus(bab);
	}
	
	public void addAttribute( CharacterAttribute ca )
	{
		//for some reason it is always reusing the same instance of the ca ... so a little cheaty hack here
		CharacterAttribute copy = new CharacterAttribute( ca );
		cattributes.put( ca.getName(), copy);
	}
	
	private class Damage {
		private int numDamageDice = 1;
		private int damageDice = 1;
		private int bonusDamage = 0;
		
		public Damage( String expression ) {
			Pattern regex = Pattern.compile("(\\d+)d*(\\d*)\\+*(\\d*)" );
			java.util.regex.Matcher matcher = regex.matcher( expression );
			if ( matcher.matches() )
			{
				String ndd = matcher.group(1);
				String dd  = matcher.group(2);
				String bd  = matcher.group(3);
				if ( ndd != null && ! ndd.isEmpty()) {
					numDamageDice = Integer.parseInt(ndd);
				}
				if ( dd != null  && ! dd.isEmpty() ) {
					damageDice = Integer.parseInt(dd);
				}
				if ( bd != null && ! bd.isEmpty()) {
					bonusDamage = Integer.parseInt( bd );
				}
			}
			else
			{
				//TODO: throw exception, invalid damage expression
			}
		}

		public String asExpression() {
			return Integer.toString(this.numDamageDice) + "d" + Integer.toString( this.damageDice );
			
		}
	}
	
	class Weapon {
		String name; 
		Damage damage;
		String categorytext;
		int critFloor;
		int critMultiplier;
		int enhancementBonus = 0;
		String equipped;
		String weaponType;
		
		private LinkedList<String> attacks = new LinkedList<String>();
		
		public Weapon (String name, String damage, String categorytext, String crit, 
			               String attackBonus, String equipped, String weaponType ) {
			this.name = name; 
			parseEnhancementBonus( name );
			this.damage = new Damage( damage );
			this.categorytext = categorytext;
			parseCrit(crit);
			parseAttackBonus( attackBonus );
			this.equipped = equipped;
			this.weaponType = weaponType;
		}
		
		//this method is a bit of a hack as herolabs doesn't provide an actual enhancement bonus in its xml
		//TODO: http://forums.wolflair.com/showthread.php?p=65056&posted=1#post65056 thread asking for it
		private void parseEnhancementBonus( String name ) {
			Pattern regex = Pattern.compile( "^\\+(\\d+)" );
			java.util.regex.Matcher matcher = regex.matcher( name );
			if ( matcher.find() )
			{
				this.enhancementBonus = Integer.parseInt(matcher.group(1));
			}
		}
		private void parseCrit( String crit ) {
			Pattern regex = Pattern.compile( "^(\\d+).*(\\d+)$" );
			java.util.regex.Matcher matcher = regex.matcher( crit );
			if ( matcher.matches() )
			{	
				critFloor      = Integer.parseInt( matcher.group(1) );
				critMultiplier = Integer.parseInt( matcher.group(2) );
			}
		}
		
		private void parseAttackBonus( String attackBonus ) {
			Pattern regex = Pattern.compile("\\d+" );
			java.util.regex.Matcher matcher = regex.matcher( attackBonus );
			while( matcher.find() ) {
				String ab = matcher.group();
				attacks.add(ab);
			}
		}
	}
	
	private HashMap <String, Weapon> weapons = new HashMap<String, Weapon>();
	public void addWeapon( String name, String damage, String categorytext, String crit, 
			               String attackBonus, String equipped, String weaponType ) 
	{
		Weapon w = new Weapon( name, damage, categorytext, crit, attackBonus, equipped, weaponType );
		weapons.put( name, w );
	}
	
	public void setArmorClassBasics( String touchAC, String flatFootedAC, String armorClass  ) {
		if ( _armorClass == null ) {
			this._armorClass = new ArmorClass();
		}
		_armorClass.AC = armorClass;
		_armorClass.touchAC = touchAC;
		_armorClass.flatFootedAC = flatFootedAC;
	}
	
	public void setArmorClassFroms( String frommisc, String fromdodge, String fromdeflect, String fromnatural, String fromsize,
			                        String fromdexterity, String fromshield, String fromarmor ) {
		if ( _armorClass == null ) {
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
	
	public void setRace( String race ) {
		this._race = race;
	}
	
	public void setHitpoints( String hitpoints ) {
		this._hitpoints = hitpoints;
	}
	
	public void setRole( String role ) {
		this._role = role;
	}
		
	private class Skill {
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
			if ( classSkill != null && ! classSkill.isEmpty() && classSkill.equals( "yes" ) )  {
				isClassSkill = true;
			}
			if ( trainedOnly != null && ! trainedOnly.isEmpty() && trainedOnly.equals( "yes" ) )  {
				useTrainedOnly = true;
			}

		}
		
	}
	
	private HashMap<String, Skill> skills = new HashMap<String, Skill>();
	
	public void addSkill( String skillName, String value, String attrName, String attrBonus, 
			              String ranks, String isClassSkill, String useTrainedOnly, String description) {
		Skill s = new Skill( skillName, value, attrName, attrBonus, ranks, isClassSkill, useTrainedOnly, description);
		skills.put( skillName, s );
	}
	
	private class Initiative {
		String total; 
		String attrName;
		String misctext;
		String attrtext;
		
		public Initiative( String total, String attrName, String misctext, String attrtext ) {
			this.total = total;
			this.attrName = attrName;
			this.misctext = misctext;
			this.attrtext = attrtext;
		}
		
		public int initModifier() {
			int itotal = Integer.parseInt( CharacterAttribute.replacePlus(( total ) ) );
			int imisc  = Integer.parseInt( CharacterAttribute.replacePlus(misctext));
			int iattr  = Integer.parseInt(CharacterAttribute.replacePlus( attrtext ) );
			if ( imisc > 0 || iattr > 0 ) {
				return itotal - iattr - imisc;
			}
			else return 0;
		}
	}
	
	private Initiative initiative = null; 
	
	public void addInitiative( String total, String attrName, String misctext, String attrtext )
	{
		initiative = new Initiative( total, attrName, misctext, attrtext);
	}
	
	class Penalty {
		
		public String name;
		public String value;
		public String text;
		
		public Penalty( String name, String value, String text ) {
			this.name = name;
			this.value = value;
			this.text = text;
		}
	}
	
	private HashMap<String, Penalty> penalties = new HashMap<String, Penalty>();


	public void addPenalty( String name, String value, String text ) {
		Penalty p = new Penalty( name, value, text );
		penalties.put( name, p );
	}
	
	public void addSize( String name ) {
		this._size = name;
	}
	
	public void addDeity( String name ) {
		this._deity = name;
	}
	
	public void addPlayerName( String playername ) {
		this._playername = playername;
	}
	
	public void addAlignment( String alignment ) {
		this._alignment = alignment;
	}
	
	public void addPersonal( String gender, String age, String height, String weight )
	{
		this._gender = gender;
		this._age = age;
		this._height = height;
		this._weight = weight;
	}
	
	public void addMovement( String speed ) {
		this._speed = speed;
	}

}
