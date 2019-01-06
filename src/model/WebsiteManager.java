package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import view.AccessProperties;
import view.Main;

/**
 * Manages website linked services
 * 
 * @author Raphaël HASCOËT
 */
public class WebsiteManager {

	private static final String WEBSITE_PATH = AccessProperties.getInstance().getLocalRepository() + File.separator;
	private static final String BLOG_PATH = WEBSITE_PATH + "BLOG/";
	private static final String POSTS_PATH = BLOG_PATH + "_posts/";

	private static Thread webRunner = new Thread();
	private static Process proc = null;

	/**
	 * Saves a post as a Markdown file in the website's posts folder
	 * 
	 * @param post The post to be saved
	 */
	public static void addPost(Post post){

		String mdPost = MarkdownGenerator.toMarkdown(post);

		try {
			FileWriter fw = new FileWriter(new File(getPostUrl(post)), false);
			fw.write(mdPost);
			fw.close();
		} catch (IOException e){
			ErrorManager.sendError("Error while creating the file.");
		}

	}
	
	/**
	 * Deletes a post's file in the website's post folder if it exists
	 * 
	 * @param post The post to be deleted
	 */
	public static void deletePost(Post post){
		File filePost = new File(getPostUrl(post));
		
		if(!filePost.delete()){
			ErrorManager.sendError("The post you're trying to delete doesn't exist.");
		}
	}

	/**
	 * Starts the demo of the website and sends the user to the website
	 */
	public static void seeDemo(){
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		
		ProcessBuilder builder = new ProcessBuilder();
		if (isWindows) {
			builder.command("cmd.exe", "/c", "bundle exec jekyll serve -q");
		} else {
			builder.command("sh", "-c", "bundle exec jekyll serve -o", ">&-");
		}
		builder.directory(new File(WebsiteManager.getBlogPath()));
		
		try {
			proc = builder.start();
			webRunner = new Thread(new WebsiteRunner(proc));
			webRunner.start();
		} catch (IOException e) {
			ErrorManager.sendError("Error while starting the demo." + e);
		}
		
	}
	
	/**
	 * Closes the demo of the website if it is open
	 */
	public static void closeDemo(){
		if(webRunner.isAlive() && proc != null){
			proc.destroy();
		} else {
			ErrorManager.sendError("The demo is not running.");
		}
	}
	
	/**
	 * @return The path of the local website
	 */
	public static String getWebsitePath(){
		return WEBSITE_PATH;
	}

	/**
	 * @return The path of the BLOG folder
	 */
	public static String getBlogPath(){
		return BLOG_PATH;
	}
	
	/**
	 * @param post The post you want the URL of
	 * @return The path to the file when it is saved in the posts folder of the website
	 */
	public static String getPostUrl(Post post){
		return POSTS_PATH + post.getFileName();
	}

}
