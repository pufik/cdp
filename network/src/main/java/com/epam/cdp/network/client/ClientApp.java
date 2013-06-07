package com.epam.cdp.network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp {

	public static void main(String[] args) {
		try {
			boolean cond = true;
			Socket serverConnection = new Socket("localhost", 1111);
			while (cond) {
				PrintWriter out = new PrintWriter(serverConnection.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));

				out.println("Hello IUrii!!!");
				
				String nextLine;
				while ((nextLine = in.readLine()) != null) {
					System.out.println("From Server: " + nextLine);
					out.println(nextLine);
				}
			}
			
			serverConnection.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
