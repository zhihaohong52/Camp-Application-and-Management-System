/**
 * 
 */
package controllers;

import java.util.Scanner;

import view.CommonView;
import store.AuthStore;

/**
 * The {@link StudentController} class is responsible for handling the
 * student-specific user interface and user interactions. It extends the
 * {@link UserController} class and provides functionality for students to
 * view available camps, register and withdraw from camps, and make enquires.
 * This class utilizes {@link ICampStudentService} to interact with underlying
 * data and perform operations
 */
public class StudentController extends UserController {

	/**
	 * {@link Scanner} object to get input
	 */
	private static final Scanner sc = new Scanner(System.in);
	
	/**
	 * Constructs an instance of {@link StudentContoller}
	 */
	public StudentController() {}
	
	public void start() {
		
		// force to change password if first login
		if(AuthStore.getCurrentUser().isFirstLogin()) {
			changePassword();
			// Restart user session after changing password
			AuthController.endSession();
			System.out.println("Password reset successfully!");
            System.out.println("Please login again");   
			return;
		}
		
		int choice;
		
		do {
			CommonView.printNavbar("CAMS > Student");
			System.out.println("1. Change password");
			System.out.println("2. View available camps");
			System.out.println("3. View registered camps");
			System.out.println("4. Register for camp");
			System.out.println("5. Withdraw from camp");
			System.out.println("6. View enquiries");
			System.out.println("7. Submit/edit enquiries");
			System.out.println("8. Exit");
			
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				CommonView.printNavbar("CAMS > Student > Change password");
				if (changePassword()) {
					// Restart user session after changing password
					AuthController.endSession();
					System.out.println("Password reset successfully!");
	                System.out.println("Please login again");   
					return;
				}
			case 2:
				CommonView.printNavbar("CAMS > Student > View available camps");
				break;
			case 3:
				CommonView.printNavbar("CAMS > Student > View registered camps");
				break;
			case 4:
				CommonView.printNavbar("CAMS > Student > Register for camp");
				break;
			case 5:
				CommonView.printNavbar("CAMS > Student > Withdraw for camp");
				break;
			case 6:
				CommonView.printNavbar("CAMS > Student > View enquiries");
				break;
			case 7:
				CommonView.printNavbar("CAMS > Student > Submit/edit enquiries");
				break;
			case 8:
				System.out.println("Exiting student menu...");
				return;
				default:
					System.out.println("Invalid choice. Please select a number between 1 and 8.");
					break;
			}
			if (choice >= 2 && choice <7) {
				CommonView.pressEnterToContinue();
			}
		}while (true);
	}
}
 