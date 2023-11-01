/**
 * 
 */
package util;

import java.util.Map;
import model.camp.Camp;

/**
 * 
 */
public class CampUtil {

	/**
	 * 
	 */	
	public static int findLowestAvailableInteger(Map<Integer, Camp> campData) {
        int lowestAvailable = 1; // Start with the first possible integer key

        while (campData.containsKey(lowestAvailable)) {
            lowestAvailable++; // Increment if the key is in use
        }

        return lowestAvailable;
    }

}
