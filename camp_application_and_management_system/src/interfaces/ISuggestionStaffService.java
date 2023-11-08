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
public interface ISuggestionStaffService {

	public ArrayList<Suggestion> viewEnquiries(Camp camp);
	
	public boolean approveSuggestion(Suggestion suggestion, String reply, boolean approve);
	
}
