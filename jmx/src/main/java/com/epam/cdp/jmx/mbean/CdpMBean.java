package com.epam.cdp.jmx.mbean;

public interface CdpMBean {

	public void operatingSystemInfo();

	public void setTask(String task);

	public String getTask();

	public void systemShutdown();

}
