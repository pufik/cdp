package com.epam.cdp.jndi.menu.item.tenant;

import com.epam.cdp.jndi.context.ApplicationContext;
import com.epam.cdp.jndi.dao.tenant.LdapTenantDAO;
import com.epam.cdp.jndi.dao.tenant.TenantDAO;
import com.epam.cdp.jndi.menu.MenuItem;
import com.epam.cdp.jndi.model.Tenant;

public class CreateTenantMenuItem implements MenuItem {

	private ApplicationContext context;

	private TenantDAO tenantDao;

	public CreateTenantMenuItem(ApplicationContext context) {
		this.context = context;
		tenantDao = new LdapTenantDAO();
	}

	public String getDescription() {
		return "Create Tenant";
	}

	public void execute() {
		System.out.println("---------- " + getDescription() + " --------------");

		Tenant tenant = new Tenant();
		System.out.println("Enter tenant's name: ");

		tenant.setName(context.getScanner().nextLine());
		tenantDao.create(tenant);

		System.out.println("--------------------------------------------------");
	}
}
