package com.epam.cdp.network.server.engine.message;

public abstract class Message {

	private int type;

	protected void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
