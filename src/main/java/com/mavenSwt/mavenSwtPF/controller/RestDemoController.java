package com.mavenSwt.mavenSwtPF.controller;

import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

/**
 * 为了演示各种接收参数的方法
 * 
 * @author Administrator
 *
 */
@RestController
public class RestDemoController {

	// HttpPostFile成功调用,swagger成功调用(String[]也有问题,如果参数是["xx,xx"],竟然变成了长度2的数组,似乎是按逗号分隔了)
	@PostMapping("/UploadJarAndArrStr")
	public AbstractApiResult<?> UploadJarAndArrStr(@RequestParam("file") MultipartFile file, String[] eurekaInstance) {
		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
	}

//HttpPostFile成功调用,swagger成功调用
	@PostMapping("/UploadJarAndArrStrP")
	public AbstractApiResult<?> UploadJarAndArrStrP(@RequestParam("file") MultipartFile file,
			@RequestParam("eurekaInstance") String[] eurekaInstance) {
		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
	}

//HttpPostFile 和 swagger 均报错：org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException: Failed to convert value of type 'java.lang.String' to required type 'com.mavenSwt.mavenSwtPF.model.UploadJarRestModel'
	@PostMapping("/UploadJarAndModelP")
	public AbstractApiResult<?> UploadJarAndModelP(@RequestParam("file") MultipartFile file,
			@RequestParam("eurekaInstance") UploadJarRestModel eurekaInstance) {
		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
	}

	//swagger报错：Content type 'multipart/form-data;boundary=----WebKitFormBoundaryxMXnKxArnNDnjZAb;charset=UTF-8' not supported
	@PostMapping("/UploadJarAndModelB")
	public AbstractApiResult<?> UploadJarAndModelB(@RequestParam("file") MultipartFile file,
			@RequestBody UploadJarRestModel eurekaInstance) {
		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
	}
	
  @PostMapping("/UploadJarBAndModelB")
  public AbstractApiResult<?> UploadJarBAndModelB(@RequestBody @RequestParam("file") MultipartFile file,
  		@RequestBody UploadJarRestModel eurekaInstance
  		) {
		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
  }
  @PostMapping("/UploadJarAndStr")
  public AbstractApiResult<?> UploadJarAndStr(@RequestParam("file") MultipartFile file,
  		String eurekaInstance
  		) {
		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
  }
	//swagger成功调用
	@PostMapping("/UploadModelB")
	public AbstractApiResult<?> UploadModelB(@RequestBody UploadJarRestModel eurekaInstance// HttpPost:报错,eurekaInstance为required
	) {
		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
	}
	

//  @PostMapping("/UploadJar")
//  public AbstractApiResult<?> UploadJar(@RequestParam("file") MultipartFile file,
//  		@RequestJson UploadJarRestModel eurekaInstance
//  		) {
//		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
//  }
//  @PostMapping("/UploadJar")
//  public AbstractApiResult<?> UploadJar(@RequestParam("file") MultipartFile file,
//  		@RequestJson PFEurekaInstance2[] eurekaInstance
//  		) {
//		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
//  }

	@PostMapping("/AllTypeParam")
	public AbstractApiResult<?> AllTypeParam(
			@PathVariable("id") int id,
			@RequestParam(value = "spno", required = true) int spno,
			@RequestBody UploadJarRestModel eurekaInstance
	) {
		return AbstractApiResult.success(JSON.toJSONString(eurekaInstance));
	}
}
