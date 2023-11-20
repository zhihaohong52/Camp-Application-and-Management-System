
package model.user;

import enums.Schools;

/**
 * This class {@link Committee} represents committee member of a camp, extending the properties of the class {@link Student}.
 * Committee members are students who actively participate in camps and accrue points.
 */
public class Committee extends Student {

	/**
     * Identifier of the camp where the committee member is associated.
     */
	
	private int campID;
	
	/**
     * Points accrued by the committee member.
     */
	private int point;

	/**
	 * Constructor of class {@code Committee}
	 * 
	 * @param name Name of the committee member.
	 * @param password Password of the committee member.
	 * @param userID User ID of the committee member.
	 * @param email Email of the committee member.
	 * @param faculty Faculty to which the committee member belongs.
	 * @param firstLogin  Flag indicating whether it's the committee member's first login.
	 * @param campID Identifier of the camp where the committee member is associated.
	 */
	public Committee(String name, String password, String userID, String email, Schools faculty, boolean firstLogin, int campID) {
		super(name, password, userID, email, faculty, firstLogin);
		this.campID = campID;
		this.point = 0;
	}
	
	/**
	 * Create an object of the {@link Committee} class
	 * @param name Name of the committee member.
	 * @param password Password of the committee member.
	 * @param userID User ID of the committee member.
	 * @param email Email of the committee member.
	 * @param faculty Faculty to which the committee member belongs.
	 * @param firstLogin  Flag indicating whether it's the committee member's first login.
	 * @param campID Identifier of the camp where the committee member is associated.
     * @param point Points accrued by the committee member.
	 */
	public Committee(String name, String password, String userID, String email, Schools faculty, boolean firstLogin, int campID, int point) {
		super(name, password, userID, email, faculty, firstLogin);
		this.campID = campID;
		this.point = point;
	}

 	/**
     * Gets the identifier of the camp where the committee member is associated.
     *
     * @return The identifier of the camp where the committee member is associated.
     */
	public int getCampID() {
		return campID;
	}
	
	 /**
     * Sets the identifier of the camp where the committee member is associated.
     *
     * @param campID The identifier to set for the camp where the committee member is associated.
     */
	public void setCampID(int campID) {
		this.campID = campID;
	}

	/**
     * Gets the points accrued by the committee member.
     *
     * @return The points accrued by the committee member.
     */
	public int getPoint() {
		return point;
	}

	/**
     * Sets the points accrued by the committee member.
     *
     * @param point The points to set for the committee member.
     */
	public void setPoint(int point) {
		this.point = point;
	}
}
