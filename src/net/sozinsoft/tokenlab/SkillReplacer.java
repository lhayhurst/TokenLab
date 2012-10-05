package net.sozinsoft.tokenlab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkillReplacer implements IMacroReplacer {

    String skillName;
    String attributeShortName;
    private static final String SELF_ONLY = "$SELF";
    private static final String SELF_MACRO_TEXT = "/self";
    private boolean isNPC ;

    public SkillReplacer( boolean isNPC, String skillName, String attributeShortName ) {
        this.skillName = skillName;
        this.attributeShortName = attributeShortName;
        this.isNPC = isNPC;
    }
    public String replace(String target) {
        String one   = target.replaceAll("SKILL_NAME", skillName);
        String two   = one.replaceAll("ATTRIBUTE_SHORT_NAME", attributeShortName);
        String three = "";
        if ( isNPC ) {
            three = two.replace( SELF_ONLY, SELF_MACRO_TEXT );
        }
        else {
            three = two.replace( SELF_ONLY, "" );
        }
        return three;
    }
}
