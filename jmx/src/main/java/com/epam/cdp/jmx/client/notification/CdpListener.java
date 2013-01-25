package com.epam.cdp.jmx.client.notification;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationListener;

public class CdpListener implements NotificationListener {

	@Override
	public void handleNotification(Notification notification, Object handback) {
		echo("Received new notification:");
		echo("ClassName: " + notification.getClass().getName());
		echo("Source: " + notification.getSource());
		echo("Type: " + notification.getType());
		echo("Message: " + notification.getMessage());
		if (notification instanceof AttributeChangeNotification) {
			AttributeChangeNotification acn = (AttributeChangeNotification) notification;
			echo("AttributeName: " + acn.getAttributeName());
			echo("AttributeType: " + acn.getAttributeType());
			echo("NewValue: " + acn.getNewValue());
			echo("OldValue: " + acn.getOldValue());
		}
	}

	private void echo(String message) {
		System.out.println(message);
	}
}
