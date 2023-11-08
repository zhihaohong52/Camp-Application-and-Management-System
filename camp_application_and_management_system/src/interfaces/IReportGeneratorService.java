/**
 * 
 */
package interfaces;

import model.camp.Camp;

/**
 * 
 */
public interface IReportGeneratorService {
	
	void generateCampReport(Camp camp, int filter);
	
	void generateCommitteePerformanceReport(Camp camp);
}
