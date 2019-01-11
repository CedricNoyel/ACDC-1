package model;

import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.concurrent.ExecutorService;
import java.util.regex.Pattern;

public class Tools {

	/**
	 * Method to execute a given command on a given path
	 * @param cmd - String of the command to execute
	 * @param path - String of the path where to execute the command
	 */
	public static void executeCmd(String cmd, String path) {
		System.out.println("COMMAND RUN: " + cmd + "\r	in " + path);
		ProcessBuilder builder = new ProcessBuilder();
		builder.directory(new File(path));
		Process process = null;
		if(System.getProperty("os.name").toLowerCase().startsWith("windows"))
			builder.command("cmd.exe", "/c", cmd); // IF windows os
		else builder.command("sh", "-c", cmd); // ELSE unix
		try {
			process = builder.start();
			process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		process.destroy();
	}
	
	public static String deAccent(String str) {
	    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
}
