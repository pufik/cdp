package com.epam.cdp.jndi.rmi.calculator;

import java.io.Serializable;

public class Task implements Serializable {

	private static final long serialVersionUID = -5105580737022321990L;

	private String javaScriptExp;

	public String getJavaScriptExp() {
		return javaScriptExp;
	}

	public void setJavaScriptExp(String javaScriptExp) {
		this.javaScriptExp = javaScriptExp;
	}
}
