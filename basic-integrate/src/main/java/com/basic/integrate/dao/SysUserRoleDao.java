package com.basic.integrate.dao;

import java.util.List;

import com.basic.common.integrate.entity.SysUserRole;

public interface SysUserRoleDao {


    void addUserRole(SysUserRole sysUserRole);

    void deletedByUserId(String userId);

    void deletedByRoleId(String roleId);

    String selectRoleIdByUserId(String userId);

    SysUserRole selectByUserRole(String userId);

    void updateRoleIdByUserId(SysUserRole sysUserRole);

    List<String> selectUserIdByRoleId(String roleId);

}
