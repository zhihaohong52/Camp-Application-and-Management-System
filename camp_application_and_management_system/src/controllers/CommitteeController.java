/**
 * 
 */
package controllers;

import java.util.ArrayList;

import interfaces.ICampView;
import interfaces.IEnquiryCommitteeService;
import interfaces.IEnquiryView;
import model.camp.Camp;
import model.camp.Enquiry;
import model.user.Committee;
import services.EnquiryCommitteeService;
import util.SelectorUtil;
import util.TextDecoratorUtil;
import view.AllCampDetailsView;
import view.CommonView;
import view.EnquiryView;

/**
 * 
 */
public class CommitteeController extends StudentController {

	private Camp camp = null;
	private static final IEnquiryCommitteeService enquiryCommitteeService = new EnquiryCommitteeService();
	
	/**
	 * 
	 */
	public CommitteeController() {}
	
	public void start(Committee committee, Camp camp) {
		
		ICampView campView;
		IEnquiryView enquiryView;
		int choice;
		
		this.camp = camp;
		
		do {
			CommonView.printNavbar("CAMS > Committee");
			System.out.printf("Current points for %s : %d", committee.getName(), committee.getPoint());
			
			System.out.println(TextDecoratorUtil.underlineText("Camp details for " + camp.getName()));
			System.out.println("1. View camp details");
			
			System.out.println(TextDecoratorUtil.underlineText("\nEnquiries and Suggestions"));
			System.out.println("2. View enquiries");
			System.out.println("3. Reply to enquiries");
			System.out.println("4. Submit suggestions");
			System.out.println("5. Edit suggestions");
			
			System.out.println(TextDecoratorUtil.underlineText("\nReports"));
			System.out.println("6. Generate camp report");
			
			System.out.println("0. Return to student view");
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					CommonView.printNavbar("CAMS > Committee > View camp details");
					campView = new AllCampDetailsView();
					campView.displayCamp(camp);
					break;
				case 2:
					CommonView.printNavbar("CAMS > Committee > View enquires");
					enquiryView = new EnquiryView();
					viewEnquiries(enquiryView);
					break;
				case 3:
					CommonView.printNavbar("CAMS > Committee > Reply to enquires");
					enquiryView = new EnquiryView();
					replyToEnquiries(enquiryView);
					break;
				case 4:
					CommonView.printNavbar("CAMS > Committee > Submit suggestions");
					break;
				case 5:
					CommonView.printNavbar("CAMS > Committee > Edit suggestions");
					break;
				case 6:
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
	
	private void viewEnquiries(IEnquiryView enquiryView) {
		ArrayList<Enquiry> enquiries = enquiryCommitteeService.viewEnquiries(camp);
		
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
	
	private void replyToEnquiries(IEnquiryView enquiryView) {
		ArrayList<Enquiry> enquiries = enquiryCommitteeService.viewEnquiries(camp);
		Enquiry selectedEnquiry = SelectorUtil.enquirySelector(enquiries);
		
		System.out.println("The selected enquiry is: ");
		enquiryView.displayEnquiries(selectedEnquiry);
		System.out.print("Enter your reply to the enquiry: ");
		String reply = sc.nextLine();
		
		boolean success = enquiryCommitteeService.replyToEnquiry(selectedEnquiry, reply);
		
		if (success) {
			System.out.println("Succesfully replied to enquiry!");
		}
		else {
			System.out.println("Reply to enquiry unsuccessful.");
		}
	}
}
