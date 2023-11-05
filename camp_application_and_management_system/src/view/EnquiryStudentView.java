/**
 * 
 */
package view;

import java.util.ArrayList;
import java.util.Map;

import interfaces.IEnquiryView;
import model.camp.Camp;
import model.camp.Enquiry;
import model.user.Committee;
import model.user.Staff;
import store.DataStore;
import util.TextDecoratorUtil;

/**
 * 
 */
public class EnquiryStudentView implements IEnquiryView {

	/**
	 * 
	 */
	public EnquiryStudentView() {}

	@Override
	public void displayEnquiries(Enquiry enquiry) {
		Map<Integer, Camp> campData = DataStore.getCampData();
		Map<String, Committee> committeeData = DataStore.getCommitteeData();
		Map<String, Staff> staffData = DataStore.getStaffData();
		Camp camp = campData.get(enquiry.getCampID());
		
		System.out.println("Camp: " + camp.getName());
		System.out.println("Question: " + enquiry.getQuestion());
		System.out.println("Answers: ");
		ArrayList<String> answers = new ArrayList<>(enquiry.getReply());
		ArrayList<String> answererID = new ArrayList<>(enquiry.getReplierID());
		
		for (int i = 0; i < answers.size(); i++) {
		    System.out.printf("%d\t%s\n", i, answers.get(i));
		    
		    
		    Committee committeeAnswerer = committeeData.get(answererID.get(i));
		    Staff staffAnswerer = null;

		    if (committeeAnswerer == null) {
		        staffAnswerer = staffData.get(answererID.get(i));
		    }
		    
		    if (committeeAnswerer != null) {
		        String answeredBy = "Replied by " + committeeAnswerer.getName();
		        System.out.println(TextDecoratorUtil.italicText(answeredBy));
		    }
		    else if (staffAnswerer != null) {
		        String answeredBy = "Replied by " + staffAnswerer.getName();
		        System.out.println(TextDecoratorUtil.italicText(answeredBy));
		    }
		    else {
		        System.out.println("Replier not found.");
		    }
		}

	}

}
