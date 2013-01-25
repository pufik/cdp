package com.epam.cdp.jmx.mbean;

import java.lang.management.ManagementFactory;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Cdp extends NotificationBroadcasterSupport implements CdpMBean {

	private String task;
	
	private int sequenceNumber = 0;

	@Override
	public void operatingSystemInfo() {
		System.out.println(ManagementFactory.getOperatingSystemMXBean().getName());
		
		Notification notification = new Notification("com.epam.cdp.jmx.notification", this, sequenceNumber++, "Writed system inforamation");
		sendNotification(notification);
	}

	@Override
	public void systemShutdown() {		
		System.exit(0);
	}

	@Override
	public void setTask(String task) {
		this.task = task;
		
		//Send notification
		Notification notification = new AttributeChangeNotification(this, sequenceNumber++, System.currentTimeMillis(), "Changed attribute", "current task", task, task, task);
		sendNotification(notification);
	}

	@Override
	public String getTask() {
		return task;
	}
	
	@Override
	public MBeanNotificationInfo[] getNotificationInfo() {
		String[] types = new String[]{
	            AttributeChangeNotification.ATTRIBUTE_CHANGE
	        };

	        String name = AttributeChangeNotification.class.getName();
	        String description = "Task attribute of this MBean has changed";
	        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
	        
	        return new MBeanNotificationInfo[]{info, new MBeanNotificationInfo(types, "com.peam.cdp.jmx.notify", "System shutdown")};
	}

}
