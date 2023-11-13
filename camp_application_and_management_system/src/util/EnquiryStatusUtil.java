/**
 * 
 */
package util;

import enums.EnquiryStatus;

/**
 * The {@link EnquiryStatusUtil} provides utility function for
 * converts string input to an enum for the enquiry status variable
 */
public class EnquiryStatusUtil {

	/**
	 * Construct an instance of {@link EnquiryStatusUtil}
	 */
	public EnquiryStatusUtil() {}
	
	/**
	 * The function that converts string to enum of enquiry status 
	 * @param input the string input of enquiry status 
	 * @return the enquiry status enum variable for code
	 */
	public static EnquiryStatus convertToEnum(String input) {
		switch(input.toLowerCase()) {
			case "processing":
				return EnquiryStatus.Processing;
			case "processed":
				return EnquiryStatus.Processed;
			default:
	            throw new IllegalArgumentException("Invalid input string: " + input);
		}
	}

}
