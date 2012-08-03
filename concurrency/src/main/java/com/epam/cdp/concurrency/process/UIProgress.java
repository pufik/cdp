package com.epam.cdp.concurrency.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.cdp.concurrency.data.ApplicationContext;

public class UIProgress implements Runnable {
	private final static Logger logger = LoggerFactory.getLogger(UIProgress.class);
	
	private ApplicationContext context;
	
	public UIProgress(ApplicationContext context){
		this.context = context;
	}

	@Override
	public void run() {
		while(true){
			System.out.println("Processed lines -> " + context.getProcessedLines().get());
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				logger.error("Thread interupt error", e);
			}
		}
	}

}
