package post;

public class MarkdownGenerator {
	
	public static String toMarkdown(Post post){
		return "";
	}
	
	public static String mdUrl(String text, String url){
		return String.format("[%s](%s)", text, url); 
	}
	
	public static String mdImage(String urlImg){
		return String.format("![%s]", urlImg);
	}
}
