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
	public Staff(String name, String password, String userID, String email, Schools faculty, boolean firstLogin) {
		super(name, password, userID, email, faculty, UserRole.Staff, firstLogin);
	}

}
