package com.epam.cdp.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 * 
 */
public class App {
	private final static Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		LOGGER.info("Start logging");
		final int n = 100;

		(new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < n; i++) {
					LOGGER.info("Hi, I log INFO");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						LOGGER.error("Thread problem", e);
					}
				}
			}
		})).start();

		(new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < n; i++) {
					LOGGER.warn("Hi, I log WARN");
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						LOGGER.error("Thread problem", e);
					}
				}

			}
		})).start();

		(new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < n; i++) {
					LOGGER.error("Hi, I log ERROR");
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						LOGGER.error("Thread problem", e);
					}
				}
			}
		})).start();

	}
}
