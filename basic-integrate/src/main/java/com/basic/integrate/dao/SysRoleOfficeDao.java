package com.basic.integrate.dao;

import java.util.List;

import com.basic.common.integrate.entity.SysRoleOffice;

public interface SysRoleOfficeDao {

    void addRoleOffice(SysRoleOffice sysRoleOffice);

    void deletedByRoleId(String roleId);

    void deletedByOfficeId(String officeId);

    List<String> selectRoleIdByOfficeId(String officeId);

    void updateRoleOfficeByRoleId(SysRoleOffice sysRoleOffice);
}
