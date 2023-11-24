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
 * Each camp has various attributes such as a unique identifier, name, closing date, available schools, location, 
 * time slots, camp committee slots, camp description, staff in charge, list of attendees and committee members, and withdrawn participants.
 * This class provides methods to interact with and edit camp related data. 
 */
public class Camp {
	
	/**
	 * camp's campID
	 */
	private int campID;
	
	/**
	 * name of camp
	 */
	private String name; 
	
	/**
	 * list of the dates the camp is held
	 */
	private List<LocalDate> dates = new ArrayList<LocalDate>();
	
	/**
	 * closing date of camp
	 */
	private LocalDate closing;
	
	/**
	 * list of available schools
	 */
	private List<Schools> available = new ArrayList<Schools>();
	
	/**
	 * location of camp
	 */
	private String location;
	
	/**
	 * total number of slots for camo
	 */
	private int totalSlots;
	
	/**
	 * list of students participating in the camp
	 */
	private List<String> students = new ArrayList<String>();
	
	/**
	 * number of camp committee slots
	 */
	private int campCommitteeSlots = 10;
	
	/**
	 * List of camp committee
	 */
	private List<String> campCommittee = new ArrayList<String>(10);
	
	/**
	 * camp description
	 */
	private String description;
	
	/**
	 * staff in-charge of the camp
	 */
	private String staffIC;
	
	/**
	 * the visibility of the camp
	 */
	private boolean visibility;
	
	/**
	 * list of withdrawn student from the camp
	 */
	private List<String> withdrawn = new ArrayList<String>();
	
	/**
	 * Constructor of class {@link Camp}
	 * @param campID ID of the camp
	 * @param name	Name of the camp
	 * @param dates A list of dates for the camp
	 * @param closing	Closing date for registration
	 * @param available	A list of schools that can register for the camp
	 * @param location	Location of the camp
	 * @param totalSlots Number of slots for registration
	 * @param description	Description of the camp
	 * @param staffIC	IC of staff in charge
	 * @param visibility	visibility of the camp to students
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
	/**
	 * Constructor of class {@link Camp}
	 * @param campID ID of the camp
	 * @param name	Name of the camp
	 * @param dates A list of dates for the camp
	 * @param closing	Closing date for registration
	 * @param available	A list of schools that can register for the camp
	 * @param location	Location of the camp
	 * @param totalSlots Number of slots for registration
	 * @param description	Description of the camp
	 * @param staffIC	IC of staff in charge
	 * @param visibility	visibility of the camp to students
	 * @param students	A list of students that registered for the camp
	 * @param campCommitteeSlots Number of slots for camp committee members
	 * @param campCommittee	A list of committee that attached to the camp
	 * @param withdrawn A list of students that withdraw from the camp
	 */
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
 	* Gets the unique identifier of the camp.
 	* @return The unique identifier of the camp.
 	*/
	public int getCampID() {
		return campID;
	}

	
	/**
 	* Sets the unique identifier of the camp.
 	*
 	* @param campID The unique identifier to set for the camp.
 	*/
	public void setCampID(int campID) {
		this.campID = campID;
	}

	/**
 	* Gets the name of the camp.
 	*
 	* @return The name of the camp.
 	*/
	public String getName() {
		return name;
	}

	/**
	 * Gets the staff in charge of the camp.
 	*
 	* @return The staff in charge of the camp.
 	*/
	public String getStaffIC() {
		return staffIC;
	}

	/**
 	* Sets the name of the camp.
 	*
 	* @param name The name to set for the camp.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
 	* Gets the list of dates associated with the camp.
 	*
 	* @return The list of dates for the camp.
 	*/

	public List<LocalDate> getDates() {
		return dates;
	}
	/**
 	* Sets the list of dates for the camp.
 	*
 	* @param dates The list of dates to set for the camp.
 	*/
	public void setDates(List<LocalDate> dates) {
		this.dates = dates;
	}

	/**
 	* Gets the closing date of the camp.
 	*
 	* @return The closing date of the camp.
 	*/
	public LocalDate getClosing() {
		return closing;
	}

	
	/**
 	* Sets the closing date of the camp.
 	*
 	* @param closing The closing date to set for the camp.
 	*/
	public void setClosing(LocalDate closing) {
		this.closing = closing;
	}

