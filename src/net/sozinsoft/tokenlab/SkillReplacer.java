package net.sozinsoft.tokenlab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkillReplacer implements IMacroReplacer {

    String skillName;
    String attributeShortName;

    public SkillReplacer( String skillName, String attributeShortName ) {
        this.skillName = skillName;
        this.attributeShortName = attributeShortName;

    }
    public String replace(String target) {
        String one   = target.replaceAll("SKILL_NAME", skillName);
        String two   = one.replaceAll("ATTRIBUTE_SHORT_NAME", attributeShortName);
        return two;
    }
}
