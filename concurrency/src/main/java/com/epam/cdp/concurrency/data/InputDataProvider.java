package com.epam.cdp.concurrency.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.cdp.concurrency.exception.SystemException;

public class InputDataProvider {
	private final static Logger logger = LoggerFactory.getLogger(InputDataProvider.class);
	
	private static volatile InputDataProvider instance;
	
	private FileReader fileReader;
	private BufferedReader reader;
	private InputDataProvider(String filePath) throws SystemException {
		try {
			fileReader = new FileReader(filePath);
			reader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			logger.error("Can't read file: " + filePath,e);
			throw new SystemException("Can't read file: " + filePath, e);
		}
	}

	public static InputDataProvider getInstance(String fileName) throws SystemException {
		if (instance == null) {
			synchronized (InputDataProvider.class) {
				if (instance == null)
					instance = new InputDataProvider(fileName);
			}
		}

		return instance;
	}

	public synchronized String getData() throws SystemException {
		String line = null;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				logger.error("Erroe while reading the file",e);
				throw new SystemException("Erroe while reading the file", e);
			}
		return line;
	}
}
