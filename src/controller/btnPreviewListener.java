package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.AccessProperties;
import model.Post;
import model.Tools;
import model.WebsiteManager;

public class btnPreviewListener implements ActionListener 
{

    private JTextField textfieldTitle;
    private JComboBox<String> textfieldCateg;
    private JTextField textfieldAuthor;
    private JTextPane textfieldText;
    private JButton btnPreview;

	public btnPreviewListener(JButton btnPreview, JTextField textfieldTitle, JComboBox<String> textfieldCateg, JTextField textfieldAuthor, JTextPane textfieldText) {
		this.textfieldTitle = textfieldTitle;
		this.textfieldCateg = textfieldCateg;
		this.textfieldAuthor = textfieldAuthor;
		this.textfieldText = textfieldText;
		this.btnPreview = btnPreview;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		Post post = new Post(textfieldTitle.getText(),	todayDate, textfieldCateg.getSelectedItem().toString(), textfieldText.getText(), textfieldAuthor.getText());
		WebsiteManager.addPost(post);
		System.out.println("Starting the demo at http://127.0.0.1:4000/blog/...");
		WebsiteManager.seeDemo();
		AccessProperties.getInstance().updateDefaultAuthor(Tools.deAccent(textfieldAuthor.getText()));
	}

}
