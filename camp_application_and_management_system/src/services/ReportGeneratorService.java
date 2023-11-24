/**
 * 
 */
package services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import enums.Schools;
import interfaces.IReportGeneratorService;
import model.camp.Camp;
import model.user.Committee;
import stores.DataStore;
import util.TextDecoratorUtil;

/**
 * {@link ReportGeneratorService} implements {@link IReportGeneratorService}
 * interface and creates an user interface for camp reports
 */
public class ReportGeneratorService implements IReportGeneratorService{

	/**
	 * Construct an instance of {@link ReportGeneratorService}
	 */
	public ReportGeneratorService(){}

	@Override
	public void generateCampReport(Camp camp, int filter) {
		// Check the filter and format parameters and apply filters accordingly
        List<String> attendeesList = null;
        List<String> campCommitteeList = null;

        if (filter == 1) {
            attendeesList = camp.getStudents();
        } else if (filter == 2) {
            campCommitteeList = camp.getCampCommittee();
        } else {
            attendeesList = camp.getStudents();
            campCommitteeList = camp.getCampCommittee();
        }
		
		StringBuilder report = new StringBuilder();
		report.append("CAMP REPORT\n\n");
        report.append("Name: ").append(camp.getName()).append("\n");
        report.append("Location: ").append(camp.getLocation()).append("\n");
        report.append("Dates: ");
        List<LocalDate> campDates = camp.getDates();
        String[] dateStrings = campDates.stream().map(LocalDate::toString).toArray(String[]::new);
        report.append(String.join(", ", dateStrings)).append("\n");
        report.append("Closing date: ").append(camp.getClosing()).append("\n");
        report.append("Available schools: ");
        List<Schools> schools = camp.getAvailable();
        String[] schoolStrings = schools.stream().map(Schools::toString).toArray(String[]::new);
        report.append(String.join(", ", schoolStrings)).append("\n");
        report.append("Number of slots left: ").append(camp.getTotalSlots()).append("\n");
        report.append("Staff IC: ").append(camp.getStaffIC()).append("\n");
        report.append("Camp Description: ").append(camp.getDescription()).append("\n");

        if (attendeesList != null) {
            report.append("Attendees:\n");
            for (String attendee : attendeesList) {
                report.append("- ").append(attendee).append("\n");
            }
        }

        if (campCommitteeList != null) {
            report.append("Camp Committee Members:\n");
            for (String committeeMember : campCommitteeList) {
                report.append("- ").append(committeeMember).append("\n");
            }
        }
		
		try {
            String filename =  camp.getName().replaceAll(" ", "_")+ "_Report.txt";
            FileWriter writer = new FileWriter(filename);
            writer.write(report.toString());
            writer.close();
            System.out.println("Report generated and saved as " + filename);
        } catch (IOException e) {
            System.err.println("Error writing the report to a file.");
        }
    }

	@Override
	public void generateCommitteePerformanceReport(Camp camp) {
		Map<String, Committee> committeeData = DataStore.getCommitteeData();
		
		StringBuilder report = new StringBuilder();
		report.append("COMMITTEE PERFORMANCE REPORT\n\n");
		report.append("Camp name: ").append(camp.getName()).append("\n");
		report.append("Staff IC: ").append(camp.getStaffIC()).append("\n");
		
		List<String> campCommitteeList = camp.getCampCommittee();
		if (campCommitteeList != null) {
			report.append("Camp committee performance:").append("\n");
			report.append("Member").append("\t\t").append("Point").append("\n");
			for (String committeeID : campCommitteeList) {
				Committee committee = committeeData.get(committeeID);
				report.append(committeeID).append("\t\t").append(committee.getPoint()).append("\n");
			}
		}
		else {
			report.append("No camp committee registered for camp.").append("\n");
		}
		
		try {
            String filename =  camp.getName().replaceAll(" ", "_")+ "_Performance_Report.txt";
            FileWriter writer = new FileWriter(filename);
            writer.write(report.toString());
            writer.close();
            System.out.println("Report generated and saved as " + filename);
        } catch (IOException e) {
            System.err.println("Error writing the report to a file.");
        }
		
	}

}
