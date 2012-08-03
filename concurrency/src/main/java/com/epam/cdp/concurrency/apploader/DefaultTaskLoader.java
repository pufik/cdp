package com.epam.cdp.concurrency.apploader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import com.epam.cdp.concurrency.data.ApplicationContext;
import com.epam.cdp.concurrency.exception.SystemException;
import com.epam.cdp.concurrency.forkjoin.ProcessDataAction;
import com.epam.cdp.concurrency.process.DataProcessor;
import com.epam.cdp.concurrency.process.OutputDataProvider;
import com.epam.cdp.concurrency.process.UIProgress;

public class DefaultTaskLoader implements ApplicationLoader {

	private final int threadNumber;

	private ApplicationContext context;

	public DefaultTaskLoader(int threadNumber, String inputFilePath, String outputFilePath) throws SystemException {
		this.threadNumber = threadNumber;
		this.context = new ApplicationContext();
		this.context.setInputFileName(inputFilePath);
		this.context.setOutputFileName(outputFilePath);
	}

	@Override
	public void mainTask() throws Exception {
		startUiProgress();
		startMainProcess();
	}

	@Override
	public void forkJoin() throws Exception {
		ForkJoinPool pool = new ForkJoinPool();
		ProcessDataAction task = new ProcessDataAction(context);

		startUiProgress();
		pool.execute(task);
		ExecutorService outputDataExecutor = startOutputDataProcessor();
		waitForResult();

		pool.shutdown();
		outputDataExecutor.shutdown();
	}

	private void startUiProgress() {
		Thread uiDraw = new Thread(new UIProgress(context));
		uiDraw.setDaemon(true);
		uiDraw.start();
	}

	private void startMainProcess() throws Exception {
		ExecutorService processDataExecutor = startMainDataProcessor();
		ExecutorService outputDataExecutor = startOutputDataProcessor();

		waitForResult();

		processDataExecutor.shutdown();
		outputDataExecutor.shutdown();
	}

	private ExecutorService startMainDataProcessor() throws SystemException {
		ExecutorService processDataExecutor = Executors.newFixedThreadPool(threadNumber);

		for (int i = 0; i < threadNumber; i++) {
			processDataExecutor.execute(new DataProcessor(context));
		}

		return processDataExecutor;
	}

	private ExecutorService startOutputDataProcessor() {
		ExecutorService outputDataExecutor = Executors.newSingleThreadExecutor();
		outputDataExecutor.execute(new OutputDataProvider(context));

		return outputDataExecutor;
	}

	private void waitForResult() throws Exception {
		synchronized (context.getDoneFlag()) {
			context.getDoneFlag().wait();
		}
	}
}
