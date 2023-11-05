/**
 * 
 */
package controllers;

import interfaces.ICampView;
import model.camp.Camp;
import util.TextDecoratorUtil;
import view.AllCampDetailsView;
import view.CommonView;

/**
 * 
 */
public class CommitteeController extends StudentController {

	//private static final ICampStudentService studentService = new campCommitteeService();
	
	/**
	 * 
	 */
	public CommitteeController() {}
	
	public void start(Camp camp) {
		
		ICampView campView;
		int choice;
		
		do {
			CommonView.printNavbar("CAMS > Committee");
			System.out.println(TextDecoratorUtil.underlineText("Camp details for " + camp.getName()));
			System.out.println("1. View camp details");
			
			System.out.println(TextDecoratorUtil.underlineText("\nEnquiries and Suggestions"));
			System.out.println("2. View/reply enquiries");
			System.out.println("3. Submit/edit suggestions");
			
			System.out.println(TextDecoratorUtil.underlineText("\nReports"));
			System.out.println("4. Generate camp report");
			
			System.out.println("0. Return to student view");
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					CommonView.printNavbar("CAMS > Committee > View camp details");
					campView = new AllCampDetailsView();
					campView.displayCamp(camp);
					break;
				case 2:
					CommonView.printNavbar("CAMS > Committee > View/reply enquires");
					break;
				case 3:
					CommonView.printNavbar("CAMS > Committee > Submit/edit suggestions");
					break;
				case 4:
					CommonView.printNavbar("CAMS > Committee > Generate reports");
					break;
				case 0:
					System.out.println("Exiting committee menu...");
					return;
				default:
					System.out.println("Invalid choice.");
					break;
			}
			if (choice >= 1 && choice <= 4) {
				CommonView.pressEnterToContinue();
			}
		} while (true);
	}
}
