package util;

/**
 * The {@link TextDecoratorUtil} class provides utility methods for providing
 * text decoration in the console display for the whole application.
 */
public class TextDecoratorUtil {

	/**
	 * Construct an instance of {@link TextDecoratorUtil}
	 */
	public TextDecoratorUtil() {}

	/**
	 * {@link UNDERLINE} constant ANSI escape code for underline text
	 */
	public static final String UNDERLINE = "\u001B[4m";
	
	/**
	 * {@link BOLD} constant ANSI escape code for bold text
	 */
	public static final String BOLD = "\u001B[1m";
	
	/**
	 * {@link ITALIC} constant ANSI escape code for italic text
	 */
	public static final String ITALIC = "\u001B[3m";
	
	/**
	 * {@link RESET} constant ANSI reset code
	 */
	public static final String RESET = "\u001B[0m";
	
	/**
	 * Formats string to be underlined
	 * 
	 * @param text to be formatted
	 * @return underlined text
	 */
	public static String underlineText(String text) {
		return UNDERLINE + text + RESET;
	}

	/**
	 * Formats string to be bold
	 * 
	 * @param text to be formatted
	 * @return bold text
	 */
	public static String boldText(String text) {
		return BOLD + text + RESET;
	}
	
	/**
	 * Formats string to be italicized
	 * 
	 * @param text to be formatted
	 * @return italic text
	 */
	public static String italicText(String text) {
		return ITALIC + text + RESET;
	}
}
