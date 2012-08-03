package com.epam.cdp.concurrency.apploader;

public interface ApplicationLoader {

	void mainTask() throws Exception;

	void forkJoin() throws Exception;
}
