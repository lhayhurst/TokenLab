package net.sozinsoft.tokenlab;

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
}
