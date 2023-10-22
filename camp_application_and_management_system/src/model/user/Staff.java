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
public class Staff extends User {

	/**
	 * list of camps created by staff
	 */
	
	List<String> campsCreated = new ArrayList<>();
	/**
	 * @param userID
	 * @param password
	 * @param faculty
	 */
	public Staff(String name, String password, String userID, Schools faculty) {
		super(name, password, userID, faculty, UserRole.Staff);
	}

}
