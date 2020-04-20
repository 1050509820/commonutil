package com.basic.integrate.service;

import java.util.Map;

import com.basic.common.integrate.entity.SysDict;

/**
 * 操作日志相关service
 * create by yr
 */
public interface SysDictService {
	/**
	 * 根据类型获取字典
	 * @param type
	 * @return
	 */
	Map<String, String> getMap(String type);
	
	SysDict getEquInfoExport(String value);
    
    SysDict getWarnInfoExport(String value);
    
    SysDict getTaskInfoExport(String value);
    
    SysDict getSysOfficeExport(String value);
    
    SysDict getSfExport(String value);
    
    SysDict getLlExport(String value);
    
    SysDict getYlExport(String value);
}                               