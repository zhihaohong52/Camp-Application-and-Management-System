/**
 * 
 */
package cams.user;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Student extends User {

	/**
	 * List of registered camps
	 */
	List<String> registeredCamps = new ArrayList<>();
	
	/**
	 * @param userID
	 * @param password
	 * @param faculty
	 */
	public Student(String userID, String password, String faculty) {
		super(userID, password, faculty);
	}

}
