package net.sozinsoft.tokenlab;

import net.rptools.maptool.model.*;
import net.rptools.maptool.util.TokenUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: lhayhurst
 * Date: 12/5/11
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class TokenLabToken {

    public static final String CHARACTER = "Character";
    public static final String RACE = "Race";
    public static final String PLAYER = "Player";
    public static final String ALIGNMENT = "Alignment";
    public static final String DEITY = "Deity";
    public static final String CLASS = "Class";
    public static final String GENDER = "Gender";
    public static final String AGE = "Age";
    public static final String HEIGHT = "Height";
    public static final String WEIGHT = "Weight";
    private static final String SPEED = "Speed";
    private static final String LEVEL = "Level";

    private Token _token;

    public Token getToken() { return _token; }

    public void setCommonProperties(  ICharacter character, HashMap<String, Object> propertyMap ) throws IOException {
        setCoreProperties(character, propertyMap);

    }

    private void setCoreProperties( ICharacter character, HashMap<String, Object> propertyMap) {

        propertyMap.put(CHARACTER, character.getName());
        propertyMap.put(RACE, character.getRace() );
        propertyMap.put(ALIGNMENT, character.getAlignment() );
        propertyMap.put(PLAYER, character.getPlayer());
        propertyMap.put(DEITY, character.getDeity() == null ? "" : character.getDeity() );
        propertyMap.put(GENDER, character.getGender());
        propertyMap.put(AGE, character.getAge().toString() );
        propertyMap.put(HEIGHT, character.getHeight() );
        propertyMap.put(WEIGHT, character.getWeight() );
        propertyMap.put(SPEED, character.getSpeed() );
        propertyMap.put(LEVEL, character.getLevel() );
        propertyMap.put(CLASS, character.getClassAbbreviation() );



    }

    public Token createToken(ICharacter character, Config.ConfigEntry configEntry ) throws IOException {
        _token = new Token();
        Asset tokenImage = null;
        File file = new File(configEntry.getImageFilePath());
        tokenImage = AssetManager.createAsset(file);
        AssetManager.putAsset(tokenImage);
        _token = new Token(character.getName(), tokenImage.getId());


        _token.setImageAsset(tokenImage.getName());
        _token.setImageAsset(tokenImage.getName(), tokenImage.getId());

        //set the image shape
        Image image = ImageIO.read(file);
        _token.setShape(TokenUtil.guessTokenType(image));


        //set the token size
        String characterSize = character.getSize();
        if (characterSize != null && !characterSize.isEmpty()) {
            SquareGrid grid = new SquareGrid();
            for (TokenFootprint footprint : grid.getFootprints()) {
                if (characterSize.equals(footprint.getName())) {
                    _token.setFootprint(grid, footprint);
                }
            }
        }

        //set the other image assets (portrait, charsheet image)
        Asset portrait = AssetManager.createAsset(new File(configEntry.getPortraitFilePath()));
        AssetManager.putAsset(portrait);
        _token.setPortraitImage(portrait.getId());
        _token.setCharsheetImage(portrait.getId());

        return _token;
    }
}
