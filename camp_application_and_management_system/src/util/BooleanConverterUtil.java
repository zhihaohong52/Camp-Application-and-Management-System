package util;

/**
 * The {@link BooleanConverterUtil} provides a function that allows conversion of
 * given input to a boolean variable for visibility or first login checking
 */
public class BooleanConverterUtil {

	/**
	 * Construct an instance of {@link BooleanConverterUtil}
	 */
	public BooleanConverterUtil() {}

	/**
	 * Converts a string input to a boolean variable
	 * @param input the string to be converted into boolean
	 * @return the boolean based on received input
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
