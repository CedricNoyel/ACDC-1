package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class btnPreviewListener implements ActionListener 
{

    private JTextField textfieldTitle;
    private JComboBox textfieldCateg;
    private JTextField textfieldAuthor;
    private JTextPane textfieldText;

	public btnPreviewListener(JTextField textfieldTitle, JComboBox textfieldCateg, JTextField textfieldAuthor, JTextPane textfieldText) {
		this.textfieldTitle = textfieldTitle;
		this.textfieldCateg = textfieldCateg;
		this.textfieldAuthor = textfieldAuthor;
		this.textfieldText = textfieldText;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
//		 String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
//		 Post post = new Post(textfieldTitle.getText(),	todayDate, textfieldCateg.getText(), textfieldText.getText(), textfieldAuthor.getText());
//		String mdPost = MarkdownGenerator.toMarkdown(post);
//		WebsiteManager.addPost(post);
		System.out.println("CLIQUE BTN PREVIEW");
		
	}

}
