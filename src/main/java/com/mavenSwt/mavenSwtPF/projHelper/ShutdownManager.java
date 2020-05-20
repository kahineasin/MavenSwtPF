package com.mavenSwt.mavenSwtPF.projHelper;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

@Component 
public class ShutdownManager{

    private static ApplicationContext _appContext;

	  @Autowired
	  public ShutdownManager(ApplicationContext appContext) {
		  ShutdownManager._appContext = appContext;
	  }
    public static void initiateShutdown(int returnCode){
        SpringApplication.exit(_appContext, () -> returnCode);
    }
}