/**
 * 
 */
package controllers;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import interfaces.IUserService;
import services.UserService;

/**
 * The {@link UserController} class is responsible for handling user-related
 * actions, such as changing the user's password. This
 * class serves as a base class for more specific user types like
 * {@link StudentController} or {@link StaffController} or
 * {@link CommitteeController}.
 */
public class UserController {

	/**
	 * {@link Scanner} object to get input from user
	 */
	private static final Scanner sc = new Scanner(System.in);
	
	/**
	 * Constructs an instance of {@link UserController} class
	 */
	public UserController() {}

	protected void changePassword() {
		String oldPassword, newPassword;
		IUserService userService = new UserService();
		
		boolean reset = false;
		boolean validPassword = false;
		boolean success = false;
		
		System.out.println("Enter old password");
		oldPassword = sc.nextLine();
		
		do {
		    System.out.println("Password must be:\n"
		            + "i. more than 8 characters\n"
		            + "ii. contain at least one lower case and one upper case letter\n"
		            + "iii. contain at least one digit\n"
		            + "iv. contain at least one special character from the following: ,.<>/:;!@#$%^&*()-_+=]");
		    System.out.println("Please set a new password: ");
		    newPassword = sc.nextLine();

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
		            	success = userService.changePassword(oldPassword, newPassword);
		                System.out.println("Password reset successfully!");
		                System.out.println("Please login again");
		                reset = true; // Set reset to true to exit the loop                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
		            }
		        }
		    }
		} while (!reset); // Check if reset is true to exit the loop
	}

}
