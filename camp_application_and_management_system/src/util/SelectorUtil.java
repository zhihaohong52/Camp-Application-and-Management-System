/**
 * 
 */
package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import enums.Schools;
import model.camp.Camp;

/**
 * 
 */
public class SelectorUtil {

	private static final Scanner sc = new Scanner(System.in);
	
	/**
	 * 
	 */
	public SelectorUtil() {}

	public static Camp campSelector(ArrayList<Camp> camps) {
		while (true) {
			System.out.println("CampID\tCamp name\tVisibility");
			camps.forEach(camp->System.out.printf("%d\t%s\t%s\n", camp.getCampID(), camp.getName(), camp.getVisibility()));
			
			System.out.println("Select campID: ");
			String input = sc.nextLine();
			int campID;
			
			if (input.isEmpty()) { // if input is empty, return
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
