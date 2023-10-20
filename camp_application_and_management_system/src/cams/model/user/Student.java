/**
 * 
 */
package cams.model.user;

import java.util.ArrayList;
import java.util.List;

import cams.util.UserType;

/**
 * 
 */
public class Student extends User {

	/**
	 * List of registered camps
	 */
	List<String> registeredCamps = new ArrayList<String>();
	
	/**
	 * @param name
	 * @param userID
	 * @param faculty
	 */
	public Student(String name, String userID, String faculty) {
		super(name, userID, faculty, UserType.Student);
	}

}
