
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
import interfaces.IEnquiryStaffService;
import interfaces.IEnquiryView;
import interfaces.IReportGeneratorService;
import interfaces.ISuggestionStaffService;
import interfaces.ISuggestionView;
import model.camp.Camp;
import model.camp.Enquiry;
import model.camp.Suggestion;
import model.user.Staff;
import services.CampStaffService;
import services.EnquiryStaffService;
import services.ReportGeneratorService;
import services.SuggestionStaffService;
import stores.AuthStore;
import stores.DataStore;
import util.BooleanConverterUtil;
import util.CampFilter;
import util.IdNumberUtil;
import util.SelectorUtil;
import util.TextDecoratorUtil;
import view.AllCampDetailsView;
import view.CommonView;
import view.EnquiryView;
import view.SuggestionView;

/**
 * The {@link StaffController} class provides methods to manage staff related functionalities in the CAMS application.
 * It includes operations such as changing passwords, creating and managing camps, handling enquiries and suggestions,
 * and generating reports.
 */
public class StaffController extends UserController {

	/**
	 * {@link Scanner} object to get input
	 */
	private static final Scanner sc = new Scanner(System.in);

	/**
	 * This class has-a {@link ICampStaffService} object deal with Services regading the camp
	 */
	private static final ICampStaffService campStaffService = new CampStaffService();
	
	/**
	 * This class has-a {@link IEnquiryStaffService} object deal with enquiries from the student regading the camp
	 */
	private static final IEnquiryStaffService enquiryStaffService = new EnquiryStaffService();
	
	/**
	 * This class has-a {@link ISuggestionStaffService} object deal with suggestions from the student regading the camp
	 */
	private static final ISuggestionStaffService suggestionStaffService = new SuggestionStaffService();
	
	/**
	 * This class has-a {@link IReportGeneratorService} object which the staff can use to generate a report regarding the camp
	 */
	private static final IReportGeneratorService reportGeneratorService = new ReportGeneratorService();
	
	/**
	 * Default constructor for the StaffContoller class
	 */
	public StaffController() {}

	
    /**
     * Starts the staff user session, providing access to various staff specific functionalities.
    */
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
		IEnquiryView enquiryView;
		ISuggestionView suggestionView;
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
			
