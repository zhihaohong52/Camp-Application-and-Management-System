/**
 * 
 */
package view;

import java.time.LocalDate;
import java.util.List;

import enums.Schools;
import interfaces.ICampView;
import model.camp.Camp;
import model.user.Committee;

/**
 * 
 */
public class AllCampsView extends CampAvailableView implements ICampView {

	/**
	 * 
	 */
	public AllCampsView() {}
	
	@Override
	public void displayCamp(Camp camp) {
		System.out.println("Name: " + camp.getName());
		System.out.print("Dates: ");
		List<LocalDate> dates = camp.getDates();
		for (int i = 0; i < dates.size(); i++) {
			System.out.print(dates.get(i));
			if (i < dates.size()-1) {
				System.out.print(",");
			}
		}
		System.out.println("\nClosing: " + camp.getClosing());
		System.out.println("Schools: ");
		List<Schools> schools = camp.getAvailable();
		for (int i = 0; i < schools.size(); i++) {
			System.out.print(schools.get(i));
			if (i < schools.size()-1) {
				System.out.print(",");
			}
		}
		System.out.println("\nLocation: " + camp.getLocation());
		System.out.println("Description: " + camp.getDescription());
		System.out.println("Staff ICL: " + camp.getStaffIC());
		System.out.println("Number of slots left: " + camp.getTotalSlots());
		System.out.print("Committee members: ");
		List<String> campCommittee = camp.getCampCommittee();
		for (int i = 0; i < campCommittee.size(); i++) {
			System.out.print(campCommittee.get(i));
			if (i < campCommittee.size()-1) {
				System.out.print(",");
			}
		}
		System.out.println("\nVisibility: " + camp.getVisibility());
	}

}
