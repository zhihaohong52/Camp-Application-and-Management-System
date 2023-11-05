/**
 * 
 */
package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import enums.Schools;
import interfaces.ICampStaffService;
import interfaces.ICampView;
import model.camp.Camp;
import model.user.Staff;
import services.CampStaffService;
import store.AuthStore;
import store.DataStore;
import util.BooleanConverterUtil;
import util.IdNumberUtil;
import util.SelectorUtil;
import util.TextDecoratorUtil;
import view.AllCampDetailsView;
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
		int choice, choice2;
		boolean back;
		
		do {
			CommonView.printNavbar("CAMS > Staff");
			System.out.println(TextDecoratorUtil.underlineText("Settings"));
			System.out.println("1. Change password");
			
			System.out.println(TextDecoratorUtil.underlineText("\nCamps"));
			System.out.println("2. View all camps");
			System.out.println("3. Create new camps");
			System.out.println("4. Edit/Delete camp");
			System.out.println("5. Toggle camp visiblity");
			
			System.out.println(TextDecoratorUtil.underlineText("\nEnquiries and Suggestions"));
			System.out.println("6. View/Reply enquiries");
			System.out.println("7. View/Approve suggestions");
			
			System.out.println(TextDecoratorUtil.underlineText("\nReports"));
			System.out.println("8. Generate reports");
			System.out.println("0. Exit");
			
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
					campView = new AllCampDetailsView();
					viewAllCamps(campView);
					break;
				case 3:
					CommonView.printNavbar("CAMS > Staff > Create new camps");
					createCamps();
					break;
				case 4:
					CommonView.printNavbar("CAMS > Staff > Edit/Delete camp");
					back = false;
					do {
						System.out.println("1. Edit camp");
						System.out.println("2. Delete camp");
						System.out.println("3. Return to homepage");
						choice2 = sc.nextInt();
						switch (choice2) {
							case 1:
								CommonView.printNavbar("CAMS > Staff > Edit/Delete camp > Edit camp");
								editCamp();
								break;
							case 2:
								CommonView.printNavbar("CAMS > Staff > Edit/Delete camp > Delete camp");
								deleteCamp();
								break;
							case 3:
								back = true;
								break;
							default:
								System.out.println("Invalid choice. Please select a number between 1 and 3.");
								break;
						}
						if (choice2 == 1 || choice2 == 2) {
							CommonView.pressEnterToContinue();
						}
					} while (back == false);
					break;
				case 5:
					CommonView.printNavbar("CAMS > Staff > Toggle camp visiblity");
					toggleCamp();
					break;
				case 6:
					CommonView.printNavbar("CAMS > Staff > View/Reply enquiries");
					break;
				case 7:
					CommonView.printNavbar("CAMS > Staff > View/Approve suggestions");
					break;
				case 8:
					CommonView.printNavbar("CAMS > Staff > Generate reports");
				case 0:
					System.out.println("Exiting staff menu...");
					AuthController.endSession();
					return;
				default:
					System.out.println("Invalid choice. Please select a number between 1 and 8.");
					break;
			}
			if (choice >= 2 && choice <8) {
				CommonView.pressEnterToContinue();
			}
		}while (true);
	}
	
	private void viewAllCamps(ICampView campView) {
		ArrayList<Camp> camps = campStaffService.getAllCamps();
		
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
	
	private void createCamps() {
		//variables
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Map<Integer, Camp> campData = DataStore.getCampData();
		
		System.out.print("Enter the number of camps to create: ");
		int campCount = sc.nextInt();
		
		Staff staff = (Staff) AuthStore.getCurrentUser();
		String staffID = staff.getID();
		ArrayList<Camp> camps = new ArrayList<>();
		
		for (int i = 0; i < campCount; i++) {
			System.out.printf("Creating project %d\n", i+1);
			System.out.println("Camp title: ");
			sc.nextLine(); //consumer newline char from previous input
			String name = sc.nextLine();
			
			List<LocalDate> dates = new ArrayList<LocalDate>();
			System.out.print("Input number of days of camp:");
			int daysCount = sc.nextInt();
			System.out.println("Please input all dates in dd/mm/yyyy format");
			for (int j = 0; j < daysCount; j++) {
				System.out.printf("Enter date for day %d of camp: ", j+1);
				String dateString = sc.next();
				LocalDate date = LocalDate.parse(dateString, formatter);
				dates.add(date);				
			}
			
			System.out.print("Closing date for signups: ");
			sc.nextLine(); //consumer newline char from previous input
			String dateString = sc.next();
			LocalDate closing = LocalDate.parse(dateString, formatter);
			
			List<Schools> available = SelectorUtil.schoolSelector();
			
			System.out.print("Location: ");
			sc.nextLine(); //consumer newline char from previous input
			String location = sc.nextLine();
			
			System.out.print("Total number of slots: ");
			int totalSlots = sc.nextInt();
			
			System.out.print("Description: ");
			sc.nextLine(); //consumer newline char from previous input
			String description  = sc.nextLine();
			
			Boolean visibility;
			do {
				System.out.print("Set visibility (Y/N): ");
				String input = sc.next();
				if (input.equals("Y") || input.equals("y")) {
					visibility = BooleanConverterUtil.convertToBoolean("TRUE");
					break;
				}
				else if (input.equals("N") || input.equals("n")) {
					visibility = BooleanConverterUtil.convertToBoolean("FALSE");
					break;
				}
				else {
					System.out.println("Invalid input");
				}
			}while (true);
			
			int campID = IdNumberUtil.findLowestAvailableCampId(campData);
			
			Camp camp = new Camp(campID, name, dates, closing, available, location, totalSlots, description, staffID, visibility);
			
			camps.add(camp);
		}
		
		campStaffService.createCamp(camps);
		
	}
	
	private void editCamp() {
	    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    ArrayList<Camp> camps = campStaffService.getCreatedCamps();
	    Camp selectedCamp = SelectorUtil.campSelector(camps);

	    if (selectedCamp == null) {
	    	System.out.println("You have not yet created any camps.");
	    }
	    else {
	        System.out.printf("Editing camp %d - %s\n", selectedCamp.getCampID(), selectedCamp.getName());

	        while (true) {
	            System.out.println("Select field to edit:");
	            System.out.println("1. Camp name");
	            System.out.println("2. Camp dates");
	            System.out.println("3. Closing date");
	            System.out.println("4. Schools");
	            System.out.println("5. Location");
	            System.out.println("6. Total number of slots");
	            System.out.println("7. Description");
	            System.out.println("0. Exit");

	            int choice = sc.nextInt();
	            sc.nextLine(); // Consume the newline character

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter new name: ");
	                    String name = sc.nextLine();
	                    campStaffService.editCamp(selectedCamp, choice, name);
	                    break;
	                case 2:
	                    System.out.print("Enter new dates (separated by spaces): ");
	                    String dateInput = sc.nextLine();
	                    List<LocalDate> dates = new ArrayList<>();
	                    String[] dateStrings = dateInput.split(" ");
	                    for (String dateString : dateStrings) {
	                        try {
	                            LocalDate date = LocalDate.parse(dateString, formatter);
	                            dates.add(date);
	                        } catch (DateTimeParseException e) {
	                            System.out.println("Invalid date format. Please use dd/mm/yyyy.");
	                        }
	                    }
	                    campStaffService.editCamp(selectedCamp, choice, dates);
	                    break;
	                case 3:
	                    System.out.print("Enter new closing date (dd/mm/yyyy): ");
	                    String dateString = sc.nextLine();
	                    try {
	                        LocalDate closing = LocalDate.parse(dateString, formatter);
	                        campStaffService.editCamp(selectedCamp, choice, closing);
	                    } catch (DateTimeParseException e) {
	                        System.out.println("Invalid date format. Please use dd/mm/yyyy.");
	                    }
	                    break;
	                case 4:
	                    System.out.println("Enter new available schools (separated by spaces): ");
	                    List<Schools> available = SelectorUtil.schoolSelector();
	                    campStaffService.editCamp(selectedCamp, choice, available);
	                    break;
	                case 5:
	                    System.out.print("Enter new location: ");
	                    String location = sc.nextLine();
	                    campStaffService.editCamp(selectedCamp, choice, location);
	                    break;
	                case 6:
	                    System.out.print("Enter new number of slots: ");
	                    int totalSlots = sc.nextInt();
	                    sc.nextLine(); // Consume the newline character
	                    campStaffService.editCamp(selectedCamp, choice, totalSlots);
	                    break;
	                case 7:
	                    System.out.print("Enter new description: ");
	                    String description = sc.nextLine();
	                    campStaffService.editCamp(selectedCamp, choice, description);
	                    break;
	                case 0:
	                    System.out.println("Exiting edit camp.");
	                    return;
	                default:
	                    System.out.println("Invalid choice. Please select a number between 1 and 7.");
	            }
	        }
	    }
	}
	
	private void deleteCamp() {
		ArrayList<Camp> camps = campStaffService.getAllCamps();
	    Camp selectedCamp = SelectorUtil.campSelector(camps);
	    String input = null;
	    
	    if (selectedCamp != null) {
	    	do {
	    		System.out.printf("Deleting camp %d - %s\n", selectedCamp.getCampID(), selectedCamp.getName());
		    	System.out.println("Please confirm the option. Do note that deleted camps will be deleted permanently. (Y/N)");
		    	input = sc.next();
		    	if (input.equals("Y") || input.equals("y")) {
		    		campStaffService.deleteCamp(selectedCamp);
		    		System.out.println("Camp deleted successfully.");
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
	
	private void toggleCamp() {
		ArrayList<Camp> camps = campStaffService.getAllCamps();
		ArrayList<Camp> selectedCamps = new ArrayList<>();

		System.out.println("Select all camps to toggle. End selection by pressing enter with no option selected.");
		
		while (true) {
			
		    Camp selectedCamp = SelectorUtil.campSelector(camps);
		    
		    if (selectedCamp != null) {
		        selectedCamps.add(selectedCamp);
		    }
		    else {
		        break;
		    }
		}		
		
	    String input = null;
	    
	    if (selectedCamps != null) {
	    	do {
	    		System.out.printf("Selected camps are:\n");
	    		System.out.printf("CampID\tCamp name\tVisiblity");
	    		
	    		for (Camp selectedCamp : selectedCamps) {
	    			System.out.printf("%d\t%s\t", selectedCamp.getCampID(), selectedCamp.getName(), selectedCamp.getVisibility());
	    		}
	    		
		    	System.out.println("Please confirm the option. (Y/N)");
		    	input = sc.next();
		    	if (input.equals("Y") || input.equals("y")) {
		    		campStaffService.toggleCampVisibilty(selectedCamps);
		    		System.out.println("Camp visibility toggled successfully.");
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


