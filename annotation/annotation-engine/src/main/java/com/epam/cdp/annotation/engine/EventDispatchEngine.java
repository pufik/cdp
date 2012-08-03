package com.epam.cdp.annotation.engine;

import com.epam.cdp.annotation.event.Event;
import com.epam.cdp.annotation.handler.BaseEventHandler;

public interface EventDispatchEngine {

	void registerEventHandler(BaseEventHandler eventHandler);

	void dispatchEvent(Event event);
}
