package com.basic.integrate.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.basic.common.equipment.data.entity.RabbitQueue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.basic.common.integrate.entity.SysLog;
import com.basic.common.integrate.entity.SysOffice;
import com.basic.integrate.dao.SysLogDao;
import com.basic.integrate.dao.SysOfficeDao;
import com.basic.common.integrate.entity.SysUser;
import com.basic.integrate.service.SysLogService;
import com.basic.integrate.service.SysService;
import com.basic.integrate.util.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class SysLogServiceImpl implements SysLogService{

	@Autowired
	private SysLogDao systemLogDao;

    @Autowired
    private SysOfficeDao sysOfficeDao;
    
	@Autowired
    private SysService sysService;
	
	@Override
	public int addSysLog(String currUserId,String type,String remark) {
		SysUser sysUser = sysService.findInfoById(currUserId);
		SysLog sysLog= new SysLog();
		sysLog.setCurrUserNo(sysUser.getNo());
		sysLog.setOfficeId(sysUser.getOfficeId());
		sysLog.setType(type);
		sysLog.setRemark(remark);
		sysLog.setUsername(sysUser.getLoginName());
        sysLog.setId(IDUtils.createUUID());
        sysLog.setCreateTime(new Date());
		systemLogDao.addSysLog(sysLog);
		return 0;
	}

	@Override
	public PageInfo<SysLog> selectSysLog(Integer pageNo, Integer pageSize,String username,String type,String startTime,String endTime,String currOfficeId) {
       
		SysOffice currOffice = sysOfficeDao.selectById(currOfficeId);
        List<SysOffice> officeList = sysOfficeDao.selectAllChildInfoById(currOfficeId);
        officeList.add(currOffice);
        List<String> officeIdList = new ArrayList<>();
        for (SysOffice office : officeList) {
        	if(office!=null) {
        		officeIdList.add(office.getId());
        	}          
        }
        PageHelper.startPage(pageNo, pageSize);
		List<SysLog> sysLogs = systemLogDao.selectSysLog(username,type, startTime, endTime,officeIdList);
		PageInfo<SysLog> page = new PageInfo<>(sysLogs);
		return page;
	}
	
	@Async
	@RabbitListener(queues = RabbitQueue.systemLog)
	public void messageArrived(SysLog sysLog) {
		SysUser sysUser = sysService.findInfoById(sysLog.getCurrUserId());
		sysLog.setCurrUserNo(sysUser.getNo());
		sysLog.setOfficeId(sysUser.getOfficeId());
		sysLog.setUsername(sysUser.getLoginName());
		systemLogDao.addSysLog(sysLog);
	}

	@Override
	public List<String> getLogType() {
		return systemLogDao.getLogType();
	}
}
