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
	 * @param userID
	 * @param password
	 * @return
	 */
	default public boolean login(String userID, String password) {
		return false;
	}
	
	/**
	 * Logs in a user with userID (for committee members accessing committee view)
	 */
	default public void login(String userID) {}
	
	/**
	 * Logs out the currently logged in user.
	 * 
	 * @return
	 */
	public boolean logout();
}
