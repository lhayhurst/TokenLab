package net.sozinsoft.tokenlab;


import net.rptools.maptool.model.Token;
import net.sozinsoft.tokenlab.d20_dtd.Character;

import java.util.HashMap;

public class D20Token implements ITokenizable, ICharacter {
    public static final String D_20 = "d20";
    private net.sozinsoft.tokenlab.d20_dtd.Character _character;
    private TokenLabToken _token;
    private HashMap<String, Object> _properties = new HashMap<String, Object>();

    public D20Token( Character c ) {
        _character = c;
        _token = new TokenLabToken();
    }


    public Token asToken(Config.ConfigEntry ce) throws Exception {
        _token.createToken( this, ce );
        _token.getToken().setPropertyType(D_20);
        _token.setCommonProperties( (ICharacter)this, _properties );
        for( String propKey : _properties.keySet() ) {
            _token.getToken().setProperty(propKey, _properties.get(propKey).toString());
        }
        return _token.getToken();
    }

    //ICharacter methods
    public String getName() {
        return _character.getName();
    }

    public String getPlayer() {
        return _character.getPlayername();
    }

    public String getRace() {
        return _character.getRace().getName();
    }

    public String getAlignment() {
        return _character.getAlignment().getName();
    }

    public String getDeity() {
        return _character.getDeity().getName();
    }

    public String getGender() {
        return _character.getPersonal().getGender();
    }

    public Integer getAge() {
        return Integer.parseInt(_character.getPersonal().getAge());
    }

    public String getHeight() {
        return _character.getPersonal().getCharheight().getText(); //bug in herolabs output here - it has the weight as the value
    }

    public String getWeight() {
        return _character.getPersonal().getCharweight().getText();

    }

    public Integer getSpeed() {
        return Integer.parseInt(_character.getMovement().getSpeed().getValue());
    }

    public Integer getLevel() {
        return Integer.parseInt( _character.getClasses().getLevel() ) ;
    }

    public String getClassAbbreviation() {
        return _character.getClasses().getSummaryabbr();
    }

    public String getSize() {
        return _character.getSize().getName();
    }

    public Integer getClassHitpoints() {
        return Integer.parseInt( _character.getHealth().getHitpoints() );
    }

    public Integer getBaseAttackBonus() {
        return Integer.parseInt( StringUtils.replacePlus( _character.getAttack().getBaseattack() ) );
    }

    public Integer getBaseAbilityScore(IAttribute attribute) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getBaseAbilityModifier(IAttribute iattribute) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getBonusAbilityScore(IAttribute attribute) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getBonusAbilityModifier(IAttribute atribute) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getAbilityDamage(IAttribute attribute) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getSavingThrowClassBonus(ISavingThrow ist) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getResistanceSavingThrowBonus(ISavingThrow ist) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getMiscellaneousSavingThrowBonus(ISavingThrow ist) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getACArmorBonus() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getACFromShield() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getACFromDeflect() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getACFromDodge() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getACFromnNatural() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getACFromSize() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getACMisc() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getVision() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
