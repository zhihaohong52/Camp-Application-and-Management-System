/**
 * 
 */
package view;

import java.util.Map;

import enums.SuggestionStatus;
import interfaces.ISuggestionView;
import model.camp.Camp;
import model.camp.Suggestion;
import model.user.Staff;
import stores.DataStore;

/**
 * The {@link SuggestionView} class implements {@link ISuggestionView}
 * and provide a method to display suggestions
 */
public class SuggestionView implements ISuggestionView {

	/**
	 * Construct an instance of {@link SuggestionView}
	 */
	public SuggestionView() {}

	@Override
	public void displaySuggestions(Suggestion suggestion) {
		Map<Integer, Camp> campData = DataStore.getCampData();
		Map<String, Staff> staffData = DataStore.getStaffData();
		Camp camp = campData.get(suggestion.getCampID());
		
		System.out.println("Camp: " + camp.getName());
		System.out.println("Question: " + suggestion.getQuestion());
		System.out.println("Status: " + suggestion.getStatus());
		if (suggestion.getStatus() == SuggestionStatus.Processing)
			return;
		System.out.println("Reply: " +  suggestion.getReply());
		
		Staff staff = staffData.get(suggestion.getReplierID());
		System.out.println("Replied by: " + staff.getName());

		CommonView.printLine();
	}

}
