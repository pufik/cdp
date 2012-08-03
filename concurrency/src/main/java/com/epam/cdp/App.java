package com.epam.cdp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.cdp.concurrency.apploader.ApplicationLoader;
import com.epam.cdp.concurrency.apploader.DefaultTaskLoader;

public class App {
	private final static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		try {
			ApplicationLoader loader = new DefaultTaskLoader(5, "e:/temp1/concurrent.txt", "e:/temp1/concurrent_result.txt");
			Date startDate = null;
			Date endDate = null;
			
			System.out.println("Choose task: [main/fork] ->");
			
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			String choice = bufferRead.readLine();

			startDate = new Date();

			if (choice.equalsIgnoreCase("main")) {
				loader.mainTask();
			}
			
			if (choice.equalsIgnoreCase("fork")) {
				loader.forkJoin();
			}
			endDate = new Date();

			System.out.println("--------------------------------------");
			System.out.println("Start at -> " + startDate + "\nFinished at -> " + endDate);
			System.out.println("\nProcessing time -> " + ((endDate.getTime() - startDate.getTime()) / 1000) + "s");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
