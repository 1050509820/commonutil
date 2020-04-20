package com.basic.integrate.service;

import java.util.List;

import com.basic.common.integrate.entity.SysLog;
import com.github.pagehelper.PageInfo;

public interface SysLogService {
	
	 int addSysLog(String currUserId,String type,String remark);
	 
	 PageInfo<SysLog> selectSysLog(Integer pageNo, Integer pageSize,String username,String type,String startTime,String endTime,String currOfficeId);
	 
	 List<String> getLogType();
}
