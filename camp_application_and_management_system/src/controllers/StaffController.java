/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.Scanner;

import interfaces.ICampStaffService;
import interfaces.ICampView;
import model.camp.Camp;
import services.CampStaffService;
import store.AuthStore;
import util.TextDecoratorUtil;
import view.AllCampsView;
import view.CampAvailableView;
import view.CampRegisteredView;
import view.CommonView;

/**
 * 
 */
public class StaffController extends UserController {

	/**
	 * {@link Scanner} object to get input
	 */
	private static final Scanner sc = new Scanner(System.in);
	
	private static final ICampStaffService campStaffService = new CampStaffService();
	
	/**
	 * 
	 */
	public StaffController() {}

	public void start() {
		
		// force to change password if first login
		if(AuthStore.getCurrentUser().isFirstLogin()) {
			System.out.println("Please change your password");
			changePassword();
			// Restart user session after changing password
			AuthController.endSession();
			System.out.println("Password reset successfully!");
            System.out.println("Please login again");   
			return;
		}
		
		ICampView campView;
		int choice;
		
		do {
			CommonView.printNavbar("CAMS > Staff");
			System.out.println(TextDecoratorUtil.underlineText("Settings"));
			System.out.println("1. Change password");
			
			System.out.println(TextDecoratorUtil.underlineText("\nCamps"));
			System.out.println("2. View all camps");
			System.out.println("3. Create new camps");
			System.out.println("4. Edit camp");
			System.out.println("5. Toggle camp visiblity");
			
			System.out.println(TextDecoratorUtil.underlineText("\nEnquiries and Suggestions"));
			System.out.println("6. View/Reply enquiries");
			System.out.println("7. View/Approve suggestions");
			
			System.out.println(TextDecoratorUtil.underlineText("\nReports"));
			System.out.println("8. Generate reports");
			System.out.println("9. Exit");
			
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				CommonView.printNavbar("CAMS > Staff > Change password");
				if (changePassword()) {
					// Restart user session after changing password
					AuthController.endSession();
					System.out.println("Password reset successfully!");
	                System.out.println("Please login again");   
					return;
				}
			case 2:
				CommonView.printNavbar("CAMS > Staff > View all camps");
				campView = new AllCampsView();
				viewAllCamps(campView);
				break;
			case 3:
				CommonView.printNavbar("CAMS > Staff > Create new camps");
				break;
			case 4:
				CommonView.printNavbar("CAMS > Staff > Edit camp");
				break;
			case 5:
				CommonView.printNavbar("CAMS > Staff > Toggle camp visiblity");
				break;
			case 6:
				CommonView.printNavbar("CAMS > Staff > View/Reply enquiries");
				break;
			case 7:
				CommonView.printNavbar("CAMS > Staff > View/Approve suggestions");
				break;
			case 8:
				CommonView.printNavbar("CAMS > Staff > Generate reports");
			case 9:
				System.out.println("Exiting student menu...");
				AuthController.endSession();
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
	
	private void viewAllCamps(ICampView campView) {
		ArrayList<Camp> camps = campStaffService.viewCamps();
		
		if (camps.isEmpty()) {
			System.out.println("No camps have been created.\n");
		}
		else {
			for (Camp camp : camps) {
				campView.displayCamp(camp);
				System.out.println();
			}
		}
	}
}


