/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import enums.Schools;
import interfaces.ICampView;
import interfaces.ICampStudentService;
import model.camp.Camp;
import model.user.Student;
import model.user.User;
import model.user.Committee;
import services.CampStudentService;
import view.CampAvailableView;
import view.CampRegisteredView;
import view.CommonView;
import store.AuthStore;
import store.DataStore;
import util.SelectorUtil;
import util.TextDecoratorUtil;

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
	 * 
	 */
	private static final ICampStudentService campStudentService = new CampStudentService();
	
	/**
	 * Constructs an instance of {@link StudentContoller}
	 */
	public StudentController() {}
	
	public void start() {
		
		User user = AuthStore.getCurrentUser();
		Map<String, Committee> committeeData = DataStore.getCommitteeData();
		Map<Integer, Camp> campData = DataStore.getCampData();
		
		// force to change password if first login
		if(user.isFirstLogin()) {
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
			CommonView.printNavbar("CAMS > Student");
			System.out.println(TextDecoratorUtil.underlineText("Settings"));
			System.out.println("1. Change password");
			
			System.out.println(TextDecoratorUtil.underlineText("\nCamps"));
			System.out.println("2. View available camps");
			System.out.println("3. View registered camps");
			System.out.println("4. Register for camp");
			System.out.println("5. Withdraw from camp");
			
			System.out.println(TextDecoratorUtil.underlineText("\nEnquiries"));
			System.out.println("6. View enquiries");
			System.out.println("7. Submit/edit enquiries");
			
			if (committeeData.containsKey(user.getID())) {
				Committee committee = committeeData.get(user.getID());
				Camp camp = campData.get(committee.getCampID());
				System.out.println(TextDecoratorUtil.underlineText("\nCamp commmitee"));
				System.out.println(user.getID() + " is a committee member for " + camp.getName());
				System.out.println("8. Enter committee view");
			}			
						
			System.out.println("0. Exit");
			
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
				break;
			case 2:
				CommonView.printNavbar("CAMS > Student > View available camps");
				campView = new CampAvailableView();
				viewAvailableCamps(campView);
				break;
			case 3:
				CommonView.printNavbar("CAMS > Student > View registered camps");
				campView = new CampRegisteredView();
				viewRegisteredCamps(campView);
				break;
			case 4:
				CommonView.printNavbar("CAMS > Student > Register for camp");
				register();
				break;
			case 5:
				CommonView.printNavbar("CAMS > Student > Withdraw from camp");
				break;
			case 6:
				CommonView.printNavbar("CAMS > Student > View enquiries");
				break;
			case 7:
				CommonView.printNavbar("CAMS > Student > Submit/edit enquiries");
				break;
			case 0:
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
	
	// Helper methods
	
	private void viewAvailableCamps(ICampView campView) {
		Student student = (Student) AuthStore.getCurrentUser();
		Schools school = student.getFaculty();
		
		ArrayList<Camp> availableCamps = campStudentService.getAvailableCamps(school);
		
		if (availableCamps.isEmpty()) {
			System.out.println("There are no camps available at the moment.\n");
		}
		else {
			for (Camp camp : availableCamps) {
				campView.displayCamp(camp);
				System.out.println();
			}
		}
	}
	
	private void viewRegisteredCamps(ICampView campView) {
		Student student = (Student) AuthStore.getCurrentUser();
		String studentID = student.getID();
		
		ArrayList<Camp> registeredCamps = campStudentService.getRegisteredCamps(studentID);
		
		if (registeredCamps.isEmpty()) {
			System.out.println("You have not registered for any camps");
		}
		else {
			for (Camp camp : registeredCamps) {
				campView.displayCamp(camp);
				System.out.println();
			}
		}
	}
	
	private void register() {
		Student student = (Student) AuthStore.getCurrentUser();
		String studentID = student.getID();
		
		ArrayList<Camp> camps = campStudentService.getAvailableCamps(student.getFaculty());
		Camp camp = SelectorUtil.campSelector(camps);
		
		int choice;
		boolean success = false;
		
		System.out.println("1. Register as attendee");
		System.out.println("2. Register as camp committee member");
		System.out.println("0. Return to homepage");
		
		choice = sc.nextInt();
		
		switch (choice) {
			case 1:
				System.out.println("Registering as attendee...");
				success = campStudentService.registerForCamp(studentID, camp.getCampID(), false);
				break;
			case 2:
				System.out.println("Registering as camp committee member...");
				success = campStudentService.registerForCamp(studentID, camp.getCampID(), true);
				break;
			case 0:
				System.out.println("Exiting");
				return;
			default:
				System.out.println("Invalid choice. Please select a number between 0 and 2");
		}
		
		if (success) {
			System.out.println("Camp registered successfully.");
		}
		else {
			System.out.println("Camp not registered.");
		}
	}
}
 