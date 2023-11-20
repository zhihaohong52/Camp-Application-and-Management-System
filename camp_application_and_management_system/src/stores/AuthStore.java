/**
 * 
 */
package stores;

import model.user.User;

/**
 * The {@link AuthStore} class provides utility methods for managing the
 * current authenticated user within the application.
 * It offers methods to get and set the current authenticated user,
 * as well as check if the user is logged in.
 */
public class AuthStore {

	/**
	 * The currently authenticated user.
	 */
	private static User currentUser;
	
	/**
	 * Private constructor to prevent instantiation of the class.
	 */
	private AuthStore() {}

	/**
	 * Gets the current user from the AuthStore
	 * 
	 * @return current {@link User} instance currentUser, or {@code null} if the user is not logged in
	 */
	public static User getCurrentUser() {
		return AuthStore.currentUser;
	}

	/**
	 * Set the current user in the AuthStore
	 * @param currentUser the currentUser to set as the current user with {@link User} instance
	 */
	public static void setCurrentUser(User currentUser) {
		AuthStore.currentUser = currentUser;
	}
	
	/**
	 * Checks if the user is logged in.
	 * 
	 * @return {@code true} if the user is logged in, otherwise {@code false}
	 */
	public static boolean isLoggedIn() {
		return currentUser != null;
	}
}
