/**
 * 
 */
package view;

import java.time.LocalDate;
import java.util.stream.Collectors;

import enums.Schools;
import interfaces.ICampView;
import model.camp.Camp;

/**
 * The {@link AllCampDetailsView} class extends {@link CampAvailableView} and
 * implements {@link ICampView} and provide method for displaying all the camp details
 */
public class AllCampDetailsView extends CampAvailableView implements ICampView {

	/**
	 * Construct an instance of {@link AllCampDetailsView}
	 */
	public AllCampDetailsView() {}
	
	@Override
	public void displayCamp(Camp camp) {
		
		String datesString = String.join(", ", camp.getDates().stream().map(LocalDate::toString).toArray(String[]::new));
		String availableString = String.join(", ", camp.getAvailable().stream().map(Schools::toString).toArray(String[]::new));
		String committeeString = camp.getCampCommittee().stream().filter(committee -> committee != null && !committee.isEmpty()).collect(Collectors.joining(", "));
		String studentString = camp.getStudents().stream().filter(student -> student != null && !student.isEmpty()).collect(Collectors.joining(", "));

		System.out.println("Name: " + camp.getName());
		System.out.println("Dates: " + datesString);
		System.out.println("Closing: " + camp.getClosing());
		System.out.println("Schools: " + availableString);
		System.out.println("Location: " + camp.getLocation());
		System.out.println("Description: " + camp.getDescription());
		System.out.println("Staff IC: " + camp.getStaffIC());
		System.out.println("Number of slots left: " + camp.getTotalSlots());
		System.out.println("Committee members: " + committeeString);
		System.out.println("Students: " + studentString);
		System.out.println("Visibility: " + camp.getVisibility());
		CommonView.printLine();
	}

}
