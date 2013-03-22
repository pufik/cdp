package com.epam.cdp.jndi.rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import com.epam.cdp.jndi.rmi.calculator.CalculateCallback;
import com.epam.cdp.jndi.rmi.calculator.Calculator;
import com.epam.cdp.jndi.rmi.calculator.Task;
import com.epam.cdp.jndi.rmi.callback.Callback;

public class ClientApplication {

	public static void main(String[] args) {
		Registry registry;
		Scanner scanner = new Scanner(System.in);
		Callback callback = new CalculateCallback();
		try {
			registry = LocateRegistry.getRegistry(null, 1010);
			Calculator calculator = (Calculator) registry.lookup(Calculator.REGISTRY_NAME);
			calculator.registerCallBack(callback);

			Task task = new Task();
			System.out.println("Enter JavaScript exp.:");
			task.setJavaScriptExp(scanner.nextLine());

			Double result = calculator.calculate(task);
			System.out.println(result);

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

	}
}
