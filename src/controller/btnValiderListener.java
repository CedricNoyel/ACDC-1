package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.GitManager;
import model.MarkdownGenerator;
import model.Post;
import model.WebsiteManager;

public class btnValiderListener implements ActionListener 
{
    private JTextField textfieldTitle;
    private JComboBox textfieldCateg;
    private JTextField textfieldAuthor;
    private JTextPane textfieldText;

	public btnValiderListener(JTextField textfieldTitle, JComboBox textfieldCateg, JTextField textfieldAuthor, JTextPane textfieldText) {
		this.textfieldTitle = textfieldTitle;
		this.textfieldCateg = textfieldCateg;
		this.textfieldAuthor = textfieldAuthor;
		this.textfieldText = textfieldText;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		Post post = new Post(textfieldTitle.getText(),	todayDate, textfieldCateg.getSelectedItem().toString(), textfieldText.getText(), textfieldAuthor.getText());

		String mdPost = MarkdownGenerator.toMarkdown(post);
		
		WebsiteManager.addPost(post);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Starting the demo at http://127.0.0.1:4000/blog/...");
		WebsiteManager.seeDemo();
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebsiteManager.closeDemo();
		System.out.println("Demo closed.");
		
		
		
		System.out.println("Post saved at " + WebsiteManager.getPostUrl(post));
		GitManager.sendPost(post);
		
			
	}

}
