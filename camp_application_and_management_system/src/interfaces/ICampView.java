
package interfaces;

import model.camp.Camp;

/**
 * The {@link ICampView} interface defines a contract for classes that provide methods to display information about camps.
 */
public interface ICampView {
	
    /**
     * Displays information about a specific camp.
     *
     * @param camp The {@link Camp} object containing information about the camp to be displayed.
     */
	public void displayCamp(Camp camp);
}
