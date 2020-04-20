package com.basic.integrate.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.basic.common.integrate.entity.SysUser;

public interface SysUserDao {

    String getCurrOfficeId(String currUserId);

    SysUser findUserByLoginName(String loginName);

    SysUser selectById(String id);
    
    SysUser selectByuserNo(String userNo);
    
    SysUser selectByuserIdCard(String idcard);

    void changeLoginDate(String id);

    int addUser(SysUser sysUser);

    int deletedUser(@Param("id") String id, @Param("currUserId") String currUserId);

    List<SysUser> getUserListByOfficeId(@Param("officeIds") List<String> officeIds, @Param("loginName") String loginName);

    List<SysUser> getUserListByName(@Param("officeIds") List<String> officeIds, @Param("name") String name);
    
    int changePwd(Map<?, ?> map);
    
    SysUser findInfoById(String id);

    int updateSysUserInfo(SysUser sysUser);
    
    List<SysUser> getListSysUserInfo(SysUser sysUser);

    void updateOfficeId(@Param("officeId") String officeId, @Param("currUserId") String currUserId);

	SysUser selectByNameImport(String name);

	int insertUserImport(SysUser sysUser);
}
