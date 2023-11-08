/**
 * 
 */
package services;

import java.util.Map;

import model.user.Staff;
import stores.AuthStore;
import stores.DataStore;

/**
 * {@link AuthStaffService} extends {@link AuthService} and 
 * provides authentication functions for Staff. 
 */
public class AuthStaffService extends AuthService {

	/**
	 * Construct an instance of {@link AuthStaffService}
	 */
	public AuthStaffService() {}

	@Override
	public boolean login(String userID, String password) {
		Map<String, Staff> staffData = DataStore.getStaffData();
		
		Staff staff = staffData.get(userID);
		
		if (authenticate(staff, password)) {
			AuthStore.setCurrentUser(staff);
			return true;
		}
		
		return false;
	}

}
