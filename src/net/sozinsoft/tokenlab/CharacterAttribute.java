package net.sozinsoft.tokenlab;

import java.util.HashMap;

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
	
	private String _value;
	
	public String getValue() {
		return _value;
	}
	private String _modifiedValue;

	private int _bonus;
	
	public int getBonus() {
		return _bonus;
	}
		
	public int getEnhancementBonus() {
		return _bonus;
	}

	private int _modifiedBonus;
	private String _text;

	public int getModifiedEnhancementBonus() {
		return _modifiedBonus;
	}

	public CharacterAttribute() { 
		
	}
	
	private static HashMap<String, String> attributeShortNameMap = new HashMap<String,String>();
	private static HashMap<String, String> saveShortNameMap = new HashMap<String,String>();
	static {
		attributeShortNameMap.put("Strength", "Str" );
		attributeShortNameMap.put("Constitution", "Con" );
		attributeShortNameMap.put("Wisdom", "Wis" );
		attributeShortNameMap.put("Dexterity", "Dex" );
		attributeShortNameMap.put("Charisma", "Cha" );
		attributeShortNameMap.put("Intelligence", "Int" );
		
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
		this._value         = ca._value;
		this._modifiedValue = ca._modifiedValue;
		this._bonus         = ca._bonus;
		this._modifiedBonus = ca._modifiedBonus;
		this._text          = ca._text;
	}

	//called from digester
	public void setValue(String value, String modified, String text ) {
		_value         = value;
		_text          = text;
		_modifiedValue = modified;
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

