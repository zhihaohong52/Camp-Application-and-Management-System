/**
 * 
 */
package model.camp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.Schools;

/**
 * Represents a camp declared within the system
 * @author Zhi Hao, !! everyone just put ur name here !!
 * @version 1.0
 * @since put date of submission 2023-11-dd
 */
public class Camp {
	
	private int campID;
	
	private String name; 
	
	private List<LocalDate> dates = new ArrayList<LocalDate>();
	
	private LocalDate closing;
	
	private List<Schools> available = new ArrayList<Schools>();
	
	private String location;
	
	private int totalSlots;
	
	private List<String> students = new ArrayList<String>();
	
	private int campCommitteeSlots = 10;
	
	private List<String> campCommittee = new ArrayList<String>(10);
	
	private String description;
	
	private String staffIC;
	
	private boolean visibility;
	
	private List<String> withdrawn = new ArrayList<String>();
	
	/**
	 * @param name
	 * @param dates
	 * @param closing
	 * @param available
	 * @param location
	 * @param totalSlots
	 * @param description
	 * @param staffIC
	 * @param visibility
	 */
	public Camp(int campID, String name, List<LocalDate> dates, LocalDate closing, List<Schools> available,
			String location, int totalSlots, String description, String staffIC, boolean visibility) {
		this.campID = campID;
		this.name = name;
		this.dates = dates;
		this.closing = closing;
		this.available = available;
		this.location = location;
		this.totalSlots = totalSlots;
		this.description = description;
		this.staffIC = staffIC;
		this.visibility = visibility;
	}
	
	public Camp(int campID, String name, List<LocalDate> dates, LocalDate closing, List<Schools> available,
			String location, int totalSlots, String description, String staffIC, boolean visibility, 
			List<String> students, int campCommitteeSlots, List<String> campCommittee, List<String> withdrawn) {
		this.campID = campID;
		this.name = name;
		this.dates = dates;
		this.closing = closing;
		this.available = available;
		this.location = location;
		this.totalSlots = totalSlots;
		this.description = description;
		this.staffIC = staffIC;
		this.visibility = visibility;
		this.students = students;
		this.campCommitteeSlots = campCommitteeSlots;
		this.campCommittee = campCommittee;
		this.withdrawn = withdrawn;
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

	
	public List<String> getStudents() {
		return students;
	}

	public void setStudents(List<String> students) {
		this.students = students;
	}
	
	public boolean addAttendee(String newAttendeeID) {
		if (totalSlots == 0) {
			System.out.println("Camp already full!");
			return false;
		}	
		else {
			students.add(newAttendeeID);
			totalSlots--;
			return true;
		}
	}
	
	public boolean removeAttendee(String attendeeID) {
		students.remove(attendeeID);
		totalSlots++;
		return true;
	}

	public int getCampCommitteeSlots() {
		return campCommitteeSlots;
	}

	public void setCampCommitteeSlots(int campCommitteeSlots) {
		this.campCommitteeSlots = campCommitteeSlots;
	}

	public List<String> getCampCommittee() {
		return campCommittee;
	}

	public void setCampCommittee(List<String> campCommittee) {
		this.campCommittee = campCommittee;
	}
	
	/**
	 * @param newCommittee
	 */
	public boolean addCommittee(String newCommittee) {
		if (totalSlots == 0 || campCommitteeSlots == 0 ) {
			System.out.println("No more slots available!");
			return false;
		}
			
		else {
			campCommittee.add(newCommittee);
			campCommitteeSlots--;
			totalSlots--;
			return true;
		}
	}

	public void setStaffIC(String staffIC) {
		this.staffIC = staffIC;
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
	
	public boolean getVisibility() {
		return visibility;
	}
	
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	/**
	 * @return the withdrawn
	 */
	public List<String> getWithdrawn() {
		return withdrawn;
	}

	/**
	 * @param withdrawn the withdrawn to set
	 */
	public void setWithdrawn(List<String> withdrawn) {
		this.withdrawn = withdrawn;
	}
}
