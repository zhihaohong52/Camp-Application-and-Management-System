/**
 * 
 */
package services;


import java.util.Map;

import model.user.Committee;
import store.AuthStore;
import store.DataStore;

/**
 * {@link AuthCommitteeService} extends {@link AuthService} and 
 * provides authentication functions for Staff. 
 */
public class AuthCommitteeService extends AuthService {

	/**
	 * Construct an instance of {@link AuthCommitteeService}
	 */
	public AuthCommitteeService() {	}

	@Override
	public boolean login(String userID, String password) {
		Map<String, Committee> committeeData = DataStore.getCommitteeData();
		
		Committee committee = committeeData.get(userID);
		
		if (authenticate(committee, password)) {
			AuthStore.setCurrentUser(committee);
			return true;
		}
		
		return false;
	}

}
