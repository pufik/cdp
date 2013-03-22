package com.epam.cdp.jndi.rmi.calculator;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.epam.cdp.jndi.rmi.callback.Callback;

public interface Calculator extends Remote {
	static final String REGISTRY_NAME = "Calculator";

	void registerCallBack(Callback callback) throws RemoteException;

	Double calculate(Task task) throws RemoteException;
}
