package model;

import java.io.File;
import java.io.IOException;

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
}
