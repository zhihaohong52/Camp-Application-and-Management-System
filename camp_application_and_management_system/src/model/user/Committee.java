/**
 * 
 */
package model.user;

import enums.Schools;

/**
 *
 */
public class Committee extends Student {

	/**
	 * Camp where user is a committee member
	 */
	
	public int campID;

	/**
	 * @param userID
	 * @param password
	 * @param faculty
	 */
	public Committee(String name, String password, String userID, String email, Schools faculty, boolean firstLogin, int campID) {
		super(name, password, userID, email, faculty, firstLogin);
		this.campID = campID;
	}

	/**
	 * @return
	 */
	public int getCampID() {
		return campID;
	}
}
