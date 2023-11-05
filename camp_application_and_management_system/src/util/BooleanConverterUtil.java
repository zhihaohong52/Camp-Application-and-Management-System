/**
 * 
 */
package util;



/**
 * 
 */
public class BooleanConverterUtil {

	/**
	 * 
	 */
	public static boolean convertToBoolean(String input) {
		if (input.equals("TRUE") || input.equals("true"))
			return true;
		else if (input.equals("FALSE") || input.equals("false"))
			return false;
		else
			throw new IllegalArgumentException("Input is an invalid value: " + input);
	}

}
