package com.epam.cdp.jndi.menu.item.user;

import java.util.ArrayList;
import java.util.List;

import com.epam.cdp.jndi.context.ApplicationContext;
import com.epam.cdp.jndi.dao.user.LdapUserDAO;
import com.epam.cdp.jndi.dao.user.UserDAO;
import com.epam.cdp.jndi.dao.user.settings.LdapUserSettingDAO;
import com.epam.cdp.jndi.dao.user.settings.UserSettingDAO;
import com.epam.cdp.jndi.menu.MenuItem;
import com.epam.cdp.jndi.model.User;
import com.epam.cdp.jndi.model.UserSettings;

public class ShowAllUserMenuItem implements MenuItem {

	private ApplicationContext context;

	private UserDAO userDao;

	private UserSettingDAO settingDao;

	public ShowAllUserMenuItem(ApplicationContext context) {
		this.context = context;
		userDao = new LdapUserDAO();
		settingDao = new LdapUserSettingDAO();
	}

	public String getDescription() {
		return "---- Show All Users";
	}

	public void execute() {
		System.out.println(getDescription());
		System.out.println(context.getCurrentTenant());
		List<User> users = new ArrayList<User>(userDao.getUsersByTenant(context.getCurrentTenant()));

		int i = 0;
		for (User user : users) {
			System.out.println(i+ ": " + user);
			i++;
		}

		System.out.println(" --- Choose User: ");
		i = context.getScanner().nextInt();
		User user = users.get(i);

		List<UserSettings> settings = settingDao.getAllByUser(user);

		for (UserSettings setting : settings) {
			System.out.println(setting);
		}
	}
}
