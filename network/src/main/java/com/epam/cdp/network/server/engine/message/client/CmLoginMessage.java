package com.epam.cdp.network.server.engine.message.client;

import com.epam.cdp.network.server.engine.message.Message;

public class CmLoginMessage extends Message {
	
	public static final int MESSAGE_TYPE = 1;

	public CmLoginMessage() {
		setType(MESSAGE_TYPE);
	}
}
