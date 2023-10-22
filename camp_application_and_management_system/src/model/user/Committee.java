/**
 * 
 */
package model.user;

import model.camp.Camp;
import util.Schools;

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
	public Committee(String name, String password, String userID, Schools faculty, int campID) {
		super(name, password, userID, faculty);
		this.campID = campID;
	}

	/**
	 * @return
	 */
	public int getCampID() {
		return campID;
	}
}
