/**
 * 
 */
package interfaces;

import java.util.ArrayList;

import model.camp.Camp;
import model.camp.Suggestion;

/**
 * 
 */
public interface ISuggestionCommitteeService {

	public ArrayList<Suggestion> viewAllSuggestions();
	
	public ArrayList<Suggestion> viewProcessingSuggestions();
	
	public boolean submitSuggestions(Camp camp);
	
	public boolean editSuggestion(Suggestion suggestion, String newQuestion);
	
	public boolean deleteSuggestion(Suggestion suggestion);
}
