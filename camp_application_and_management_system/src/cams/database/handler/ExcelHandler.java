/**
 * 
 */
package cams.database.handler;

import cams.model.user.*;
import cams.util.UserRole;

import java.io.IOException;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 */
public class ExcelHandler {
	public static List read(String fileName) throws IOException {
		List<String> data = new ArrayList<String>() ;
	    Scanner sc = new Scanner(new FileInputStream(fileName));
	    sc.nextLine();
	    try {
	      while (sc.hasNextLine()){
	    	  data.add(sc.nextLine());
	      }
	    }
	    finally{
	      sc.close();
	    }
	    return data;
	}
	
	public static ArrayList<User> readContent(String fileName, UserRole type) throws IOException{
		ArrayList<String> stringArray = (ArrayList<String>)read(fileName);
		ArrayList<User> userList = new ArrayList<User>();
		
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String)stringArray.get(i);
			
			StringTokenizer star =new StringTokenizer(st, "\t");
			String name = star.nextToken().trim();
			String email = star.nextToken().trim();
			
			int idx = email.indexOf("@");
			String userID = email.substring(0,idx);
			
			String faculty = star.nextToken().trim();
			
			if (type == UserRole.Student) {
				Student student = new Student(name, userID, faculty);
				userList.add(student);
			}
			else {
				Staff staff = new Staff(name, userID, faculty);
				userList.add(staff);
			}
		}
		return userList;
	}
	
	/*public static void main(String[] args) throws IOException {
		ArrayList<User> userList = ExcelHandler.readContent("student_list.txt", UserType.Student);
		userList.addAll(ExcelHandler.readContent("staff_list.txt", UserType.Staff));
		for (int i = 0 ; i < userList.size() ; i++) {
			User user = (User)userList.get(i);
			System.out.println("Name: " + user.getName());
			System.out.println("ID: " + user.getID());
			System.out.println("Faculty: " + user.getFaculty());
			System.out.println("User type: " + user.getClass());
		}
	}*/
}
