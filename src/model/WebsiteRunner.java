package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Runnable used to run the demo of the website in the background
 * 
 * @author Raphaël HASCOËT
 */
public class WebsiteRunner implements Runnable{

	private Process proc;
	
	public WebsiteRunner(Process proc) {
		this.proc = proc;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader rtOutput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

			@SuppressWarnings("unused")
			String s = null;
			while((s = rtOutput.readLine()) != null) {}

			// System.out.println("Sorti 2");

		} catch (IOException e) {}
	}



}
