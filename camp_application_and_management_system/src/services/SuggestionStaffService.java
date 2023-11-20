/**
 * 
 */
package services;

import java.util.ArrayList;
import java.util.Map;

import interfaces.ISuggestionStaffService;
import model.camp.Camp;
import model.camp.Suggestion;
import model.user.Committee;
import stores.DataStore;

/**
 * {@link SuggestionStaffService} implements {@link ISuggestionStaffService}
 * interface and provide suggestions related functions for a staff user
 * for selected camp
 */
public class SuggestionStaffService implements ISuggestionStaffService {

	/**
	 * Create an instance of {@link SuggestionStaffService}
	 */
	public SuggestionStaffService() {}

	@Override
	public ArrayList<Suggestion> viewEnquiries(Camp camp) {
		Map<Integer, Suggestion> suggestionData = DataStore.getSuggestionData();
		int campID = camp.getCampID();
		ArrayList<Suggestion> suggestions = new ArrayList<Suggestion>();
		for (Suggestion suggestion : suggestionData.values()) {
			if (suggestion.getCampID() == campID) {
				suggestions.add(suggestion);
			}
		}
		return suggestions;
	}

	@Override
	public boolean approveSuggestion(Suggestion suggestion, String reply, boolean approve) {
		Map<String, Committee> committeeData = DataStore.getCommitteeData();
		Committee committee = committeeData.get(suggestion.getCommiteeID());
		int point = committee.getPoint();
		
		boolean success = suggestion.approveSuggestion(reply, committee.getID(), approve);
		
		if (success && approve) {
			point++;
			committee.setPoint(point);
		}
		return DataStore.saveData();
	}

}
