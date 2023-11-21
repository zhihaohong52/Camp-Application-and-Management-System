package util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.camp.Camp;

/**
 * The {@link CampFilter} provides a filter methods for user interface
 */
public class CampFilter {

	/**
	 * Construct an instance of {@link CampFilter}
	 */
	public CampFilter() {}

	/**
	 * Filter by camp names
	 * @param camps the list of camps
	 * @param name the string filter
	 * @return list of filtered camps results
	 */
	public static ArrayList<Camp> filterByName(ArrayList<Camp> camps, String name){
		ArrayList<Camp> filteredCamps = new ArrayList<Camp>();
		
		// filter camps if camp names contains name string
		for (Camp camp : camps) {
			if (camp.getName().toLowerCase().contains(name.toLowerCase())) {
				filteredCamps.add(camp);
			}
		}
		
		return filteredCamps;
	}

	/**
	 * filter by camp dates
	 * @param camps the list of camps
	 * @param date the date filter
	 * @return list of filtered camps results
	 */
	public static ArrayList<Camp> filterByDate(ArrayList<Camp> camps, LocalDate date){
		
		ArrayList<Camp> filteredCamps = new ArrayList<Camp>();
		
		// filter camps if camp dates contains date	string
		for (Camp camp : camps) {
			if (camp.getDates().contains(date)) {
				filteredCamps.add(camp);
			}
		}
		
		return filteredCamps;
	}
	
	/**
	 * filter by camp location
	 * @param camps the list of camps
	 * @param location the location filter
	 * @return list of filtered camps results
	 */
	public static ArrayList<Camp> filterByLocation(ArrayList<Camp> camps, String location){
		
		ArrayList<Camp> filteredCamps = new ArrayList<Camp>();
		
		// filter camps if camp location contains location string
		for (Camp camp : camps) {
			if (camp.getLocation().toLowerCase().contains(location.toLowerCase())) {
				filteredCamps.add(camp);
			}
		}
		
		return filteredCamps;
	}
	
	/**
	 * filter by camp description
	 * @param camps the list of camps
	 * @param description the description filter
	 * @return list of filtered camps results
	 */
	public static ArrayList<Camp> filterByDescription(ArrayList<Camp> camps, String description){
		
		ArrayList<Camp> filteredCamps = new ArrayList<Camp>();
		
		//filter camps if camp description contains description string
		for (Camp camp : camps) {
			if (camp.getDescription().toLowerCase().contains(description.toLowerCase())) {
				filteredCamps.add(camp);
			}
		}
		
		return filteredCamps;
	}
	
	/**
	 * filter by student participants involved in camp 
	 * @param camps the list of camps
	 * @param studentID the studentId filter
	 * @return list of filtered camps results
	 */
	public static ArrayList<Camp> filterByStudent(ArrayList<Camp> camps, String studentID){
		
		ArrayList<Camp> filteredCamps = new ArrayList<Camp>();
		
		//filter camps if camp student list contains studentID string
		for (Camp camp : camps) {
			List<String> students = camp.getStudents();
			if (students != null && students.stream().anyMatch(s -> s.toLowerCase().equals(studentID.toLowerCase()))) {
                filteredCamps.add(camp);
            }
		}
		
		return filteredCamps;		
	}
	
	/**
	 * filter by committee member involved in camp 
	 * @param camps the list of camps
	 * @param committeeID the committeeID filter
	 * @return list of filtered camps results
	 */
	public static ArrayList<Camp> filterByCommittee(ArrayList<Camp> camps, String committeeID){
		
		ArrayList<Camp> filteredCamps = new ArrayList<Camp>();
		
		//filter camps if camp committee list contains committeeID string
		for (Camp camp : camps) {
			List<String> students = camp.getStudents();
			if (students != null && students.stream().anyMatch(s -> s.toLowerCase().equals(committeeID.toLowerCase()))) {
                filteredCamps.add(camp);
            }
		}
		
		return filteredCamps;		
	}
}
