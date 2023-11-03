/**
 * 
 */
package interfaces;

import java.util.ArrayList;

import enums.Schools;
import model.camp.Camp;

/**
 * {@link ICampStudentService} defines a contract for managing
 * services for Student
 */
public interface ICampStudentService {

	/**
	 * 
	 * Retrieves a list of available camps

	 * @param school student's school
	 * @return a {@link ArrayList} of {@link Camp} objects representing
	 * the available camps
	 */
	public ArrayList<Camp> getAvailableCamps(Schools school);
	
	/**
	 * Retrieves the camps the student has registered for
	 * 
	 * @param studentID
	 * @return a {@link ArrayList} of {@link Camp} objects representing
	 * the camps registered, or null is no camp is registered
	 */
	public ArrayList<Camp> getRegisteredCamps(String studentID);
	
	/**
	 * Registers the student for a camp
	 * 
	 * @param studentID
	 * @param campID
	 * @param committee whether student intends to register as a committee member
	 * @return true if student registered for camp successfully, false if otherwise
	 */
	public boolean registerForCamp(String studentID, int campID, boolean committee);
}
