/**
 * 
 */
package model.user;

import java.util.Scanner;
import java.util.regex.Pattern;

import util.Schools;
import util.UserRole;

import java.util.regex.Matcher;

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
	
	private boolean firstLogin;
	
	/**
	 * Create an object of the {@link User} class
	 * 
	 * @param name
	 * @param userID
	 * @param faculty
	 */
	public User(String name, String password, String userID, String email, Schools faculty, UserRole type) {
		this.name = name;
		this.userID = userID;
		this.email = email;
		this.faculty = faculty;
		this.password = password;
		this.firstLogin = true;
		this.type = type;
	}
	
	/**
	 * Gets the user's name
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the user's ID
	 * @return userID
	 */
	public String getID() {
		return this.userID;
	}
	
	/**
	 * Gets the user's password
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Set password
	 */
	public boolean setPassword(String oldPassword, String newPassword) {
		if (!oldPassword.equals(newPassword))
			return false;
		this.password = newPassword;
		this.removeDefault();
		return true;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the user's faculty info
	 * @return faculty
	 */
	public Schools getFaculty() {
		return this.faculty;
	}

	/**
	 * Gets the user's role
	 * @return type
	 */
	public UserRole getType() {
		return type;
	}

	/**
	 * Check's if it's user's first login
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
