/**
 * 
 */
package model.user;

import java.util.ArrayList;
import java.util.List;

import enums.Schools;
import enums.UserRole;

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
	public Student(String name, String password, String userID, String email, Schools faculty, boolean firstLogin) {
		super(name, password, userID, email, faculty, UserRole.Student, firstLogin);
	}

}
