/**
 * 
 */
package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import enums.Schools;
import interfaces.ICampStudentService;
import model.camp.Camp;
import model.user.Committee;
import model.user.Student;
import store.AuthStore;
import store.DataStore;

/**
 * 
 */
public class CampStudentService implements ICampStudentService {

	/**
	 * 
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
		return availableCamps;
	}

	@Override
	public ArrayList<Camp> getRegisteredCamps(String studentID) {
		Map<Integer, Camp> campData = DataStore.getCampData();
		ArrayList<Camp> registeredCamps = new ArrayList<>();
		
		for (Camp camp : campData.values()) {
			List<String> studentList = camp.getStudents();
			
			if (studentList.contains(studentID)) {
				registeredCamps.add(camp);
			}
		}
		return registeredCamps;
	}

	@Override
	public boolean registerForCamp(String studentID, int campID, boolean committee) {
		Map<Integer, Camp> campData = DataStore.getCampData();
		Camp camp = campData.get(campID);
		
		Map<String, Committee> committeeData = DataStore.getCommitteeData();
		
		Student student = (Student) AuthStore.getCurrentUser();
		
		Boolean success;
		
		//check for date clash
		List<LocalDate> campDates = camp.getDates();
		
		ArrayList<Camp> registeredCamps = getRegisteredCamps(studentID);
		Set<LocalDate> unavailableDates = new HashSet<>();
		
		for (Camp registeredCamp : registeredCamps) {
			unavailableDates.addAll(registeredCamp.getDates());
		}
		
		for (LocalDate campDate : campDates) {
			if (unavailableDates.contains(campDate)) {
				System.out.println("Camp date for " + camp + "clashes with previously registered camp");
				return false;
			}
		}
		
		// register student for camp if no clash
		if (committee) {
			// create new committee objects and store into hashmaps
			Committee newCommittee = new Committee(student.getName(), student.getPassword(), 
					String.join(Integer.toString(campID), studentID), 
					student.getEmail(), student.getFaculty(), student.isFirstLogin(), campID);
			committeeData.put(String.join(Integer.toString(campID), studentID), newCommittee);
			
			DataStore.setCommitteeData(committeeData);
			System.out.print(committeeData);
			
			success = camp.addCommittee(studentID);
		}
		else
			success = camp.addAttendee(studentID);
		
		return success && DataStore.saveData();
	}

}
