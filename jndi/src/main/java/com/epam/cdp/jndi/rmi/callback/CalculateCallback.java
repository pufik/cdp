package com.epam.cdp.jndi.rmi.callback;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.epam.cdp.jndi.rmi.callback.Callback;

public class CalculateCallback implements Callback, Serializable {

	private static final long serialVersionUID = -1621460652711070814L;

	public void callback() throws RemoteException {
		System.out.println("Do callback!!!!");
		
		UnicastRemoteObject.unexportObject(this, true);
	}
}
