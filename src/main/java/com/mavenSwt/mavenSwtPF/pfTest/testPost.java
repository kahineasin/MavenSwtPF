package com.mavenSwt.mavenSwtPF.pfTest;

import pf.java.pfHelper.PFRequestResult;
import pf.java.pfHelper.config.PFDataHelper;

public class testPost {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) throws Exception{
        try {
        	PFRequestResult r=new PFRequestResult();
			/*s=PFDataHelper.HttpPut(String.format("http://localhost:8761/eureka/apps/%s/%s/status?value=OUT_OF_SERVICE",
					"SERVICE-HI","spring-cloud-front1"
					),"");
					
			s=PFDataHelper.HttpDelete(String.format("http://localhost:8761/eureka/apps/%s/%s",
					"SERVICE-HI","spring-cloud-front1"
					),"");*/
        	r=PFDataHelper.HttpPut("http://localhost:48080/demo/hi3?name=1323", "");
			System.out.println("ben:\r\n"+r.statusCode+"\r\ncontent:\r\n"+r.content+"\r\nerror:\r\n"+r.error);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
