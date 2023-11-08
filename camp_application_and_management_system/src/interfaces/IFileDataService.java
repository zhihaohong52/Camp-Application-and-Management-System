/**
 * 
 */
package interfaces;

import java.util.Map;

import model.camp.Camp;
import model.camp.Enquiry;
import model.camp.Suggestion;
import model.user.Committee;
import model.user.Staff;
import model.user.Student;

/**
 * 
 */
public interface IFileDataService {

	Map<String, Student> importStudentData(String usersFilePath, String studentsFilePath);
	
	boolean exportStudentData(String usersFilePath, String studentsFilePath, Map<String, Student> studentMap);
	
	Map<String, Staff> importStaffData(String usersFilePath, String staffsFilePath);
	
	boolean exportStaffData(String usersFilePath, String staffsFilePath, Map<String, Staff> staffMap);
	
	Map<String, Committee> importCommitteeData(String usersFilePath, String committeesFilePath);
	
	boolean exportCommitteeData(String usersFilePath, String committeesFilePath, Map<String, Committee> committeeMap);
	
	Map<Integer, Camp> importCampData(String campsFilePath);
	
	boolean exportCampData(String campsFilePath, Map<Integer, Camp> campMap);
	
	Map<Integer, Enquiry> importEnquiryData(String inquiryFilePath);
	
	boolean exportEnquiryData(String inquiryFilePath, Map<Integer, Enquiry> enquiryMap);

	Map<Integer, Suggestion> importSuggestionData(String enquiryFilePath);
	
	boolean exportSuggestionData(String enquiryFilePath, Map<Integer, Suggestion> suggestionMap);
}
