/**
 * 
 */
package model.user;

import java.util.ArrayList;
import java.util.List;

import util.Schools;
import util.UserRole;

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
	public Student(String name, String password, String userID, Schools faculty) {
		super(name, password, userID, faculty, UserRole.Student);
	}

}
