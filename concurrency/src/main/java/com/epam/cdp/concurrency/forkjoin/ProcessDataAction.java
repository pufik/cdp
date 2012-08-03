package com.epam.cdp.concurrency.forkjoin;

import java.util.concurrent.RecursiveAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.cdp.concurrency.data.ApplicationContext;
import com.epam.cdp.concurrency.data.InputDataProvider;
import com.epam.cdp.concurrency.exception.SystemException;
import com.epam.cdp.concurrency.utils.CustomStringConverter;

public class ProcessDataAction extends RecursiveAction {

	private static final int ACTION_NUMBER = 1;

	private static final long serialVersionUID = -6698940203306004847L;

	private final static Logger logger = LoggerFactory.getLogger(ProcessDataAction.class);

	private ApplicationContext context;

	private InputDataProvider dataProvider;

	public ProcessDataAction(ApplicationContext context) throws SystemException {
		this.context = context;
		this.dataProvider = InputDataProvider.getInstance(context.getInputFileName());
	}

	@Override
	protected void compute() {
		doAction();
		try {
			for (int i = 0; (i < ACTION_NUMBER) && (context.canProcessData()); i++) {

				ProcessDataAction action = new ProcessDataAction(context);
				action.fork();
			}
		} catch (SystemException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void doAction() {
		try {
			String line = dataProvider.getData();
			if (line != null) {
				String result = CustomStringConverter.convertString(line);
				context.getResultList().put(result);
			} else {
				context.dataProcessed();
				synchronized (context.getDoneFlag()) {
					context.getDoneFlag().notifyAll();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
