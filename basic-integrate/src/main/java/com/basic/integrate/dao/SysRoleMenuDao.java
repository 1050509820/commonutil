package com.basic.integrate.dao;

import java.util.List;

import com.basic.common.integrate.entity.SysRoleMenu;

public interface SysRoleMenuDao {

    void addRoleMenu(SysRoleMenu sysRoleMenu);

    void deletedByMenuId(String menuId);

    int deletedByRoleId(String roleId);

    void deletedByRoleMenu(SysRoleMenu sysRoleMenu);

    List<String> selectMenuIdByRoleId(String roleId);

    SysRoleMenu selectByRoleMenu(SysRoleMenu sysRoleMenu);

    void updateRoleMenuByRoleId(SysRoleMenu sysRoleMenu);


}
