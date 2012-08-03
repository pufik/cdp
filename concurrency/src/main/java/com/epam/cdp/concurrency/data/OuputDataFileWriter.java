package com.epam.cdp.concurrency.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OuputDataFileWriter {
	private final static Logger logger = LoggerFactory.getLogger(OuputDataFileWriter.class);

	private String fileName;
	private BufferedWriter buffWriter;

	public OuputDataFileWriter(String fileName) {
		FileWriter fileWriter;
		try {
			this.fileName = fileName;
			fileWriter = new FileWriter(fileName);
			buffWriter = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			logger.error("Can't open file for writing: " + fileName, e);
		}
	}

	public void writeData(String data) {
		try {
			buffWriter.write(data);
			buffWriter.newLine();
			buffWriter.flush();
		} catch (IOException e) {
			logger.error("Can't write data into file: " + fileName, e);
		}
	}
	
	public void close(){
		try {
			buffWriter.close();
		} catch (IOException e) {
			logger.error("Can't finish file writing: " + fileName, e);
		}
	}
}
