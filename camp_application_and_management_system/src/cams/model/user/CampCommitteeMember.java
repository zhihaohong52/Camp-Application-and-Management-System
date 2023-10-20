/**
 * 
 */
package cams.model.user;

import cams.model.camp.Camp;

/**
 *
 */
public class CampCommitteeMember extends Student {

	/**
	 * Camp where user is a committee member
	 */
	
	public Camp committeeMemberCamp;
	
	/**
	 * @param userID
	 * @param password
	 * @param faculty
	 */
	public CampCommitteeMember(String name, String userID, String faculty, Camp committeeMemberCamp) {
		super(name, userID, faculty);
		this.committeeMemberCamp = committeeMemberCamp;
	}

}
