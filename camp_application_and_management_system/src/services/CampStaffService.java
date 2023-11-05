/**
 * 
 */
package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.Schools;
import interfaces.ICampStaffService;
import model.camp.Camp;
import model.user.Staff;
import store.AuthStore;
import store.DataStore;

/**
 * 
 */
public class CampStaffService implements ICampStaffService {

	/**
	 * 
	 */
	public CampStaffService() {}

	@Override
	public boolean createCamp(ArrayList<Camp> camps) {
		Map<Integer, Camp> campData = DataStore.getCampData();
		Map<Integer, Camp> newCampMap = new HashMap<Integer, Camp>();
		
		for (int i = 0; i < camps.size(); i++) {
			newCampMap.put(camps.get(i).getCampID(), camps.get(i));
        }

        campData.putAll(newCampMap);;
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
	
	public ArrayList<Camp> getCreatedCamps(){
		Map<Integer, Camp> campData = DataStore.getCampData();
		Staff staff = (Staff) AuthStore.getCurrentUser();
		ArrayList<Camp> createdCamps = new ArrayList<>();
		
		for (Camp camp : campData.values()) {
			if (camp.getStaffIC() == staff.getID()) {
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
