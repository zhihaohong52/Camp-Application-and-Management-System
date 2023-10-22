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
	 * Logs in a user with userID and password
	 * 
	 * @param userID
	 * @param password
	 * @return
	 */
	public boolean login(String userID, String password);
	
	/**
	 * Logs out the currently logged in user.
	 * 
	 * @return
	 */
	public boolean logout();
}
