package com.epam.cdp.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.epam.cdp.jmx.mbean.Cdp;
import com.epam.cdp.jmx.mxbean.CdpX;

public class ApplicationLoader {

	public static void main(String[] args) {
		try {
			MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
			ObjectName objectName = new ObjectName("com.epam.cdp.jmx.simple.mbean:type=Cdp");
			Cdp cdp = new Cdp();
			mbeanServer.registerMBean(cdp, objectName);

			ObjectName objectXName = new ObjectName("com.epam.cdp.jmx.simple.mxbean:type=CdpX");
			CdpX cdpX = new CdpX();
			mbeanServer.registerMBean(cdpX, objectXName);

			System.out.println("Waiting forever...");
			Thread.sleep(Long.MAX_VALUE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
