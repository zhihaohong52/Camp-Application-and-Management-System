
package model.user;

import java.util.ArrayList;
import java.util.List;

import enums.Schools;
import enums.UserRole;

/**
 * This class {@link Student} represents student in the system
 * Students have ability to register for camps. 
 */
public class Student extends User {

	/**
	 * List of registered camps
	 */
	List<String> registeredCamps = new ArrayList<String>();
	
	/**
     * Constructor of class Student
     *
     * @param name Name of the student.
     * @param password Password of the student.
     * @param userID User ID of the student.
     * @param email Email of the student.
     * @param faculty Faculty to which the student belongs.
     * @param firstLogin Flag indicating whether it's the student's first login.
     */
	public Student(String name, String password, String userID, String email, Schools faculty, boolean firstLogin) {
		super(name, password, userID, email, faculty, UserRole.Student, firstLogin);
	}

}
