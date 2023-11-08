/**
 * 
 */
package services;

import java.util.Map;

import model.user.Student;
import stores.AuthStore;
import stores.DataStore;

/**
 * {@link AuthStudentService} extends {@link AuthService} and 
 * provides authentication functions for Students. 
 */
public class AuthStudentService extends AuthService {

	/**
	 * Construct an instance of {@link AuthStudentService}
	 */
	public AuthStudentService() {}

	@Override
	public boolean login(String userID, String password) {
		Map<String, Student> studentData = DataStore.getStudentData();
		
		Student student = studentData.get(userID);
		
		if (authenticate(student, password)) {
			AuthStore.setCurrentUser(student);
		
			return true;
		}
		
		return false;
	}
}
