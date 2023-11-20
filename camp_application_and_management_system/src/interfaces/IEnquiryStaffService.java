package interfaces;

import java.util.ArrayList;

import model.camp.Camp;
import model.camp.Enquiry;

/**
 * The {@link IEnquiryStaffService} interface defines the contract for services related to handling enquiries for camp staff. 
 * Implementing classes are expected to provide functionality for viewing enquiries associated with a specific camp and replying to those enquiries.
 */
public interface IEnquiryStaffService {
	 /**
     * Retrieves a list of enquiries associated with a specific camp.
     *
     * @param camp The camp for which enquiries are to be retrieved.
     * @return An {@code ArrayList} of {@link Enquiry} objects representing the enquiries for the specified camp.
     */
	public ArrayList<Enquiry> viewEnquiries(Camp camp);
	/**
     * Replies to a specific enquiry with the provided reply message.
     *
     * @param enquiry The {@link Enquiry} object to which the reply is being made.
     * @param reply   The reply message to be associated with the enquiry.
     * @return {@code true} if the reply is successful, {@code false} otherwise.
     */
	public boolean replyToEnquiry(Enquiry enquiry, String reply);
	
}
