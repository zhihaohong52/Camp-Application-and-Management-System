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
	
	private int campID;
	
	/**
	 * 
	 */
	private int point;

	/**
	 * @param name
	 * @param password
	 * @param userID
	 * @param email
	 * @param faculty
	 * @param firstLogin
	 * @param campID
	 */
	public Committee(String name, String password, String userID, String email, Schools faculty, boolean firstLogin, int campID) {
		super(name, password, userID, email, faculty, firstLogin);
		this.campID = campID;
		this.point = 0;
	}
	
	/**
	 * @param name
	 * @param password
	 * @param userID
	 * @param email
	 * @param faculty
	 * @param firstLogin
	 * @param campID
	 * @param point
	 */
	public Committee(String name, String password, String userID, String email, Schools faculty, boolean firstLogin, int campID, int point) {
		super(name, password, userID, email, faculty, firstLogin);
		this.campID = campID;
		this.point = point;
	}

	/**
	 * @return
	 */
	public int getCampID() {
		return campID;
	}
	
	public void setCampID(int campID) {
		this.campID = campID;
	}

	/**
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}
}
