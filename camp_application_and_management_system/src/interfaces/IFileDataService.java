/**
 * 
 */
package interfaces;

import java.util.Map;

import model.camp.*;
import model.user.*;

/**
 * 
 */
public interface IFileDataService {

	Map<String, Student> importStudentData(String usersFilePath, String studentsFilePath);
	
	boolean exportStudentData(String usersFilePath, String studentsFilePath, Map<String, Student> studentMap);
	
	Map<String, Staff> importStaffData(String usersFilePath, String staffsFilePath);
	
	boolean exportStaffData(String usersFilePath, String staffsFilePath, Map<String, Staff> staffMap);
	
	Map<String, Committee> importCommitteeData(String usersFilePath, String CommitteesFilePath);
	
	boolean exportCommitteeData(String usersFilePath, String studentsFilePath, String CommitteesFilePath, Map<String, Committee> committeeMap);
	
	Map<Integer, Camp> importCampData(String campsFilePath, String usersFilePath, String studentsFilePath, String staffsFilePath, String CommitteesFilePath);
	
	boolean exportCampData(String campsFilePath, Map<Integer, Camp> campMap);
	
	Map<Integer, Request> importRequestData(String requestsFilePath);
	
	boolean exportRequestData(String requestsFilePath, Map<Integer, Request> requestMap);
}
