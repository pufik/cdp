package com.epam.cdp.jndi.rmi.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Callback extends Remote {
	void callback() throws RemoteException;
}
