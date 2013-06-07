package com.epam.cdp.network.server.engine.message.server;

import com.epam.cdp.network.server.engine.message.Message;

public class SmLoginSuccess extends Message {
	
	public static final int MESSAGE_TYPE = 0;

	public SmLoginSuccess() {
		setType(MESSAGE_TYPE);
	}
}
