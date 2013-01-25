package com.epam.cdp.jmx.mxbean;

public class CdpX implements CdpXMXBean {

	@Override
	public void shutdown() {
		System.out.println("Bye!!!");
		System.exit(0);
	}

}
