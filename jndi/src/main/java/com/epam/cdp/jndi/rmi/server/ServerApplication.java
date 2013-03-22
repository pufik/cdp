package com.epam.cdp.jndi.rmi.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.epam.cdp.jndi.rmi.calculator.Calculator;
import com.epam.cdp.jndi.rmi.calculator.RemoteCalculator;

public class ServerApplication {

	public static void main(String[] args) {
		Calculator calc = new RemoteCalculator();
		try {
			Calculator stub = (Calculator) UnicastRemoteObject.exportObject(calc, 0);
			Registry registry = LocateRegistry.createRegistry(1010);
			registry.bind(Calculator.REGISTRY_NAME, stub);

			System.out.println("Calculation server started...");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
