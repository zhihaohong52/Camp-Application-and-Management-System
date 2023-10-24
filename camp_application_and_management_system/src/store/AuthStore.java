/**
 * 
 */
package store;

import model.user.User;

/**
 * 
 */
public class AuthStore {

	/**
	 * 
	 */
	private static User currentUser;
	
	/**
	 * 
	 */
	private AuthStore() {}

	/**
	 * @return the currentUser
	 */
	public static User getCurrentUser() {
		System.out.println(currentUser);
		return AuthStore.currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public static void setCurrentUser(User currentUser) {
		AuthStore.currentUser = currentUser;
	}
	
	/**
	 * @return
	 */
	public static boolean isLoggedIn() {
		return currentUser != null;
	}
}
