package com.chao.job;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

public class JobTest {
	
	

	@Test
	public void addJob() {
		
		Map<String,Object> formMap = MapUtil.builder(new HashMap<String,Object>())
			.put("jobGroup", 1)
			.put("jobDesc", "测试任务2")
			.put("executorRouteStrategy", "FIRST")
			.put("jobCron", "0 0 0 * * ? *")
			.put("glueType", "BEAN")
			.put("executorHandler", "demoJobHandler")
			.put("executorBlockStrategy", "SERIAL_EXECUTION")
			//.put("childJobId", 1)
			.put("executorTimeout", 0)
			.put("executorFailRetryCount", 0)
			.put("author", "ck")
			.put("alarmEmail", "")
			.put("executorParam", "{\"username\":\"king\",\"password\":\"123456\"}")
			.put("glueRemark", "GLUE代码初始化")
			.put("glueSource", "")
			.build();
		
		HttpResponse httpResponse = HttpRequest.post("http://127.0.0.1:8080/xxl-job-admin/jobinfo/add")
			.contentType("application/x-www-form-urlencoded; charset=UTF-8")
			.header("cache-control", "no-cache")
			.cookie("XXL_JOB_LOGIN_IDENTITY=6333303830376536353837616465323835626137616465396638383162336437")
			.form(formMap)
			.execute();
		
		Console.log(httpResponse.getStatus());
		Console.log(httpResponse.isOk());
		Console.log(httpResponse.body());
	}
	
	@Test
	public void addJob2() {
		
		Map<String,Object> formMap = MapUtil.builder(new HashMap<String,Object>())
				.put("jobGroup", 1)
				.put("jobDesc", "测试任务2")
				.put("executorRouteStrategy", "FIRST")
				.put("jobCron", "0 0 0 * * ? *")
				.put("glueType", "BEAN")
				.put("executorHandler", "demoJobHandler")
				.put("executorBlockStrategy", "SERIAL_EXECUTION")
				.put("childJobId", 1)
				.put("executorTimeout", 0)
				.put("executorFailRetryCount", 0)
				.put("author", "ck")
				.put("alarmEmail", "")
				.put("executorParam", "{\"username\":\"king\",\"password\":\"123456\"}")
				.put("glueRemark", "GLUE代码初始化")
				.put("glueSource", "")
				.build();
		String res = HttpUtil.post("http://192.168.0.155:8080/xxl-job-admin/jobinfo/add", formMap);
		Console.log(res);
	}
	
	@Test
	public void test3() {
		String url = "http://192.168.0.206/spad-login/common/userLogin";
		Map<String,Object> paramMap = MapUtil.builder(new HashMap<String,Object>()).put("username", "king").put("password", "123456").build();
		String body = JSONUtil.toJsonStr(paramMap);
//		String res = HttpUtil.post(url, body);
//		Console.log(res);
		
		HttpResponse httpResponse = HttpRequest.post(url)
			.contentType("application/json")
			.body(body)
			.execute();
		
		Console.log(httpResponse.body());
		Map<String,Object> bodyMap = JSONUtil.toBean(httpResponse.body(), new TypeReference<Map<String,Object>>() {}, true);
		Console.log(bodyMap);
	}
	
	private static final String LOCAL_URL = "http://127.0.0.1:8080/xxl-job-admin/jobinfo/pageList";
	
	private static final String DEV_URL = "http://116.62.195.201:8080/xxl-job-admin/jobinfo/pageList";
	
	String cookie = "";
	@Test
	public void test4() {
		
		Map<String,Object> paramMap = MapUtil.builder(new HashMap<String,Object>()).put("jobGroup", 2).put("start", 0).put("length", 10).build();
		//String body = JSONUtil.toJsonStr(paramMap);
//		String res = HttpUtil.post(url, body);
//		Console.log(res);
		if(StrUtil.isNotBlank(cookie)) {
			cookie = toLogin();
		}
		HttpResponse httpResponse = HttpRequest.post(DEV_URL)
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.cookie(cookie)
				.form(paramMap)
				.execute();
		if(httpResponse.getStatus() == 302) {
			cookie = toLogin();
			test4();
		}
		Console.log(httpResponse);
		Map<String,Object> bodyMap = JSONUtil.toBean(httpResponse.body(), new TypeReference<Map<String,Object>>() {}, true);
		Console.log(bodyMap);
	}
	
	
	
	public String toLogin() {
		Map<String,Object> paramMap = MapUtil.builder(new HashMap<String,Object>()).put("userName", "admin").put("password", "123456").build();
		
		HttpResponse httpResponse = HttpRequest.post("http://127.0.0.1:8080/xxl-job-admin/login")
				.form(paramMap)
				.execute();
		if(httpResponse.getStatus() == 200) {
			Console.log(httpResponse.getCookieStr());
			return httpResponse.getCookieStr();
		}
		return null;
	}
	
}

