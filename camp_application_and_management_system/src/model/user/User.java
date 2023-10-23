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
	
	public boolean firstLogin;
	
	/**
	 * Create an object of the {@link User} class
	 * 
	 * @param name
	 * @param userID
	 * @param faculty
	 */
	public User(String name, String password, String userID, Schools faculty, UserRole type) {
		this.name = name;
		this.userID = userID;
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
	public void setPassword() {
		/*password requirements*/
	
		try (Scanner sc = new Scanner(System.in)) {
			boolean reset = false;
			boolean validPassword = false;
			
			do {
			    System.out.println("Password must be:\n"
			            + "i. more than 8 characters\n"
			            + "ii. contain at least one lower case and one upper case letter\n"
			            + "iii. contain at least one digit\n"
			            + "iv. contain at least one special character from the following: ,.<>/:;!@#$%^&*()-_+=]");
			    System.out.println("Please set a new password: ");
			    String newPassword = sc.nextLine();

			    if (newPassword.length() > 8) {
			        // Check for at least 1 upper case, 1 lower case, 1 digit, and 1 special character
			    	String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[,.<>/:;!@#$%^&*()-_+=])";
			        Pattern pattern = Pattern.compile(regex);
			        Matcher matcher = pattern.matcher(newPassword);
			        validPassword = matcher.find();
			        
			        if (validPassword) {
			            System.out.println("Please enter the new password again");
			            String checkPassword = sc.nextLine();

			            if (checkPassword.equals(newPassword)) {
			            	this.password = newPassword;
			            	this.removeDefault();
			                System.out.println("Password reset successfully!");
			                System.out.println("Please login again");
			                reset = true; // Set reset to true to exit the loop                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
			            }
			        }
			    }
			} while (!reset); // Check if reset is true to exit the loop
		}
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
