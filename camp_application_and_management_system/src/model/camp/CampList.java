/**
 * 
 */
package model.camp;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class CampList{
	
	public List<Camp> camps = new ArrayList<Camp>();
	
	public CampList(){}
	
	public void createCamp(Camp newCamp) {
		camps.add(newCamp);
	}
	
	public void deleteCamp(Camp toDelete) {
		camps.remove(toDelete);
	}
	
	public void editCamp(Camp before, Camp after) {
		int index = camps.indexOf(before);
		camps.set(index, after);
	}
}
