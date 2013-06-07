package com.epam.cdp.network.server.provider.converter.message;

import java.util.HashMap;
import java.util.Map;

import com.epam.cdp.network.server.engine.message.Message;
import com.epam.cdp.network.server.engine.message.client.CmLoginMessage;

public class MessageConverterFactory {

	private static MessageConverterFactory instance = null;

	private Map<Integer, MessageConverter> converters;

	private MessageConverterFactory() {
		converters = new HashMap<>();

		converters.put(CmLoginMessage.MESSAGE_TYPE, new CmLoginMessageConverter());

	}

	public static MessageConverterFactory getInstance() {
		if (instance == null) {
			synchronized (MessageConverterFactory.class) {
				if (instance == null) {
					instance = new MessageConverterFactory();
				}
			}
		}

		return instance;
	}

	public MessageConverter<Message> getMessageConverter(Integer messageType) {
		return converters.get(messageType);
	}
}
