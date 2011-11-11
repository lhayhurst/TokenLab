package net.sozinsoft.tokenlab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkillReplacer implements IMacroReplacer {

    String skillName;
    String attributeShortName;
    String attributeBonus;
    String skillRanks;

    public SkillReplacer( String skillName, String attributeShortName, String attributeBonus ) {
        this.skillName = skillName;
        this.attributeShortName = attributeShortName;
        this.attributeBonus = attributeBonus;
        this.skillRanks = skillName.replaceAll( "\\s|\\(|\\)", "");
    }
    public String replace(String target) {
        String one   = target.replaceAll("SKILL_NAME", skillName);
        String two   = one.replaceAll("ATTRIBUTE_SHORT_NAME", attributeShortName);
        String three = two.replace( "SKILL_RANKS", skillRanks );
        String four =  three.replace( "ATTRIBUTE_BONUS", attributeBonus );
        return four;
    }
}
