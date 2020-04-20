package com.basic.integrate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.basic.common.integrate.entity.SysLog;

public interface SysLogDao {
	
	 int addSysLog(SysLog sysLog);
	 
	 List<String> getLogType();
	 
	 List<SysLog> selectSysLog(@Param("username")String username,@Param("type")String type,
			 @Param("startTime")String startTime,@Param("endTime")String endTime,
			 @Param("officeIdList") List<String> officeIdList
			 );
}
