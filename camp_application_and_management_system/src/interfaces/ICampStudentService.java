
package interfaces;

import java.util.ArrayList;

import enums.Schools;
import model.camp.Camp;
/**
 * The {@link ICampStudentService} interface defines a contract for classes that provide services related to camp management for students.
 * It includes methods for retrieving available camps, registered camps, registering for a camp, and withdrawing from a camp.
 */
public interface ICampStudentService {

	/**
     * Retrieves the available camps for a specific school.
     *
     * @param school The school for which available camps are to be retrieved.
     * @return An {@code ArrayList} of {@link Camp}s containing the available camps.
     */
	public ArrayList<Camp> getAvailableCamps(Schools school);
	 
	/**
     * Retrieves the camps registered by a specific student.
     *
     * @param studentID The ID of the student for whom registered camps are to be retrieved.
     * @return An {@code ArrayList} of {@link Camp}s containing the registered camps.
     */
	
	public ArrayList<Camp> getRegisteredCamps(String studentID);
	 
	/**
     * Registers a student for a specific camp.
     *
     * @param studentID The ID of the student to be registered.
     * @param campID    The ID of the camp for registration.
     * @param committee A boolean indicating whether the student wants to become a committee.
     * @return {@code true} if the registration is successful, {@code false} otherwise.
     */
	public boolean registerForCamp(String studentID, int campID, boolean committee);
	
    /**
     * Withdraws a student from a specific camp.
     *
     * @param studentID The ID of the student to be withdrawn.
     * @param campID    The ID of the camp for withdrawal.
     * @return {@code true} if the withdrawal is successful, {@code false} otherwise.
     */
	public boolean withdrawFromCamp(String studentID, int campID);
}
