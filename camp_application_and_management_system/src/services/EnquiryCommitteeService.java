/**
 * 
 */
package services;

import java.util.ArrayList;
import java.util.Map;

import interfaces.IEnquiryCommitteeService;
import model.camp.Camp;
import model.camp.Enquiry;
import model.user.Committee;
import model.user.User;
import stores.AuthStore;
import stores.DataStore;

/**
 * {@link EnquiryCommitteeService} implements {@link IEnquiryCommitteeService}
 * interface and provide enquiry related functions for a committee user
 * for selected camp
 */
public class EnquiryCommitteeService implements IEnquiryCommitteeService {

	/**
	 * Construct an instance of {@link EnquiryCommitteeService}
	 */
	public EnquiryCommitteeService() {}

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
