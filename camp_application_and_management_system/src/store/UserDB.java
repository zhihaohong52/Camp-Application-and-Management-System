/**
 * 
 */
package store;

import model.user.*;
import util.UserRole;
import cams.database.handler.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class UserDB {
	private List<User> users;
	
	public UserDB() {
		try {
			users = new ArrayList<User>();
			users.addAll(ExcelHandler.readContent("student_list.txt", UserRole.Student));
			users.addAll(ExcelHandler.readContent("staff_list.txt", UserRole.Staff));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public User findUser(String userID) {
		for (User user: users) {
			if (user.getID().equals(userID)) {
				return user;
			}
		}
		return null;
	}
	
	public boolean checkPassword(User user, String password) {
		return user.getPassword().equals(password);
	}
	
	/*public void printDB() {
		for (User user: users) {
			System.out.println(user.getID());
		}
	}*/
}
