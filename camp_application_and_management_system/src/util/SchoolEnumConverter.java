/**
 * 
 */
package util;

import enums.Schools;

/**
 * 
 */
public class SchoolEnumConverter {

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
}
