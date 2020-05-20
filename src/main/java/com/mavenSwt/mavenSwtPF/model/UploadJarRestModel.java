package com.mavenSwt.mavenSwtPF.model;

import java.util.List;

//public class UploadJarRestModel {
//	private PFEurekaInstance2[] eurekaInstance;
//
//	public PFEurekaInstance2[] getEurekaInstance() {
//		return eurekaInstance;
//	}
//
//	public void setEurekaInstance(PFEurekaInstance2[] eurekaInstance) {
//		this.eurekaInstance = eurekaInstance;
//	}
//}

public class UploadJarRestModel {
	private List<PFEurekaInstance2> eurekaInstance;

	public List<PFEurekaInstance2> getEurekaInstance() {
		return eurekaInstance;
	}

	public void setEurekaInstance(List<PFEurekaInstance2> eurekaInstance) {
		this.eurekaInstance = eurekaInstance;
	}
}
