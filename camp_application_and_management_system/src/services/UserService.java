/**
 * 
 */
package services;

import interfaces.IUserService;
import model.user.User;
import stores.AuthStore;
import stores.DataStore;
import view.CommonView;

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
			System.out.println("Saving new password...");
			CommonView.printLine();
			DataStore.saveData(); //save new password to database
			return true;
		}	
	}

}
