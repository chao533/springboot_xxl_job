package com.kang.common.constans;

import cn.hutool.setting.Setting;

public class JobConstants {

	public static final String APPLICATION = "application.properties";

	public interface AppKey {
		String ADDRESSES = "xxl.job.admin.addresses";
		String USER = "admin";
		String PASSWORD = "123456";
	}

	public static String getProp(String file, String key) {
		Setting setting = new Setting(file);
		return setting.get(key);
	}
	
	public static final String JOB_LOGIN = getProp(JobConstants.APPLICATION, JobConstants.AppKey.ADDRESSES) + "/login";
	
	public static final String JOB_ADD = getProp(JobConstants.APPLICATION, JobConstants.AppKey.ADDRESSES) + "/jobinfo/add";
	
	public static final String JOB_PAGE_LIST = getProp(JobConstants.APPLICATION, JobConstants.AppKey.ADDRESSES) + "/jobinfo/pageList";

	public static final String JOB_START = getProp(JobConstants.APPLICATION, JobConstants.AppKey.ADDRESSES) + "/jobinfo/start";
	
	public static final String JOB_STOP = getProp(JobConstants.APPLICATION, JobConstants.AppKey.ADDRESSES) + "/jobinfo/stop";
	
	public static final String JOB_DELETE = getProp(JobConstants.APPLICATION, JobConstants.AppKey.ADDRESSES) + "/jobinfo/remove";
}
