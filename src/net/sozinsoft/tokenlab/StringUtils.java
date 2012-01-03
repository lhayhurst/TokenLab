package net.sozinsoft.tokenlab;

import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: lhayhurst
 * Date: 12/5/11
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {

        public static String replacePlus(String value) {
		if ( value != null && value.length() > 0 && value.charAt(0) == '+') {
			String tmp = value.replace("+", "");
			return tmp;
		}
		return value;
	}

    public static String removeCommas( String value ) {
        return value.replaceAll( ",", "" );
    }

    static Pattern XNRegex = Pattern.compile("^.*\\s+\\(x(\\d+)");
    public static int isXN(String name) {
        java.util.regex.Matcher matcher = XNRegex.matcher(name);
        int numRepeats = 0;
        if (matcher.find()) {
            numRepeats = Integer.parseInt(matcher.group(1));
        }
        return numRepeats;

    }

    public static String removeXN(String name) {
        String ret = name.replaceAll("\\s+\\(x\\d+\\).*$", "");
        return ret;
    }
}
