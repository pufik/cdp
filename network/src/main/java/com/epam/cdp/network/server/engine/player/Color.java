package com.epam.cdp.network.server.engine.player;

public class Color {

	private byte r;

	private byte g;

	private byte b;

	public byte getR() {
		return r;
	}

	public void setR(byte r) {
		this.r = r;
	}

	public byte getG() {
		return g;
	}

	public void setG(byte g) {
		this.g = g;
	}

	public byte getB() {
		return b;
	}

	public void setB(byte b) {
		this.b = b;
	}

	public void setRGB(byte r, byte g, byte b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
}
