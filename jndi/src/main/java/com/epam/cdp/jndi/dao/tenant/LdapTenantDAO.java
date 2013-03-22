package com.epam.cdp.jndi.dao.tenant;

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

public class LdapTenantDAO extends AbstractBaseDAO<Tenant, Long> implements TenantDAO {

	public void create(Tenant entity) {
		DirContext context = getDirContext();
		String tenantId = String.valueOf(getCounter());
		Attributes attrs = prepareAttributes(entity, tenantId);

		try {
			context.createSubcontext(formSelector(tenantId), attrs);
		} catch (NamingException e) {
			throw new ContextDataException("Execute CREATE request error", e);
		}
	}

	private Attributes prepareAttributes(Tenant entity, String tenantId) {
		Attributes attrs = new BasicAttributes(true);
		Attribute objClass = new BasicAttribute(Constants.OBJECTCLASS_ATTRIBUTE_NAME);
		objClass.add(Constants.TOP_ATTRIBUTE_NAME);
		objClass.add(Constants.ORGANIZATIONAL_UNIT);
		attrs.put(objClass);

		attrs.put(Constants.LOCALITY_NAME, entity.getName());
		attrs.put(Constants.DESTINATION_INDICATOR, tenantId);

		return attrs;
	}

	public Tenant getById(Long id) {
		return getByName(formSelector(id.toString()));
	}

	public void update(Tenant entity) {
		DirContext context = getDirContext();
		String tenantId = entity.getId().toString();
		Attributes attrs = prepareAttributes(entity, tenantId);

		try {
			context.modifyAttributes(formSelector(tenantId), DirContext.REPLACE_ATTRIBUTE, attrs);
		} catch (NamingException e) {
			throw new ContextDataException("Execute UPDATE request error", e);
		}
	}

	public void remove(Tenant entity) {
		DirContext context = getDirContext();
		try {
			context.destroySubcontext(formSelector(entity.getId().toString()));
		} catch (NamingException e) {
			throw new ContextDataException("Execute REMOVE request error", e);
		}
	}

	public Collection<Tenant> getAll() {
		List<Tenant> list = new LinkedList<Tenant>();
		String selector = "";
		try {
			DirContext context = getDirContext();
			NamingEnumeration<NameClassPair> objects = context.list(selector);

			while (objects.hasMore()) {
				NameClassPair nc = objects.next();
				list.add(getByName(nc.getName()));
			}

		} catch (NamingException e) {
			throw new ContextDataException("Execute REMOVE request error", e);
		}

		return list;
	}

	private Tenant getByName(String name) {
		Tenant tenant = null;
		DirContext context = getDirContext();
		Attributes answer;

		try {
			String[] attrIDs = { Constants.DESTINATION_INDICATOR, Constants.LOCALITY_NAME };

			answer = context.getAttributes(name, attrIDs);

			tenant = convertTenantInfo(answer);

		} catch (NamingException e) {
			throw new ContextDataException("Execute request error", e);
		}
		
		return tenant;
	}

	private String formSelector(String tenantId) {
		return "ou=Tenant[" + tenantId + "]";
	}

	private Tenant convertTenantInfo(Attributes answer) throws NamingException {
		Tenant tenant = new Tenant();

		String tenantId = answer.get(Constants.DESTINATION_INDICATOR).get().toString();
		String name = answer.get(Constants.LOCALITY_NAME).get().toString();

		tenant = new Tenant(Long.valueOf(tenantId));
		tenant.setName(name);

		return tenant;
	}

}
