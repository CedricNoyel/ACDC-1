package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

import com.hexidec.ekit.Ekit;
import model.CategoryManager;

public class editGitPathListener implements ActionListener {

	private JTextField txtfieldGitPath;

	public editGitPathListener(JTextField txtfieldGitPath) 
	{
		this.txtfieldGitPath = txtfieldGitPath;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home") + "\\Desktop"));
		chooser.setDialogTitle("Selectionner le chemin vers votre répertoire git (web-master)");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showSaveDialog(chooser) == JFileChooser.APPROVE_OPTION)
		{ 
			String fileID = chooser.getSelectedFile().getPath();
			txtfieldGitPath.setText(fileID);
			// System.out.println(fileID);
			// AccessProperties.getInstance().updateLocalRepository(fileID);
		}
		
		CategoryManager.updateFilePath();
		Ekit.updateCategoriesComboBox();
	}
}
