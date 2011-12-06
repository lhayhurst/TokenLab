package net.sozinsoft.tokenlab;

import net.rptools.maptool.model.Token;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: lhayhurst
 * Date: 12/5/11
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ITokenizable {
    public String getName();
    Token asToken(Config.ConfigEntry ce) throws Exception;
}
