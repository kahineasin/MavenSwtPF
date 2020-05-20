package com.mavenSwt.mavenSwtPF.projHelper;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.mavenSwt.mavenSwtPF.form.MavenSwtPFAppForm;

import pf.java.pfHelper.PFRequestResult;
import pf.java.pfHelper.config.PFDataHelper;

public class ProjHelper {
	public static Shell _shell;
	public static Display _display;
    public static MavenSwtPFAppForm _mainForm;
	public static PFRequestResult EurekaServiceOut(String serviceName,String instanceName) {
		PFRequestResult r=null;
		try {
			r= PFDataHelper.HttpPut(String.format(
			"http://localhost:8761/eureka/apps/%s/%s/status?value=OUT_OF_SERVICE",
			serviceName,instanceName),"");
		} catch (Exception e) {
			//// TODO Auto-generated catch block
			//e.printStackTrace();
			r= new PFRequestResult();
			r.error=e.getMessage();
		} 
		return r;
	}
	public static PFRequestResult EurekaServiceDelete(String serviceName,String instanceName) {
		PFRequestResult r=null;
		try {
			r= PFDataHelper.HttpDelete(String.format(
			"http://localhost:8761/eureka/apps/%s/%s",
			serviceName,instanceName),"");
		} catch (Exception e) {
			//// TODO Auto-generated catch block
			//e.printStackTrace();
			r= new PFRequestResult();
			r.error=e.getMessage();
		} 
		return r;
	}
}
