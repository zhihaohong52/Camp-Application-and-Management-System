/**
 * 
 */
package services;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import enums.SuggestionStatus;
import interfaces.ISuggestionCommitteeService;
import model.camp.Camp;
import model.camp.Suggestion;
import model.user.Committee;
import model.user.User;
import stores.AuthStore;
import stores.DataStore;
import util.IdNumberUtil;

/**
 * {@link SuggestionCommitteeService} implements {@link ISuggestionCommitteeService}
 * interface
 */
public class SuggestionCommitteeService implements ISuggestionCommitteeService {
	/**
	 * {@link Scanner} object to get input from user
	 */
	private static final Scanner sc = new Scanner(System.in);
	
	/**
	 * Construct an instance of {@link SuggestionCommitteeService}
	 */
	public SuggestionCommitteeService() {}

	@Override
	public ArrayList<Suggestion> viewAllSuggestions() {
		User user = AuthStore.getCurrentUser();
		Map<Integer, Suggestion> suggestionData = DataStore.getSuggestionData();
		
		ArrayList<Suggestion> suggestionList = new ArrayList<>();
		
		for (Suggestion suggestion : suggestionData.values()) {
			if (suggestion.getCommiteeID().equals(user.getID())) {
				suggestionList.add(suggestion);
			}
		}
		
		return suggestionList;
	}

	@Override
	public ArrayList<Suggestion> viewProcessingSuggestions() {
		User user = AuthStore.getCurrentUser();
		Map<Integer, Suggestion> suggestionData = DataStore.getSuggestionData();
		
		ArrayList<Suggestion> suggestionList = new ArrayList<>();
		
		for (Suggestion suggestion : suggestionData.values()) {
			if ((suggestion.getStatus() == SuggestionStatus.Processing) && (suggestion.getCommiteeID().equals(user.getID()))) {
				suggestionList.add(suggestion);
			}
		}
		
		return suggestionList;
	}

	@Override
	public boolean submitSuggestions(Camp camp) {
		User user = AuthStore.getCurrentUser();
		Map<String, Committee> committeeData = DataStore.getCommitteeData();
		Committee committee = committeeData.get(user.getID());
		
		int point = committee.getPoint();
		
		Map<Integer, Suggestion> suggestionData = DataStore.getSuggestionData();
		
		sc.nextLine();
		
		System.out.print("Please input your enquiry: ");
		String question = sc.nextLine();
		
		int inquiryID = IdNumberUtil.findLowestAvailableSuggestionId(suggestionData);
		
		Suggestion suggestion = new Suggestion(inquiryID, camp.getCampID(), committee.getID(), question);
		
		point++;
		committee.setPoint(point);
		
		suggestionData.put(inquiryID, suggestion);
		
		return DataStore.saveData();
	}

	@Override
	public boolean editSuggestion(Suggestion suggestion, String newQuestion) {
		suggestion.setQuestion(newQuestion);
		return DataStore.saveData();
	}

	@Override
	public boolean deleteSuggestion(Suggestion suggestion) {
		Map<Integer, Suggestion> suggestionData = DataStore.getSuggestionData();
		
		suggestionData.remove(suggestion.getSuggestionID());
		
		return DataStore.saveData();
	}

}
