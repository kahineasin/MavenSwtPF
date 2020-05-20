package com.mavenSwt.mavenSwtPF.model;

//import pf.java.pfHelper.config.PFDataHelper;

public class PFSpringModel extends PFBaseSpringModel {
	private String springName;//只用于Tab的标题
	private String jarPath;
	private String javaCmd;
//	private String serviceName;//Eureka的服务名(app)
//	private String instanceName;//Eureka的实例名
	/**
	 * 只用于Tab的标题
	 * @return the springName
	 */
	public String getSpringName() {
		return springName;
	}
	/**
	 * @param springName the springName to set
	 */
	public void setSpringName(String springName) {
		this.springName = springName;
	}
	public String getJarPath() {
		return jarPath;
	}
	public void setJarPath(String jarPath) {
		this.jarPath = jarPath;
	}
	public String getJavaCmd() {
		return javaCmd;
	}
	public void setJavaCmd(String javaCmd) {
		this.javaCmd = javaCmd;
	}
//	public String getServiceName() {
//		return serviceName;
//	}
//	public void setServiceName(String serviceName) {
//		this.serviceName = serviceName;
//	}
//	public String getInstanceName() {
//		return instanceName;
//	}
//	public void setInstanceName(String instanceName) {
//		this.instanceName = instanceName;
//	}
//	public Boolean isEurekaClient() {
//		return !PFDataHelper.StringIsNullOrWhiteSpace(serviceName);
//	}
}
