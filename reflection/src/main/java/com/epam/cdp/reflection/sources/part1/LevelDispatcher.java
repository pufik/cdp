package com.epam.cdp.reflection.sources.part1;

public interface LevelDispatcher {
	
	void start(String level) throws Exception;

	void stop(String level) throws Exception;
	
	@Deprecated
	void pause(String level, Long place) throws Exception;
}
