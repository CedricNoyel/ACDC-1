package post;

public class ErrorManager {
	
	static void sendError(String error){
		SendPost.newLine();
		System.out.println("Error : ");
		System.out.println(error);
		SendPost.newLine();
	}

	static void wrongValueError(){
		sendError("The value you entered is not in the list.");
	}
	
	static void readingError(){
		sendError("Error while reading the input");
	}
	
}
