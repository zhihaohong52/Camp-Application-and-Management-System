/**
 * 
 */
package interfaces;

/**
 * The {@link IUserService} interface defines a contract for user services
 */
public interface IUserService {

	/**
	 * Changes the password of user if provided old password 
	 * matches current password
	 * 
	 * @param oldPassword user's current password
	 * @return true if password was changed successfully, false otherwise
	 */
	public boolean changePassword(String newPassword);
}
