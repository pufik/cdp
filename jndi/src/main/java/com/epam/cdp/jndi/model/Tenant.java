package com.epam.cdp.jndi.model;

import java.util.LinkedList;
import java.util.List;

public class Tenant {
	private Long id;

	private String name;

	private List<User> users;

	{
		users = new LinkedList<User>();
	}

	public Tenant() {
	}

	public Tenant(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		users.add(user);
	}

	public String getSelector() {
		return "ou=Tenant[" + id.toString() + "]";
	}

	@Override
	public String toString() {
		return "Tenant [id=" + id + ", name=" + name + "]";
	}
}
