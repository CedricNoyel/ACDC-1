/*
GNU Lesser General Public License

Ekit - Java Swing HTML Editor & Viewer
Copyright (C) 2000 Howard Kistler

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
// 
package com.hexidec.ekit;

// <!> === STARTING USER INTERFACE AT LINE 106 === <!>

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale.Category;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.hexidec.ekit.EkitCore;
import com.hexidec.ekit.EkitCoreSpell;
import com.sun.prism.Image;

import jdk.nashorn.internal.runtime.AccessorProperty;
import model.AccessProperties;
import model.CategoryManager;

/** Ekit
  * App for editing and saving HTML in a Java text component
  *
  * @author Howard Kistler
  * @version 1.5
  *
  * REQUIREMENTS
  * Java 2 (JDK 1.5 or higher)
  * Swing Library
  */

public class Ekit extends JFrame implements WindowListener
{
	private EkitCore ekitCore;
	private JTextField txtfieldTitle;
	private JTextField txtfieldGitrepo;
	private static JComboBox<String> txtfieldCateg;
	private JTextField txtfieldAuthor;
	private JButton btnRemoveCateg;
	private JButton btnAddCateg;
	
	private int WINDOW_SIZE_X = 600;
	private int WINDOW_SIZE_Y = 700;
	private Dimension labelSize = new Dimension(80, 25);

	private File currentFile = (File)null;

	/** Master Constructor
	  * @param sDocument         [String]  A text or HTML document to load in the editor upon startup.
	  * @param sStyleSheet       [String]  A CSS stylesheet to load in the editor upon startup.
	  * @param sRawDocument      [String]  A document encoded as a String to load in the editor upon startup.
	  * @param urlStyleSheet     [URL]     A URL reference to the CSS style sheet.
	  * @param includeToolBar    [boolean] Specifies whether the app should include the toolbar.
	  * @param showViewSource    [boolean] Specifies whether or not to show the View Source window on startup.
	  * @param showMenuIcons     [boolean] Specifies whether or not to show icon pictures in menus.
	  * @param editModeExclusive [boolean] Specifies whether or not to use exclusive edit mode (recommended on).
	  * @param sLanguage         [String]  The language portion of the Internationalization Locale to run Ekit in.
	  * @param sCountry          [String]  The country portion of the Internationalization Locale to run Ekit in.
	  * @param base64            [boolean] Specifies whether the raw document is Base64 encoded or not.
	  * @param debugMode         [boolean] Specifies whether to show the Debug menu or not.
	  * @param useSpellChecker   [boolean] Specifies whether to include the spellchecker or not.
	  * @param multiBar          [boolean] Specifies whether to use multiple toolbars or one big toolbar.
	  * @param enterBreak        [boolean] Specifies whether the ENTER key should insert breaks instead of paragraph tags.
	  */
	public Ekit(String sDocument, String sStyleSheet, String sRawDocument, URL urlStyleSheet, boolean includeToolBar, boolean showViewSource, boolean showMenuIcons, boolean editModeExclusive, String sLanguage, String sCountry, boolean base64, boolean debugMode, boolean useSpellChecker, boolean multiBar, boolean enterBreak)
	{
		if(useSpellChecker)
		{
			ekitCore = new EkitCoreSpell(false, sDocument, sStyleSheet, sRawDocument, null, urlStyleSheet, includeToolBar, showViewSource, showMenuIcons, editModeExclusive, sLanguage, sCountry, base64, debugMode, true, multiBar, (multiBar ? EkitCore.TOOLBAR_DEFAULT_MULTI : EkitCore.TOOLBAR_DEFAULT_SINGLE), enterBreak);
		}
		else
		{
			ekitCore = new EkitCore(false, sDocument, sStyleSheet, sRawDocument, null, urlStyleSheet, includeToolBar, showViewSource, showMenuIcons, editModeExclusive, sLanguage, sCountry, base64, debugMode, false, multiBar, (multiBar ? EkitCore.TOOLBAR_DEFAULT_MULTI : EkitCore.TOOLBAR_DEFAULT_SINGLE), enterBreak);
		}

		ekitCore.setFrame(this);

		/* Add the components to the app */

		// MAIN CONTAINER
		setResizable(false);
		
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.getContentPane().setPreferredSize(new Dimension(WINDOW_SIZE_X, WINDOW_SIZE_Y));
		
		// CREATION DES COMPOSANTS DE LA FENETRE
		createPanelGitRepo();
		createPanelTitle();
		createPanelCateg();
		createPanelAuthor();
		createEkitContainer(includeToolBar);
		createSouthPanel();
		
		// RECUPERER CONTENU POST
		// ekitCore.getTextPane().getText();


		this.setJMenuBar(ekitCore.getMenuBar());
		this.addWindowListener(this);
		this.updateTitle();
		this.pack();
		this.setVisible(true);
	}

