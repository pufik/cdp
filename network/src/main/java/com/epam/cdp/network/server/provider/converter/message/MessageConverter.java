package com.epam.cdp.network.server.provider.converter.message;

import com.epam.cdp.network.server.engine.message.Message;

public interface MessageConverter<T extends Message> {
	
	T convertBinToMessage(byte[] binMessage);

	byte[] converMessageToBin(T message);
}
