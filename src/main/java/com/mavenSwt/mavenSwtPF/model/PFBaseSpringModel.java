package com.mavenSwt.mavenSwtPF.model;

import pf.java.pfHelper.PFEnumClass;
import pf.java.pfHelper.config.PFDataHelper;

public abstract class PFBaseSpringModel {
	protected String _serviceName;//Eureka的服务名(app)
	protected String _instanceName;//Eureka的实例名
	/*
	 * Eureka的服务名(app)
	 */
	public String getServiceName() {
		return _serviceName;
	}
	public void setServiceName(String serviceName) {
		this._serviceName = serviceName;
	}
	/*
	 * Eureka的实例名
	 */
	public String getInstanceName() {
		return _instanceName;
	}
	public void setInstanceName(String instanceName) {
		this._instanceName = instanceName;
	}
	public Boolean isEurekaClient() {
		return !PFDataHelper.StringIsNullOrWhiteSpace(_serviceName);
	}
	
	@Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof PFBaseSpringModel) {
        	return equalsT((PFBaseSpringModel)anObject);
//        	PFBaseSpringModel anotherString = (PFBaseSpringModel)anObject;
//            return this.getServiceName()==anotherString.getServiceName()&&
//            		this.getInstanceName()==anotherString.getInstanceName();
        }
        return false;
    }
    public <T extends PFBaseSpringModel>  boolean equalsT(T anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof PFBaseSpringModel) {
        	PFBaseSpringModel anotherString = (PFBaseSpringModel)anObject;
            return this.getServiceName()==anotherString.getServiceName()&&
            		this.getInstanceName()==anotherString.getInstanceName();
        }
        return false;
    }
}
