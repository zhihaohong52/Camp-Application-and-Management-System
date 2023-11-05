/**
 * 
 */
package util;

import enums.EnquiryStatus;

/**
 * 
 */
public class EnquiryStatusUtil {

	/**
	 * 
	 */
	public EnquiryStatusUtil() {}
	
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
