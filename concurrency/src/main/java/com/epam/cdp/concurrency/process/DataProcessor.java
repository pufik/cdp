package com.epam.cdp.concurrency.process;

import java.util.concurrent.RecursiveAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.cdp.concurrency.data.ApplicationContext;
import com.epam.cdp.concurrency.data.InputDataProvider;
import com.epam.cdp.concurrency.exception.SystemException;
import com.epam.cdp.concurrency.utils.CustomStringConverter;

public class DataProcessor extends RecursiveAction implements Runnable {

	private static final long serialVersionUID = 1L;

	private final static Logger logger = LoggerFactory.getLogger(DataProcessor.class);
			
	private InputDataProvider dataProvider;
	private ApplicationContext context;

	public DataProcessor(ApplicationContext context) throws SystemException {
		this.dataProvider = InputDataProvider.getInstance(context.getInputFileName());
		this.context = context;
	}

	@Override
	public void run() {
		try {
			while (context.canProcessData()) {
				String line = dataProvider.getData();
				if (line != null) {
					String result = CustomStringConverter.convertString(line);
					context.getResultList().put(result);
				}else{
					context.dataProcessed();
					synchronized (context.getDoneFlag()) {
						context.getDoneFlag().notifyAll();
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	protected void compute() {
		run();		
	}
}
