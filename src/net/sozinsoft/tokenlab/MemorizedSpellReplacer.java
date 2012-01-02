package net.sozinsoft.tokenlab;

/**
 * Created by IntelliJ IDEA.
 * User: lhayhurst
 * Date: 12/30/11
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class MemorizedSpellReplacer implements IMacroReplacer {
    private Integer level = 0;
    private static final String SUBSTITUTE_LEVEL_HERE = "$LEVEL";

    public MemorizedSpellReplacer (Integer level) {
        this.level = level;
    }

    public String replace(String target) {
        String ret = target.replace(SUBSTITUTE_LEVEL_HERE, level.toString() );
        return ret;
    }
}
