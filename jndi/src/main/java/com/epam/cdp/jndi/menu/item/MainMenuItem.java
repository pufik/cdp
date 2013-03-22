package com.epam.cdp.jndi.menu.item;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.epam.cdp.jndi.context.ApplicationContext;
import com.epam.cdp.jndi.menu.MenuItem;
import com.epam.cdp.jndi.menu.item.tenant.CreateTenantMenuItem;
import com.epam.cdp.jndi.menu.item.tenant.ShowAllTenantMenuItem;

public class MainMenuItem implements MenuItem {

	private static final int EXIT_MENU = 0;

	private ApplicationContext context;

	private Map<Integer, MenuItem> subItems;

	public MainMenuItem(ApplicationContext context) {
		this.context = context;
		subItems = new LinkedHashMap<Integer, MenuItem>();
		subItems.put(Integer.valueOf(1), new ShowAllTenantMenuItem(context));
		subItems.put(Integer.valueOf(2), new CreateTenantMenuItem(context));
	}

	public String getDescription() {
		return "Main menu";
	}

	public void execute() {
		System.out.println(" -------- " + getDescription() + " --------------");

		do {
			try {
				System.out.println("[-------------Choouse action-------------------]");
				
				for (Integer key : subItems.keySet()) {
					System.out.println(key + " -> " + subItems.get(key).getDescription());
				}
				
				System.out.println(" 0 -> Exit");
				System.out.println("---------------------------------------");

				Integer action = Integer.valueOf(context.getScanner().nextInt());
				MenuItem item = subItems.get(action);

				if (action.intValue() == EXIT_MENU) {
					break;
				} else if (item == null) {
					System.out.println("Bad action number! Try again");
					continue;
				}
				item.execute();

			} catch (InputMismatchException e) {
				System.out.println("Incorrect input data");
				// TODO: ADD logger
			}

		} while (true);

		System.out.println("Goodbye!!! ;)))");
	}
}
