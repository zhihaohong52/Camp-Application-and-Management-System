/**
 * 
 */
package util;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@link FilePathsUtil} class provides utility methods for managing file
 * paths within the application. It contains a method to return a mapping of CSV
 * file paths for various data types.
 */
public class FilePathsUtil {
	
	/**
	 * A {@link Map} object that contains the file paths for various data types used
	 * in the application. Keys in the map are "user", "student", "staff",
	 * "committee", "camp", "enquiry", and "suggestion"
	 * The corresponding values are the file paths for each data type.
	 */
	private static Map<String, String> filePathsMap = new HashMap<String, String>();
	
	/**
	 * Private constructor to prevent instantiation of the class.
	 */
	private FilePathsUtil() {};
	
	/**
	 * Returns a mapping of CSV file paths for various data types used in the
	 * application. The returned map contains keys such as "user", "student",
	 * "staff", "committee", "camp", "enquiry", and "suggestion",
	 * each associated with their respective file paths.
	 *
	 * @return a {@link Map} containing the CSV file paths for various data types
	 */
	public static Map<String, String> csvFilePaths(){
		filePathsMap.put("user", "data/user.csv");
		filePathsMap.put("student", "data/student.csv");
		filePathsMap.put("staff", "data/staff.csv");
		filePathsMap.put("committee", "data/committee.csv");
		filePathsMap.put("camp", "data/camp.csv");
		filePathsMap.put("enquiry", "data/enquiry.csv");
		filePathsMap.put("suggestion", "data/suggestion.csv");
		
		return filePathsMap;
	}
}
