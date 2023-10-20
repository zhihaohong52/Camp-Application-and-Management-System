/**
 * 
 */
package cams.user;

import java.util.ArrayList;
import java.util.List;

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
	public Staff(String userID, String password, String faculty) {
		super(userID, password, faculty);
	}

}
