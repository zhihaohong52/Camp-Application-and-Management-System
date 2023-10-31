/**
 * 
 */
package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfaces.ICampStaffService;
import model.camp.Camp;
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
	public ArrayList<Camp> viewCamps() {
		Map<Integer, Camp> campData = DataStore.getCampData();
		ArrayList<Camp> allCamps = new ArrayList<>();
		
		for (Camp camp : campData.values()) {
			allCamps.add(camp);
		}
		
		return allCamps;
	}

	@Override
	public boolean editCamp(Camp camp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean toggleCampVisibilty(ArrayList<Camp> camps) {
		// TODO Auto-generated method stub
		return false;
	}

}