	/**
 	* Gets the list of available schools for the camp.
 	*
 	* @return The list of available schools for the camp.
 	*/
	public List<Schools> getAvailable() {
		return available;
	}

	/**
	 * Sets the list of available schools for the camp.
	 * @param school The list of available schools to set for the camp.
	 */
	public void setAvailable(List<Schools> school) {
		this.available.addAll(school);
	}

	/**
 	* Gets the location of the camp.
 	*	
 	* @return The location of the camp.
 	*/
	public String getLocation() {
		return location;
	}

	/**
 	* Sets the location of the camp.
 	*
 	* @param location The location to set for the camp.
 	*/
	public void setLocation(String location) {
		this.location = location;
	}

	/**
 	* Gets the total number of slots available for the camp.
 	*
 	* @return The total number of slots available for the camp.
 	*/
	public int getTotalSlots() {
		return totalSlots;
	}

	/**
 	* Sets the total number of slots available for the camp.
 	*
 	* @param totalSlots The total number of slots to set for the camp.
 	*/
	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}

	/**
 	* Gets the list of students (attendees) registered for the camp.
 	*
 	* @return The list of students registered for the camp.
 	*/
	public List<String> getStudents() {
		return students;
	}
	/**
 	* Sets the list of students (attendees) for the camp.
 	*
 	* @param students The list of students to set for the camp.
 	*/
	public void setStudents(List<String> students) {
		this.students = students;
	}
	/**
 	* Adds an attendee to the camp and decreases the total available slots.
 	*
 	* @param newAttendeeID The unique identifier of the new attendee.
 	* @return True if the attendee is successfully added, false otherwise.
 	*/
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

	/**
 	* Removes an attendee from the camp, increases the total available slots and add the attendee into the withdraw list
 	*
 	* @param attendeeID The unique identifier of the attendee to remove.
	* @return True if the attendee is successfully removed, false otherwise.
	*/
	public boolean removeAttendee(String attendeeID) {
		students.remove(attendeeID);
		totalSlots++;
		withdrawn.add(attendeeID);
		return true;
	}

	/**
	 * Gets the number of camp committee slots available for the camp.
 	*
 	* @return The number of camp committee slots available for the camp.
 	*/
	public int getCampCommitteeSlots() {
		return campCommitteeSlots;
	}

	/**
 	* Sets the number of camp committee slots available for the camp.
	*
	* @param campCommitteeSlots The number of camp committee slots to set for the camp.
	*/
	public void setCampCommitteeSlots(int campCommitteeSlots) {
		this.campCommitteeSlots = campCommitteeSlots;
	}

	/**
	 * Gets the list of camp committee members.
	 *
	 * @return The list of camp committee members.
	 */
	public List<String> getCampCommittee() {
		return campCommittee;
	}

	/**
	 * Sets the list of camp committee members.
	 *
	 * @param campCommittee The list of camp committee members to set.
	 */
	public void setCampCommittee(List<String> campCommittee) {
		this.campCommittee = campCommittee;
	}

	/**
	 * Adds a committee member to the camp and decreases both total available slots and camp committee slots.
 	*
 	* @param newCommittee The unique identifier of the new committee member.
	* @return True if the committee member is successfully added, false otherwise.
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

	/**
	 * Sets Unique identifier of the staff.
	 *
	 * @param staffIC The unique identifier of staff 
	 */
	public void setStaffIC(String staffIC) {
		this.staffIC = staffIC;
	}
	
	/**
	 * Gets the description of the camp.
	 *
	 * @return The description of the camp.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the camp.
	 *
	 * @param description The description to set for the camp.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the visibility status of the camp.
	 *
	 * @return True if the camp is visible, false otherwise.
	 */
	public boolean getVisibility() {
		return visibility;
	}
	
	/**
	 * Sets the visibility status of the camp.
	 *
	 * @param visibility True to set the camp as visible, false to set it as invisible.
	 */
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
	/**
	 * Gets the list of withdrawn participants from the camp.
	 *
	 * @return The list of withdrawn students from the camp.
	 */
	public List<String> getWithdrawn() {
		return withdrawn;
	}

	/**
	 * Sets the list of withdrawn participants for the camp.
	 *
	 * @param withdrawn The list of withdrawn participants to set for the camp.
	 */
	public void setWithdrawn(List<String> withdrawn) {
		this.withdrawn = withdrawn;
	}
}