	private void createEkitContainer(Boolean includeToolBar) {

		// === EKIT JPANEL ===
		JPanel ekitContainer = new JPanel();
		ekitContainer.setLayout(new GridBagLayout());
		ekitContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.getContentPane().add(ekitContainer);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill       = GridBagConstraints.HORIZONTAL;
		gbc.anchor     = GridBagConstraints.NORTH;
		gbc.gridheight = 1;
		gbc.gridwidth  = 1;
		gbc.weightx    = 1.0;
		gbc.weighty    = 0.0;
		gbc.gridx      = 1;

		gbc.gridy      = 1;
		ekitContainer.add(ekitCore.getToolBarMain(includeToolBar), gbc);
		gbc.gridy      = 2;
		ekitContainer.add(ekitCore.getToolBarFormat(includeToolBar), gbc);

		gbc.anchor     = GridBagConstraints.SOUTH;
		gbc.fill       = GridBagConstraints.BOTH;
		gbc.weighty    = 1.0;
		gbc.gridy      = 3;
		ekitContainer.add(ekitCore, gbc);
	}

	private void createPanelAuthor() 
	{
		JPanel panel = new JPanel();
		
		JLabel lblAuthor = new JLabel("Auteur", SwingConstants.CENTER);
		txtfieldAuthor = new JTextField();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		lblAuthor.setPreferredSize(labelSize);
		txtfieldAuthor.setText(AccessProperties.getInstance().getDefaultAuthor());
		
		panel.add(lblAuthor);
		panel.add(txtfieldAuthor);
		this.getContentPane().add(panel, BorderLayout.NORTH);
		
	}

	private void createPanelGitRepo() 
	{
		ImageIcon icon = new ImageIcon("./src/ressources/edit.png");
		java.awt.Image img = icon.getImage() ;  
		java.awt.Image iconEditGitPath = img.getScaledInstance(13, 13, java.awt.Image.SCALE_DEFAULT);

		JPanel panel = new JPanel();
		JLabel lblGitRepo = new JLabel("Dépot git", SwingConstants.CENTER);
		JButton btnEditGitPath = new JButton(new ImageIcon(iconEditGitPath));
		txtfieldGitrepo = new JTextField();
		txtfieldGitrepo.setText(AccessProperties.getInstance().getLocalRepository());

		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		lblGitRepo.setPreferredSize(labelSize);

		ActionListener btnEditGitPathListener = new controller.editGitPathListener(txtfieldGitrepo);
		btnEditGitPath.addActionListener(btnEditGitPathListener);
		
		panel.add(lblGitRepo);
		panel.add(txtfieldGitrepo);
		panel.add(btnEditGitPath);
		this.getContentPane().add(panel, BorderLayout.NORTH);
	}
	
