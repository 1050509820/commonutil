package com.basic.integrate.service;

import java.util.List;
import java.util.Map;

import com.basic.integrate.dto.SysAreaDTO;
import com.basic.common.equipment.entity.Result;
import com.basic.common.integrate.entity.MenuRoute;
import com.basic.common.integrate.entity.SysDict;
import com.basic.common.integrate.entity.SysMenu;
import com.basic.common.integrate.entity.SysOffice;
import com.basic.common.integrate.entity.SysRole;
import com.basic.common.integrate.entity.SysRoleMenu;
import com.basic.common.integrate.entity.SysRoleOffice;
import com.basic.common.integrate.entity.SysUser;
import com.basic.common.integrate.entity.SysUserRole;
import com.github.pagehelper.PageInfo;


/**
 * 系统设置相关service（user、Office、role、menu、area、dict）
 * create by LiHD
 */
public interface SysService {


    Result login(String loginName, String password);


    SysUser selectById(String id);
    
    SysUser selectByuserNo(String userNo);
    
    SysUser selectByuserIdCard(String idcard);

    String getCurrOfficeId(String currUserId);

    int changePwd(Map map);

    int addOffice(SysOffice sysOffice);

    void addRoleOffice(SysRoleOffice sysRoleOffice);

    int addUser(SysUser sysUser);

    void addUserRole(SysUserRole sysUserRole);

    int addRole(SysRole sysRole);

    void addRoleMenu(SysRoleMenu sysRoleMenu);

    Boolean deletedRoleMenu(SysRoleMenu sysRoleMenu);

    int addMenu(SysMenu sysMenu);

    void deletedUser(String userIds, String currUserId);

    void deletedMenu(String menuIds, String currUserId);

    void deletedRole(String roleIds, String currUserId);

    void deletedOffice(String officeIds, String currUserId);

    String selectRoleIdByUserId(String userId);

    SysRole selectByUserId(String userId);

    PageInfo<SysUser> getUserListByOfficeId(Integer pageNo, Integer pageSize, String officeId, String loginName);

    List<SysOffice> getOfficeListByCurrUser(String officeId, String name, String type);

    PageInfo<SysRole> getRoleListByOfficeId(Integer pageNo, Integer pageSize, String name, String officeId, String conditionOfficeId);

    SysUser findInfoById(String id);
    
    SysOffice getOfficeInfoById(String id);

    SysRole getSysRoleInfoById(String id);

    List<SysUser> selectUserListByRoleId(String roleId);
    
    List<SysUser> getUserListByName(String officeId, String name);
    

    List<SysOffice> getAllOfficeTree();

    List<SysOffice> getCurrOfficeTree(String officeId);

    List<SysOffice> getCurrOfficeTreeByType(String officeId, String type);

    List<SysMenu> getAllMenuTree();

    List<String> getCurrMenuTree(String currUserId);

    int updateSysUserInfo(SysUser sysUser);

    int updateSysOfficeInfo(SysOffice sysOffice);

    int updateSysMenuInfo(SysMenu sysMenu);

    void updateSysRoleInfo(SysRole sysRole);

    void updateRoleOfficeByRoleId(SysRoleOffice roleOffice);

    SysRoleMenu selectByRoleMenu(SysRoleMenu sysRoleMenu);

    void updateRoleMenuByRoleId(SysRoleMenu sysRoleMenu);

    void updateUserRole(SysUserRole sysUserRole);

    List<SysDict> selectAllOfficeTypeList();

    List<SysDict> selectCurrOfficeTypeList(String officeId);

    List<SysAreaDTO> getAreas(Map map);

    List<SysDict> getEquWarnTypeListByEquType(Integer equType);
    
    List<SysUser> getListSysUserInfo(SysUser sysUser);
    
    List<SysMenu> selectBySysMenu(SysMenu sysMenu);
    
    List<SysRole> selectBySysRole(SysRole sysRole);

    SysMenu getSysMenuInfo(String id);

    List<SysMenu> getSysMenuListByUserId(String currUserId, String name, String isShow);

    List<SysMenu> getMenu(String currUserId);

    List<SysDict> getEquTypeList();

	SysUser selectByNameImport(String name);

	int insertUserImport(SysUser sysUser);

    String selectUserRoleByUserId(String userId);

    List<String> getHiddenMenu();

    Boolean checkPath(String roleId, String path);

    List<String> getIsSysRoleId();
    
    List<SysMenu> getMeunsByUser(String userid);

}
