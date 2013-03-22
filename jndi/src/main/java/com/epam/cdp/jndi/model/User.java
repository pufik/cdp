package com.epam.cdp.jndi.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class User {

	private Long id;

	private String name;

	private String email;

	private Date birthDate;

	private List<UserSettings> settings;

	private Tenant tenant;

	public User(Long id) {
		this.id = id;
	}

	public User() {
		settings = new LinkedList<UserSettings>();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<UserSettings> getSettings() {
		return settings;
	}

	public void setSettings(List<UserSettings> settings) {
		this.settings = settings;
	}

	public void addSettings(UserSettings settings) {
		this.settings.add(settings);
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public String getSelector() {
		return "cn=" + name + "," + tenant.getSelector();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
}
