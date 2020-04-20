package com.basic.integrate.service;

import java.util.List;

import com.basic.common.integrate.entity.SysOffice;

/**
 * 区域相关service
 * create by yr
 */
public interface SysOfficeService {


	
	List<SysOffice> selectByOffice(SysOffice sysOffice);

	SysOffice selectByNameImport(String name);
	
	SysOffice selectById(String id);

	List<SysOffice> selectShouBuByOfficeId(String officeId);

	SysOffice selectByOfficeIdImport(String officeParentId, String name);
}                               