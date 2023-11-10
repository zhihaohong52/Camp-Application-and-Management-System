package interfaces;

import java.util.Map;

import model.camp.Camp;
import model.camp.Enquiry;
import model.camp.Suggestion;
import model.user.Committee;
import model.user.Staff;
import model.user.Student;

/**
 * The {@link IFileDataService} interface defines a contract for classes that handle the import and export of data related to users, camps, enquiries, and suggestions.
 * Implementations of this interface should provide methods to import and export data for students, staff, committees, camps, enquiries, and suggestions.
 */
public interface IFileDataService {
	/**
     * Imports student data from specified files.
     *
     * @param usersFilePath   The file path for user data.
     * @param studentsFilePath The file path for student data.
     * @return A {@code Map} containing {@link Student} data with user IDs as keys.
     */
	Map<String, Student> importStudentData(String usersFilePath, String studentsFilePath);
	
    /**
     * Exports student data to specified files.
     *
     * @param usersFilePath   The file path for user data.
     * @param studentsFilePath The file path for student data.
     * @param studentMap      A {@code Map} containing {@link Student} data with user IDs as keys.
     * @return {@code true} if the export is successful, {@code false} otherwise.
     */
	boolean exportStudentData(String usersFilePath, String studentsFilePath, Map<String, Student> studentMap);
	
    /**
     * Imports staff data from specified files.
     *
     * @param usersFilePath The file path for user data.
     * @param staffsFilePath The file path for staff data.
     * @return A {@code Map} containing {@link Staff} data with user IDs as keys.
     */
	Map<String, Staff> importStaffData(String usersFilePath, String staffsFilePath);
	
      /**
     * Exports staff data to specified files.
     *
     * @param usersFilePath The file path for user data.
     * @param staffsFilePath The file path for staff data.
     * @param staffMap      A {@code Map} containing {@link Staff} data with user IDs as keys.
     * @return {@code true} if the export is successful, {@code false} otherwise.
     */
	boolean exportStaffData(String usersFilePath, String staffsFilePath, Map<String, Staff> staffMap);
	
    /**
     * Imports committee data from specified files.
     *
     * @param usersFilePath      The file path for user data.
     * @param committeesFilePath The file path for committee data.
     * @return A {@code Map} containing {@link Committee} data with user IDs as keys.
     */

	Map<String, Committee> importCommitteeData(String usersFilePath, String committeesFilePath);
	 
      /**
     * Exports committee data to specified files.
     *
     * @param usersFilePath      The file path for user data.
     * @param committeesFilePath The file path for committee data.
     * @param committeeMap       A {@code Map} containing {@link Committee} data with user IDs as keys.
     * @return {@code true} if the export is successful, {@code false} otherwise.
     */
	boolean exportCommitteeData(String usersFilePath, String committeesFilePath, Map<String, Committee> committeeMap);
	 
      /**
     * Imports camp data from the specified file.
     *
     * @param campsFilePath The file path for camp data.
     * @return A {@code Map} containing {@link Camp} data with camp IDs as keys.
     */
	Map<Integer, Camp> importCampData(String campsFilePath);
	
     /**
     * Exports camp data to the specified file.
     *
     * @param campsFilePath The file path for camp data.
     * @param campMap       A {@code Map} containing {@link Camp} data with camp IDs as keys.
     * @return {@code true} if the export is successful, {@code false} otherwise.
     */
	boolean exportCampData(String campsFilePath, Map<Integer, Camp> campMap);
	
      /**
     * Imports enquiry data from the specified file.
     *
     * @param inquiryFilePath The file path for enquiry data.
     * @return A {@code Map} containing {@link Enquiry} data with enquiry IDs as keys.
     */
	Map<Integer, Enquiry> importEnquiryData(String inquiryFilePath);
	
     /**
     * Exports enquiry data to the specified file.
     *
     * @param inquiryFilePath The file path for enquiry data.
     * @param enquiryMap       A {@code Map} containing {@link Enquiry} data with enquiry IDs as keys.
     * @return {@code true} if the export is successful, {@code false} otherwise.
     */
	boolean exportEnquiryData(String inquiryFilePath, Map<Integer, Enquiry> enquiryMap);
	 
      /**
     * Imports suggestion data from the specified file.
     *
     * @param enquiryFilePath The file path for suggestion data.
     * @return A {@code Map} containing {@link Suggestion} data with suggestion IDs as keys.
     */
	Map<Integer, Suggestion> importSuggestionData(String enquiryFilePath);
	
     /**
     * Exports suggestion data to the specified file.
     *
     * @param enquiryFilePath The file path for suggestion data.
     * @param suggestionMap   A {@code Map} containing {@link Suggestion} data with suggestion IDs as keys.
     * @return {@code true} if the export is successful, {@code false} otherwise.
     */
	boolean exportSuggestionData(String enquiryFilePath, Map<Integer, Suggestion> suggestionMap);
}
