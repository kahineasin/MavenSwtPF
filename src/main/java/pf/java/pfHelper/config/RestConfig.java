//package pf.java.pfHelper.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.TypeReference;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.type.TypeFactory;
//import com.fasterxml.jackson.databind.util.Converter;
//import com.mavenSwt.mavenSwtPF.model.PFEurekaInstance2;
//
//@Configuration
//public class RestConfig {
//    @Bean
//    public Converter<String, List<?>> LocalDateConvert() {
//        return new Converter<String, List<?>>() {
//
//			@Override
//			public List<?> convert(String value) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public JavaType getInputType(TypeFactory typeFactory) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public JavaType getOutputType(TypeFactory typeFactory) {
//				// TODO Auto-generated method stub
//				return null;
//			}
// 
//        };
//    }
//    @Bean
//    public Converter<String, ArrayList<PFEurekaInstance2>> EurekaListConvert() {
//        return new Converter<String, ArrayList<PFEurekaInstance2>>() {
//
//			@Override
//			public ArrayList<PFEurekaInstance2> convert(String value) {
//				// TODO Auto-generated method stub
//				//return JSON.parseObject(value, ArrayList<PFEurekaInstance2>);
//				//return JSON.parseObject(value, new TypeReference<ArrayList<PFEurekaInstance2>>(){});
//				ArrayList<PFEurekaInstance2> r=new ArrayList<PFEurekaInstance2>();
//				r.add(new PFEurekaInstance2("CONFIG-SERVER", "config-server1"));
//				return r;
//			}
//
//			@Override
//			public JavaType getInputType(TypeFactory typeFactory) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public JavaType getOutputType(TypeFactory typeFactory) {
//				// TODO Auto-generated method stub
//				return null;
//			}
// 
//        };
//    }
//}
