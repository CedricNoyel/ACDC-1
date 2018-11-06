package post;

public class Post {
	
	private String title;
	private String date;
	private String category;
	private String content;
	private String author;
	
	public Post(String title, String date, String category, String content, String author){
		this.title = title;
		this.date = date;
		this.category = category;
		this.content = content;
		this.author = author;
	}
	
	public void send(){
		
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
