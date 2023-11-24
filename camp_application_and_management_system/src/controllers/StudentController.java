
package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import enums.Schools;
import interfaces.ICampStudentService;
import interfaces.ICampView;
import interfaces.IEnquiryStudentService;
import interfaces.IEnquiryView;
import model.camp.Camp;
import model.camp.Enquiry;
import model.user.Committee;
import model.user.Student;
import model.user.User;
import services.CampStudentService;
import services.EnquiryStudentService;
import stores.AuthStore;
import stores.DataStore;
import util.CampFilter;
import util.SelectorUtil;
import util.TextDecoratorUtil;
import view.CampAvailableView;
import view.CampRegisteredView;
import view.CommonView;
import view.EnquiryView;

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
	protected static final Scanner sc = new Scanner(System.in);
	
	/**
	 * This class has-a {@link ICampStudentService} object which the StudentController object is able to access all the services given.
	 */
	private static final ICampStudentService campStudentService = new CampStudentService();
	
	/**
	 * This class has-a {@link IEnquiryStudentService} object which the StudentController object can access all the services that the enquiry class gives.
	 */
	private static final IEnquiryStudentService enquiryStudentService = new EnquiryStudentService();
	
	/**
	 * Default constructor for StudentController
	 */
	public StudentController() {}
	
	/**
     * Starts the student controller, provides access to various functionalities based on the user's choice.
	 * Functions such as view available/registered camps, register camps, submit/edit/delete enquiries.
     */
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
		IEnquiryView enquiryView;
		int choice, choice2;
		boolean back;

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
		    System.out.println("7. Submit/edit/delete enquiries");

		    if (committeeData.containsKey(user.getID())) {
		        Committee committee = committeeData.get(user.getID());
		        Camp camp = campData.get(committee.getCampID());
		        System.out.println(TextDecoratorUtil.underlineText("\nCamp committee"));
		        System.out.println(user.getID() + " is a committee member for " + camp.getName());
		        System.out.println("8. Enter committee view");
		    }

		    System.out.println("0. Exit");

		    choice = sc.nextInt();

		    if (choice == 8) {
		        // Handle the case where 8 was selected (committee view)
		        if (committeeData.containsKey(user.getID())) {
		        	Committee committee = committeeData.get(user.getID());
			        Camp camp = campData.get(committee.getCampID());
			        
		        	new CommitteeController().start(committee, camp);
		        } 
		        else {
		            System.out.println("Invalid choice.");
		        }
		        CommonView.pressEnterToContinue();
		    } 
		    else if (choice >= 0 && choice <= 7) {
		        // Handle other options using the switch statement
		        switch (choice) {
		            case 1:
		                // Handle option 1
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
		                // Handle option 2
		                CommonView.printNavbar("CAMS > Student > View available camps");
		                campView = new CampAvailableView();
		                viewAvailableCamps(campView);
		                break;
		            case 3:
		                // Handle option 3
		                CommonView.printNavbar("CAMS > Student > View registered camps");
		                campView = new CampRegisteredView();
		                viewRegisteredCamps(campView);
		                break;
		            case 4:
		                // Handle option 4
		                CommonView.printNavbar("CAMS > Student > Register for camp");
		                register();
		                break;
		            case 5:
		                // Handle option 5
		                CommonView.printNavbar("CAMS > Student > Withdraw from camp");
		                withdraw();
		                break;
		            case 6:
		                // Handle option 6
		                CommonView.printNavbar("CAMS > Student > View enquiries");
		                enquiryView = new EnquiryView();
		                viewEnquiries(enquiryView);
		                break;
		            case 7:
		                // Handle option 7
		                CommonView.printNavbar("CAMS > Student > Submit/Edit/Delete enquiries");
		                back = false;
		                do {
		                	System.out.println("1. Submit enquiries");
		                	System.out.println("2. Edit enquiries");
		                	System.out.println("3. Delete enquiries");
		                	System.out.println("4. Return to homepage");
		                	choice2 = sc.nextInt();
		                	switch(choice2) {
		                	case 1:
		                		CommonView.printNavbar("CAMS > Student > Submit/Edit/Delete enquiries > Submit enquiries");
		                		submitEnquiry();
		                		break;
		                	case 2:
		                		CommonView.printNavbar("CAMS > Student > Submit/Edit/Delete enquiries > Edit enquiries");
		                		editEnquiry();
		                		break;
		                	case 3:
		                		CommonView.printNavbar("CAMS > Student > Submit/Edit/Delete enquiries > Delete enquiries");
		                		deleteEnquiry();
		                		break;
		                	case 4:
		                		back = true;
		                		break;
		                	}
		                } while (back == false);
		                break;
		            case 0:
		            	// Handle option 0 (Exit)
				        System.out.println("Exiting student menu...");
				        AuthController.endSession();
				        return;
				    default:
				    	System.out.println("Invalid choice.");
            	}
		    }
		    
		    if (choice >= 2 && choice <7) {
				CommonView.pressEnterToContinue();
			}
		    
		} while (true);

	}

	/**
     * Displays available camps to the student based on their school and visibility.
	 * It allows student to view detailed information.
     *
     * @param campView The view interface for displaying camp details.
     */
	private void viewAvailableCamps(ICampView campView) {
	    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    Student student = (Student) AuthStore.getCurrentUser();
	    Schools school = student.getFaculty();

	    ArrayList<Camp> availableCamps = campStudentService.getAvailableCamps(school);

	    if (availableCamps.isEmpty()) {
	        System.out.println("There are no camps available at the moment.\n");
	    } 
	    else {
	        ArrayList<Camp> filteredCamps = new ArrayList<>();
	        int choice;

	        do {
	            System.out.println("Filter by:");
	            System.out.println("0. None");
	            System.out.println("1. Name");
	            System.out.println("2. Date");
	            System.out.println("3. Location");
	            System.out.println("4. Exit");
	            choice = sc.nextInt();
	            sc.nextLine(); // Consume the newline character

	            switch (choice) {
	                case 1:
	                    System.out.println("Enter name: ");
	                    String name = sc.nextLine();
	                    filteredCamps = CampFilter.filterByName(availableCamps, name);
	                    break;
	                case 2:
	                    System.out.println("Enter date (dd/mm/yyyy): ");
	                    String dateString = sc.next();
	                    LocalDate date = LocalDate.parse(dateString, formatter);
	                    filteredCamps = CampFilter.filterByDate(availableCamps, date);
	                    break;
	                case 3:
	                    System.out.println("Enter location: ");
	                    String location = sc.next();
	                    filteredCamps = CampFilter.filterByLocation(availableCamps, location);
	                    break;
	                case 0:
	                    filteredCamps = availableCamps;
	                    break;
	                case 4:
	                    System.out.println("Exiting filter.");
	                    return;
	                default:
	                    System.out.println("Invalid input.");
	            }

	            if (filteredCamps.isEmpty() && choice != 4) {
	                System.out.println("No camp found!");
	            }

	            for (Camp camp : filteredCamps) {
	                campView.displayCamp(camp);
	                System.out.println();
	            }
	        } while (choice != 4);
	    }
	}
	
	 /**
     * Displays camps that the student is registered for and allows them to view detailed information.
     *
     * @param campView The view interface for displaying camp details.
     */
	private void viewRegisteredCamps(ICampView campView) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Student student = (Student) AuthStore.getCurrentUser();
		String studentID = student.getID();
		
		ArrayList<Camp> registeredCamps = campStudentService.getRegisteredCamps(studentID);
		
		if (registeredCamps.isEmpty()) {
			System.out.println("You have not registered for any camps");
		}
		else {
			ArrayList<Camp> filteredCamps = new ArrayList<>();
	        int choice;

	        do {
	            System.out.println("Filter by:");
	            System.out.println("0. None");
	            System.out.println("1. Name");
	            System.out.println("2. Date");
	            System.out.println("3. Location");
	            System.out.println("4. Exit");
	            choice = sc.nextInt();
	            sc.nextLine(); // Consume the newline character

	            switch (choice) {
	                case 1:
	                    System.out.println("Enter name: ");
	                    String name = sc.nextLine();
	                    filteredCamps = CampFilter.filterByName(registeredCamps, name);
	                    break;
	                case 2:
	                    System.out.println("Enter date (dd/mm/yyyy): ");
	                    String dateString = sc.next();
	                    LocalDate date = LocalDate.parse(dateString, formatter);
	                    filteredCamps = CampFilter.filterByDate(registeredCamps, date);
	                    break;
	                case 3:
	                    System.out.println("Enter location: ");
	                    String location = sc.next();
	                    filteredCamps = CampFilter.filterByLocation(registeredCamps, location);
	                    break;
	                case 0:
	                    filteredCamps = registeredCamps;
	                    break;
	                case 4:
	                    System.out.println("Exiting filter...");
	                    return;
	                default:
	                    System.out.println("Invalid input.");
	            }

	            if (filteredCamps.isEmpty() && choice != 4) {
	                System.out.println("No camp found!");
	            }

	            for (Camp camp : filteredCamps) {
	                campView.displayCamp(camp);
	                System.out.println();
	            }
	        } while (choice != 4);
	    }
	}
	
	/**
     * Allows the current student to register for a camp, choosing between attendee and camp committee member roles.
     */
	private void register() {
		Student student = (Student) AuthStore.getCurrentUser();
		String studentID = student.getID();
		
		ArrayList<Camp> camps = campStudentService.getAvailableCamps(student.getFaculty());
		
		if (camps.isEmpty()) {
			System.out.println("There are no camps available at the moment.\n");
		}
		else {
			Camp camp = SelectorUtil.campSelector(camps);
			
			int choice;
			boolean success = false;
			
			sc.nextLine();// consume newline character
			
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
	
	/**
     * Allows the current student to withdraw from a camp they are registered for.
     */
	private void withdraw() {
		Student student = (Student) AuthStore.getCurrentUser();
		String studentID = student.getID();
		
		ArrayList<Camp> camps = campStudentService.getRegisteredCamps(studentID);
		
		if (camps.isEmpty()) {
			System.out.println("You have not registered for any camps");
		}
		else {
			Camp camp = SelectorUtil.campSelector(camps);
			
			boolean success = campStudentService.withdrawFromCamp(studentID, camp.getCampID());
			
			if (success) {
				System.out.println("Camp withdrwan successfully.\n"
						+ "!!Please not that you are NOT allowed to register for this camp again!!");
			}
			else {
				System.out.println("Camp not withdrawn from.");
			}
		}
	}

	/**
     * Allows the student to view their own enquiries.
	 * 
     * @param enquiryView The view interface for displaying enquiry details.
     */
	private void viewEnquiries(IEnquiryView enquiryView) {
		ArrayList<Enquiry> enquiries = enquiryStudentService.viewAllEnquiries();
		
		if (enquiries.isEmpty()) {
			System.out.println("You have not made any enquiries.");
		}
		else {
			for (Enquiry enquiry : enquiries) {
				enquiryView.displayEnquiries(enquiry);
				System.out.println();
			}
		}
	}

	 /**
     * Allows the student to submit a new enquiry for a specific camp.
     */
	private void submitEnquiry() {
		Student student = (Student) AuthStore.getCurrentUser();
		ArrayList<Camp> camps = campStudentService.getAvailableCamps(student.getFaculty());
		
		if (camps.isEmpty()) {
			System.out.println("There are no camps available at the moment.\n");
		}
		else {
			Camp camp = SelectorUtil.campSelector(camps);
			
			boolean success = enquiryStudentService.submitEnquiry(camp);
			
			if (success) {
				System.out.println("Enquiry submitted successfully.\n"
						+ "Please be patients while we get back to you.");
			}
			else {
				System.out.println("Enquiry not submitted");
			}
		}
	}

	/**
     * Allows the student to edit an existing enquiry.
     */
	private void editEnquiry() {
		ArrayList<Enquiry> enquiries = enquiryStudentService.viewProcessingEnquiries();
		
		if (enquiries.isEmpty()) {
			System.out.println("You have not made any enquiries.");
		}
		else {
			Enquiry selectedEnquiry = SelectorUtil.enquirySelector(enquiries);
			
			if (selectedEnquiry == null) {
				return;
			}
			else {
				System.out.println("Current question: " + selectedEnquiry.getQuestion());
				
				sc.nextLine(); //consume newline character
				
				System.out.println("Enter edited question");
				String newQuestion = sc.nextLine();
				
				boolean success = enquiryStudentService.editEnquiry(selectedEnquiry, newQuestion);
				
				if (success) {
					System.out.println("Enquiry edited successfully.");
				}
				else {
					System.out.println("Enquiry not edited.");
				}
			}
		}
	}
	
	/**
     * Allows the student to delete an existing enquiry.
     */
	private void deleteEnquiry() {
		Map<Integer, Camp> campData = DataStore.getCampData();
		
		ArrayList<Enquiry> enquiries = enquiryStudentService.viewProcessingEnquiries();
		
		if (enquiries.isEmpty()) {
			System.out.println("You have not made any enquiries.");
			return;
		}
		
		Enquiry selectedEnquiry = SelectorUtil.enquirySelector(enquiries);
		String input = null;
		
		if (selectedEnquiry == null) {
			return;
		}
		else {
	    	do {
	    		System.out.printf("Deleting enquiry for camp %s - %s\n", campData.get(selectedEnquiry.getCampID()).getName(), selectedEnquiry.getQuestion());
		    	System.out.println("Please confirm the option. Do note that deleted camps will be deleted permanently. (Y/N)");
		    	input = sc.next();
		    	if (input.equals("Y") || input.equals("y")) {
		    		enquiryStudentService.deleteEnquiry(selectedEnquiry);
		    		System.out.println("Enquiry deleted successfully.");
		    		break;
		    	}
		    	else if (input.equals("N") || input.equals("n")) {
		    		break;
		    	}
		    	else {
		    		System.out.println("Invalid input. Please input Y or N.");
		    	}
	    	} while (true);
	    }
	}
}
 