package com.epam.cdp.concurrency.data;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class ApplicationContext {
	private ArrayBlockingQueue<String> resultList;
	private AtomicLong processedLines;
	private AtomicBoolean canProcessData;
	private Boolean doneFlag;
	private String inputFileName;
	private String outputFileName;

	public ApplicationContext() {
		canProcessData = new AtomicBoolean(true);
		resultList = new ArrayBlockingQueue<String>(1000);
		processedLines = new AtomicLong(0);
		doneFlag = new Boolean(true);
	}

	public ArrayBlockingQueue<String> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayBlockingQueue<String> resultList) {
		this.resultList = resultList;
	}

	public AtomicLong getProcessedLines() {
		return processedLines;
	}

	public void setProcessedLines(AtomicLong processedLines) {
		this.processedLines = processedLines;
	}

	public void incrementProcessedLines() {
		this.processedLines.incrementAndGet();
	}

	public boolean canProcessData() {
		return canProcessData.get();
	}

	public void dataProcessed() {
		this.canProcessData.set(false);
	}

	public String getInputFileName() {
		return inputFileName;
	}

	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public Boolean getDoneFlag() {
		return doneFlag;
	}

	public void setDoneFlag(Boolean doneFlag) {
		this.doneFlag = doneFlag;
	}
}
