
package enums;

/**
 * This enum represent the status of a suggestion, which can be "Processing","Processed" or "Rejected"
 * This indicates the current processing status of a suggestion
 */
public enum SuggestionStatus {
	/**
	 * Suggestion is Processing
	 */
	Processing, 
	/**
	 * Suggestion is Approved
	 */
	Approved, 
	/**
	 * Suggestion is Rejected
	 */
	Rejected
}
