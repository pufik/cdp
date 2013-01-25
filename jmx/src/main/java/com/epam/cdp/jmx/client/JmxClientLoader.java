package com.epam.cdp.jmx.client;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.epam.cdp.jmx.client.notification.CdpListener;
import com.epam.cdp.jmx.mbean.CdpMBean;

public class JmxClientLoader {

	public static void main(String[] args) {
		JMXServiceURL url;
		try {
			url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
			JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
			MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
			NotificationListener listener = new CdpListener();

			System.out.println("\nDomains:");
			String domains[] = mbsc.getDomains();
			Arrays.sort(domains);
			for (String domain : domains) {
				System.out.println("\tDomain = " + domain);
			}

			System.out.println("\nMBeanServer default domain = " + mbsc.getDefaultDomain());

			System.out.println("\nMBean count = " + mbsc.getMBeanCount());
			System.out.println("\nQuery MBeanServer MBeans:");
			Set<ObjectName> names = new TreeSet<ObjectName>(mbsc.queryNames(null, null));
			
			for (ObjectName name : names) {
				System.out.println("\tObjectName = " + name);
			}
			
			ObjectName mbeanName = new ObjectName("com.epam.cdp.jmx.simple.mbean:type=Cdp");
			CdpMBean mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, 
			                                          CdpMBean.class, true);

			System.out.println("\nAdd notification listener...");
			mbsc.addNotificationListener(mbeanName, listener, null, null);
			
			System.out.println("Print system info:");
			mbeanProxy.operatingSystemInfo();

			Thread.sleep(2000);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
