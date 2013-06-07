package com.epam.cdp.network.server.engine.player;

public class WorldPosition {

	private final long x;

	private final long y;

	public WorldPosition(long x, long y) {
		this.x = x;
		this.y = y;
	}

	public long getX() {
		return x;
	}

	public long getY() {
		return y;
	}
}
