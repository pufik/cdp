package com.epam.cdp.reflection.sources.part1.impl;

import com.epam.cdp.reflection.sources.part1.LevelDispatcher;

public class BaseLevelDispatcher implements LevelDispatcher {

	@Override
	public void start(String level) throws Exception {
		System.out.println("\nSTART: " + level + "\n");
		Thread.sleep(1000);
	}

	@Override
	public void stop(String level) throws Exception {
		System.out.println("\nSTOP: " + level + "\n");
		Thread.sleep(1500);
	}

	@Override
	public void pause(String level, Long place) throws Exception {
		System.out.println("\nPAUSE: " + level + "\n");
		Thread.sleep(2000);
	}
}
