package model;

/**
 * Represents a post created by an user to be sent to a Jekyll website
 * 
 * @author Rapha�l HASCO�T
 */
public class Post {

	private String title;
	private String date;
	private String category;
	private String content;
	private String author;

	/**
	* @param title Title of the post
	* @param date Date of the post
	* @param category Category of the post
	* @param content Content of the post
	* @param author Author of the post
	 */
	public Post(String title, String date, String category, String content, String author){
		this.title = title;
		this.date = date;
		this.category = category;
		this.content = content;
		this.author = author;
	}

	/**
	 * @return The file name used when generating the post
	 */
	public String getFileName(){
		return getDate() + '-' + getTitle().replace(' ', '-') + ".html";
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