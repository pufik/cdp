package com.epam.cdp.network.server.engine.player;

import com.epam.cdp.network.server.engine.message.Listener;
import com.epam.cdp.network.server.engine.message.Message;
import com.epam.cdp.network.server.provider.connector.Connector;

public class Player implements Listener {

	private int id;

	private String name;

	private WorldPosition position;

	private Color color;

	private final Connector connector;

	public Player(Connector connector) {
		this.connector = connector;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorldPosition getPosition() {
		return position;
	}

	public void setPosition(WorldPosition position) {
		this.position = position;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void sendMessage(Message message) {
		connector.sendMessage(message);
	}
}
