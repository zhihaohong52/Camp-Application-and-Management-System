/**
 * 
 */
package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import enums.Schools;
import interfaces.ICampStudentService;
import model.camp.Camp;
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
			if (available.contains(school)) {
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

}
