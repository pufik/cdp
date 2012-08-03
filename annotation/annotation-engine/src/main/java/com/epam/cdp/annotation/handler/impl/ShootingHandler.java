package com.epam.cdp.annotation.handler.impl;

import com.epam.cdp.annotation.event.Event;
import com.epam.cdp.annotation.event.EventHandler;
import com.epam.cdp.annotation.event.impl.BatleChildEvent;
import com.epam.cdp.annotation.event.impl.BattleEvent;
import com.epam.cdp.annotation.handler.BaseEventHandler;

public class ShootingHandler implements BaseEventHandler {

	@EventHandler
	public void shootFromArbalest(BattleEvent event) {
		System.out.println("Arbalest Event: " + event.getClass().toString());
	}
	
	@EventHandler
	public void shootFromArbalest(BatleChildEvent event) {
		System.out.println("Arbalest Event: " + event.getClass().toString());
	}

	// Method for testing requirements
	@EventHandler
	private void shootFromGun(BattleEvent event) {
		System.out.println("Gun Event: " + event.getClass().toString());
	}

	@EventHandler
	public static void shootFromBow(BattleEvent event) {
		System.out.println("Bow Event: " + event.getClass().toString());
	}

	@EventHandler
	public void shootByDefault(Event event) {
		System.out.println("Default Event: " + event.getClass().toString());
	}
	
	@EventHandler
	public void badMethod(String tmp) {
	}
}
