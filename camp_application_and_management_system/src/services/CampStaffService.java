/**
 * 
 */
package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import enums.Schools;
import interfaces.ICampStaffService;
import model.camp.Camp;
import model.user.User;
import stores.AuthStore;
import stores.DataStore;

/**
 * {@link CampStaffService} implements {@link ICampStaffService} interface and
 * provide camp functions in the role permission of a staff
 */
public class CampStaffService implements ICampStaffService {

	/**
	 * Construct an instance of {@link CampStaffService}
	 */
	public CampStaffService() {}

	@Override
	public boolean createCamp(Camp camp) {
		Map<Integer, Camp> campData = DataStore.getCampData();
		
		campData.put(camp.getCampID(), camp);
		
        DataStore.setCampData(campData);

        return true;
	}

	@Override
	public ArrayList<Camp> getAllCamps() {
		Map<Integer, Camp> campData = DataStore.getCampData();
		ArrayList<Camp> allCamps = new ArrayList<>();
		
		for (Camp camp : campData.values()) {
			allCamps.add(camp);
		}
		
		return allCamps;
	}
	
	/**
	 * Check who the logged in staff user is and 
	 * get all the camps created by this staff user
	 * 
	 * @return an ArrayList of camps created by the logged in staff user
	 */
	public ArrayList<Camp> getCreatedCamps(){
		Map<Integer, Camp> campData = DataStore.getCampData();
		User user = AuthStore.getCurrentUser();
		ArrayList<Camp> createdCamps = new ArrayList<>();
		
		for (Camp camp : campData.values()) {
			if (camp.getStaffIC().equals(user.getID())) {
				createdCamps.add(camp);
			}
		}
		
		return createdCamps;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean editCamp(Camp camp, int field, Object value) {
		switch (field) {
			case 1:
				if (value instanceof String) {
					camp.setName((String) value);
				}
				break;
			case 2:
				if (value instanceof List<?> && !((List<?>) value).isEmpty()) {
					List<?> listValue = (List<?>) value;
					if (listValue.get(0) instanceof LocalDate) {
						camp.setDates((List<LocalDate>) value);
					}
				}
				break;
			case 3:
				if (value instanceof LocalDate) {
					camp.setClosing((LocalDate) value);
				}
				break;
			case 4:
				if (value instanceof List<?> && !((List<?>) value).isEmpty()) {
					List<?> listValue = (List<?>) value;
					if (listValue.get(0) instanceof Schools) {
						camp.setAvailable((List<Schools>) value);
					}
				}
			case 5:
				if (value instanceof String) {
					camp.setLocation((String) value);
				}
				break;
			case 6:
				if (value instanceof Integer) {
					camp.setTotalSlots((int) value);
				}
				break;
			case 7:
				if (value instanceof String) {
					camp.setDescription((String) value);
				}
				break;
			}
		return DataStore.saveData();
	}
	
	@Override
	public boolean deleteCamp(Camp camp) {
		Map<Integer, Camp> campData = DataStore.getCampData();
		
		campData.remove(camp.getCampID());
		
		return DataStore.saveData();
	}

	@Override
	public boolean toggleCampVisibilty(ArrayList<Camp> camps) {
		for (Camp camp : camps) {
			boolean visibility = camp.getVisibility();
			camp.setVisibility(!visibility);
		}
		return DataStore.saveData();
	}
}
