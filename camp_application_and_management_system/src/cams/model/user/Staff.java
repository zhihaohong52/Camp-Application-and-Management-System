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
	public Staff(String name, String userID, String faculty) {
		super(name, userID, faculty, UserType.Staff);
	}

}
