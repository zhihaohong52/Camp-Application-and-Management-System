/**
 * 
 */
package controllers;

import java.util.Scanner;

import interfaces.IAuthService;

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
			
			authenticated = authService.login(userID, password);
			
			System.out.println("UserID or password incorrect! Please enter again!")
;		} while (!authenticated);
	}
	
	/**
	 * Ends the current user session by logging the user out
	 */
	public static void endSession() {
		authService.logout();
		System.out.println("Logging out...");
	}
}
