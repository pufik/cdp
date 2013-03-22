package com.epam.cdp.jndi.dao.user;

import java.util.List;

import com.epam.cdp.jndi.dao.BaseDAO;
import com.epam.cdp.jndi.model.Tenant;
import com.epam.cdp.jndi.model.User;

public interface UserDAO extends BaseDAO<User, String> {
	
	List<User> getUsersByTenant(Tenant tenant);

}
