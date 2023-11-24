/**
 * 
 */
package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import enums.EnquiryStatus;
import enums.Schools;
import enums.SuggestionStatus;
import interfaces.IFileDataService;
import model.camp.Camp;
import model.camp.Enquiry;
import model.camp.Suggestion;
import model.user.Committee;
import model.user.Staff;
import model.user.Student;
import util.BooleanConverterUtil;
import util.EnquiryStatusUtil;
import util.SchoolEnumUtil;
import util.SuggestionStatusUtil;

/**
 * The {@link CsvDataService} class implements the {@link IFileDataService}
 * interface and provides methods for reading and writing data from/to CSV files.
 */
public class CsvDataService implements IFileDataService {

	/**
	 * List of headers for the CSV file that stores user data.
	 */
	private static List<String> userCsvHeaders = new ArrayList<String>();
	
	/**
	 * List of headers for the CSV file that stores student data.
	 */
	private static List<String> studentCsvHeaders = new ArrayList<String>();
	
	/**
	 * List of headers for the CSV file that stores staff data.
	 */
	private static List<String> staffCsvHeaders = new ArrayList<String>();

	/**
	 * List of headers for the CSV file that stores camp committee member data.
	 */
	private static List<String> committeeCsvHeaders = new ArrayList<String>();

	/**
	 * List of headers for the CSV file that stores camp data.
	 */
	private static List<String> campCsvHeaders = new ArrayList<String>();

	/**
	 * List of headers for the CSV file that stores enquiry data.
	 */
	private static List<String> enquiryCsvHeaders = new ArrayList<String>();

	/**
	 * List of headers for the CSV file that stores suggestion data.
	 */
	private static List<String> suggestionCsvHeaders = new ArrayList<String>();

	/**
	 * Constructs an instance of the {@link CsvDataService} class.
	 */
	public CsvDataService() {}
	
