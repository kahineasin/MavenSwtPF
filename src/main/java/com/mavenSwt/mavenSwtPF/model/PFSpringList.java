package com.mavenSwt.mavenSwtPF.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ho.yaml.Yaml;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pf.java.pfHelper.config.PFDataHelper;

public class PFSpringList {
	private List<PFSpringModel> list;
	public PFSpringList() {}
	public PFSpringList(Resource resource) {

		try {
//			//如果yml不打包
//			//String yPath="/src/main/resources/application.yml";
//			//String yPath2="/src/main/resources/testYaml.yaml";
//			String yPath2="/src/main/resources/springList.yaml";
//	        File dumpFile=new File(System.getProperty("user.dir") + yPath2);
//	        //_pfSpringList = (PFSpringList) Yaml.loadType(dumpFile, PFSpringList.class);
//	        _pfSpringList = (PFSpringList) Yaml.loadType(dumpFile);
	        
//			//yml打包
			BufferedReader br;
			br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
	        //_pfSpringList = yaml.loadType(br, com.mavenSwt.mavenSwtPF.model.PFSpringList.class);
			//注意：以上Yaml.loadType方法在Spring boot+swt环境下会无法转换，网上解析说用了不同的ClassLoader,暂时的解决办法是：
	        Object pfSpringList = Yaml.load(br);
	        PFSpringList _pfSpringList=(PFSpringList)PFDataHelper.map2Object((Map<String, Object>)pfSpringList,PFSpringList.class);

	        ObjectMapper mapper = new ObjectMapper();
	        setList(mapper.convertValue(_pfSpringList.getList(), new TypeReference<ArrayList<PFSpringModel>>() { }));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public PFSpringList(String ymlConfigPath) {

		try {
			//PFDataHelper.WriteLog(ymlConfigPath);
//			//如果yml不打包
File dumpFile=new File(ymlConfigPath);
//PFSpringList _pfSpringList =Yaml.loadType(dumpFile, PFSpringList.class);
Object pfSpringList =Yaml.load(dumpFile);

PFSpringList _pfSpringList=(PFSpringList)PFDataHelper.map2Object((Map<String, Object>)pfSpringList,PFSpringList.class);

	        ObjectMapper mapper = new ObjectMapper();
	        setList(mapper.convertValue(_pfSpringList.getList(), new TypeReference<ArrayList<PFSpringModel>>() { }));
		} catch (IOException e) {
			//PFDataHelper.WriteError(e);
			e.printStackTrace();
		}
	}

	public List<PFSpringModel> getList() {
		return list;
	}

	public void setList(List<PFSpringModel> list) {
		this.list = list;
	}
	
	
}
