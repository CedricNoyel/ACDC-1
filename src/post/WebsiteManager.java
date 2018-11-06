package post;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WebsiteManager {

	private static final String BLOG_PATH = "web-master/BLOG/";
	private static final String POSTS_PATH = BLOG_PATH + "_posts/";
	private static final String HOST_NAME = "127.0.0.1";
	private static final int PORT_USED = 4000;
	private static final String SITE_URL = "http://" + HOST_NAME + ":" + PORT_USED + "/blog/";

	private static Thread webRunner = new Thread();
	private static Process proc = null;

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
	
	public static void deletePost(Post post){
		File filePost = new File(getPostUrl(post));
		
		if(!filePost.delete()){
			ErrorManager.sendError("The post you're trying to delete doesn't exist.");
		}
	}

	public static void seeDemo(){
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		
		ProcessBuilder builder = new ProcessBuilder();
		if (isWindows) {
			builder.command("cmd.exe", "/c", "bundle exec jekyll serve -o");
		} else {
			builder.command("sh", "-c", "bundle exec jekyll serve -o", ">&-");
		}
		builder.directory(new File(WebsiteManager.getBlogPath()));
		
		try {
			proc = builder.start();
			webRunner = new Thread(new WebsiteRunner(proc));
			webRunner.start();
		} catch (IOException e) {
			ErrorManager.sendError("Error while starting the demo.");
		}
		
	}
	
	public static void closeDemo(){
		if(webRunner.isAlive() && proc != null){
			proc.destroy();
		} else {
			ErrorManager.sendError("The demo is not running.");
		}
	}

	public static String getBlogPath(){
		return BLOG_PATH;
	}
	
	public static String getSiteUrl(){
		return SITE_URL;
	}
	
	public static String getPostUrl(Post post){
		return POSTS_PATH + post.getDate() + '-' + post.getTitle().replace(' ', '-') + ".markdown";
	}

}
