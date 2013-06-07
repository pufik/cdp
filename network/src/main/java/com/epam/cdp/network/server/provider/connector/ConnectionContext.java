package com.epam.cdp.network.server.provider.connector;

import java.net.Socket;

public class ConnectionContext {

	private Socket clientSocket;

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
}
