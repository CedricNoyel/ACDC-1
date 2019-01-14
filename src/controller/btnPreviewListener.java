package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale.Category;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.hexidec.ekit.Ekit;

import model.CategoryManager;
import model.Post;
import model.Tools;
import model.WebsiteManager;
import sun.security.util.Length;

public class btnPreviewListener implements ActionListener 
{

	private static JTextField textfieldTitle;
	private static JComboBox<String> textfieldCateg;
	private static JTextField textfieldAuthor;
	private static JTextField textfieldGitRepo;
	private static JTextPane textfieldText;
	private JButton btnPreview;

	public btnPreviewListener(JButton btnPreview, JTextField textfieldTitle, JComboBox<String> textfieldCateg, JTextField textfieldAuthor, JTextPane textfieldText, JTextField textfieldGitRepo) {
		this.textfieldTitle = textfieldTitle;
		this.textfieldCateg = textfieldCateg;
		this.textfieldAuthor = textfieldAuthor;
		this.textfieldText = textfieldText;
		this.btnPreview = btnPreview;
		this.textfieldGitRepo = textfieldGitRepo;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if (areFieldsOk()) 
		{
			String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			Post post = new Post(textfieldTitle.getText(), todayDate, textfieldCateg.getSelectedItem().toString(), textfieldText.getText(), textfieldAuthor.getText());
			WebsiteManager.addPost(post);
			System.out.println("Starting the demo at http://127.0.0.1:4000/blog/...");
			WebsiteManager.seeDemo();
		}
	}

	public static boolean areFieldsOk()
	{
		List<String> fieldsError = new ArrayList<String>();
		int defaulTextfieldTextLength = 110;
		
		if (Ekit.getTxtfieldGitrepo().getText().isEmpty()) {
			fieldsError.add("'Dépot git'");
		}
		if (textfieldTitle.getText().isEmpty()) {
			fieldsError.add("'Titre'");
		}
		if (textfieldAuthor.getText().isEmpty()) {
			fieldsError.add("'Auteur'");
		}
		if (textfieldCateg.getSelectedItem() == null) {
			fieldsError.add("'Catégorie'");
		}
		if (textfieldText.getText().length() == defaulTextfieldTextLength) {
			fieldsError.add("texte");
		}
		
		if (fieldsError.size() !=0) {
			String errorMsg = "Merci de vérifier le(s) champs ";
			for (String elem : fieldsError) 
			{
				if (elem == fieldsError.get(0)) {
					errorMsg += elem;
				} else {
					errorMsg += ", " + elem;
				}
			}
			errorMsg += ".";
			JOptionPane.showMessageDialog(null, errorMsg);
			return false;
		}
		return true;
	}
}
