package com.epam.cdp.jndi.menu.item.tenant;

import java.util.LinkedHashMap;
import java.util.Map;

import com.epam.cdp.jndi.context.ApplicationContext;
import com.epam.cdp.jndi.menu.MenuItem;
import com.epam.cdp.jndi.menu.item.user.CreateUserMenuItem;
import com.epam.cdp.jndi.menu.item.user.ShowAllUserMenuItem;

public class TenantOperationsMenuItem implements MenuItem {

	private ApplicationContext context;

	private Map<Integer, MenuItem> subItems;

	public TenantOperationsMenuItem(ApplicationContext context) {
		this.context = context;
		subItems = new LinkedHashMap<Integer, MenuItem>();
		subItems.put(1, new ShowAllUserMenuItem(context));
		subItems.put(2, new CreateUserMenuItem(context));
	}

	public String getDescription() {
		return "------- Tenant Operations -----------";
	}

	public void execute() {

		for (Integer key : subItems.keySet()) {
			System.out.println(key + " -> " + subItems.get(key).getDescription());
		}

		int i = context.getScanner().nextInt();
		subItems.get(Integer.valueOf(i)).execute();
	}
}
