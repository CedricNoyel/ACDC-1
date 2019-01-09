package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.hexidec.ekit.Ekit;

import model.CategoryManager;

public class addCategListener implements ActionListener
{
    private JComboBox<String> categ;

	public addCategListener(JComboBox<String> categ) {
		this.categ = categ;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String categToAdd = categ.getEditor().getItem().toString();
		CategoryManager.addCategory(categToAdd);
		
		Ekit.updateCategoriesComboBox();
		categ.setSelectedItem(categToAdd);
	}

}
