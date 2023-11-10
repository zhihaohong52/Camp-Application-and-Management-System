
package model.user;

import java.util.ArrayList;
import java.util.List;

import enums.Schools;
import enums.UserRole;

/**
 * This class {@link Staff} represents a staff member within the system.
 * Staff members are responsible for managing camps.
 */
public class Staff extends User {

	/**
	 * list of camps created by staff
	 */
	
	List<String> campsCreated = new ArrayList<>();
	/**
	 * Constructor of class Staff
	 * 
	 * @param name Name of the staff member.
	 * @param password Password of the staff member.
	 * @param userID User ID of the staff member.
	 * @param email Email of the staff member.
	 * @param faculty Faculty to which the staff member belongs.
	 * @param firstLogin Flag indicating whether it's the staff member's first login.
	 */
	public Staff(String name, String password, String userID, String email, Schools faculty, boolean firstLogin) {
		super(name, password, userID, email, faculty, UserRole.Staff, firstLogin);
	}

}
