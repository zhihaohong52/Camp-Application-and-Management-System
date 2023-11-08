/**
 * 
 */
package interfaces;

import java.util.ArrayList;

import model.camp.Camp;
import model.camp.Enquiry;

/**
 * 
 */
public interface IEnquiryStaffService {

	public ArrayList<Enquiry> viewEnquiries(Camp camp);
	
	public boolean replyToEnquiry(Enquiry enquiry, String reply);
	
}
