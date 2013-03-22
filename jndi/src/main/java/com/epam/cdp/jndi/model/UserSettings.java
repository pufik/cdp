package com.epam.cdp.jndi.model;

import java.util.HashMap;
import java.util.Map;

public class UserSettings {

	private Long id;

	private User user;

	private Map<String, String> properties;

	public UserSettings() {
		properties = new HashMap<String, String>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "UserSettings [id=" + id + ", user=" + user + ", properties=" + properties + "]";
	}
}
