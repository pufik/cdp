package com.epam.cdp.concurrency.process;

import com.epam.cdp.concurrency.data.ApplicationContext;
import com.epam.cdp.concurrency.data.OuputDataFileWriter;

public class OutputDataProvider implements Runnable {
	private ApplicationContext context;
	private OuputDataFileWriter fileWriter;

	public OutputDataProvider(ApplicationContext context) {
		this.context = context;
		this.fileWriter = new OuputDataFileWriter(context.getOutputFileName());
	}

	@Override
	public void run() {
		do {
			writeData();
		} while (context.canProcessData() || (!context.getResultList().isEmpty()));
		
		fileWriter.close();
	}

	private void writeData() {
		while (!context.getResultList().isEmpty()) {
			fileWriter.writeData(context.getResultList().poll());
			context.incrementProcessedLines();
		}
	}
}
