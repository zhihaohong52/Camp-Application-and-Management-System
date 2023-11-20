package interfaces;

import java.util.ArrayList;

import model.camp.Camp;
import model.camp.Enquiry;
/**
 * The {@link IEnquiryStudentService} interface provides methods for interacting with student enquiries related to camps.
 * Implementing classes should define these methods to handle various aspects of enquiries, such as viewing, submitting, editing, and deleting.
 */
public interface IEnquiryStudentService {
	
	/**
     * Retrieves a list of all enquiries made by the student.
     *
     * @return An {@link ArrayList} of {@link Enquiry} containing all enquiries made by the student.
     */
	public ArrayList<Enquiry> viewAllEnquiries();
	/**
     * Retrieves a list of processing enquiries made by the student.
     *
     * @return An {@link ArrayList} of {@link Enquiry} containing processing enquiries made by the student.
     */

	public ArrayList<Enquiry> viewProcessingEnquiries();
	/**
     * Submits a new enquiry for a specific camp.
     *
     * @param camp The camp for which the enquiry is submitted.
     * @return {@code true} if the enquiry is successfully submitted, {@code false} otherwise.
     */
	public boolean submitEnquiry(Camp camp);
	/**
     * Edits the content of an existing enquiry.
     *
     * @param enquiry      The enquiry to be edited.
     * @param newQuestion  The edited enquiry.
     * @return {@code true} if the enquiry is successfully edited, {@code false} otherwise.
     */
	public boolean editEnquiry(Enquiry enquiry, String newQuestion);
	/**
     * Deletes a specific enquiry.
     *
     * @param enquiry The enquiry to be deleted.
     * @return {@code true} if the enquiry is successfully deleted, {@code false} otherwise.
     */
	public boolean deleteEnquiry(Enquiry enquiry);
}
