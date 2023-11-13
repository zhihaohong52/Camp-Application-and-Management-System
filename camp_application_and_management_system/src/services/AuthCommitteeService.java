/**
 * 
 */
package services;


import java.util.Map;

import model.user.Committee;
import model.user.Student;
import stores.AuthStore;
import stores.DataStore;

/**
 * {@link AuthCommitteeService} extends {@link AuthStudentService} and 
 * provides authentication functions for Committee. 
 */
public class AuthCommitteeService extends AuthStudentService {

	/**
	 * Construct an instance of {@link AuthCommitteeService}
	 */
	public AuthCommitteeService() {}

	@Override
	public void login(String userID) {
		Map<String, Committee> committeeData = DataStore.getCommitteeData();
		
		Committee committee = committeeData.get(userID);
		
		AuthStore.setCurrentUser(committee);
	}
	
	/**
	 * Log out of committee member user 
	 * @param userID the current logged in {@link Student} user
	 */
	public void logout(String userID) {
		Map<String, Student> studentData = DataStore.getStudentData();
		
		Student student = studentData.get(userID);
		
		AuthStore.setCurrentUser(student);
	}

}