	private void createPanelCateg() 
	{
		ImageIcon iconAddCateg = new ImageIcon("./src/ressources/add.png");
		java.awt.Image img = iconAddCateg.getImage();
		java.awt.Image imgAddCateg = img.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
		
		JPanel panel = new JPanel();
		btnRemoveCateg = new JButton(new ImageIcon("./src/ressources/delete.png"));
		btnAddCateg = new JButton(new ImageIcon(imgAddCateg));
		JLabel lblCateg = new JLabel("Catégorie", SwingConstants.CENTER);
		txtfieldCateg = new JComboBox<String>();
		
		updateCategoriesComboBox();

		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		lblCateg.setPreferredSize(labelSize);
		txtfieldCateg.setEditable(true);
		btnAddCateg.setToolTipText("Ajouter une catégorie");
		btnRemoveCateg.setToolTipText("Supprimer la catégorie sélectionnée");

		ActionListener btnAddCategListener = new controller.addCategListener(txtfieldCateg);
		btnAddCateg.addActionListener(btnAddCategListener);

		ActionListener btnRmvCategListener = new controller.rmvCategListener(txtfieldCateg);
		btnRemoveCateg.addActionListener(btnRmvCategListener);
		
		panel.add(lblCateg);
		panel.add(txtfieldCateg);
		panel.add(btnAddCateg);
		panel.add(btnRemoveCateg);
		this.getContentPane().add(panel, BorderLayout.NORTH);
	}
	
	public static void updateCategoriesComboBox() {
		txtfieldCateg.removeAllItems();
		for(String categ : CategoryManager.getCategories()) {
			txtfieldCateg.addItem(categ);
		}
	}

	private void createPanelTitle() 
	{
		JPanel panel = new JPanel();
		
		JLabel lblTitle = new JLabel("Titre", SwingConstants.CENTER);
		txtfieldTitle = new JTextField();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		lblTitle.setPreferredSize(labelSize);
		
		panel.add(lblTitle);
		panel.add(txtfieldTitle);
		this.getContentPane().add(panel, BorderLayout.NORTH);
	}
	
	private void createSouthPanel() {

		JPanel panel = new JPanel();
		
		JButton btnPreview = new JButton("Voir l'aperçu");
		JButton btnValid = new JButton("Envoyer la news");
		
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(10, 30, 10, 30));
		
		ActionListener btnValidListener = new controller.btnValiderListener(txtfieldTitle, txtfieldCateg, txtfieldAuthor, ekitCore.getTextPane());
		btnValid.addActionListener(btnValidListener);
		ActionListener btnPreviewListener = new controller.btnPreviewListener(txtfieldTitle, txtfieldCateg, txtfieldAuthor, ekitCore.getTextPane());
		btnPreview.addActionListener(btnPreviewListener);
		
		panel.add(btnPreview, BorderLayout.NORTH);
		panel.add(btnValid, BorderLayout.SOUTH);

