package com.epam.cdp.jndi;

import com.epam.cdp.jndi.context.ApplicationContext;
import com.epam.cdp.jndi.menu.MenuItem;
import com.epam.cdp.jndi.menu.item.MainMenuItem;

public class ApplicationLoader {

	public static void main(String[] args) {
		ApplicationContext context = new ApplicationContext();
		MenuItem mainMenu = new MainMenuItem(context);

		mainMenu.execute();
	}
}
