package com.basic.integrate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.basic.common.integrate.entity.SysOffice;

public interface SysOfficeDao {


    int addOffice(SysOffice sysOffice);

    int deletedOffice(@Param("id") String id, @Param("currUserId") String currUserId);

    SysOffice selectById(String id);

    List<String> selectAllChildById(String id);

    List<SysOffice> selectAllChildInfoById(String id);

    List<SysOffice> selectChildById(String officeId);
    
    SysOffice getOfficeInfoById(String id);

    List<SysOffice> getMaxParentOffice();

    int updateSysOfficeInfo(SysOffice sysOffice);
    //导出接口
    List<SysOffice> selectByOffice(SysOffice sysOffice);

    List<SysOffice> selectShouBuByOfficeId(String officeId);

    List<SysOffice> selectByCondition(@Param("officeId") String officeId, @Param("name") String name, @Param("type") String type);

	SysOffice selectByNameImport(@Param("name")String name);

	List<SysOffice> selectPianquList();

	List<SysOffice> selectShoubuList();
	
	SysOffice selectByOfficeIdImport(@Param("officeParentId")String officeParentId, @Param("name")String name);
	

}
