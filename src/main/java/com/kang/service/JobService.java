package com.kang.service;

import java.util.List;

import com.kang.model.XxlJobInfo;

/**
 * <p>Title: JobService</p>
 * <p>Description: xxl-job<p>
 * @author ChaoKang
 * @date 2020年4月25日
 */
public interface JobService {

	/**
	 * <p>Title: add</p>
	 * <p>Description: 添加任务</p>
	 * @param @param jobInfo
	 */
	Boolean add(XxlJobInfo jobInfo);
	
	/**
	 * <p>Title: pageList</p>
	 * <p>Description: 查询所有任务</p>
	 * @param @param jobInfo
	 * @param @return
	 */
	List<XxlJobInfo> pageList(XxlJobInfo jobInfo);
	
	/**
	 * <p>Title: start</p>
	 * <p>Description: 启动任务</p>
	 * @param @param jobInfo
	 * @param @return
	 */
	Boolean start(XxlJobInfo jobInfo);
	
	/**
	 * <p>Title: stop</p>
	 * <p>Description: 关闭任务</p>
	 * @param @param jobInfo
	 * @param @return
	 */
	Boolean stop(XxlJobInfo jobInfo);
	
	/**
	 * <p>Title: delete</p>
	 * <p>Description: 删除任务</p>
	 * @param @param jobInfo
	 * @param @return
	 */
	Boolean delete(XxlJobInfo jobInfo);
	
	/**
	 * <p>Title: toLogin</p>
	 * <p>Description: 登录返回Cookie</p>
	 * @param @return
	 */
	String toLogin();
}
