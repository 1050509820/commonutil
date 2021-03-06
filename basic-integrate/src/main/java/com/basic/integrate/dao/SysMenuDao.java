package com.basic.integrate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.basic.common.integrate.entity.SysMenu;

public interface SysMenuDao {

    int addMenu(SysMenu sysMenu);

    int deletedMenu(@Param("id") String id, @Param("currUserId") String currUserId);

    List<SysMenu> getMaxParentMenu();

    List<SysMenu> selectChildById(@Param("id") String id, @Param("name") String name, @Param("isShow") String isShow);

    List<SysMenu> getMenuChild(@Param("id") String id);

    int updateSysMenuInfo(SysMenu sysMenu);

    SysMenu getSysMenuInfo(@Param("id") String id, @Param("name") String name);

    List<String> getHiddenMenu();

    SysMenu checkPath(@Param("menuIdList") List<String> menuIdList, @Param("path") String path);
    
    //导出查询接口
    List<SysMenu> selectBySysMenu(SysMenu sysMenu);
    
    List<SysMenu> selectMenusByUid(String userid);

}
