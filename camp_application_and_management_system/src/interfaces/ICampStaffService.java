/**
 * 
 */
package interfaces;

import java.util.ArrayList;

import model.camp.Camp;

/**
 * {@link ICampStudentService} defines a contract for managing
 * services for Staff
 */
public interface ICampStaffService {

	/**
	 * Create new camps
	 * 
	 * @param camps an {@link ArrayList} of {@link Camp} objects to be created
	 * @return true if camps are created successfully, false if otherwise
	 */
	public boolean createCamp(ArrayList<Camp> camps);
	
	/**
	 * View the list of camps
	 * 
	 * @return {@link ArrayList} of {@link Camp} objects that represents all camps
	 */
	public ArrayList<Camp> viewCamps();
	
	/**
	 * Update camp details
	 * 
	 * @param camp to be edited
	 * @return true if camp is updated successfully, false if otherwise
	 */
	public boolean editCamp(Camp camp);
	
	/**
	 * Toggle visibility of camps 
	 * 
	 * @param camps with visibility to be toggled
	 * @return true if visibility is updated successfully, false if otherwise
	 */
	public boolean toggleCampVisibilty(ArrayList<Camp> camps);
}
