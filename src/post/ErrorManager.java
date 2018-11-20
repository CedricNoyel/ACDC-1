package post;

/**
 * Manages the error messages sent by the program
 * 
 * @author Raphaël HASCOËT
 */
public class ErrorManager {
	
	/**
	 * Handles errors sent by the program
	 * 
	 * @param error The error to be shown
	 */
	static void sendError(String error){
		SendPost.newLine();
		System.out.println("Error : ");
		System.out.println(error);
		SendPost.newLine();
	}

	/**
	 * Error shown when the user inputs a wrong value
	 */
	static void wrongValueError(){
		sendError("The value you entered is not in the list.");
	}
	
	/**
	 * Error shown when the program encounters an error while reading input
	 */
	static void readingError(){
		sendError("Error while reading the input");
	}
	
}
