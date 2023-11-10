
package interfaces;

import java.util.ArrayList;

import model.camp.Camp;
import model.camp.Suggestion;

/**
 * The {@link ISuggestionStaffService} interface defines a contract for classes that provide methods to manage suggestions submitted by students, specifically for staff members.
 */
public interface ISuggestionStaffService {
     
	/**
     * Retrieves a list of suggestions associated with the specified camp.
     *
     * @param camp The {@link Camp} object for which to retrieve suggestions.
     * @return An {@code ArrayList} of {@link Suggestion} objects representing suggestions for the camp.
     */
	public ArrayList<Suggestion> viewEnquiries(Camp camp);
	
    /**
     * Approves or rejects the specified suggestion with an reply.
     *
     * @param suggestion The {@link Suggestion} object to be approved or rejected.
     * @param reply      A reply to the suggestion.
     * @param approve    {@code true} if the suggestion is approved, {@code false} if rejected.
     * @return {@code true} if the approval or rejection is successful, {@code false} otherwise.
     */
	public boolean approveSuggestion(Suggestion suggestion, String reply, boolean approve);
	
}
