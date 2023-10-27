/**
 * 
 */
package services;

import interfaces.IUserService;
import model.user.User;
import store.AuthStore;
import store.DataStore;

/**
 * The {@link UserService} class implements the {@link IUserService} interface and 
 * provides functions for user management
 */
public class UserService implements IUserService {

	/**
	 * Constructs an instance of {@link UserService} class
	 */
	public UserService() {}
	
	@Override
	public boolean changePassword(String newPassword) {
		User user = AuthStore.getCurrentUser();
		
		Boolean success = user.setPassword(newPassword);
		
		if(!success) {
			return false;
		}
		else {
			System.out.println("Saving new password");
			DataStore.saveData(); //save new password to database
			return true;
		}	
	}

}