	// ----- Helper Function ------ //
	/**
	 * Reads data from the CSV file located at the given file path and returns
	 * it as a list of string arrays.
	 * 
	 * @param filePath	the file path of the CSV file to read
	 * @param headers	the list of headers for the CSV file
	 * @return			a list of string arrays containing the CSV data
	 */
	public List<String[]> readCsvFile(String filePath, List<String> headers){
		List<String[]> dataList = new ArrayList<String[]>();
		headers.clear();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
			//Headers
			String[] headerRow = br.readLine().split(",");
			for (String header : headerRow) {
				headers.add(header);
			}
			
			//Content
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				dataList.add(values);
			}
		} catch (IOException e) {
			System.out.println("Cannot import data! " + filePath);
		}
		
		return dataList;
	}
	
	/**
	 * Writes the given data to a CSV file located at the given file path.
	 * 
	 * @param filePath	the file path of the CSV file to write
	 * @param headers	the list of headers for the CSV file
	 * @param lines		the list of lines to write to the CSV file
	 * @return			true if the data is written successfully, false otherwise
	 */
	public boolean writeCsvFile(String filePath, List<String> headers, List<String> lines) {
		try (FileWriter writer = new FileWriter(filePath)){
			// Write Headers
			String headerLine = String.join(",", headers);
			writer.write(headerLine + "\n");
			
			// Write Content
			for (String line : lines) {
				writer.write(line + "\n");
			}
			
		} catch (IOException e) {
			System.out.println("Unable to export data!" + filePath);
			return false;
		}
		return true;
	}
	
	/**
	 * parse string into a map for code
	 * @param userRow string from csv file
	 * @return map for code
	 */
	private Map<String, String> parseUserRow(String[] userRow){
		String userID = userRow[0];
		String name = userRow[1];
		String email = userRow[2];
		String faculty = userRow[3];
		String role = userRow[4];
		String password = userRow[5];
		String firstLogin = userRow[6];
		
		// return
		Map<String, String> userInfoMap = new HashMap<String, String>();
		userInfoMap.put("userID", userID);
		userInfoMap.put("name", name);
		userInfoMap.put("email", email);
		userInfoMap.put("faculty", faculty);
		userInfoMap.put("role", role);
		userInfoMap.put("password", password);
		userInfoMap.put("firstLogin", firstLogin);
		
		return userInfoMap;
	}

	// ----- Interface method implementation ----- //
	
	// Student
	@Override
	public Map<String, Student> importStudentData(String usersFilePath, String studentsFilePath) {
		Map<String, Student> studentMap = new HashMap<String, Student>();
		
		List<String[]> usersRows = this.readCsvFile(usersFilePath, userCsvHeaders);
		List<String[]> studentsRows = this.readCsvFile(studentsFilePath, studentCsvHeaders);
		
		for (String[] userRow : usersRows) {
			Map<String, String> userInfoMap = parseUserRow(userRow);
			
			String role = userInfoMap.get("role");
			if (!role.equals("student"))
				continue;
			
			String userID = userInfoMap.get("userID");
			String password = userInfoMap.get("password");
			String email = userInfoMap.get("email");
			String name = userInfoMap.get("name");
			Schools faculty = SchoolEnumUtil.convertToEnum(userInfoMap.get("faculty"));
			boolean firstLogin = BooleanConverterUtil.convertToBoolean(userInfoMap.get("firstLogin"));
			
			for (String[] studentRow : studentsRows) {
				if (!studentRow[0].equals(userID))
					continue;
			}
			
			Student student = new Student(name, password, userID, email, faculty, firstLogin);
			
			studentMap.put(userID, student);
		}
		return studentMap;
	}

	@Override
	public boolean exportStudentData(String usersFilePath, String studentsFilePath, Map<String, Student> studentMap) {
		List<String> studentLines = new ArrayList<String>();
		List<String> userLines = new ArrayList<String>();

		// User
		List<String[]> usersRows = this.readCsvFile(usersFilePath, userCsvHeaders);
		for (String[] userRow : usersRows) {
			Map<String, String> userInfoMap = parseUserRow(userRow);
			String userLine = String.format("%s,%s,%s,%s,%s,%s,%s", 
					userInfoMap.get("userID"), 
					userInfoMap.get("name"),
					userInfoMap.get("email"), 
					userInfoMap.get("faculty"),
					userInfoMap.get("role"),
					userInfoMap.get("password"),
					userInfoMap.get("firstLogin"));
			if (userInfoMap.get("role").equals("student")) {
				Student student = studentMap.get(userInfoMap.get("userID"));
				
				userLine = String.format("%s,%s,%s,%s,%s,%s,%s", 
						student.getID(), 
						student.getName(),
						student.getEmail(),
						student.getFaculty().toString(), 
						"student",
						student.getPassword(),
						student.isFirstLogin());
			}
			
			userLines.add(userLine);
		}
		
		// Student
		for (Student student : studentMap.values()) {
			String studentLine = String.format("%s", student.getID());
			studentLines.add(studentLine);
		}
		
		boolean successUsers = this.writeCsvFile(usersFilePath, userCsvHeaders, userLines);
;		boolean successStudents = this.writeCsvFile(studentsFilePath, studentCsvHeaders, studentLines);
		return successUsers && successStudents;
	}

	@Override
	public Map<String, Staff> importStaffData(String usersFilePath, String staffsFilePath) {
		Map<String, Staff> staffMap = new HashMap<String, Staff>();
		
		List<String[]> usersRows = this.readCsvFile(usersFilePath, userCsvHeaders);
		List<String[]> staffsRows = this.readCsvFile(staffsFilePath, studentCsvHeaders);
		
		for (String[] userRow : usersRows) {
			Map<String, String> userInfoMap = parseUserRow(userRow);
			
			String role = userInfoMap.get("role");
			if (!role.equals("staff"))
				continue;
			
			String userID = userInfoMap.get("userID");
			String password = userInfoMap.get("password");
			String name = userInfoMap.get("name");
			String email = userInfoMap.get("email");
			Schools faculty = SchoolEnumUtil.convertToEnum(userInfoMap.get("faculty"));
			boolean firstLogin = BooleanConverterUtil.convertToBoolean(userInfoMap.get("firstLogin"));
			
			for (String[] staffRow : staffsRows) {
				if (!staffRow[0].equals(userID))
					continue;		
			}
			
			Staff staff = new Staff(name, password, userID, email, faculty, firstLogin);
			
			staffMap.put(userID, staff);
		}
		return staffMap;
	}

	@Override
	public boolean exportStaffData(String usersFilePath, String staffsFilePath, Map<String, Staff> staffMap) {
		List<String> staffLines = new ArrayList<String>();
		List<String> userLines = new ArrayList<String>();

		// User
		List<String[]> usersRows = this.readCsvFile(usersFilePath, userCsvHeaders);
		for (String[] userRow : usersRows) {
			Map<String, String> userInfoMap = parseUserRow(userRow);
			String userLine = String.format("%s,%s,%s,%s,%s,%s,%s", 
					userInfoMap.get("userID"), 
					userInfoMap.get("name"),
					userInfoMap.get("email"), 
					userInfoMap.get("faculty"),
					userInfoMap.get("role"),
					userInfoMap.get("password"),
					userInfoMap.get("firstLogin"));
			if (userInfoMap.get("role").equals("staff")) {
				Staff staff = staffMap.get(userInfoMap.get("userID"));
				
				userLine = String.format("%s,%s,%s,%s,%s,%s,%s", 
						staff.getID(), 
						staff.getName(),
						staff.getEmail(),
						staff.getFaculty().toString(), 
						"staff",
						staff.getPassword(),
						staff.isFirstLogin());
			}
			userLines.add(userLine);
		}
		
		// Staff
		for (Staff staff : staffMap.values()) {
			String staffLine = String.format("%s", staff.getID());
			staffLines.add(staffLine);
		}
		
		boolean successUsers = this.writeCsvFile(usersFilePath, userCsvHeaders, userLines);
;		boolean successStaffs = this.writeCsvFile(staffsFilePath, staffCsvHeaders, staffLines);
		return successUsers && successStaffs;
	}

	@Override
	public Map<String, Committee> importCommitteeData(String usersFilePath,String committeesFilePath) {
		Map<String, Committee> committeeMap = new HashMap<String, Committee>();
		
		List<String[]> usersRows = this.readCsvFile(usersFilePath, userCsvHeaders);
		List<String[]> committeesRows = this.readCsvFile(committeesFilePath, committeeCsvHeaders);
			
		for (String[] committeeRow : committeesRows) {
			String userID = committeeRow[0];
			int campID = Integer.parseInt(committeeRow[1]);
			int point = Integer.parseInt(committeeRow[2]);
			
			for (String[] userRow : usersRows) {
				Map<String, String> userInfoMap = parseUserRow(userRow);
				if (!userInfoMap.get("userID").equals(userID))
					continue;
				String name = userInfoMap.get("name");
				String password = userInfoMap.get("password");
				String email = userInfoMap.get("email");
				Schools faculty = SchoolEnumUtil.convertToEnum(userInfoMap.get("faculty"));
				boolean firstLogin = BooleanConverterUtil.convertToBoolean(userInfoMap.get("firstLogin"));
				Committee committee = new Committee(name, password, userID, email, faculty, firstLogin, campID, point);
				
				committeeMap.put(userID, committee);
			}
		}
			
		return committeeMap;
	}

	@Override
	public boolean exportCommitteeData(String usersFilePath, String committeesFilePath, Map<String, Committee> committeeMap) {
		List<String> committeeLines = new ArrayList<String>();
	
		// Committee
		for (Committee committee : committeeMap.values()) {
			String committeeLine = String.format("%s,%d,%s", committee.getID(), committee.getCampID(), committee.getPoint());
			committeeLines.add(committeeLine);
		}

;		boolean successCommittees = this.writeCsvFile(committeesFilePath, committeeCsvHeaders, committeeLines);
		return successCommittees;
	}

	@Override
	public Map<Integer, Camp> importCampData(String campsFilePath) {
		Map<Integer, Camp> campMap = new HashMap<Integer, Camp>();
		List<String[]> campsRows = this.readCsvFile(campsFilePath, campCsvHeaders);
		
		for (String[] campRow : campsRows) {
			int campID = Integer.parseInt(campRow[0]);
			String name = campRow[1];

			// Parse the dates and initialize as empty lists
	        List<LocalDate> dates = new ArrayList<>();
	        
			
	     // Parse the date and school strings from the CSV and add them to the lists
	        String datesString = campRow[2]; // Assuming dates are in the third column
	        String[] dateStrings = datesString.split(";"); // Split dates by a delimiter
	        for (String dateString : dateStrings) {
	            LocalDate date = LocalDate.parse(dateString);
	            dates.add(date);
	        }
	        
	        LocalDate closing = LocalDate.parse(campRow[3]);
	        
	        //Parse available schools and initialize as empty list
	        List<Schools> available = new ArrayList<>();
	        
	        String availableSchoolsString = campRow[4]; // Assuming available schools are in the fourth column
	        String[] schoolStrings = availableSchoolsString.split(";"); // Split schools by a delimiter
	        for (String schoolString : schoolStrings) {
	            Schools school = SchoolEnumUtil.convertToEnum(schoolString); // Convert into enum
	            available.add(school);
	        }
	        	        
	        String location = campRow[5];
	        int totalSlots = Integer.parseInt(campRow[6]);
	        
	        //Parse students and initialize as empty list
	        List<String> students = new ArrayList<>();
	        String studentsString = campRow[7];
	        String[] studentsList = studentsString.split(";");
	        for (String studentID : studentsList) {
	        	students.add(studentID);
	        }
	        
	        int campCommitteeSlots = Integer.parseInt(campRow[8]);
	        
	        //Parse camp committee and initialize as empty list
	        List<String> campCommittee = new ArrayList<>();
	        String campCommitteeString = campRow[9];
	        String[] campCommitteeList = campCommitteeString.split(";");
	        for (String campCommitteeID : campCommitteeList) {
	        	campCommittee.add(campCommitteeID);
	        }
	        
	        //Parse withdrawn and initialize as empty list
	        List<String> withdrawn = new ArrayList<>();
	        String withdrawnString = campRow[10];
	        String[] withdrawnList = withdrawnString.split(";");
	        for (String withdrawnID : withdrawnList) {
	        	withdrawn.add(withdrawnID);
	        }
	        
	        String description = campRow[11];
	        String staffIC = campRow[12];
	        
	        String visibilityString = campRow[13];
	        boolean visibility = BooleanConverterUtil.convertToBoolean(visibilityString);
	        	        
	        Camp camp = new Camp(campID, name, dates, closing, available, location, totalSlots, 
	        		description, staffIC, visibility, students, campCommitteeSlots, campCommittee, withdrawn);
	        
	        campMap.put(campID, camp);
		}
		
		return campMap;
	}

	@Override
	public boolean exportCampData(String campsFilePath, Map<Integer, Camp> campMap) {
		List<String> campLines = new ArrayList<String>();
		
		// Camp
		for (Camp camp : campMap.values()) {
			String datesString = String.join(";", camp.getDates().stream().map(LocalDate::toString).toArray(String[]::new));
			String availableString = String.join(";", camp.getAvailable().stream().map(Schools::toString).toArray(String[]::new));
			String studentString = camp.getStudents().stream().collect(Collectors.joining(";"));
			String campCommitteeString = camp.getCampCommittee().stream().collect(Collectors.joining(";"));
			String withdrawnString = camp.getWithdrawn().stream().collect(Collectors.joining(";"));
			String campLine = String.format("%d,%s,%s,%s,%s,%s,%d,%s,%d,%s,%s,%s,%s,%s", 
					camp.getCampID(), //1
					camp.getName(), //2
					datesString, //3
					camp.getClosing().toString(), //4
					availableString, //5
					camp.getLocation(), //6
					camp.getTotalSlots(), //7
					studentString, //8
					camp.getCampCommitteeSlots(), //9
					campCommitteeString, //10
					withdrawnString,//11
					camp.getDescription(), //12
					camp.getStaffIC(), //13
					camp.getVisibility()); //14
			
			campLines.add(campLine);
		}
	
		return this.writeCsvFile(campsFilePath, campCsvHeaders, campLines);
	}

	@Override
	public Map<Integer, Enquiry> importEnquiryData(String enquiryFilePath) {
		Map<Integer, Enquiry> enquiryMap = new HashMap<Integer, Enquiry>();
		List<String[]> enquiryRows = this.readCsvFile(enquiryFilePath, enquiryCsvHeaders);
		
		for (String[] enquiryRow : enquiryRows) {
			int enquiryID = Integer.parseInt(enquiryRow[0]);
			
			String studentID = enquiryRow[1];
			String question = enquiryRow[2];
			
			List<String> replies = new ArrayList<>();
	        String replyString = enquiryRow[3];
	        String[] replyList = replyString.split(";");
	        for (String reply : replyList) {
	        	replies.add(reply);
	        }
	        
	        List<String> replierIDs = new ArrayList<>();
	        String replierIDString = enquiryRow[4];
	        String[] replierIDList = replierIDString.split(";");
	        for (String replierID: replierIDList) {
	        	replierIDs.add(replierID);
	        }
	        
	        int campID = Integer.parseInt(enquiryRow[5]);
	        
	        EnquiryStatus status = EnquiryStatusUtil.convertToEnum(enquiryRow[6]);
	        
	        Enquiry enquiry = new Enquiry(enquiryID, campID, studentID, question, replies, replierIDs, status);
	        
	        enquiryMap.put(enquiryID, enquiry);

	    }
		
		return enquiryMap;
	}

	@Override
	public boolean exportEnquiryData(String enquiryFilePath, Map<Integer, Enquiry> enquiryMap) {
		List<String> enquiryLines = new ArrayList<String>();
		
		for (Enquiry enquiry : enquiryMap.values()) {
			String replyString = enquiry.getReply().stream().collect(Collectors.joining(";"));
			String replierIDString = enquiry.getReplierID().stream().collect(Collectors.joining(";"));
			String enquiryLine = String.format("%d,%s,%s,%s,%s,%d,%s",
					enquiry.getEnquiryID(),
					enquiry.getStudentID(),
					enquiry.getQuestion(),
					replyString,
					replierIDString,
					enquiry.getCampID(),
					enquiry.getStatus().toString());
			enquiryLines.add(enquiryLine);
		}
		
		return this.writeCsvFile(enquiryFilePath, enquiryCsvHeaders, enquiryLines);
	}

	@Override
	public Map<Integer, Suggestion> importSuggestionData(String suggestionFilePath) {
		Map<Integer, Suggestion> suggestionMap = new HashMap<Integer, Suggestion>();
		List<String[]> suggestionRows = this.readCsvFile(suggestionFilePath, suggestionCsvHeaders);
		
		for (String[] suggestionRow : suggestionRows) {
			int suggestionID = Integer.parseInt(suggestionRow[0]);
			String studentID = suggestionRow[1];
			String question = suggestionRow[2];
	        String reply = suggestionRow[3];
	        String replierID = suggestionRow[4]; 
	        int campID = Integer.parseInt(suggestionRow[5]);
	        SuggestionStatus status = SuggestionStatusUtil.convertToEnum(suggestionRow[6]);
	        Suggestion suggestion = new Suggestion(suggestionID, campID, studentID, question, reply, replierID, status);
	        
	        suggestionMap.put(suggestionID, suggestion);
	    }
		
		return suggestionMap; 
	}

	@Override
	public boolean exportSuggestionData(String suggestionFilePath, Map<Integer, Suggestion> suggestionMap) {
		List<String> suggestionLines = new ArrayList<String>();
		
		for (Suggestion suggestion : suggestionMap.values()) {
			String suggestionLine = String.format("%d,%s,%s,%s,%s,%d,%s",
					suggestion.getSuggestionID(),
					suggestion.getCommiteeID(),
					suggestion.getQuestion(),
					suggestion.getReply(),
					suggestion.getReplierID(),
					suggestion.getCampID(),
					suggestion.getStatus().toString());
			suggestionLines.add(suggestionLine);
		}
		
		return this.writeCsvFile(suggestionFilePath, suggestionCsvHeaders , suggestionLines);
	}

}
