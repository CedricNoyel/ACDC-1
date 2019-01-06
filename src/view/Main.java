package view;

import java.io.File;

import model.Post;
import model.SendPost;

public class Main {

	
	public static void main(String[] args) {
		
		String GIT_REPO = AccessProperties.getInstance().getLocalRepository();
		
	
		System.out.println(GIT_REPO);
		// Post post = new Post("Title", "2018-11-02", "jobs", "C'est le contenu.", "Author");
		SendPost.mainMenu();
	}

}
