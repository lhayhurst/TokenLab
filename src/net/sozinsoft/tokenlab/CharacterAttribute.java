package net.sozinsoft.tokenlab;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CharacterAttribute {
	
	private String _name;
	
	public boolean isVoid() { 
		boolean isVoid = _text.equals("-");
		return isVoid;
	}
	
	public void setName( String name ) {
		_name = name;
	}

	public String getName() {
		return _name;
	}
	
	private String _base;
	
	public String getBase() {
		return _base;
	}

	private String _modifiedBase;

	private int _bonus;
	
	public int getBonus() {
		return _bonus;
	}

	private int _modifiedBonus;

	private String _text;

	public int getModifiedBonus() {
		return _modifiedBonus;
	}

    public int getEnhancementBonus() {
        return _modifiedBonus - _bonus;
    }

    public int getEnhancement() {
        return Integer.parseInt(_modifiedBase) - Integer.parseInt(_base);
    }

	public CharacterAttribute() { 
		
	}
	
	private static Map<String, String> attributeShortNameMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
	private static Map<String, String> saveShortNameMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
	static {

        //it looks like the 5.3 version of Herolab took out the long names, and now it is just short name
        //so just stash short name to short name
		attributeShortNameMap.put("Strength", "Str" );
        attributeShortNameMap.put("Str", "Str" );

        attributeShortNameMap.put("Constitution", "Con" );
        attributeShortNameMap.put("Con", "Con" );

        attributeShortNameMap.put("Wisdom", "Wis" );
        attributeShortNameMap.put("Wis", "Wis" );

        attributeShortNameMap.put("Dexterity", "Dex" );
        attributeShortNameMap.put("Dex", "Dex" );

		attributeShortNameMap.put("Charisma", "Cha" );
        attributeShortNameMap.put("Cha", "Cha" );

		attributeShortNameMap.put("Intelligence", "Int" );
        attributeShortNameMap.put("Int", "Int" );

		saveShortNameMap.put("Constitution", "Fort");
		saveShortNameMap.put("Wisdom", "Will");
		saveShortNameMap.put("Dexterity", "Dex");

	}

	public static String getSaveShortName(String name) {
		return saveShortNameMap.get(name);
	}

	public static String getShortName( String longName ) {
		return attributeShortNameMap.get( longName );
	}

	public CharacterAttribute(CharacterAttribute ca) {
		this._name          = ca._name;
		this._base          = ca._base;
		this._modifiedBase = ca._modifiedBase;
		this._bonus         = ca._bonus;
		this._modifiedBonus = ca._modifiedBonus;
		this._text          = ca._text;
	}

	//called from digester
	public void setValue(String value, String modified, String text ) {
		_base          = value;
		_text          = text;
		_modifiedBase = modified;
	}

	//called from digester
	public void setBonus(String value, String modified ) {
		value          = replacePlus(value);
		modified       = replacePlus( modified );
		_bonus         = Integer.parseInt(value);
		_modifiedBonus = Integer.parseInt(modified);
	}

	public static String replacePlus(String value) {
		if ( value.charAt(0) == '+') {
			String tmp = value.replace("+", "");
			return tmp;
		}
		return value;
	}

}

