package com.epam.cdp.jndi.dao.user.settings;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;

import com.epam.cdp.jndi.dao.AbstractBaseDAO;
import com.epam.cdp.jndi.exception.ContextDataException;
import com.epam.cdp.jndi.model.User;
import com.epam.cdp.jndi.model.UserSettings;

public class LdapUserSettingDAO extends AbstractBaseDAO<UserSettings, Long> implements UserSettingDAO {

	public List<UserSettings> getAllByUser(User user) {
		List<UserSettings> settings = new LinkedList<UserSettings>();

		DirContext context = getDirContext();

		try {
			NamingEnumeration<NameClassPair> objects = context.list(user.getSelector());

			while (objects.hasMore()) {
				NameClassPair nc = objects.next();
				settings.add(getByName(nc.getName(), getDirContext(user)));
			}

		} catch (NamingException e) {
			throw new ContextDataException("Execute request error", e);
		}

		return settings;
	}
	
	private UserSettings getByName(String name, DirContext context) {
		UserSettings setting = null;
		Attributes answer;

		try {
			answer = context.getAttributes(name);

			setting = convertSettingsInfo(answer);

		} catch (NamingException e) {
			throw new ContextDataException("Execute request error", e);
		}

		return setting;
	}
	
	private UserSettings convertSettingsInfo(Attributes answer) throws NamingException {
		UserSettings setting = new UserSettings();

		NamingEnumeration<? extends Attribute> objects = (NamingEnumeration<? extends Attribute>) answer.getAll();

		while (objects.hasMore()) {
			Attribute attr = objects.next();
			setting.getProperties().put(attr.getID(), attr.get().toString());
		}

		return setting;
	}

	private DirContext getDirContext(User user) throws NamingException {
		DirContext context = (DirContext) getDirContext().lookup(user.getSelector());
		return context;
	}

	public void create(UserSettings entity) {
		// TODO Auto-generated method stub

	}

	public UserSettings getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(UserSettings entity) {
		// TODO Auto-generated method stub

	}

	public void remove(UserSettings entity) {
		// TODO Auto-generated method stub

	}

	public Collection<UserSettings> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
