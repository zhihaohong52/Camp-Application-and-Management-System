/**
 * 
 */
package cams.ui;

import cams.database.database.UserDB;
import cams.model.user.*;

import java.util.Scanner;

/**
 * 
 */
public class LoginUI {
		
	public LoginUI() {
		Scanner sc = new Scanner(System.in);
		UserDB users = new UserDB();
		
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
					System.out.println("Login succeeful! Welcome " + user.getID());
					if (user.isFirstLogin()) {
						user.setPassword();
						user.removeDefault();
					}
					autheticated = true;
				}
				else {
					System.out.print("Incorrect password. Please try again!");
				}
			}
			
		}while (autheticated == false); 
		
	}
	
	
}
