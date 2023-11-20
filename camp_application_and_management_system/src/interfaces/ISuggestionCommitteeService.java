/**
 * 
 */
package interfaces;

import java.util.ArrayList;

import model.camp.Camp;
import model.camp.Suggestion;

/**
 * The {@link ISuggestionCommitteeService} interface defines a contract for classes that provide methods to manage suggestions submitted by students related to camps.
 */
public interface ISuggestionCommitteeService {
	
     /**
     * Retrieves a list of all suggestions.
     *
     * @return An {@code ArrayList} of {@link Suggestion} objects representing all suggestions.
     */
	public ArrayList<Suggestion> viewAllSuggestions();
	 
      /**
     * Retrieves a list of processing suggestions.
     *
     * @return An {@code ArrayList} of {@link Suggestion} objects representing processing suggestions.
     */
	public ArrayList<Suggestion> viewProcessingSuggestions();
	
     /**
     * Submits a suggestion for the specified camp.
     *
     * @param camp The {@link Camp} object for which the suggestion is submitted.
     * @return {@code true} if the suggestion is submitted successfully, {@code false} otherwise.
     */
	public boolean submitSuggestions(Camp camp);
	
     /**
     * Edits the specified suggestion with a new question.
     *
     * @param suggestion   The {@link Suggestion} object to be edited.
     * @param newQuestion  The edited suggestion.
     * @return {@code true} if the suggestion is edited successfully, {@code false} otherwise.
     */
	public boolean editSuggestion(Suggestion suggestion, String newQuestion);
	
    /**
     * Deletes the specified suggestion.
     *
     * @param suggestion The {@link Suggestion} object to be deleted.
     * @return {@code true} if the suggestion is deleted successfully, {@code false} otherwise.
     */
	public boolean deleteSuggestion(Suggestion suggestion);
}
