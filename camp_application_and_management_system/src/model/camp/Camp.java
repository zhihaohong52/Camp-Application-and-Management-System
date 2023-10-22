/**
 * 
 */
package model.camp;

import model.user.*;
import util.Schools;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Represents a camp declared within the system
 * @author whoever want can just put ur name here lol yes
 * @version 1.0
 * @since 2023-10-20
 */
public class Camp {
	
	public int campID;
	
	public String name; 
	
	public List<LocalDate> dates = new ArrayList<LocalDate>();
	
	public LocalDate closing;
	
	public List<Schools> available = new ArrayList<Schools>();
	
	public String location;
	
	public int totalSlots;
	
	public List<Student> students = new ArrayList<>();
	
	public int campCommitteeSlots = 10;
	
	public List<Committee> campCommittee = new ArrayList<Committee>(10);
	
	public String description;
	
	 String staffIC;
	
	/**
	 * @param name
	 * @param dates
	 * @param closing
	 * @param available
	 * @param location
	 * @param totalSlots
	 * @param description
	 * @param staffIC
	 */
	public Camp(int campID, String name, List<LocalDate> dates, LocalDate closing, List<Schools> available,
			String location, int totalSlots, String description, String staffIC) {
		this.campID = campID;
		this.name = name;
		this.dates = dates;
		this.closing = closing;
		this.available = available;
		this.location = location;
		this.totalSlots = totalSlots;
		this.description = description;
		this.staffIC = staffIC;
	}

	/**
	 * @return
	 */
	public int getCampID() {
		return campID;
	}

	/**
	 * @param campID
	 */
	public void setCampID(int campID) {
		this.campID = campID;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getStaffIC() {
		return staffIC;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public List<LocalDate> getDates() {
		return dates;
	}

	/**
	 * @param dates
	 */
	public void setDates(List<LocalDate> dates) {
		this.dates = dates;
	}

	/**
	 * @return
	 */
	public LocalDate getClosing() {
		return closing;
	}

	/**
	 * @param closing
	 */
	public void setClosing(LocalDate closing) {
		this.closing = closing;
	}

	/**
	 * @return
	 */
	public List<Schools> getAvailable() {
		return available;
	}

	/**
	 * @param available
	 */
	public void setAvailable(List<Schools> school) {
		this.available.addAll(school);
	}

	/**
	 * @return
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public void addAttendee(Student newAttendee) {
		if (totalSlots == 0) 
			System.out.println("Camp already full!");
		else {
			students.add(newAttendee);
			totalSlots--;
		}
	}
	
	/**
	 * @return
	 */
	public int getTotalSlots() {
		return totalSlots;
	}

	/**
	 * @param totalSlot
	 */
	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}

	
	/**
	 * @param newCommittee
	 */
	public void addCommitteeCommittee(Committee newCommittee) {
		if (totalSlots == 0 || campCommitteeSlots == 0 ) //
			System.out.println("No more slots available!");
		else {
			campCommittee.add(newCommittee);
			campCommitteeSlots--;
			totalSlots--;
		}
	}
	
	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
