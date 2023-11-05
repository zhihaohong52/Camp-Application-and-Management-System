/**
 * 
 */
package util;

import enums.UserRole;

/**
 * 
 */
public class UserRoleUtil {

	/**
	 * 
	 */
	public static UserRole convertToEnum(String input) {
		switch(input.toLowerCase()) {
			case "student":
				return UserRole.Student;
			case "staff":
				return UserRole.Staff;
			case "committee":
				return UserRole.Committee;
			default:
	            throw new IllegalArgumentException("Invalid input string: " + input);
		}
	}
}
