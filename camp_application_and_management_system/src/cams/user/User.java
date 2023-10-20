/**
 * 
 */
package cams.user;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Represents a user using the system
 * @author Hong Zhi Hao
 * @version 1.0
 * @since 2023-10-20
 */
public class User {
	/**
	 * Unique ntu id
	 */
	public String userID;
	
	/**
	 * Password for user
	 */
	protected String password;
	
	/**
	 * Faculty information
	 */
	public String faculty;
	
	/**
	 * Variable to check for first login
	 */
	
	public boolean firstLogin;
	
	/**
	 * Creates a new user with the userID. Password and faculty info is also stored
	 * @param userID
	 * @param password
	 * @param faculty
	 */
	
	public User(String userID, String password, String faculty) {
		this.userID = userID;
		this.password = password;
		this.faculty = faculty;
		this.firstLogin = true;
	}
	
	
	/**
	 * Gets the user's ID
	 * @return userID
	 */
	public String getID() {
		return this.userID;
	}
	
	/**
	 * Set password
	 */
	public void setPassword() {
		/*password requirements*/
		System.out.println("Password must be\n "
				+ "i. more than 8 characters\n"
				+ "ii. contain at least one lower case and one upper case letter\n"
				+ "iii. contain at least one digit\n"
				+ "iv. contain at least one special character");
		System.out.println("Please set a new password: ");
		
		Scanner sc = new Scanner(System.in);
		String newPassword = sc.nextLine();
		boolean validPassword = false;
		
		do {
			if (newPassword.length()>8) {
			// Check for at least 1 upper case, 1 lower case, 1 digit, and 1 special character
		        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=])";
		        Pattern pattern = Pattern.compile(regex);
		        Matcher matcher = pattern.matcher(password);
		        validPassword = matcher.find();
			}
			else
				validPassword = false;
		}while (validPassword = false);
	}
	
	/**
	 * Gets the user's faculty info
	 * @return faculty
	 */
	public String getFaculty() {
		return this.faculty;
	}
}
