package com.basic.integrate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.integrate.dao.SysOfficeDao;
import com.basic.common.integrate.entity.SysOffice;
import com.basic.integrate.service.SysOfficeService;

@Service
public class SysOfficeServiceImpl implements SysOfficeService {

	@Autowired
	private SysOfficeDao sysOfficeDao;

	@Override
	public List<SysOffice> selectByOffice(SysOffice sysOffice) {
		return sysOfficeDao.selectByOffice(sysOffice);
	}

	@Override
	public SysOffice selectByNameImport(String name) {
		return sysOfficeDao.selectByNameImport(name);
	}

	@Override
	public SysOffice selectById(String id) {
		return sysOfficeDao.selectById(id);
	}

	@Override
	public List<SysOffice> selectShouBuByOfficeId(String officeId) {
		return sysOfficeDao.selectShouBuByOfficeId(officeId);
	}

	@Override
	public SysOffice selectByOfficeIdImport(String officeParentId, String name) {
		return sysOfficeDao.selectByOfficeIdImport(officeParentId, name);
	}

}
