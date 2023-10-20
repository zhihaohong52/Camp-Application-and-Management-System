/**
 * 
 */
package cams.model.camp;

import cams.model.user.*;
import cams.util.Schools;

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
	
	public String name; 
	
	public List<LocalDate> dates = new ArrayList<LocalDate>();
	
	public LocalDate closing;
	
	public List<Schools> available = new ArrayList<Schools>();
	
	public String location;
	
	public int totalSlots;
	
	public List<Student> students = new ArrayList<>();
	
	public int campCommitteeSlots = 10;
	
	public List<CampCommitteeMember> campCommittee = new ArrayList<CampCommitteeMember>(10);
	
	public String description;
	
	public Staff staffIC;
	
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
	public Camp(String name, List<LocalDate> dates, LocalDate closing, List<Schools> available,
			String location, int totalSlots, String description, Staff staffIC) {
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
	public String getName() {
		return name;
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
	 * @param newMember
	 */
	public void addCommitteeMember(CampCommitteeMember newMember) {
		if (totalSlots == 0 || campCommitteeSlots == 0 ) //
			System.out.println("No more slots available!");
		else {
			campCommittee.add(newMember);
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
