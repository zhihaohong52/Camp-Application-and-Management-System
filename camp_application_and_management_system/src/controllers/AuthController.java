/**
 * 
 */
package controllers;

import java.util.Scanner;

import interfaces.IAuthService;
import services.AuthStudentService;
import services.AuthStaffService;
import services.AuthCommitteeService;

/**
 * The {@link AuthController} class provides utility methods for managing
 * user authentication within the CAMS application. It contains method to 
 * start and end user sessions, and handle user login and logout. The class
 * utilizes the {@link IAuthService} interface for handling the authentication process.
 */
public class AuthController{

	/**
	 * {@link Scanner} object to get input from user
	 */
	private static final Scanner sc = new Scanner(System.in);
	
	/**
	 * {@link IAuthService} object to authenticate user
	 */
	private static IAuthService authService;
	
	/**
	 * 
	 */
	private AuthController() {}
	
	public static void startSession() {
		boolean authenticated = false;
		
		do {
			String userID, password;
			
			System.out.print("UserID: ");
			userID = sc.nextLine();
			
			System.out.print("Password: ");
			password = sc.nextLine();
			
			while (true) {
				System.out.println();
				System.out.println("Login as:");
	            System.out.println("1. Student");
	            System.out.println("2. Staff");
	            System.out.println("3. Camp Committee Member");
	            System.out.println("------------------------");
	            System.out.println("Enter 0 to exit CAMS");
	            
	            String input = sc.nextLine();
            
				if (input.matches("[0-9]+")) { // If the input is an integer, proceed with the code
                    int choice = Integer.parseInt(input);
                    
                    switch (choice) {
                    case 0:
    	            	System.out.println("Exiting CAMS...");
    	            	return;
    	            case 1:
    	            	System.out.println("Entering AuthStudentService");
    	            	authService = new AuthStudentService();
    	            	break;
    	            case 2:
    	            	authService = new AuthStaffService();
    	            	break;
    	            case 3:
    	            	authService = new AuthCommitteeService();
    	            	break;
    	            	default:
    	            		System.out.println("Invalid input. Please enter 0 - 3");
                    }
                    
                } else { // If the input is not an integer, prompt the user to enter again
                    System.out.println("Invalid input. Please enter an integer.");
                }
			}
	            
			authenticated = authService.login(userID, password);
			
			System.out.println("UserID or password incorrect! Please enter again!");
		} while (!authenticated);
	}
	
	/**
	 * Ends the current user session by logging the user out
	 */
	public static void endSession() {
		authService.logout();
		System.out.println("Logging out...");
	}
}
