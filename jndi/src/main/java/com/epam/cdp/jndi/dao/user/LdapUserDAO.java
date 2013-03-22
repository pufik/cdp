package com.epam.cdp.jndi.dao.user;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;

import com.epam.cdp.jndi.dao.AbstractBaseDAO;
import com.epam.cdp.jndi.dao.util.Constants;
import com.epam.cdp.jndi.exception.ContextDataException;
import com.epam.cdp.jndi.model.Tenant;
import com.epam.cdp.jndi.model.User;

public class LdapUserDAO extends AbstractBaseDAO<User, String> implements UserDAO {

	public List<User> getUsersByTenant(Tenant tenant) {
		List<User> users = new LinkedList<User>();

		DirContext context = getDirContext();

		try {
			NamingEnumeration<NameClassPair> objects = context.list(formTenantSelector(tenant.getId().toString()));

			while (objects.hasMore()) {
				NameClassPair nc = objects.next();
				users.add(getByName(nc.getName(), tenant));
			}

		} catch (NamingException e) {
			throw new ContextDataException("Execute request error", e);
		}

		return users;
	}

	private User getByName(String name, Tenant tenant) {
		User user = null;
		Attributes answer;

		try {
			DirContext context = getDirContext(tenant);
			String[] attrIDs = { Constants.CN, Constants.DESTINATION_INDICATOR, Constants.LOCALITY_NAME, Constants.MAIL };

			answer = context.getAttributes(name, attrIDs);

			user = convertUserInfo(answer);
			user.setTenant(tenant);

		} catch (NamingException e) {
			throw new ContextDataException("Execute request error", e);
		}

		return user;
	}

	private User convertUserInfo(Attributes answer) throws NamingException {
		User user;

		String userId = answer.get(Constants.DESTINATION_INDICATOR).get().toString();
		String name = answer.get(Constants.CN).get().toString();
		String email = answer.get(Constants.MAIL).get().toString();

		user = new User(Long.valueOf(userId));
		user.setName(name);
		user.setEmail(email);

		return user;
	}

	private Attributes prepareAttributes(User entity, String userId) {
		Attributes attrs = new BasicAttributes(true);
		Attribute objClass = new BasicAttribute(Constants.OBJECTCLASS_ATTRIBUTE_NAME);
		objClass.add(Constants.TOP_ATTRIBUTE_NAME);
		objClass.add(Constants.INET_ORG_PERSON);
		attrs.put(objClass);

		attrs.put(Constants.LOCALITY_NAME, entity.getName());
		attrs.put(Constants.DESTINATION_INDICATOR, userId);
		attrs.put(Constants.MAIL, entity.getEmail());
		attrs.put(Constants.CN, entity.getName());
		attrs.put(Constants.SN, entity.getName());

		return attrs;
	}

	private String formTenantSelector(String tenantId) {
		return "ou=Tenant[" + tenantId + "]";
	}

	private String formUserSelector(String name) {
		return "cn=" + name;
	}

	public void create(User entity) {
		Attributes attrs = prepareAttributes(entity, String.valueOf(getCounter()));

		try {
			DirContext context = getDirContext(entity.getTenant());
			context.createSubcontext(formUserSelector(entity.getName()), attrs);
		} catch (NamingException e) {
			throw new ContextDataException("Execute CREATE request error", e);
		}
	}

	private DirContext getDirContext(Tenant tenant) throws NamingException {
		return (DirContext) getDirContext().lookup(formTenantSelector(tenant.getId().toString()));
	}

	public User getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(User entity) {
		// TODO Auto-generated method stub

	}

	public void remove(User entity) {
		// TODO Auto-generated method stub

	}

	public Collection<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
