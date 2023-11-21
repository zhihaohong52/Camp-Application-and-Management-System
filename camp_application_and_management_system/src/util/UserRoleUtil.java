package util;

import enums.UserRole;

/**
 * The {@link UserRoleUtil} provide utility function for
 * converting string input to an enum for the user role variable
 */
public class UserRoleUtil {
	/**
	 * Construct an instance of {@link UserRoleUtil}
	 */
	public UserRoleUtil() {}

	/**
	 * Convert from string input to UserRole enum variable
	 * @param input the string input of UserRole status from csv file
	 * @return the UserRole enum variable for code
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
