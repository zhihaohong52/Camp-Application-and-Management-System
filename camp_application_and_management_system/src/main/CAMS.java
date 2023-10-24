/**
 * 
 */
package main;

import controllers.AuthController;
import model.user.User;
import services.CsvDataService;
import store.AuthStore;
import store.DataStore;
import util.FilePathsUtil;
import view.CommonView;

/**
 * The main class responsible for running the CAMS application
 */
public class CAMS {

	/**
	 * Private constructor to prevent instantiation of the class
	 */
	private CAMS() {}
	/**
	 * Entry point for CAMS application.
	 * This method is responsible for running an infinite loop
	 * which allows multiple users to operate the application
	 * 
	 * @param args an array of String arguments passed to this methods
	 */
	public static void main(String[] args) {
		try {
			do {
				// Initialize DataStore
				DataStore.initDataStore(new CsvDataService(), FilePathsUtil.csvFilePaths());
				
				// Display start up screen
				CommonView.printStartUpScreen();
				
				// Authentication
				AuthController.startSession();
				if(!AuthStore.isLoggedIn())
					return;
				
				// Start session
				User user = AuthStore.getCurrentUser();
				System.out.print("Welcome to CAMS, " + user.getName());
				switch (user.getType()) {
				case Student:
					//new StudentController().start();
					System.out.println("Student");
					break;
				case Staff:
					//new StaffController().start();
					System.out.println("Staff");
					break;
				case Committee:
					//new CommitteeController().start();
					System.out.println("Committee");
					break;
				}
				
			} while (true);
		} catch (Exception e) {
			// Save data and logout
			DataStore.saveData();
			AuthController.endSession();
			
			// Print error message
			System.out.println("Error: " + e.getMessage());
			System.out.println("Please restart the CAMS application.");
		}
	}
}
	