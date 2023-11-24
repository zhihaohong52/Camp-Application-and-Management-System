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
 * The {@link DataStore} class provides utility methods for managing data storage
 * within the application. It offers methods to initialize the data store, import and export
 * data to and from the file system, and interact with data maps for various data types.
 */
public class DataStore {

	/**
	 * The {@link IFileDataService} instance used for data operations
	 */
	private static IFileDataService fileDataService;
	
	/**
	 * A {@link Map} containing file paths for various data types.
	 */
	private static Map<String, String> filePathsMap;
	
	
	/**
	 * A {@link Map} containing user ID as the key and {@link User} objects
	 * as the value
	 */
	private static Map<String, User> userData = new HashMap<String, User>();
	
	/**
	 * A {@link Map} containing student ID as the key and {@link Student} objects
	 * as the value.
	 */
	private static Map<String, Student> studentData = new HashMap<String, Student>();
	
	/**
	 * A {@link Map} containing staff ID as the key and {@link Staff} objects
	 * as the value.
	 */
	private static Map<String, Staff> staffData = new HashMap<String, Staff>();
	
	/**
	 * A {@link Map} containing comittee ID as the key and {@link Committee} objects
	 * as the value.
	 */
	private static Map<String, Committee> committeeData = new HashMap<String, Committee>();
	
	/**
	 * A {@link Map} containing a numerical Camp ID as the key and {@link Camp} objects
	 * as the value
	 */
	private static Map<Integer, Camp> campData = new HashMap<Integer, Camp>();

	/**
	 * A {@link Map} containing a numerical Enquiry ID as the key and {@link Enquiry} objects
	 * as the value
	 */
	private static Map<Integer, Enquiry> enquiryData = new HashMap<Integer, Enquiry>();
	
	/**
	 * A {@link Map} containing a numerical Suggestion ID as the key and {@link Suggestion} objects
	 * as the value
	 */
	private static Map<Integer, Suggestion> suggestionData = new HashMap<Integer, Suggestion>();
	
	/**
	 * Private constructor to prevent instantiation of the class
	 */
	private DataStore() {}

	/**
	 * Initializes the DataStore by setting up the file data service, file paths map,
	 * and importing data from the file system
	 * @param fileDataService the {@link IFileDataService} instance to use for data operations
	 * @param filePathsMap the {@link Map} containing file paths for various data types
	 * @return {@code true} if the initialisation is successful,
	 * {@code false} otherwise
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
	 * Save the data from the DataStore to the file system.
	 * @return {@code true} if the data is saved successfully, {@code false} otherwise
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
	 * Get the users data map
	 * @return a {@link Map} containing user ID as the key and {@link User} objects
	 * as the value
	 */
	public static Map<String, User> getUserData() {
		return userData;
	}

	/**
	 * Sets the user data map and saves the data to the file system.
	 * 
	 * @param userData to set a {@link Map} containing user ID as the key and 
	 * {@link User} objects as the value
	 */
	public static void setUserData(Map<String, User> userData) {
		DataStore.userData = userData;
	}

	/**
	 * Gets the student data map
	 * @return a {@link Map} containing student ID as the key and
	 * {@link Student} objects as the value
	 */
	public static Map<String, Student> getStudentData() {
		return DataStore.studentData;
	}

	/**
	 * Sets the student data map and saves the data to the file system.
	 * @param studentData to set a {@link Map} containing student ID as the key and
	 * {@link Student} objects as the value
	 */
	public static void setStudentData(Map<String, Student> studentData) {
		DataStore.studentData = studentData;
		fileDataService.exportStudentData(filePathsMap.get("user"), filePathsMap.get("student"), studentData);
	}

	/**
	 * Gets the staff data map
	 * @return a {@link Map} containing staff ID as the key and
	 * {@link Staff} objects as the value
	 */
	public static Map<String, Staff> getStaffData() {
		return DataStore.staffData;
	}

	/**
	 * Sets the staff data map and saves the data to the file system.
	 * @param staffData to set a {@link Map} containing staff ID as the key and
	 * {@link Staff} objects as the value
	 */
	public static void setStaffData(Map<String, Staff> staffData) {
		DataStore.staffData = staffData;
		fileDataService.exportStaffData(filePathsMap.get("user"), filePathsMap.get("staff"), staffData);
	}

	/**
	 * Gets the committee data map
	 * @return a {@link Map} containing committee ID as the key and
	 * {@link Committee} objects as the value
	 */
	public static Map<String, Committee> getCommitteeData() {
		return DataStore.committeeData;
	}

	/**
	 * Sets the committee data map and saves the data to the file system.
	 * @param committeeData to set a {@link Map} containing committee ID as the key and
	 * {@link Committee} objects as the value
	 */
	public static void setCommitteeData(Map<String, Committee> committeeData) {
		DataStore.committeeData = committeeData;
		fileDataService.exportCommitteeData(filePathsMap.get("user"), filePathsMap.get("committee"), committeeData);
	}

	/**
	 * Gets the camp data map
	 * @return a {@link Map} containing a numeric camp ID as the key and
	 * {@link Camp} objects as the value
	 */
	public static Map<Integer, Camp> getCampData() {
		return DataStore.campData;
	}

	/**
	 * Sets the camp data map and saves the data to the file system.
	 * @param campData to set a {@link Map} containing a numeric camp ID as the key and
	 * {@link Camp} objects as the value
	 */
	public static void setCampData(Map<Integer, Camp> campData) {
		DataStore.campData = campData;
		fileDataService.exportCampData(filePathsMap.get("camp"), campData);
	}

	/**
	 * Gets the request data map
	 * @return a {@link Map} containing a numeric enquiry ID as the key and
	 * {@link Enquiry} objects as the value
	 */
	public static Map<Integer, Enquiry> getEnquiryData() {
		return DataStore.enquiryData;
	}

	/**
	 * Sets the request data map and saves the data to the file system.
	 * @param enquiryData to set a {@link Map} containing a numeric enquiry ID as the key and
	 * {@link Enquiry} objects as the value
	 */
	public static void setEnquiryData(Map<Integer, Enquiry> enquiryData) {
		DataStore.enquiryData = enquiryData;
		fileDataService.exportEnquiryData(filePathsMap.get("enquiry"), enquiryData);
	}

	/**
	 * Gets the suggestion data map
	 * @return a {@link Map} containing a numeric suggestion ID as the key and
	 * {@link Suggestion} objects as the value
	 */
	public static Map<Integer, Suggestion> getSuggestionData() {
		return suggestionData;
	}

	/**
	 * Sets the suggestion data map and saves the data to the file system.
	 * @param suggestionData to set a {@link Map} containing a numeric suggestion ID as the key and
	 * {@link Suggestion} objects as the value
	 */
	public static void setSuggestionData(Map<Integer, Suggestion> suggestionData) {
		DataStore.suggestionData = suggestionData;
		fileDataService.exportSuggestionData(filePathsMap.get("suggestion"), suggestionData);
	}
}
