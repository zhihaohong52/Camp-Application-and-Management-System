/**
 * 
 */
package interfaces;

/**
 * The {@link IAuthService} interface defines the methods for managing
 * authentication services i.e. login and logout.
 */
public interface IAuthService {

	/**
	 * Logs in a user with userID and password (for student and staff)
	 * 
	 * @param userID user's userID
	 * @param password user's password
	 * @return {@code True} if the user is authenticated successfully, {@code False} otherwise
	 */
	default public boolean login(String userID, String password) {
		return false;
	}
	/**
	 * Logs in a user with userID 
	 * 
	 * @param userID Identifier of the user
	 */
	default public void login(String userID) {}
	/**
	 * Logs out the currently logged in user.
	 * @return {@code True} if successfully log out, {@code False} otherwise.
	 */
	public boolean logout();
}