			System.out.println(TextDecoratorUtil.underlineText("\nEnquiries and Suggestion"));
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
					break;
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
					back = false;
					do {
						System.out.println("1. View enquiries");
						System.out.println("2. Reply to enquiries");
						System.out.println("3. Return to homepage");
						choice2 = sc.nextInt();
						switch (choice2) {
							case 1:
								CommonView.printNavbar("CAMS > Staff > View/Reply enquiries > View enquiries");
								enquiryView = new EnquiryView();
								viewEnquiries(enquiryView);
								break;
							case 2:
								CommonView.printNavbar("CAMS > Staff > View/Reply enquiries > Reply to enquiries");
								enquiryView = new EnquiryView();
								replyToEnquiries(enquiryView);
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
				case 7:
					CommonView.printNavbar("CAMS > Staff > View/Approve suggestions");
					back = false;
					do {
						System.out.println("1. View suggestions");
						System.out.println("2. Approve suggestions");
						System.out.println("3. Return to homepage");
						choice2 = sc.nextInt();
						switch (choice2) {
							case 1:
								CommonView.printNavbar("CAMS > Staff > View/Approve suggestions > View suggestions");
								suggestionView = new SuggestionView();
								viewSuggestion(suggestionView);
								break;
							case 2:
								CommonView.printNavbar("CAMS > Staff > View/Approve suggestions > Approve suggestions");
								suggestionView = new SuggestionView();
								approveSuggestion(suggestionView);
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
				case 8:
					CommonView.printNavbar("CAMS > Staff > Generate reports");
					back = false;
					do {
						System.out.println("1. Generate camp report");
						System.out.println("2. Generate committee performance report");
						System.out.println("3. Return to homepage");
						choice2 = sc.nextInt();
						switch (choice2) {
							case 1:
								CommonView.printNavbar("CAMS > Staff > Generate reports > Generate camp report");
								generateCampReport();
								break;
							case 2:
								CommonView.printNavbar("CAMS > Staff > Generate reports > Generate committee performance report");
								generatePerformanceReport();
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

	/**
	 * Displays all camps regardless who is the creator, providing detailed information about each camp.
	 * @param campView The view interface that displays the camp details
	 */
	private void viewAllCamps(ICampView campView) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ArrayList<Camp> camps = campStaffService.getAllCamps();
		
		if (camps.isEmpty()) {
			System.out.println("No camps have been created.\n");
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
	            System.out.println("4. Student");
	            System.out.println("5. Camp committee");
	            System.out.println("6. Exit");
	            choice = sc.nextInt();
	            sc.nextLine(); // Consume the newline character

	            switch (choice) {
	                case 1:
	                    System.out.println("Enter name: ");
	                    String name = sc.nextLine();
	                    filteredCamps = CampFilter.filterByName(camps, name);
	                    break;
	                case 2:
	                    System.out.println("Enter date (dd/mm/yyyy): ");
	                    String dateString = sc.nextLine();
	                    LocalDate date = LocalDate.parse(dateString, formatter);
	                    filteredCamps = CampFilter.filterByDate(camps, date);
	                    break;
	                case 3:
	                    System.out.println("Enter location: ");
	                    String location = sc.nextLine();
	                    filteredCamps = CampFilter.filterByLocation(camps, location);
	                    break;
	                case 4:
	                	System.out.println("Enter student: ");
	                	String student = sc.nextLine();
	                	filteredCamps = CampFilter.filterByStudent(filteredCamps, student);
	                	break;
	                case 5:
	                	System.out.println("Enter committee: ");
	                	String committee = sc.nextLine();
	                	filteredCamps = CampFilter.filterByCommittee(filteredCamps, committee);
	                	break;
	                case 0:
	                    filteredCamps = camps;
	                    break;
	                case 6:
	                    System.out.println("Exiting filter.");
	                    return;
	                default:
	                    System.out.println("Invalid input.");
	            }

	            if (filteredCamps.isEmpty() && choice != 6) {
	                System.out.println("No camp found!");
	            }

	            for (Camp camp : filteredCamps) {
	                campView.displayCamp(camp);
	                System.out.println();
	            }
	        } while (choice != 6);
	    }
	}

	/**
	 * Allows staff to create multiple camps by providing information: camp name, dates, registration due date, schools,
	 * location, total slots, description and visibility
	 */
	private void createCamps() {
		//variables
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.print("Enter the number of camps to create: ");
		int campCount = sc.nextInt();
		
		Staff staff = (Staff) AuthStore.getCurrentUser();
		String staffID = staff.getID();
		
		for (int i = 0; i < campCount; i++) {
			System.out.printf("Creating camp %d\n", i+1);
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
			
			Map<Integer, Camp> campData = DataStore.getCampData();
			
			int campID = IdNumberUtil.findLowestAvailableCampId(campData);
			
			Camp camp = new Camp(campID, name, dates, closing, available, location, totalSlots, description, staffID, visibility);
			
			campStaffService.createCamp(camp);
		}
		
	}

	/**
 	* Edits the details of a specific camp, allowing staff to modify various attributes such as name, dates, closing date,
 	* schools, location, total slots, and description.
 	*/
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

	/**
 	* Deletes a specific camp, including all associated data (enquiries, suggestions). Staff members need to confirm
 	* their intention to delete a camp.
 	*/
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
	
	/**
 	* Toggles the visibility of selected camps. Staff members can choose to make camps visible or invisible.
 	*/
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
	    		System.out.printf("CampID\tCamp name\tVisiblity\n");
	    		
	    		for (Camp selectedCamp : selectedCamps) {
	    			System.out.printf("%d\t%s\t%b\n", selectedCamp.getCampID(), selectedCamp.getName(), selectedCamp.getVisibility());
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

	/**
 	* Displays enquiries from all camps created by current staff, providing details about each enquiry.
 	*
 	* @param enquiryView The view interface for displaying enquiry details.
 	*/
	private void viewEnquiries(IEnquiryView enquiryView) {
		ArrayList<Camp> camps = campStaffService.getCreatedCamps();
		ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();
		
		for (Camp camp : camps) {
			enquiries.addAll(enquiryStaffService.viewEnquiries(camp));
		}
		
		if (enquiries.isEmpty()) {
			System.out.println("There are no enquiries.");
		}
		else {
			for (Enquiry enquiry : enquiries) {
				enquiryView.displayEnquiries(enquiry);
				System.out.println();
			}
		}
	}

	/**
 	* Staff to reply to a selected enquiry with response.
 	*	
 	* @param enquiryView The view interface for displaying enquiry details.
 	*/
	private void replyToEnquiries(IEnquiryView enquiryView) {
		ArrayList<Camp> camps = campStaffService.getCreatedCamps();
		ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();
		
		for (Camp camp : camps) {
			enquiries.addAll(enquiryStaffService.viewEnquiries(camp));
		}
		
		Enquiry selectedEnquiry = SelectorUtil.enquirySelector(enquiries);
		
		System.out.println("The selected enquiry is: ");
		enquiryView.displayEnquiries(selectedEnquiry);
		System.out.print("Enter your reply to the enquiry: ");
		String reply = sc.nextLine();
		
		boolean success = enquiryStaffService.replyToEnquiry(selectedEnquiry, reply);
		
		if (success) {
			System.out.println("Succesfully replied to enquiry!");
		}
		else {
			System.out.println("Reply to enquiry unsuccessful.");
		}
	}

	/**
 	* Displays suggestions from all camps created by staff, providing details about each suggestion.
 	*
 	* @param suggestionView The view interface for displaying suggestion details.
 	*/
	private void viewSuggestion(ISuggestionView suggestionView) {
		ArrayList<Camp> camps = campStaffService.getCreatedCamps();
		ArrayList<Suggestion> suggestions = new ArrayList<Suggestion>();
		
		for (Camp camp : camps) {
			suggestions.addAll(suggestionStaffService.viewEnquiries(camp));
		}
		
		if (suggestions.isEmpty()) {
			System.out.println("There are no enquiries.");
		}
		else {
			for (Suggestion suggestion : suggestions) {
				suggestionView.displaySuggestions(suggestion);
				System.out.println();
			}
		}
		
	}

	/**
 	* Staff can approve or reject a selected suggestion and provides a response. Staff need to confirm their decision.
 	*
 	* @param suggestionView The view interface for displaying suggestion details.
 	*/
	private void approveSuggestion(ISuggestionView suggestionView) {
		ArrayList<Camp> camps = campStaffService.getCreatedCamps();
		ArrayList<Suggestion> suggestions = new ArrayList<Suggestion>();
		
		for (Camp camp : camps) {
			suggestions.addAll(suggestionStaffService.viewEnquiries(camp));
		}
		
		Suggestion selectedSuggestion = SelectorUtil.suggestionSelector(suggestions);
		
		System.out.println("The selected suggestion is: ");
		suggestionView.displaySuggestions(selectedSuggestion);
		
		do {
			sc.nextLine();
			System.out.print("Enter your reply to the suggestion: ");
			String reply = sc.nextLine();
			if (reply != null) {
				do {
					System.out.println("Please select if suggestion is approved. (Y/N)");
					String input = sc.next();
					
					if (input.equals("Y") || input.equals("y")) {
						suggestionStaffService.approveSuggestion(selectedSuggestion, reply, true);
			    		System.out.println("Suggestion approved");
			    		break;
			    	}
			    	else if (input.equals("N") || input.equals("n")) {
			    		suggestionStaffService.approveSuggestion(selectedSuggestion, reply, false);
			    		System.out.println("Suggestion rejected");
			    		break;
			    	}
			    	else {
			    		System.out.println("Invalid input. Please input Y or N.");
			    	}
				} while (true);
				break;
			}
			System.out.println("Please enter a reply");
		} while (true);
		
		
	}

	/**
 	* Staff members can generate a performance report for a specific camp.
	* Staff members can choose to filter the report for attendees only, camp committee only, or both.
 	*/
	private void generatePerformanceReport() {
		sc.nextLine(); // Consume the newline character
		ArrayList<Camp> camps = campStaffService.getCreatedCamps();
		
		if (camps.isEmpty()) {
			System.out.println("There are no camps created.");
		}
		else {
			Camp selectedCamp = SelectorUtil.campSelector(camps);
			
			System.out.println("Please select filters for report:");
			System.out.println("1. Attendees only");
			System.out.println("2. Camp committee only");
			System.out.println("Press any other number to see both attendee and camp committee");
			int filter = sc.nextInt();
			reportGeneratorService.generateCampReport(selectedCamp, filter);
		}
	}
	
	/**
 	* Staff can generate a detailed report for a specific camp, including information about attendees and camp committee members.
 	*/
	private void generateCampReport() {
		sc.nextLine(); // Consume the newline character
		ArrayList<Camp> camps = campStaffService.getCreatedCamps();
		
		if (camps.isEmpty()) {
			System.out.println("There are no camps created.");
		}
		else {
			Camp selectedCamp = SelectorUtil.campSelector(camps);
			
			reportGeneratorService.generateCommitteePerformanceReport(selectedCamp);
		}
	}
}


