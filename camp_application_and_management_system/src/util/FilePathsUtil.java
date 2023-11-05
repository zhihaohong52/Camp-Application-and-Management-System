/**
 * 
 */
package util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class FilePathsUtil {
	
	/**
	 * 
	 */
	private static Map<String, String> filePathsMap = new HashMap<String, String>();
	
	/**
	 * 
	 */
	private FilePathsUtil() {};
	
	public static Map<String, String> csvFilePaths(){
		filePathsMap.put("user", "data/user.csv");
		filePathsMap.put("student", "data/student.csv");
		filePathsMap.put("staff", "data/staff.csv");
		filePathsMap.put("committee", "data/committee.csv");
		filePathsMap.put("camp", "data/camp.csv");
		filePathsMap.put("inquiry", "data/inquiry.csv");
		
		return filePathsMap;
	}
}
