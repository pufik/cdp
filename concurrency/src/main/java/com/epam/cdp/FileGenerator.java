package com.epam.cdp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class FileGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String resultFile = "e:/temp1/concurrent.txt";
		int n = 10000000;
		
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(resultFile);
			BufferedWriter buffWriter = new BufferedWriter(fileWriter);
			
			for(int i = 0; i < n; i++){
				buffWriter.write(UUID.randomUUID().toString());
				buffWriter.newLine();
				buffWriter.flush();
			}
			
			buffWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
