/**
 * 
 */
package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import enums.Schools;
import model.camp.Camp;
import model.camp.Enquiry;
import model.camp.Suggestion;
import stores.DataStore;

/**
 * The {@link SelectorUtil} class provides utility methods to select various
 * entities like camps, enquirie, suggestions and schools from a list by getting user
 * input.
 */
public class SelectorUtil {

	/**
     * {@link Scanner} object to get input from the user.
     */
	private static final Scanner sc = new Scanner(System.in);
	
	/**
	 * Constructs an instance of the {@link SelectorUtil} class
	 */
	public SelectorUtil() {}

	/**
     * Selects a camp from a list of camps by getting user input.
     *
     * @param camps the list of camps
     * @return the selected camp or null if no camp is selected
     */
	public static Camp campSelector(ArrayList<Camp> camps) {
		while (true) {
			sc.nextLine(); //consumes newline
			
			System.out.println("CampID\tCamp name");
			camps.forEach(camp->System.out.printf("%d\t%s\n", camp.getCampID(), camp.getName()));
			
			System.out.print("Select campID: ");
			String input = sc.nextLine();
			int campID;
			
			if (input.isEmpty()) {
				return null;
			}
			else if (input.matches("[0-9]+")) { // if input is an integer, find corresponding camp
				campID = Integer.parseInt(input);
			}
			else {
				System.out.println("Invalid input. Please enter a campID.");
				continue;
			}
			
			Optional<Camp> optionalSelectedCamp = camps.stream().filter(camp -> camp.getCampID() == campID).findFirst();
			
			if (optionalSelectedCamp.isPresent()) {
				return optionalSelectedCamp.get();
			}
			else {
				System.out.println("Invalid input. Please enter again.");
			}
		}
	}
	
	/**
     * Selects an enquiry from a list of enquiries by getting user input.
     *
     * @param enquiries the list of enquiries
     * @return the selected enquiry or null if no enquiry is selected
     */
	public static Enquiry enquirySelector(ArrayList<Enquiry> enquiries) {
		Map<Integer, Camp> campData = DataStore.getCampData();
		while (true) {
			for (Enquiry enquiry : enquiries) {
				System.out.println("Enquiry no. " + enquiry.getEnquiryID());
				System.out.println("Camp: " + campData.get(enquiry.getCampID()).getName());
				System.out.println("Question: " + enquiry.getQuestion());
			}
			
			sc.nextLine();
			
			System.out.println("Press Enter to exit");
			System.out.print("Select enquiry no. : ");
			
			String input = sc.nextLine();
			int enquiryID;
			
			if (input.isEmpty()){
				return null;
			}
			else if (input.matches("[0-9]+")) { // if input is an integer, find corresponding camp
				enquiryID = Integer.parseInt(input);
			}
			else {
				System.out.println("Invalid input. Please enter a campID.");
				continue;
			}
			
			Optional<Enquiry> optionalSelectedEnquiry = enquiries.stream().filter(enquiry -> enquiry.getEnquiryID() == enquiryID).findFirst();
			
			if (optionalSelectedEnquiry.isPresent()) {
				return optionalSelectedEnquiry.get();
			}
			else {
				System.out.println("Invalid input. Please enter again.");
			}
		}
	}
	
	/**
     * Selects an suggestion from a list of suggestions by getting user input.
     *
     * @param suggestions the list of suggestions
     * @return the selected suggestion or null if no suggestion is selected
     */
	public static Suggestion suggestionSelector(ArrayList<Suggestion> suggestions) {
		Map<Integer, Camp> campData = DataStore.getCampData();
		while (true) {
			for (Suggestion suggestion : suggestions) {
				System.out.println("Suggestion no. " + suggestion.getSuggestionID());
				System.out.println("Camp: " + campData.get(suggestion.getCampID()).getName());
				System.out.println("Question: " + suggestion.getQuestion());
			}
			
			sc.nextLine();
			
			System.out.println("Press Enter to exit");
			System.out.print("Select suggestion no. : ");
			
			String input = sc.nextLine();
			int suggestionID;
			
			if (input.isEmpty()){
				return null;
			}
			else if (input.matches("[0-9]+")) { // if input is an integer, find corresponding camp
				suggestionID = Integer.parseInt(input);
			}
			else {
				System.out.println("Invalid input. Please enter a campID.");
				continue;
			}
			
			Optional<Suggestion> optionalSelectedSuggestion = suggestions.stream().filter(suggestion -> suggestion.getSuggestionID() == suggestionID).findFirst();
			
			if (optionalSelectedSuggestion.isPresent()) {
				return optionalSelectedSuggestion.get();
			}
			else {
				System.out.println("Invalid input. Please enter again.");
			}
		}
	}
	
