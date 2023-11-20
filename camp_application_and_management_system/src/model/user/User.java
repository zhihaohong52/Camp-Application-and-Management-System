/**
 * 
 */
package model.user;

import enums.Schools;
import enums.UserRole;

/**
 * Represents a user using the system
 */
public class User {
	
	/**
	 * Unique NTU ID of the user.
	 */
	private String userID;
	
	/**
	 * Password of the user.
	 */
	private String password;
	
	/**
	 * Faculty information.
	 */
	private Schools faculty;
	
	/**
	 * Name of user.
	 */
	private String name;
	
	/**
	 * {@link UserRole} of the user.
	 */
	private UserRole type;
	
	/**
	 * Variable to check for first login
	 */
	
	private String email;
	
	/**
	 * Boolean to check if it is user's first login
	 */
	private boolean firstLogin;
	
	/**
	 * Create an object of the {@link User} class
	 * @param name user's name
	 * @param password user's password
	 * @param userID user's ID
	 * @param email user's email
	 * @param faculty user's faculty info
	 * @param type user's role
	 * @param firstLogin Check if it's user's first login
	 */
	public User(String name, String password, String userID, String email, Schools faculty, UserRole type, boolean firstLogin) {
		this.name = name;
		this.userID = userID;
		this.email = email;
		this.faculty = faculty;
		this.password = password;
		this.type = type;
		this.firstLogin = firstLogin;
	}
	
	/**
	 * Gets the user's name
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the user's ID
	 * 
	 * @return userID
	 */
	public String getID() {
		return this.userID;
	}
	
	/**
	 * Gets the user's password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Set password
	 * @param newPassword The new password set by the user
	 * @return boolean that show password set success 
	 */
	public boolean setPassword(String newPassword) {
	
		this.password = newPassword;
		
		if (this.firstLogin) {
			this.removeDefault();
		}
		
		return true;
	}
	
	/**
	 * Gets the email of the user 
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the user's faculty info
	 * 
	 * @return faculty
	 */
	public Schools getFaculty() {
		return this.faculty;
	}

	/**
	 * Gets the user's role
	 * 
	 * @return type of user's role
	 */
	public UserRole getType() {
		return type;
	}

	/**
	 * Sets the usertype 
	 * 
	 * @param type the type to set
	 */
	public void setType(UserRole type) {
		this.type = type;
	}

	/**
	 * Check's if it's user's first login
	 * 
	 * @return firstLogin
	 */
	public boolean isFirstLogin() {
		return firstLogin;
	}

	/**
	 * Change firstLogin boolean to false after removing the default password
	 */
	public void removeDefault() {
		this.firstLogin = false;
	}
}
