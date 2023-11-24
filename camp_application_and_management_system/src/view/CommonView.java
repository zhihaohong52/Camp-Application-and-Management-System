package view;

import java.util.Scanner;

/**
 * The {@link CommonView} class provides utility methods for displaying
 * UI interfaces in console.
 */
public class CommonView {

	/**
	 * {@link Scanner} object to get input from user
	 */
	private static final Scanner sc = new Scanner(System.in);
	
	/**
	 * Private constructor to prevent instantiation of the class
	 */
	private CommonView() {}
	
	/**
	 * Prints the start up screen for Camp Application and Management System (CAMS)
	 */
	public static void printStartUpScreen() {
		System.out.println(
				  "\r\n"
				  + "Welcome to\n"
				  + " ██████╗ █████╗ ███╗   ███╗███████╗\r\n"
				  + "██╔════╝██╔══██╗████╗ ████║██╔════╝\r\n"
				  + "██║     ███████║██╔████╔██║███████╗\r\n"
				  + "██║     ██╔══██║██║╚██╔╝██║╚════██║\r\n"
				  + "╚██████╗██║  ██║██║ ╚═╝ ██║███████║\r\n"
				  + " ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝\r\n"
				  + "Camp Application and Management System\n"
				  + "");
	
	}
	
	/**
	 * Prints the navigation bar with the given path.
	 *
	 * @param path the path to be displayed in the navigation bar
	 */
	public static void printNavbar(String path) {
		String spaces = String.format("%" + (97 - path.length()) + "s", "");

		// Display
		System.out.println();
		System.out.println();

		System.out.println("┏" + "━".repeat(98) + "┓");
		System.out.println("┃ " + path + spaces + "┃");
		System.out.println("┗" + "━".repeat(98) + "┛");
	}

	/**
	 * Prompts the user to press the "Enter" key to continue with the application.
	 */
	public static void pressEnterToContinue() {
		System.out.println("Press Enter key to continue...");
		sc.nextLine();
	}

	/**
	 * Prints a line of '=' to separate text
	 */
	public static void printLine() {
		System.out.println();
		System.out.println("=".repeat(100));
		System.out.println();
	}
}
