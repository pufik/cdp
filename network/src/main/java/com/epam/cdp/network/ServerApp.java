package com.epam.cdp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.cdp.network.server.provider.connector.standard.NativeServerConnector;

/**
 * Hello world!
 *
 */
public class ServerApp 
{
    public static void main( String[] args )
    {
        try {
			ServerSocket serverSocket = new ServerSocket(1111);
			System.out.println("Server started...");
			Socket clientConnection = serverSocket.accept();
			new Thread(new NativeServerConnector(clientConnection)).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
