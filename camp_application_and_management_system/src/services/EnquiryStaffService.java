/**
 * 
 */
package services;

import java.util.ArrayList;
import java.util.Map;

import interfaces.IEnquiryStaffService;
import model.camp.Camp;
import model.camp.Enquiry;
import model.user.Committee;
import model.user.User;
import store.AuthStore;
import store.DataStore;

/**
 * 
 */
public class EnquiryStaffService implements IEnquiryStaffService {

	/**
	 * 
	 */
	public EnquiryStaffService() {}

	@Override
	public ArrayList<Enquiry> viewEnquiries(Camp camp) {
		Map<Integer, Enquiry> enquiryData = DataStore.getEnquiryData();
		int campID = camp.getCampID();
		ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();
		for (Enquiry enquiry : enquiryData.values()) {
			if (enquiry.getCampID() == campID) {
				enquiries.add(enquiry);
			}
		}
		return enquiries;
	}

	@Override
	public boolean replyToEnquiry(Enquiry enquiry, String reply) {
		Map<String, Committee> committeeData = DataStore.getCommitteeData();
		User user = AuthStore.getCurrentUser();
		Committee committee = committeeData.get(user.getID());
		int point = committee.getPoint();
		
		boolean success = enquiry.replyToEnquiry(reply, committee.getID());
		
		if (success) {
			point++;
			committee.setPoint(point);
		}
		return DataStore.saveData();
	}

}
