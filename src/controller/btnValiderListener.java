package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.hexidec.ekit.Ekit;

import model.AccessProperties;
import model.GitManager;
import model.Post;
import model.Tools;
import model.WebsiteManager;

public class btnValiderListener implements ActionListener 
{
    private JTextField textfieldGitRepo;
    private JTextField textfieldTitle;
    private JComboBox<String> textfieldCateg;
    private JTextField textfieldAuthor;
    private JTextPane textfieldText;
    private JButton btnValid;

	public btnValiderListener(JButton btnValid, JTextField textfieldTitle, JComboBox<String> textfieldCateg, 
			JTextField textfieldAuthor, JTextPane textfieldText, JTextField textfieldGitRepo) {
		this.textfieldTitle = textfieldTitle;
		this.textfieldCateg = textfieldCateg;
		this.textfieldAuthor = textfieldAuthor;
		this.textfieldText = textfieldText;
		this.btnValid = btnValid;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		WebsiteManager.closeDemo();
		
		btnValid.setEnabled(false);
		btnValid.setText("Publication du post en cours");
		
		String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		Post post = new Post(textfieldTitle.getText(),	todayDate, textfieldCateg.getSelectedItem().toString(), textfieldText.getText(), textfieldAuthor.getText());

		GitManager.sendPost(post);

		AccessProperties.getInstance().updateDefaultAuthor(Tools.deAccent(textfieldAuthor.getText()));
		// QUIT PROGRAM
		System.exit(0);
	}

}
