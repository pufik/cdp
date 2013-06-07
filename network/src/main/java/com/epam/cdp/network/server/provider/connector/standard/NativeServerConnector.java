package com.epam.cdp.network.server.provider.connector.standard;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.epam.cdp.network.server.engine.message.Message;
import com.epam.cdp.network.server.provider.connector.Connector;
import com.epam.cdp.network.server.provider.converter.message.MessageConverterFactory;

public class NativeServerConnector implements Connector, Runnable {

	private MessageConverterFactory converterFactory;

	private Socket clientSocket;

	public NativeServerConnector(Socket clientSocket) {
		this.converterFactory = MessageConverterFactory.getInstance();
		this.clientSocket = clientSocket;
	}

	@Override
	public void sendMessage(Message message) {
		if (!clientSocket.isClosed()) {
			try {
				byte[] outputMessage = converterFactory.getMessageConverter(message.getType()).converMessageToBin(message);
				clientSocket.getOutputStream().write(outputMessage);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void getMessageFromClient() throws IOException {
		// TODO: Implement read input message
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		DataInputStream in = new DataInputStream(clientSocket.getInputStream());

		int messageType;
		while ((messageType = in.readInt()) > 0) {
			int messageSize = in.available();
			byte[] messageBody = new byte[messageSize];
			in.readFully(messageBody);

			out.println("BBBBBBRRRRRRRRRRRR");
		}
	}

	@Override
	public void run() {
		try {
			getMessageFromClient();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
