package interfaces;

import java.util.ArrayList;

import model.camp.Camp;
import model.camp.Enquiry;

/**
 * The {@link IEnquiryCommitteeService} interface defines the contract for services related to camp enquiries
 * for committee members. It provides methods to view enquiries for a specific camp and reply to those enquiries.
 */
public interface IEnquiryCommitteeService {
	/**
     * Retrieves a list of enquiries associated with the provided camp.
     *
     * @param camp The camp for which enquiries are to be viewed.
     * @return An {@code ArrayList} of {@link Enquiry} objects representing the enquiries for the specified camp.
     */
	public ArrayList<Enquiry> viewEnquiries(Camp camp);
	
	/**
     * Replies to a specific enquiry with the given reply text.
     *
     * @param enquiry The {@link Enquiry} object representing the enquiry to which the reply is made.
     * @param reply   The text of the reply to the enquiry.
     * @return {@code true} if the reply is successfully added, {@code false} otherwise.
     */
	public boolean replyToEnquiry(Enquiry enquiry, String reply);
	
}
