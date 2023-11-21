package util;

import java.util.Map;
import model.camp.Camp;
import model.camp.Enquiry;
import model.camp.Suggestion;

/**
 * The {@link IdNumberUtil} class provide utility functions that
 * find the lowest available ID integer for Camp, Enquiry, and Suggestion
 * so that the new object created will take the lowest available ID
 */
public class IdNumberUtil {

    /**
	 * Construct an instance of {@link IdNumberUtil}
	 */
	public IdNumberUtil() {}

	/**
     * Find the lowest available {@link Camp} ID integer from a list of camps
     * @param campData the {@link Map} object with numeric Camp ID key and Camp object as value
     * @return the lowest Camp ID not occupied by a camp
     */
	public static int findLowestAvailableCampId(Map<Integer, Camp> campData) {
        int lowestAvailable = 1; // Start with the first possible integer key

        while (campData.containsKey(lowestAvailable)) {
            lowestAvailable++; // Increment if the key is in use
        }

        return lowestAvailable;
    }
	
    /**
     * Find the lowest available {@link Enquiry} ID integer from a list of enquiries
     * @param enquiryData the {@link Map} object with numeric Enquiry ID key and Enquiry object as value
     * @return the lowest Enquiry ID not occupied by an enquiry
     */
	public static int findLowestAvailableEnquiryId(Map<Integer, Enquiry> enquiryData) {
        int lowestAvailable = 1; // Start with the first possible integer key

        while (enquiryData.containsKey(lowestAvailable)) {
            lowestAvailable++; // Increment if the key is in use
        }

        return lowestAvailable;
    }
	
    /**
     * Find the lowest available {@link Suggestion} ID integer from a list of suggestions
     * @param suggestionData the {@link Map} object with numeric Suggestion ID key and Suggesion object as value
     * @return the lowest Suggestion ID not occupied by an suggestion
     */
	public static int findLowestAvailableSuggestionId(Map<Integer, Suggestion> suggestionData) {
		int lowestAvailable = 1; // Start with the first possible integer key

        while (suggestionData.containsKey(lowestAvailable)) {
            lowestAvailable++; // Increment if the key is in use
        }

        return lowestAvailable;
	}

}