		this.getContentPane().add(panel);
	}

	public Ekit()
	{
		this(null, null, null, null, true, false, true, true, null, null, false, false, false, true, false);
	}

	/* WindowListener methods */
	public void windowClosing(WindowEvent we)
	{
		this.dispose();
		System.exit(0);
	}
	public void windowOpened(WindowEvent we)      { ; }
	public void windowClosed(WindowEvent we)      { ; }
	public void windowActivated(WindowEvent we)   { ; }
	public void windowDeactivated(WindowEvent we) { ; }
	public void windowIconified(WindowEvent we)   { ; }
	public void windowDeiconified(WindowEvent we) { ; }

	/** Convenience method for updating the application title bar
	  */
	private void updateTitle()
	{
		this.setTitle(ekitCore.getAppName() + (currentFile == null ? "" : " - " + currentFile.getName()));
	}

	/** Usage method
	  */
	public static void usage()
	{
		System.out.println("usage: com.hexidec.ekit.Ekit [-t|t+|T] [-s|S] [-m|M] [-x|X] [-b|B] [-v|V] [-p|P] [-fFILE] [-cCSS] [-rRAW] [-uURL] [-lLANG] [-d|D] [-h|H|?]");
		System.out.println("       Each option contained in [] brackets is optional,");
		System.out.println("       and can be one of the values separated be the | pipe.");
		System.out.println("       Each option must be proceeded by a - hyphen.");
		System.out.println("       The options are:");
		System.out.println("         -t|t+|T : -t = single toolbar, -t+ = multiple toolbars, -T = no toolbar");
		System.out.println("         -s|S    : -s = show source window on startup, -S = hide source window");
		System.out.println("         -m|M    : -m = show icons on menus, -M = no menu icons");
		System.out.println("         -x|X    : -x = exclusive document/source windows, -X = use split window");
		System.out.println("         -b|B    : -b = use Base64 document encoding, -B = use regular encoding");
		System.out.println("         -v|V    : -v = include spell checker, -V = omit spell checker");
		System.out.println("         -p|P    : -p = ENTER key inserts paragraph, -P = inserts break");
		System.out.println("         -fFILE  : load HTML document on startup (replace FILE with file name)");
		System.out.println("         -cCSS   : load CSS stylesheet on startup (replace CSS with file name)");
		System.out.println("         -rRAW   : load raw document on startup (replace RAW with file name)");
		System.out.println("         -uURL   : load document at URL on startup (replace URL with file URL)");
		System.out.println("         -lLANG  : specify the starting language (defaults to your locale)");
		System.out.println("                    replace LANG with xx_XX format (e.g., US English is en_US)");
		System.out.println("         -d|D    : -d = DEBUG mode on, -D = DEBUG mode off (developers only)");
		System.out.println("         -h|H|?  : print out this help information");
		System.out.println("         ");
		System.out.println("The defaults settings are equivalent to: -t+ -S -m -x -B -V -p -D");
		System.out.println("         ");
		System.out.println("For further information, read the README file.");
	}

	/** Main method
	  */
	public static void main(String[] args)
	{
		String sDocument = null;
		String sStyleSheet = null;
		String sRawDocument = null;
		URL urlStyleSheet = null;
		boolean includeToolBar = true;
		boolean multibar = true;
		boolean includeViewSource = false;
		boolean includeMenuIcons = true;
		boolean modeExclusive = true;
		String sLang = null;
		String sCtry = null;
		boolean base64 = false;
		boolean debugOn = false;
		boolean spellCheck = false;
		boolean enterBreak = false;
		for(int i = 0; i < args.length; i++)
		{
			if     (args[i].equals("-h") ||
					args[i].equals("-H") ||
					args[i].equals("-?"))     { usage(); }
			else if(args[i].equals("-t"))     { includeToolBar = true; multibar = false; }
			else if(args[i].equals("-t+"))    { includeToolBar = true; multibar = true; }
			else if(args[i].equals("-T"))     { includeToolBar = false; multibar = false; }
			else if(args[i].equals("-s"))     { includeViewSource = true; }
			else if(args[i].equals("-S"))     { includeViewSource = false; }
			else if(args[i].equals("-m"))     { includeMenuIcons = true; }
			else if(args[i].equals("-M"))     { includeMenuIcons = false; }
			else if(args[i].equals("-x"))     { modeExclusive = true; }
			else if(args[i].equals("-X"))     { modeExclusive = false; }
			else if(args[i].equals("-b"))     { base64 = true; }
			else if(args[i].equals("-B"))     { base64 = false; }
			else if(args[i].startsWith("-f")) { sDocument = args[i].substring(2, args[i].length()); }
			else if(args[i].startsWith("-c")) { sStyleSheet = args[i].substring(2, args[i].length()); }
			else if(args[i].startsWith("-r")) { sRawDocument = args[i].substring(2, args[i].length()); }
			else if(args[i].equals("-v"))     { spellCheck = true; }
			else if(args[i].equals("-V"))     { spellCheck = false; }
			else if(args[i].equals("-p"))     { enterBreak = false; }
			else if(args[i].equals("-P"))     { enterBreak = true; }
			else if(args[i].startsWith("-u"))
			{
				try
				{
					urlStyleSheet = new URL(args[i].substring(2, args[i].length()));
				}
				catch(MalformedURLException murle)
				{
					murle.printStackTrace(System.err);
				}
			}
			else if(args[i].startsWith("-l"))
			{
				if(args[i].indexOf('_') == 4 && args[i].length() >= 7)
				{
					sLang = args[i].substring(2, args[i].indexOf('_'));
					sCtry = args[i].substring(args[i].indexOf('_') + 1, args[i].length());
				}
			}
			else if(args[i].equals("-d"))     { debugOn = true; }
			else if(args[i].equals("-D"))     { debugOn = false; }
		}
		Ekit ekit = new Ekit(sDocument, sStyleSheet, sRawDocument, urlStyleSheet, includeToolBar, includeViewSource, includeMenuIcons, modeExclusive, sLang, sCtry, base64, debugOn, spellCheck, multibar, enterBreak);
	}

}