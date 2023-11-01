/**
 * 
 */
package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import util.BooleanConverter;
import util.CampUtil;
import util.SchoolEnumConverter;
import util.TextDecoratorUtil;
import view.AllCampsView;
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
			System.out.println("4. Edit/Delete camp");
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
				createCamps();
				break;
			case 4:
				CommonView.printNavbar("CAMS > Staff > Edit/Delete camp");
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
			
			List<Schools> available = new ArrayList<Schools>();
			
			do {
				System.out.println("Please indicate if the camp is open to all schools (Y/N): ");
				String input = sc.next();
				if (input.equals("Y") || input.equals("y")) {
					System.out.println("Camp is open to all schools.");
					available = SchoolEnumConverter.allSchools();
					break;
				}
				else if (input.equals("N") || input.equals("n")) {
					System.out.println("Please indicate if the camp is open to a college (Y/N): ");
					String input2 = sc.next();
					if (input2.equals("Y") || input2.equals("y")) {
						System.out.println("Select college: ");
						System.out.println("1. CoE");
						System.out.println("2. CoHASS");
						System.out.println("3. CoS");
						int choice = sc.nextInt();
						switch(choice) {
							case 1:
								for (Schools school : SchoolEnumConverter.CoE)
									available.add(school);
								break;
							case 2:
								for (Schools school : SchoolEnumConverter.CoHASS)
									available.add(school);
								break;
							case 3:
								for (Schools school : SchoolEnumConverter.CoS)
									available.add(school);
								break;
							default:
								System.out.println("Invalid input");		
						}
						break;
					}
					else if (input2.equals("N") || input2.equals("n")) {
						System.out.print("Number of schools the camps if open to: ");
						int numOfSchools = sc.nextInt();
						List<Schools> schoolList = SchoolEnumConverter.allSchools();
						for (int j = 0; j < numOfSchools; j++) {
							System.out.println("Please indicate which school(s) the camp is open to: ");
							for (int k = 0; k < schoolList.size(); k++) {
								Schools school = schoolList.get(k);
								System.out.println((k+1) + "." + school);
							}
							int choice = sc.nextInt();
							switch(choice) {
								case 1:
				                    available.add(Schools.SCSE);
				                    break;
				                case 2:
				                    available.add(Schools.CCEB);
				                    break;
				                case 3:
				                    available.add(Schools.EEE);
				                    break;
				                case 4:
				                    available.add(Schools.CEE);
				                    break;
				                case 5:
				                    available.add(Schools.MSE);
				                    break;
				                case 6:
				                    available.add(Schools.MAE);
				                    break;
				                case 7:
				                    available.add(Schools.NBS);
				                    break;
				                case 8:
				                    available.add(Schools.ADM);
				                    break;
				                case 9:
				                    available.add(Schools.SOH);
				                    break;
				                case 10:
				                    available.add(Schools.SSS);
				                    break;
				                case 11:
				                    available.add(Schools.WKWSCI);
				                    break;
				                case 12:
				                    available.add(Schools.SPMS);
				                    break;
				                case 13:
				                    available.add(Schools.SBS);
				                    break;
				                case 14:
				                    available.add(Schools.ASE);
				                    break;
				                case 15:
				                    available.add(Schools.LKCMedicine);
				                    break;
				                case 16:
				                    available.add(Schools.NIE);
				                    break;
				                default:
				                    System.out.println("Invalid input");
								}
						}
						break;
					}
					else {
						System.out.println("Invalid input! Please input Y or N.");
					}
					System.out.println("Camp is open to the following schools: ");
					for (int k = 0; k < available.size(); k++) {
						Schools school = available.get(k);
						System.out.print(school + ",");
					}
					System.out.println();
					break;
				}
				else
					System.out.println("Invalid input! Please input Y or N.");
			} while (true);
			
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
					visibility = BooleanConverter.convertToBoolean("TRUE");
					break;
				}
				else if (input.equals("N") || input.equals("n")) {
					visibility = BooleanConverter.convertToBoolean("FALSE");
					break;
				}
				else {
					System.out.println("Invalid input");
				}
			}while (true);
			
			int campID = CampUtil.findLowestAvailableInteger(campData);
			
			Camp camp = new Camp(campID, name, dates, closing, available, location, totalSlots, description, staffID, visibility);
			
			camps.add(camp);
		}
		
		campStaffService.createCamp(camps);
		
	}
}


