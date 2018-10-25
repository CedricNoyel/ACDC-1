package post;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SendPost {

	private static final String NEW_LINE = System.getProperty("line.separator");
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args){
		mainMenu();
	}
	
	static void mainMenu(){
		System.out.println("What do you want to do ?");
		System.out.println("1 - Create a new post");
		System.out.println("2 - Manage categories");
		System.out.println("3 - Leave");

		try{
			int i = sc.nextInt();

			switch(i){
			case 1:
				createPost();
				break;
			case 2:
				manageCategories();
				break;
			case 3:
				leave();
				break;
			default:
				wrongValue();
				mainMenu();
				break;
			}
		} catch(InputMismatchException ime){
			wrongValue();
			mainMenu();
		}
	}

	static void createPost(){

	}

	static void manageCategories(){

	}

	static void leave(){
		System.exit(1);
	}

	static void wrongValue(){
		System.out.println(NEW_LINE + "The value you entered is not in the list." + NEW_LINE);
	}
}
