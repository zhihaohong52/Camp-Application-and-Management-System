/**
 * 
 */
package store;

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
	private static Map<Integer, Request> requestData = new HashMap<Integer, Request>();
	
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
		//DataStore.setCommitteeData(fileDataService.importCommitteeData(filePathsMap.get("user"), filePathsMap.get("committee")));
		DataStore.setCampData(fileDataService.importCampData(filePathsMap.get("camp"), filePathsMap.get("user"), filePathsMap.get("student"), filePathsMap.get("staff"), filePathsMap.get("committee")));
		//DataStore.setRequestData(fileDataService.importRequestData(filePathsMap.get("request")));
		
		return true;
	}
	
	/**
	 * @return
	 */
	public static boolean saveData() {
		DataStore.setStudentData(studentData);
		DataStore.setStaffData(staffData);
		//DataStore.setCommitteeData(committeeData);
		DataStore.setCampData(campData);
		//DataStore.setRequestData(requestData);
		
		return true;
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
		fileDataService.exportCommitteeData(filePathsMap.get("user"), filePathsMap.get("student"), filePathsMap.get("committee"), committeeData);
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
	public static Map<Integer, Request> getRequestData() {
		return DataStore.requestData;
	}

	/**
	 * @param requestData the requestData to set
	 */
	public static void setRequestData(Map<Integer, Request> requestData) {
		DataStore.requestData = requestData;
		fileDataService.exportRequestData(filePathsMap.get("requests"), requestData);
	}
	
}
