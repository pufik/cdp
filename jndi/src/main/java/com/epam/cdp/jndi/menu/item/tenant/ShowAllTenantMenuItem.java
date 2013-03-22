package com.epam.cdp.jndi.menu.item.tenant;

import java.util.ArrayList;
import java.util.List;

import com.epam.cdp.jndi.context.ApplicationContext;
import com.epam.cdp.jndi.dao.tenant.LdapTenantDAO;
import com.epam.cdp.jndi.dao.tenant.TenantDAO;
import com.epam.cdp.jndi.menu.MenuItem;
import com.epam.cdp.jndi.model.Tenant;

public class ShowAllTenantMenuItem implements MenuItem {
	
	private ApplicationContext context;

	private TenantDAO tenantDao;

	public ShowAllTenantMenuItem(ApplicationContext context) {
		this.context = context;
		tenantDao = new LdapTenantDAO();
	}

	public String getDescription() {
		return "Show All Tenants";
	}

	public void execute() {
		List<Tenant> tenants = new ArrayList<Tenant>(tenantDao.getAll());

		int i = 0;
		for (Tenant tenant : tenants) {
			System.out.println(i + " -> [" + tenant.getId() + "] " + tenant.getName());
			i++;
		}

		System.out.println(" --- Choose tenant: ");
		i = context.getScanner().nextInt();
		Tenant tenant = tenants.get(i);
		context.setCurrentTenant(tenant);
		
		MenuItem item = new TenantOperationsMenuItem(context);
		item.execute();
	}
}
