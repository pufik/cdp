package com.epam.cdp;

import com.epam.cdp.annotation.engine.EventDispatchEngine;
import com.epam.cdp.annotation.engine.impl.GameEventDispatchEngine;
import com.epam.cdp.annotation.event.impl.BattleEvent;
import com.epam.cdp.annotation.event.impl.SprintEvent;
import com.epam.cdp.annotation.handler.BaseEventHandler;
import com.epam.cdp.annotation.handler.impl.ShootingHandler;

public class AppLoader {

	public static void main(String[] args) {
		EventDispatchEngine engine = new GameEventDispatchEngine();
		BaseEventHandler handler = new ShootingHandler();

		engine.registerEventHandler(handler);
		engine.dispatchEvent(new BattleEvent());
		engine.dispatchEvent(new SprintEvent());
	}
}
