package com.epam.cdp.jndi.context;

import java.util.Scanner;

import com.epam.cdp.jndi.model.Tenant;

public class ApplicationContext {
	
	private Tenant currentTenant;

	public ApplicationContext() {
	}

	public Scanner getScanner() {
		return new Scanner(System.in);
	}

	public Tenant getCurrentTenant() {
		return currentTenant;
	}

	public void setCurrentTenant(Tenant currentTenant) {
		this.currentTenant = currentTenant;
	}
}
