/**
 * 
 */
package services;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import enums.EnquiryStatus;
import interfaces.IEnquiryStudentService;
import model.camp.Camp;
import model.camp.Enquiry;
import model.user.Student;
import model.user.User;
import stores.AuthStore;
import stores.DataStore;
import util.IdNumberUtil;

/**
 * {@link EnquiryStudentService} implemets {@link IEnquiryStudentService}
 * interface and provide enquiry related functions for a student user
 * for selected camp
 */
public class EnquiryStudentService implements IEnquiryStudentService{

	/**
	 * {@link Scanner} object to get input from user
	 */
	private static final Scanner sc = new Scanner(System.in);
	
	/**
	 * Construct an instance of {@link EnquiryStudentService}
	 */
	public EnquiryStudentService()  {}

	@Override
	public ArrayList<Enquiry> viewAllEnquiries() {
		User user = AuthStore.getCurrentUser();
		Map<Integer, Enquiry> enquiryData = DataStore.getEnquiryData();
		
		ArrayList<Enquiry> enquiryList = new ArrayList<>();
		
		for (Enquiry enquiry : enquiryData.values()) {
			if (enquiry.getStudentID().equals(user.getID())) {
				enquiryList.add(enquiry);
			}
		}
		
		return enquiryList;
	}
	
	@Override
	public ArrayList<Enquiry> viewProcessingEnquiries(){
		User user =  AuthStore.getCurrentUser();
		Map<Integer, Enquiry> enquiryData = DataStore.getEnquiryData();
		
		ArrayList<Enquiry> enquiryList = new ArrayList<>();
		
		for (Enquiry enquiry : enquiryData.values()) {
			if ((enquiry.getStatus() == EnquiryStatus.Processing) && (enquiry.getStudentID().equals(user.getID()))) {
				enquiryList.add(enquiry);
			}
		}
		
		return enquiryList;
	}

	@Override
	public boolean submitEnquiry(Camp camp) {
		User user = AuthStore.getCurrentUser();
		Map<String, Student> studentData = DataStore.getStudentData();
		Student student = studentData.get(user.getID());
		
		Map<Integer, Enquiry> enquiryData = DataStore.getEnquiryData();
		
		System.out.print("Please input your enquiry: ");
		String question = sc.nextLine();
		
		int enquiryID = IdNumberUtil.findLowestAvailableEnquiryId(enquiryData);
		
		Enquiry enquiry = new Enquiry(enquiryID, camp.getCampID(), student.getID(), question);
		
		enquiryData.put(enquiryID, enquiry);
		
		return DataStore.saveData();
	}

	@Override
	public boolean editEnquiry(Enquiry enquiry, String newQuestion) {
		enquiry.setQuestion(newQuestion);
		return DataStore.saveData();
	}

	@Override
	public boolean deleteEnquiry(Enquiry enquiry) {
		Map<Integer, Enquiry> enquiryData = DataStore.getEnquiryData();
		
		enquiryData.remove(enquiry.getEnquiryID());
		
		return DataStore.saveData();
	}

}
