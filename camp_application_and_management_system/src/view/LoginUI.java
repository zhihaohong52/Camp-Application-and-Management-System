/**
 * 
 */
package view;

//import cams.database.store.UserDB;
import cams.model.user.*;

import java.util.Scanner;

/**
 * 
 */
public class LoginUI {
		
	public LoginUI() {
		Scanner sc = new Scanner(System.in);
		//UserDB users = new UserDB();
		
		System.out.println("Welcome to NTU Camp Management and Application System (CAMs)");
		System.out.println("---Login---");
	
		
		boolean autheticated = false;
		
		do {
			System.out.println("Enter your user ID: ");
			String userID = sc.nextLine();
			
			User user = users.findUser(userID);
			if (user == null) {
				System.out.println("User not found!");
			}
			else {
				System.out.println("Enter your password: ");
				String password = sc.nextLine();
				if (users.checkPassword(user, password)) {
					System.out.println("Login succeeful! Welcome " + user.getName());
					if (!user.isFirstLogin()) {
						autheticated = true;
						return;
					}
					else {
						System.out.println("First logon. Please reset your password.");
						user.setPassword();
					}
				}
				else {
					System.out.println("Incorrect password. Please try again!");
				}
			}			
		}while (autheticated == false); 
		
	}
	
}
