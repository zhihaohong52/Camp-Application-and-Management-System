
package interfaces;

import java.util.ArrayList;

import model.camp.Camp;
/**
 * The {@link ICampStaffService} interface defines a contract for classes that provide services related to camp management for staff members.
 * It includes methods for creating, retrieving, editing, and deleting camps, as well as toggling the visibility of camps.
 */

public interface ICampStaffService {
    /**
     * Creates camps based on the provided list.
     * @param camp The list of camps that exists already.
     * @return {@code true} if the camps are created successfully, {@code false} otherwise.
     */
	public boolean createCamp(Camp camp);
	
	/**
     * Retrieves all camps.
     *
     * @return An {@code ArrayList} of {@link Camp} containing all camps.
     */
	public ArrayList<Camp> getAllCamps();
	
	/**
     * Retrieves camps created by the staff member.
     *
     * @return An {@code ArrayList} of {@link Camp} containing camps created by the staff member.
     */
	public ArrayList<Camp> getCreatedCamps();
	
	/**
     * Edits a specific field (such as date, description ...) of a camp.
     *
     * @param camp   The camp to be edited.
     * @param field  The field index to be edited. 
     * @param value  The new value for the field.
     * @return {@code true} if the camp is edited successfully, {@code false} otherwise.
     */
	public boolean editCamp(Camp camp, int field, Object value);
	
	 /**
     * Deletes a camp.
     *
     * @param camp The camp to be deleted.
     * @return {@code true} if the camp is deleted successfully, {@code false} otherwise.
     */
	public boolean deleteCamp(Camp camp);
	
	 /**
     * Toggles the visibility of the provided list of camps.
     *
     * @param camps The {@code ArrayList} of {@link Camp}s for which visibility will be toggled.
     * @return {@code true} if the visibility is toggled successfully, {@code false} otherwise.
     */
	public boolean toggleCampVisibilty(ArrayList<Camp> camps);
}
