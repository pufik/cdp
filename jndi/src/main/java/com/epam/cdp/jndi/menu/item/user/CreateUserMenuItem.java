package com.epam.cdp.jndi.menu.item.user;

import com.epam.cdp.jndi.context.ApplicationContext;
import com.epam.cdp.jndi.dao.user.LdapUserDAO;
import com.epam.cdp.jndi.dao.user.UserDAO;
import com.epam.cdp.jndi.menu.MenuItem;
import com.epam.cdp.jndi.model.User;

public class CreateUserMenuItem implements MenuItem {

	private ApplicationContext context;

	private UserDAO userDao;

	public CreateUserMenuItem(ApplicationContext context) {
		this.context = context;
		userDao = new LdapUserDAO();
	}

	public String getDescription() {
		return "--- Create New User for Tenant --";
	}

	public void execute() {
		System.out.println(getDescription());
		User user = new User();

		System.out.println("Enter name: ");
		user.setName(context.getScanner().nextLine());

		System.out.println("Enter email: ");
		user.setEmail(context.getScanner().nextLine());

		user.setTenant(context.getCurrentTenant());

		userDao.create(user);
		
		System.out.println("--------------------------------------------------");

	}

}
