/**
 * 
 */
package util;

import enums.SuggestionStatus;

/**
 * 
 */
public class SuggestionStatusUtil {

	/**
	 * 
	 */
	public SuggestionStatusUtil() {}
	
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
