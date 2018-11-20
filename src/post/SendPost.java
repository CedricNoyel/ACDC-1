package post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

/**
 * Main class for the console application
 * 
 * @author Raphaël HASCOËT
 */
public class SendPost {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args){
		mainMenu();
		
		//Post post = new Post("Title", "2018-11-02", "jobs", "C'est le contenu.", "Author");
	}

	static void mainMenu(){
		newLine();

		System.out.println("- Main Menu -");
		System.out.println("1 - Create a new post");
		System.out.println("2 - Manage categories");
		System.out.println("3 - Leave");

		try{
			int i = menuChoice();

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
				ErrorManager.wrongValueError();
				mainMenu();
				break;
			}
		} catch(InputMismatchException ime){
			ErrorManager.wrongValueError();
			mainMenu();
		}
	}

	static void createPost(){
		
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		newLine();

		System.out.println("Enter the title of the post :");
		String title = readLine();

		newLine();

		System.out.println("Enter the name of the author :");
		String author = readLine();

		String category = null;

		if(!CategoryManager.getCategories().isEmpty()){

			boolean correctCategory = false;
			do {
				newLine();

				System.out.println("Categories :");

				for(String categoryPrint : CategoryManager.getCategories()){
					System.out.println(categoryPrint);
				}

				newLine();

				System.out.println("Enter the category of the post :");
				category = readLine();
				if(CategoryManager.getCategories().contains(category)){
					correctCategory = true;
				} else {
					ErrorManager.sendError("The category entered is not in the list.");
				}
			} while (!correctCategory);
		}

		newLine();

		System.out.println("Enter the content of the post :");
		String content = readLine();

		boolean addUrl = true;
		do{
			newLine();
			
			System.out.println("Do you want to add an URL ? (y/n)");
			if(readYesNo()){
				System.out.println("Enter the text of the URL :");
				String urlText = readLine();
				
				System.out.println("Enter the URL :");
				String url = readLine();
				
				content += "\n" + MarkdownGenerator.mdUrl(urlText, url);
			} else {
				addUrl = false;
			}
		} while(addUrl);
		
		boolean addImage = true;
		do{
			newLine();
			
			System.out.println("Do you want to add an image ? (y/n)");
			if(readYesNo()){
				System.out.println("Enter the URL of the image:");
				String url = readLine();
				
				content += "\n" + MarkdownGenerator.mdImage(url);
			} else {
				addImage = false;
			}
		} while(addImage);
		
		Post post = new Post(title, date, category, content, author);

		String mdPost = MarkdownGenerator.toMarkdown(post);
		
		newLine();
		System.out.println(mdPost);
		
		WebsiteManager.addPost(post);
				
		System.out.println("Starting the demo at http://127.0.0.1:4000/blog/...");
		
		WebsiteManager.seeDemo();
		
		newLine();
		System.out.println("Press ENTER to close the demo.");
		
		waitForEnter();
		
		WebsiteManager.closeDemo();
		
		System.out.println("Demo closed.");
		
		System.out.println("Do you want to save the post ? (y/n)");
		
		if(readYesNo()){
			System.out.println("Post saved at " + WebsiteManager.getPostUrl(post));
			
		} else {
			WebsiteManager.deletePost(post);
			System.out.println("Post deleted.");
			
			mainMenu();
		}
		
		System.out.println("Do you want to send the post to Git ? (y/n)");
		
		if(readYesNo()){
			System.out.println("Sending the post to Git...");
			GitManager.sendPost(post);
			System.out.println("Post sent");
		}
		
		mainMenu();
	}

	static void manageCategories(){
		newLine();

		System.out.println("- Manage Categories -");
		System.out.println("1 - List the categories");
		System.out.println("2 - Add a category");
		System.out.println("3 - Delete a category");
		System.out.println("4 - Go back");

		try{
			int i = menuChoice();

			newLine();

			switch(i){
			case 1:
				for(String category : CategoryManager.getCategories()){
					System.out.println(category);
				}
				manageCategories();
				break;
			case 2:
				System.out.println("Enter the name of the category you want to add :");
				String catAdd = readLine();
				if(catAdd != null){
					if(CategoryManager.addCategory(catAdd)){
						System.out.println("Added the category " + catAdd);
					}
				}
				manageCategories();
				break;
			case 3:
				System.out.println("Enter the name of the category you want to delete :");
				String catDel = readLine();
				if(catDel != null){
					if(CategoryManager.deleteCategory(catDel)){
						System.out.println("Deleted the category " + catDel);
					}
				}
				manageCategories();
				break;
			case 4:
				mainMenu();
				break;
			default:
				ErrorManager.wrongValueError();
				manageCategories();
				break;
			}
		} catch(InputMismatchException ime){
			ErrorManager.wrongValueError();
			manageCategories();
		}

	}

	static void newLine(){
		System.out.println();
	}
	
	static void waitForEnter(){
		try {
			System.in.read();
		} catch (IOException e) {
			ErrorManager.readingError();
		}
	}

	static String readLine(){
		String ret = null;
		while(ret == null) {
			System.out.print(">");
			try {
				ret = br.readLine();
			} catch (IOException e) {
				ErrorManager.readingError();
			}
		}
		return ret;
	}

	static int menuChoice(){
		int ret = -1;
		while(ret == -1){
			System.out.print(">");
			try {
				ret = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				ErrorManager.wrongValueError();
				ret = -1;
			} catch (IOException e) {
				ErrorManager.readingError();
				ret = -1;
			}
		}
		return ret;
	}

	static boolean readYesNo(){
		boolean ret = false;
		boolean correctValue = false;
		do{
			try {
				System.out.print(">");
				String answer = br.readLine();
				if(answer.equals("y")){
					correctValue = true;
					ret = true;
				} else if(answer.equals("n")){
					correctValue = true;
					ret = false;
				} else {
					ErrorManager.sendError("The value you enter must be 'y' or 'n'");
				}
			} catch (IOException e) {
				ErrorManager.readingError();
			}
		} while (!correctValue);
		return ret;
	}

	static void leave(){
		System.exit(1);
	}
}
