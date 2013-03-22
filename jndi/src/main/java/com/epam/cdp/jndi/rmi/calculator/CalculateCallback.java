package com.epam.cdp.jndi.rmi.calculator;

import java.io.Serializable;

import com.epam.cdp.jndi.rmi.callback.Callback;

public class CalculateCallback implements Callback, Serializable {

	private static final long serialVersionUID = -1621460652711070814L;

	public void callback() {
		System.out.println("Do callback!!!!");
	}
}
