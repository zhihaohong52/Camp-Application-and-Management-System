
package controllers;

import java.util.Scanner;

import interfaces.IAuthService;
import services.AuthCommitteeService;
import services.AuthStaffService;
import services.AuthStudentService;

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
	 * Private constructor to prevent instantiation as it is not meant to be instantiated. 
	 */
	private AuthController() {}

	/**
     * Starting a session for log in
     * 
     */
	public static void startSession() {
        int choice;
        boolean authenticated = false;

        do {

            while (true) {
                System.out.println("<Enter 0 to shutdown system>\n");
                System.out.println("Login as:");
                System.out.println("1. Student");
                System.out.println("2. Staff");
                //System.out.println("3. Camp committee member");

                String input = sc.nextLine();

                if (input.matches("[0-9]+")) { // If the input is an integer, proceed with the code
                    choice = Integer.parseInt(input);

                    if (choice < 0 || choice > 3) {
                        System.out.println("Invalid input. Please enter 0-3!");
                    } else {
                        break;
                    }
                } else { // If the input is not an integer, prompt the user to enter again
                    System.out.println("Invalid input. Please enter an integer.\n");
                }

            }

            switch (choice) {
                case 0:
                    System.out.println("Shutting down CAMS...");
                    return;
                case 1:
                    authService = new AuthStudentService();
                    break;
                case 2:
                    authService = new AuthStaffService();
                    break;
            }

            String userID, password;

            System.out.print("UserID: ");
            userID = sc.nextLine();

            System.out.print("Password: ");
            password = sc.nextLine();

            authenticated = authService.login(userID, password);
            if (!authenticated) {
                // We do not specify whether the userID or password is incorrect to make it more
                // secure
                System.out.println("Credentials invalid! Note that UserID and Password are case-sensitive.\n");
            }
        } while (!authenticated);
    }
	
    /**
     * Switch logged in user from student to committee
     */
	public static void changeToCommittee() {
		authService = (AuthCommitteeService) authService;
	}
	
    /**
     * Switch logged in user from committee to student
     */
	public static void changeToStudent() {
		authService = (AuthStudentService) authService;
	}
	
	/**
	 * Ends the current user session by logging the user out
	 */
	public static void endSession() {
		authService.logout();
		System.out.println("Logging out...");
	}
}
