
package interfaces;

import model.camp.Suggestion;

/**
 * The {@link ISuggestionView} interface defines a contract for classes that provide methods to display suggestions, particularly for user interfaces.
 */
public interface ISuggestionView {
     
	/**
     * Displays information about the specified suggestion.
     *
     * @param suggestion The {@link Suggestion} object to be displayed.
     */
	public void displaySuggestions(Suggestion suggestion);
}
