package com.mavenSwt.mavenSwtPF.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
//import com.fasterxml.jackson.core.type.TypeReference;
import com.mavenSwt.mavenSwtPF.form.MavenSwtPFAppForm;
import com.mavenSwt.mavenSwtPF.model.PFEurekaInstance;
import com.mavenSwt.mavenSwtPF.model.PFEurekaInstance2;
import com.mavenSwt.mavenSwtPF.model.UploadJarRestModel;

import pf.java.pfHelper.AbstractApiResult;
import pf.java.pfHelper.RequestJson;

@RestController
public class HelloworldController {

//	@GetMapping("/")
//	public AbstractApiResult<?> index() {
//		return AbstractApiResult.success();
//	}
	@GetMapping("/")
	String index() {
		String str = "<b style=\"color:blue\">Hello Spring Boot,包含的方法有:</b><br />";
		str += "<a style=\"color:orange\" href=\"/swagger-ui.html\">swagger Api文档</a><br />";
		// List<Method[]> mdsList=new ArrayList<Method[]>();
		Map<String, Method[]> mdsList = new HashMap<String, Method[]>();
		mdsList.put("home", HelloworldController.class.getDeclaredMethods());

		Iterator<String> iter = mdsList.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			Method[] value = mdsList.get(key);

			for (Method method : value) {
				String p = method.getName().toLowerCase();
				if (p.equals("main") || p.equals("index") || p.equals("addviewcontrollers")) {
					continue;
				}
				str += String.format("<a href=\"/%s/%s\">%s</a>", key, p, method.getName()) + "<span>,</span><br />";
			}
		}

		return str;
	}
//    @PostMapping("/UploadJar")
//    public AbstractApiResult<?> UploadJar(
////    		@RequestBody PFEurekaInstance2[] eurekaInstance
//    		//String[] eurekaInstance//HttpPost:不报错，但接收为空
//    	//@RequestParam String[] eurekaInstance//HttpPost:报错,eurekaInstance为required
//    	@RequestBody String[] eurekaInstance//HttpPost:报错,eurekaInstance为required
//    		) {
//		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
//    }
    
    @PostMapping("/UploadEurekaArray")
    public AbstractApiResult<?> UploadEurekaArray(
    		PFEurekaInstance2[] eurekaInstance//HttpPost:报错,eurekaInstance为required
    		) {
		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
    }
    @PostMapping("/UploadEurekaArray2")
    public AbstractApiResult<?> UploadEurekaArray2(
    		PFEurekaInstance2[] eurekaInstance//HttpPost:报错,eurekaInstance为required
    		) {
		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
    }

    @PostMapping("/UploadEurekaList")
    public AbstractApiResult<?> UploadEurekaList(
    		List<PFEurekaInstance2> eurekaInstance//HttpPost:报错,eurekaInstance为required
    		) {
		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
    }

    
    //HttpPostFile成功调用,swagger成功调用
    @PostMapping("/UploadJar")
    public AbstractApiResult<?> UploadJar(@RequestParam("file") MultipartFile file,
    		String eurekaInstance//PFEurekaInstance[]类型就是接收不了，多次尝试无效(String[]也有问题,如果参数是["xx,xx"],竟然变成了长度2的数组,似乎是按逗号分隔了)
    		) {
    	java.util.List<PFEurekaInstance> list=JSONObject.parseObject(eurekaInstance, new TypeReference<java.util.List<PFEurekaInstance>>(){}) ;

//    	java.util.List<PFEurekaInstance> list=new java.util.ArrayList<PFEurekaInstance>();
//    	for(int i=0;i<eurekaInstance.length;i++) {
//    		String[] sp=eurekaInstance[i].split(",");
//    		list.add(new PFEurekaInstance(sp[0],sp[1],"",""));
//    	}
    	MavenSwtPFAppForm.UpdateAndRebootSpringJars(file,list);
		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
    }

    
    
	
	//报错：nested exception is java.lang.IllegalStateException: Cannot convert value of type 'java.lang.String' to required type 'com.mavenSwt.mavenSwtPF.model.PFEurekaInstance2': no matching editors or conversion strategy found
//    @PostMapping("/UploadJar")
//    public AbstractApiResult<?> UploadJar(
//    		@RequestParam(value = "eurekaInstance") PFEurekaInstance2[] eurekaInstance
//    		) {
//		return AbstractApiResult.success();
//    }
//    @PostMapping("/UploadJar")
//    public AbstractApiResult<?> UploadJar(
//    		@RequestBody UploadJarRestModel eurekaInstanceModel
//    		) {
//		return AbstractApiResult.success();
//    }
    
//	//报错：'multipart/form-data;boundary=pEp1njOjEYvA5igMRriJ8UT8AeBvkA82;charset=UTF-8' not supported","trace":"org.springframework.web.HttpMediaTypeNotSupportedException
//    @PostMapping("/UploadJar")
//    public AbstractApiResult<?> UploadJar(
//    		 PFEurekaInstance2[] eurekaInstance
//    		) {
//		return AbstractApiResult.success();
//    }
    
//	//报错：'multipart/form-data;boundary=pEp1njOjEYvA5igMRriJ8UT8AeBvkA82;charset=UTF-8' not supported","trace":"org.springframework.web.HttpMediaTypeNotSupportedException
//    @PostMapping("/UploadJar")
//    public AbstractApiResult<?> UploadJar(
//    		@RequestBody PFEurekaInstance2[] eurekaInstance
//    		) {
//		return AbstractApiResult.success();
//    }
    
//    //HttpPostFile参接收到
//    @PostMapping("/UploadJar")
//    public AbstractApiResult<?> UploadJar(
//    		String[] eurekaInstance 
//    		) {
//		return AbstractApiResult.success();
//    }


    @GetMapping("/StopSpring")
    public AbstractApiResult<?> StopSpring(
    		) {   
    	MavenSwtPFAppForm.StopSpring("CONFIG-SERVER", "config-server1");
		return AbstractApiResult.success();
    }
    @GetMapping("/RunSpring")
    public AbstractApiResult<?> RunSpring(
    		) {   
    	MavenSwtPFAppForm.RunSpring("CONFIG-SERVER", "config-server1");
		return AbstractApiResult.success();
    }
}
