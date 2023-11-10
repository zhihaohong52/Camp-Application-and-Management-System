
package interfaces;

/**
 * The {@link IUserService} interface defines a contract for user services
 */
public interface IUserService {

	/**
	 * Changes the password of user if provided old password matches current password
	 * 
	 * @param newPassword user's new password 
	 * @return {@code True} if password was changed successfully, {@code False} otherwise
	 */
	public boolean changePassword(String newPassword);
}
