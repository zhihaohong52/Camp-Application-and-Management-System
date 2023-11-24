/**
 * 
 */
package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import enums.Schools;
import interfaces.ICampStudentService;
import model.camp.Camp;
import model.user.Committee;
import model.user.User;
import stores.AuthStore;
import stores.DataStore;

/**
 * {@link CampStudentService} implements {@link ICampStudentService} interface and
 * provide camp functions in the role permission of a student
 */
public class CampStudentService implements ICampStudentService {

	/**
	 * Construct an instance of {@link CampStudentService}
	 */
	public CampStudentService() {}

	@Override
	public ArrayList<Camp> getAvailableCamps(Schools school) {
		Map<Integer, Camp> campData = DataStore.getCampData();
		ArrayList<Camp> availableCamps = new ArrayList<>();
		
		for (Camp camp : campData.values()) {
			List<Schools> available = camp.getAvailable();
			
			// Check if the student's school is in list of schools available for camp
			if (available.contains(school) && camp.getVisibility()) {
				availableCamps.add(camp);
			}
		}
			
		Collections.sort(availableCamps, (camp1, camp2) -> camp1.getName().compareTo(camp2.getName()));
		
		return availableCamps;
	}

	@Override
	public ArrayList<Camp> getRegisteredCamps(String studentID) {
		Map<Integer, Camp> campData = DataStore.getCampData();
		ArrayList<Camp> registeredCamps = new ArrayList<>();
		
		for (Camp camp : campData.values()) {
			List<String> studentList = camp.getStudents();
			List<String> committeeList = camp.getCampCommittee();
			
			if (studentList.contains(studentID)) {
				registeredCamps.add(camp);
			}
			else if (committeeList.contains(studentID)) {
				registeredCamps.add(camp);
			}
		}
		
		Collections.sort(registeredCamps, (camp1, camp2) -> camp1.getName().compareTo(camp2.getName()));
		
		return registeredCamps;
	}

	@Override
	public boolean registerForCamp(String studentID, int campID, boolean committee) {
		Map<Integer, Camp> campData = DataStore.getCampData();
		Camp camp = campData.get(campID);
		
		User user = AuthStore.getCurrentUser();
		
		boolean success;
		
		//check if withdrawn
		if (camp.getWithdrawn().contains(studentID)) {
			System.out.println("You are not allowed to register for a camp you have previously withdrawn from.");
			return false;
		}
		
		//check for date clash
		List<LocalDate> campDates = camp.getDates();
		ArrayList<Camp> registeredCamps = getRegisteredCamps(studentID);
		Set<LocalDate> unavailableDates = new HashSet<>();
		
		for (Camp registeredCamp : registeredCamps) {
			unavailableDates.addAll(registeredCamp.getDates());
		}
		
		for (LocalDate campDate : campDates) {
			if (unavailableDates.contains(campDate)) {
				System.out.println("Camp date for " + camp.getName() + "clashes with previously registered camp");
				return false;
			}
		}
		
		// register student for camp if no clash
		if (committee) {
			Map<String, Committee> committeeData = DataStore.getCommitteeData();
			if (committeeData.get(studentID) != null) {
				System.out.println("You are already a camp committee member for another camp.");
				System.out.println("Please register as an attendee instead.");
				return false;
			}
			Committee newCommittee = new Committee(user.getName(), user.getPassword(), studentID, user.getEmail(), user.getFaculty(), user.isFirstLogin(), campID);
			committeeData.put(studentID, newCommittee);
			success = camp.addCommittee(studentID);
		}
		else
			success = camp.addAttendee(studentID);
		
		return success && DataStore.saveData();
	}

	@Override
	public boolean withdrawFromCamp(String studentID, int campID) {
		Map<Integer, Camp> campData = DataStore.getCampData();
		Camp camp = campData.get(campID);
		
		boolean success;
		
		List<String> committee = camp.getCampCommittee();
		
		if (committee.contains(studentID)) {
			System.out.println("Camp committee members are not allowed to withdraw from camp");
			success = false;
		}
		else {
			System.out.println("Please confirm the option. You are not allowed to register for camps you have withdrawn from");
			success = camp.removeAttendee(studentID);
		}
		
		return success && DataStore.saveData();
	}
}
