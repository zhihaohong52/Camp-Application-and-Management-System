
package interfaces;

import model.camp.Camp;

/**
 * The {@link IReportGeneratorService} interface defines a contract for classes that provide methods to generate reports related to camps and committee performance.
 */
public interface IReportGeneratorService {
	 /**
     * Generates a camp report based on the specified filter.
     *
     * @param camp   The {@link Camp} object for which the report is generated.
     * @param filter The filter parameter used to customize the report.
     */
	void generateCampReport(Camp camp, int filter);
	  /**
     * Generates a committee performance report for the specified camp.
     *
     * @param camp The {@link Camp} object for which the committee performance report is generated.
     */
	void generateCommitteePerformanceReport(Camp camp);
}
