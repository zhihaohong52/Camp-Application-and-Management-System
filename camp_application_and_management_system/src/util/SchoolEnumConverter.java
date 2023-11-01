/**
 * 
 */
package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import enums.Schools;

/**
 * 
 */
public class SchoolEnumConverter {
	
	public static final Schools[] CoE = {Schools.SCSE, Schools.CCEB, Schools.EEE, Schools.CEE, Schools.MSE, Schools.MAE};

	public static final Schools[] CoHASS = {Schools.ADM, Schools.SOH, Schools.SSS, Schools.WKWSCI};
	
	public static final Schools[] CoS = {Schools.CCEB, Schools.SPMS, Schools.SBS, Schools.ASE};
	
	/**
	 * 
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
	
	public static List<Schools> allSchools(){
		Schools[] allSchools = Schools.values();
		List<Schools> schoolList = new ArrayList<>(Arrays.asList(allSchools));
		return schoolList;
	}
}
