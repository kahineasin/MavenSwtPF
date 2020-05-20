package com.mavenSwt.mavenSwtPF.model;

import org.dom4j.Element;

public class PFEurekaInstance2 extends Object{
	private String serviceName;// Eureka的服务名(app)
	private String instanceName;// Eureka的实例名
	public PFEurekaInstance2() {//用于controller参数时，要有无参构造
		super();
	}
	public PFEurekaInstance2(String serviceName, String instanceName) {
		super();
		this.serviceName = serviceName;
		this.instanceName = instanceName;
	}
//	@Override
//	public void init() {
//	
//	}
	/*
	 * Eureka的服务名(app)
	 */
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/*
	 * Eureka的实例名
	 */
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
}
