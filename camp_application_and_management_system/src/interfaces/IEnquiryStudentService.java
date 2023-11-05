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
public interface IEnquiryStudentService {

	public ArrayList<Enquiry> viewAllEnquiries();
	
	public ArrayList<Enquiry> viewProcessingEnquiries();
	
	public boolean submitEnquiry(Camp camp);
	
	public boolean editEnquiry(Enquiry enquiry, String newQuestion);
	
	public boolean deleteEnquiry(Enquiry enquiry);
}
