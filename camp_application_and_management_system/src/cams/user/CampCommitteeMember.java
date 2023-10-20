/**
 * 
 */
package cams.user;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CampCommitteeMember extends Student {

	/**
	 * List of camps where user is a camp committee member
	 */
	
	List<String> inCommittee = new ArrayList<>();
	
	/**
	 * @param userID
	 * @param password
	 * @param faculty
	 */
	public CampCommitteeMember(String userID, String password, String faculty) {
		super(userID, password, faculty);
		// TODO Auto-generated constructor stub
	}

}
