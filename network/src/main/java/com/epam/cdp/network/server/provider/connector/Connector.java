package com.epam.cdp.network.server.provider.connector;

import com.epam.cdp.network.server.engine.message.Message;

public interface Connector {
	
	void sendMessage(Message message);
}
