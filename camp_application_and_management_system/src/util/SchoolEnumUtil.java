package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import enums.Schools;

/**
 * The {@link SchoolEnumUtil} provides utility functions for
 * schools that camps are able to participate in
 */
public class SchoolEnumUtil {

	/**
	 * Construct an instance of {@link SchoolEnumUtil}
	 */
	public SchoolEnumUtil() {}
	
	/**
	 * Schools under College of Engineering
	 */
	public static final Schools[] CoE = {Schools.SCSE, Schools.CCEB, Schools.EEE, Schools.CEE, Schools.MSE, Schools.MAE};

	/**
	 * Schools under College of Humanities and Social Sciences
	 */
	public static final Schools[] CoHASS = {Schools.ADM, Schools.SOH, Schools.SSS, Schools.WKWSCI};
	
	/**
	 * Schools under College of Science
	 */
	public static final Schools[] CoS = {Schools.CCEB, Schools.SPMS, Schools.SBS, Schools.ASE};
	
	/**
	 * Get a string input which can be converted to School enum variable
	 * @param input the string input of the school from csv file
	 * @return the school enum variable for code
	 */
	public static Schools convertToEnum(String input) {
		switch(input.toUpperCase()) {
			case "SCSE":
	            return Schools.SCSE;
	        case "CCEB":
	            return Schools.CCEB;
	        case "EEE":
	            return Schools.EEE;
	        case "CEE":
	            return Schools.CEE;
	        case "MSE":
	            return Schools.MSE;
	        case "MAE":
	            return Schools.MAE;
	        case "NBS":
	            return Schools.NBS;
	        case "ADM":
	            return Schools.ADM;
	        case "SOH":
	            return Schools.SOH;
	        case "SSS":
	            return Schools.SSS;
	        case "WKWSCI":
	            return Schools.WKWSCI;
	        case "SPMS":
	            return Schools.SPMS;
	        case "SBS":
	            return Schools.SBS;
	        case "ASE":
	            return Schools.ASE;
	        case "LKCMEDICINE":
	            return Schools.LKCMedicine;
	        case "NIE":
	            return Schools.NIE;
	        default:
	            throw new IllegalArgumentException("Invalid input string: " + input);
		}
	}

	/**
	 * Return a list of schools
	 * @return all the schools that exists in NTU
	 */
	public static List<Schools> allSchools(){
		Schools[] allSchools = Schools.values();
		List<Schools> schoolList = new ArrayList<>(Arrays.asList(allSchools));
		return schoolList;
	}
}
