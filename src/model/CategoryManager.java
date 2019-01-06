package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import view.AccessProperties;

/**
 * Manages the post categories list
 * 
 * @author Raphaël HASCOËT
 */
public class CategoryManager {
	
	private final static String FILE_PATH = AccessProperties.getInstance().getLocalRepository() 
			+ File.separator + "BLOG" + File.separator + "category" + File.separator + "categories.txt";

	public static ArrayList<String> getCategories(){
		ArrayList<String> categories = new ArrayList<String>();
		
		try {
			Scanner scFile = new Scanner(new File(FILE_PATH));
			
			while(scFile.hasNextLine()){
				categories.add(scFile.nextLine());
			}
			
			scFile.close();			
		} catch (FileNotFoundException e) {
			ErrorManager.sendError("File not found");
		}
		
		return categories;
	}
	
	public static boolean addCategory(String cat){
		boolean success = false;
		if(!getCategories().contains(cat)){
			try{
				final Path path = Paths.get(FILE_PATH);
				Files.write(path, Arrays.asList(cat), StandardCharsets.UTF_8,
				        Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
				success = true;
			} catch (final IOException ioe){
				ErrorManager.sendError("Cannot write to the category file");
			}
		} else {
			ErrorManager.sendError("The category \"" + cat + "\" already exists");
		}
		return success;
	}
	
	public static boolean deleteCategory(String cat){
		boolean success = false;
		
		ArrayList<String> categories = getCategories();
		try {
			Files.newBufferedWriter(Paths.get(FILE_PATH) , StandardOpenOption.TRUNCATE_EXISTING);
			
			boolean found = false;
			for(String readCat : categories){
				if(readCat.equals(cat)){
					found = true;
				} else {
					addCategory(readCat);
				}
			}
			
			if(found){
				success = true;
			} else {
				ErrorManager.sendError("The category does not exist");
			}

		} catch (IOException e) {
			ErrorManager.sendError("Cannot write to the category file");
		}
		return success;
	}
	
}