	/**
     * Selects school(s) from a list of schools by getting user input.
     * @return the list of schools the camp is open to
     */
	public static List<Schools> schoolSelector(){
		List<Schools> available = new ArrayList<Schools>();
		
		do {
			System.out.println("Please indicate if the camp is open to all schools (Y/N): ");
			String input = sc.next();
			if (input.equals("Y") || input.equals("y")) {
				System.out.println("Camp is open to all schools.");
				available = SchoolEnumUtil.allSchools();
				break;
			}
			else if (input.equals("N") || input.equals("n")) {
				System.out.println("Please indicate if the camp is open to a college (Y/N): ");
				String input2 = sc.next();
				if (input2.equals("Y") || input2.equals("y")) {
					System.out.println("Select college: ");
					System.out.println("1. CoE");
					System.out.println("2. CoHASS");
					System.out.println("3. CoS");
					int choice = sc.nextInt();
					switch(choice) {
						case 1:
							for (Schools school : SchoolEnumUtil.CoE)
								available.add(school);
							break;
						case 2:
							for (Schools school : SchoolEnumUtil.CoHASS)
								available.add(school);
							break;
						case 3:
							for (Schools school : SchoolEnumUtil.CoS)
								available.add(school);
							break;
						default:
							System.out.println("Invalid input");		
					}
					break;
				}
				else if (input2.equals("N") || input2.equals("n")) {
					System.out.print("Number of schools the camps is open to: ");
					int numOfSchools = sc.nextInt();
					List<Schools> schoolList = SchoolEnumUtil.allSchools();
					for (int i = 0; i < numOfSchools; i++) {
						System.out.println("Please indicate which school(s) the camp is open to: ");
						for (int j = 0; j < schoolList.size(); j++) {
							Schools school = schoolList.get(j);
							System.out.println((j+1) + "." + school);
						}
						int choice = sc.nextInt();
						switch(choice) {
							case 1:
			                    available.add(Schools.SCSE);
			                    break;
			                case 2:
			                    available.add(Schools.CCEB);
			                    break;
			                case 3:
			                    available.add(Schools.EEE);
			                    break;
			                case 4:
			                    available.add(Schools.CEE);
			                    break;
			                case 5:
			                    available.add(Schools.MSE);
			                    break;
			                case 6:
			                    available.add(Schools.MAE);
			                    break;
			                case 7:
			                    available.add(Schools.NBS);
			                    break;
			                case 8:
			                    available.add(Schools.ADM);
			                    break;
			                case 9:
			                    available.add(Schools.SOH);
			                    break;
			                case 10:
			                    available.add(Schools.SSS);
			                    break;
			                case 11:
			                    available.add(Schools.WKWSCI);
			                    break;
			                case 12:
			                    available.add(Schools.SPMS);
			                    break;
			                case 13:
			                    available.add(Schools.SBS);
			                    break;
			                case 14:
			                    available.add(Schools.ASE);
			                    break;
			                case 15:
			                    available.add(Schools.LKCMedicine);
			                    break;
			                case 16:
			                    available.add(Schools.NIE);
			                    break;
			                default:
			                    System.out.println("Invalid input");
							}
					}
					break;
				}
				else {
					System.out.println("Invalid input! Please input Y or N.");
				}
				System.out.println("Camp is open to the following schools: ");
				for (int j = 0; j < available.size(); j++) {
					Schools school = available.get(j);
					System.out.print(school + ",");
				}
				System.out.println();
				break;
			}
			else
				System.out.println("Invalid input! Please input Y or N.");
		} while (true);
		return available;
	}
}
