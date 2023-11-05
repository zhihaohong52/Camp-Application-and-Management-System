/**
 * 
 */
package view;

import java.time.LocalDate;

import enums.Schools;
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
		String datesString = String.join(";", camp.getDates().stream().map(LocalDate::toString).toArray(String[]::new));
		String availableString = String.join(";", camp.getAvailable().stream().map(Schools::toString).toArray(String[]::new));
		
		System.out.println("Name: " + camp.getName());
		System.out.println("Dates: " + datesString);
		System.out.println("Closing: " + camp.getClosing());
		System.out.println("Schools: " + availableString);
		System.out.println("Location: " + camp.getLocation());
		System.out.println("Description: " + camp.getDescription());
		System.out.println("Number of slots left: " + camp.getTotalSlots());
	}

}
