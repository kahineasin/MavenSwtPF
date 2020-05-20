package com.mavenSwt.mavenSwtPF.model;

import org.dom4j.Element;

public class PFEurekaInstance extends PFBaseSpringModel {
//	private String _serviceName;// Eureka的服务名(app)
//	private String _instanceName;// Eureka的实例名
	private String _ipAddr;// 格式如192.168.205.111
	private String _port;//端口号 格式如 8888
	public PFEurekaInstance() {//用于controller参数时，要有无参构造
		
	}
	public PFEurekaInstance(String serviceName, String instanceName, String ipAddr, String port) {
		super();
		this._serviceName = serviceName;
		this._instanceName = instanceName;
		this._ipAddr = ipAddr;
		_port = port;
	}

	public PFEurekaInstance(Element element) {
		this(element.elementText("app"), element.elementText("instanceId"), 
				element.elementText("ipAddr"),element.elementText("port"));
	}
//	public String getServiceName() {
//		return _serviceName;
//	}
//
//	public void setServiceName(String serviceName) {
//		this._serviceName = serviceName;
//	}
//
//	public String getInstanceName() {
//		return _instanceName;
//	}
//
//	public void setInstanceName(String _instanceName) {
//		this._instanceName = _instanceName;
//	}

	public String getIpAddr() {
		return _ipAddr;
	}

	public void setIpAddr(String _ipAddr) {
		this._ipAddr = _ipAddr;
	}
	public String getPort() {
		return _port;
	}
}
