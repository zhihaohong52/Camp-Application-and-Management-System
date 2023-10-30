/**
 * 
 */
package view;

import interfaces.ICampView;
import model.camp.Camp;

/**
 * 
 */
public class CampAvailableView implements ICampView {

	/**
	 * 
	 */
	public CampAvailableView() {}

	@Override
	public void displayCamp(Camp camp) {
		System.out.println("Name: " + camp.getName());
		System.out.println("Dates: " + camp.getDates());
		System.out.println("Closing: " + camp.getClosing());
		System.out.println("Schools: " + camp.getAvailable());
		System.out.println("Location: " + camp.getLocation());
		System.out.println("Description: " + camp.getDescription());
		System.out.println("Number of slots left: " + camp.getTotalSlots());
	}

}
