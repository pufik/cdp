package com.epam.cdp.jndi.rmi.calculator;

import java.rmi.RemoteException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.epam.cdp.jndi.rmi.callback.Callback;

@SuppressWarnings("restriction")
public class RemoteCalculator implements Calculator {

	private Callback callback;

	public Double calculate(Task task) throws RemoteException {
		Double result = 0.0;
		System.out.println("Start script processing...\n" + task.getJavaScriptExp());

		try {
			ScriptEngineManager factory = new ScriptEngineManager();
			ScriptEngine engine = factory.getEngineByName("JavaScript");
			result = (Double) engine.eval(task.getJavaScriptExp());

			if (callback != null) {
				callback.callback();
			}
		} catch (ScriptException e) {
			throw new RemoteException(e.getMessage(), e);
		}

		return result;
	}

	public void registerCallBack(Callback callback) throws RemoteException {
		this.callback = callback;
	}
}
