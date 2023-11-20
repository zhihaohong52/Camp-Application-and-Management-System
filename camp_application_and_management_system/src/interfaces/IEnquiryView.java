package interfaces;

import model.camp.Enquiry;

/**
 * The {@link IEnquiryView} interface defines a contract for classes that
 * handle the display of {@link Enquiry} objects.
 */
public interface IEnquiryView {
	/**
     * Displays the details of an {@link Enquiry}.
     *
     * @param enquiry The {@link Enquiry} object to be displayed.
     */
	public void displayEnquiries(Enquiry enquiry);
}
