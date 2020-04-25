package com.kang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kang.common.constans.JobConstants;
import com.kang.model.XxlJobInfo;
import com.kang.service.JobService;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;

public class JobServiceImpl implements JobService{

	@Override
	public Boolean add(XxlJobInfo jobInfo) {

		Map<String,Object> formMap = MapUtil.builder(new HashMap<String,Object>())
			.put("jobGroup", 1)
			.put("jobDesc", "测试任务2")
			.put("executorRouteStrategy", "FIRST")
			.put("jobCron", jobInfo.getJobCron())
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
		
		HttpResponse httpResponse = HttpRequest.post(JobConstants.JOB_ADD)
			.contentType("application/x-www-form-urlencoded; charset=UTF-8")
			.header("cache-control", "no-cache")
			.cookie(toLogin())
			.form(formMap)
			.execute();
		return httpResponse.isOk();
	}

	@Override
	public List<XxlJobInfo> pageList(XxlJobInfo jobInfo) {
		Map<String,Object> paramMap = Dict.create().set("jobGroup", 1).set("start", 0).set("length", 10);
		HttpResponse httpResponse = HttpRequest.post(JobConstants.JOB_PAGE_LIST)
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.cookie(toLogin())
				.form(paramMap)
				.execute();
		Map<String,Object> bodyMap = JSONUtil.toBean(httpResponse.body(), new TypeReference<Map<String,Object>>() {}, true);
		return MapUtil.get(bodyMap, "data", new TypeReference<List<XxlJobInfo>>() {});
	}
	
	@Override
	public Boolean start(XxlJobInfo jobInfo) {
		Map<String,Object> paramMap = Dict.create().set("id", jobInfo.getId());
		HttpResponse httpResponse = HttpRequest.post(JobConstants.JOB_START)
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.cookie(toLogin())
				.form(paramMap)
				.execute();
		return httpResponse.isOk();
	}
	
	@Override
	public Boolean stop(XxlJobInfo jobInfo) {
		Map<String,Object> paramMap = Dict.create().set("id", jobInfo.getId());
		HttpResponse httpResponse = HttpRequest.post(JobConstants.JOB_STOP)
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.cookie(toLogin())
				.form(paramMap)
				.execute();
		return httpResponse.isOk();
	}
	
	@Override
	public Boolean delete(XxlJobInfo jobInfo) {
		Map<String,Object> paramMap = Dict.create().set("id", jobInfo.getId());
		HttpResponse httpResponse = HttpRequest.post(JobConstants.JOB_DELETE)
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.cookie(toLogin())
				.form(paramMap)
				.execute();
		return httpResponse.isOk();
	}
	
	@Override
	public String toLogin() {
		Map<String,Object> paramMap = Dict.create().set("userName", JobConstants.AppKey.USER).set("password", JobConstants.AppKey.PASSWORD);
		HttpResponse httpResponse = HttpRequest.post(JobConstants.JOB_LOGIN)
				.form(paramMap)
				.execute();
		if(httpResponse.getStatus() == 200) {
			return httpResponse.getCookieStr();
		}
		return null;
	}

	

}
