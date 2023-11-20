/**
 * 
 */
package util;

import enums.SuggestionStatus;

/**
 * The {@link SuggestionStatusUtil} provides utility function for
 * converts string input to an enum for the suggestion status variable
 */
public class SuggestionStatusUtil {

	/**
	 * Construct instance of {@link SuggestionStatusUtil}
	 */
	public SuggestionStatusUtil() {}
	
	/**
	 * Convert from string input to suggestion enum variable
	 * @param input the string input of suggestion status from csv file
	 * @return the suggestion status enum variable for code
	 */
	public static SuggestionStatus convertToEnum(String input) {
		switch(input.toLowerCase()) {
		case "processing":
			return SuggestionStatus.Processing;
		case "approved":
			return SuggestionStatus.Approved;
		case "rejected":
			return SuggestionStatus.Rejected;
		default:
            throw new IllegalArgumentException("Invalid input string: " + input);
	}
	}
}
