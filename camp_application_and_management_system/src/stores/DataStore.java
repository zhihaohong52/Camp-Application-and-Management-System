/**
 * 
 */
package stores;

import model.camp.*;
import model.user.*;

import java.util.HashMap;
import java.util.Map;

import interfaces.IFileDataService;

/**
 * 
 */
public class DataStore {

	/**
	 * 
	 */
	private static IFileDataService fileDataService;
	
	/**
	 * 
	 */
	private static Map<String, String> filePathsMap;
	
	
	/**
	 * 
	 */
	private static Map<String, User> userData = new HashMap<String, User>();
	
	/**
	 * 
	 */
	private static Map<String, Student> studentData = new HashMap<String, Student>();
	
	/**
	 * 
	 */
	private static Map<String, Staff> staffData = new HashMap<String, Staff>();
	
	/**
	 * 
	 */
	private static Map<String, Committee> committeeData = new HashMap<String, Committee>();
	
	/**
	 * 
	 */
	private static Map<Integer, Camp> campData = new HashMap<Integer, Camp>();

	/**
	 * 
	 */
	private static Map<Integer, Enquiry> enquiryData = new HashMap<Integer, Enquiry>();
	
	
	private static Map<Integer, Suggestion> suggestionData = new HashMap<Integer, Suggestion>();
	
	/**
	 * 
	 */
	private DataStore() {}

	/**
	 * @param fileDataService
	 * @param filePathsMap
	 * @return
	 */
	public static boolean initDataStore(IFileDataService fileDataService, Map<String, String> filePathsMap) {
		DataStore.filePathsMap = filePathsMap;
		DataStore.fileDataService = fileDataService;
		
		//import all data
		DataStore.setStudentData(fileDataService.importStudentData(filePathsMap.get("user"), filePathsMap.get("student")));
		DataStore.setStaffData(fileDataService.importStaffData(filePathsMap.get("user"), filePathsMap.get("staff")));
		DataStore.setCommitteeData(fileDataService.importCommitteeData(filePathsMap.get("user"), filePathsMap.get("committee")));
		DataStore.setCampData(fileDataService.importCampData(filePathsMap.get("camp")));
		DataStore.setEnquiryData(fileDataService.importEnquiryData(filePathsMap.get("enquiry")));
		DataStore.setSuggestionData(fileDataService.importSuggestionData(filePathsMap.get("suggestion")));
		
		return true;
	}
	
	/**
	 * @return
	 */
	public static boolean saveData() {
		DataStore.setStudentData(studentData);
		DataStore.setStaffData(staffData);
		DataStore.setCommitteeData(committeeData);
		DataStore.setCampData(campData);
		DataStore.setEnquiryData(enquiryData);
		DataStore.setSuggestionData(suggestionData);
		
		return true;
	}

	/**
	 * @return the userData
	 */
	public static Map<String, User> getUserData() {
		return userData;
	}

	/**
	 * @param userData the userData to set
	 */
	public static void setUserData(Map<String, User> userData) {
		DataStore.userData = userData;
	}

	/**
	 * @return the studentData
	 */
	public static Map<String, Student> getStudentData() {
		return DataStore.studentData;
	}

	/**
	 * @param studentData the studentData to set
	 */
	public static void setStudentData(Map<String, Student> studentData) {
		DataStore.studentData = studentData;
		fileDataService.exportStudentData(filePathsMap.get("user"), filePathsMap.get("student"), studentData);
	}

	/**
	 * @return the staffData
	 */
	public static Map<String, Staff> getStaffData() {
		return DataStore.staffData;
	}

	/**
	 * @param staffData the staffData to set
	 */
	public static void setStaffData(Map<String, Staff> staffData) {
		DataStore.staffData = staffData;
		fileDataService.exportStaffData(filePathsMap.get("user"), filePathsMap.get("staff"), staffData);
	}

	/**
	 * @return the campCommitteeMemberData
	 */
	public static Map<String, Committee> getCommitteeData() {
		return DataStore.committeeData;
	}

	/**
	 * @param committeeData the campCommitteeMemberData to set
	 */
	public static void setCommitteeData(Map<String, Committee> committeeData) {
		DataStore.committeeData = committeeData;
		fileDataService.exportCommitteeData(filePathsMap.get("user"), filePathsMap.get("committee"), committeeData);
	}

	/**
	 * @return the campData
	 */
	public static Map<Integer, Camp> getCampData() {
		return DataStore.campData;
	}

	/**
	 * @param campData the campData to set
	 */
	public static void setCampData(Map<Integer, Camp> campData) {
		DataStore.campData = campData;
		fileDataService.exportCampData(filePathsMap.get("camp"), campData);
	}

	/**
	 * @return the requestData
	 */
	public static Map<Integer, Enquiry> getEnquiryData() {
		return DataStore.enquiryData;
	}

	/**
	 * @param requestData the requestData to set
	 */
	public static void setEnquiryData(Map<Integer, Enquiry> requestData) {
		DataStore.enquiryData = requestData;
		fileDataService.exportEnquiryData(filePathsMap.get("requests"), requestData);
	}

	/**
	 * @return the suggestionData
	 */
	public static Map<Integer, Suggestion> getSuggestionData() {
		return suggestionData;
	}

	/**
	 * @param suggestionData the suggestionData to set
	 */
	public static void setSuggestionData(Map<Integer, Suggestion> suggestionData) {
		DataStore.suggestionData = suggestionData;
	}
	
	
	
}