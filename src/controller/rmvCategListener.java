package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import com.hexidec.ekit.Ekit;

import model.CategoryManager;

public class rmvCategListener implements ActionListener {

    private JComboBox<String> categ;

	public rmvCategListener(JComboBox<String> categ) 
	{
		this.categ = categ;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		CategoryManager.deleteCategory(categ.getEditor().getItem().toString());
		Ekit.updateCategoriesComboBox();
	}
}
