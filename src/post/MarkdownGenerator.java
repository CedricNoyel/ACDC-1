package post;

/**
 * Utility class to generate Mardown snippets
 * 
 * @author Raphaël HASCOËT
 */
public class MarkdownGenerator {
	
	public static String toMarkdown(Post post){
		String md = "---\n" +
					"layout: post\n" +
					"title: \"" + post.getTitle() + "\"\n" +
					"date: " + post.getDate() + "\n" +
					"categories: " + post.getCategory() + "\n" +
					"---\n" +
					"\n" +
					"*By " + post.getAuthor() + "*\n" +
					"\n" +
					post.getContent();
		return md;
	}
	
	public static String mdUrl(String text, String url){
		return String.format("[%s](%s)", text, url); 
	}
	
	public static String mdImage(String urlImg){
		return String.format("![%s]", urlImg);
	}
}
