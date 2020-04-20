package com.basic.integrate.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.basic.integrate.config.enums.ResultEnum;
import com.basic.integrate.dao.SysAreaDao;
import com.basic.integrate.dao.SysDictDao;
import com.basic.integrate.dao.SysMenuDao;
import com.basic.integrate.dao.SysOfficeDao;
import com.basic.integrate.dao.SysRoleDao;
import com.basic.integrate.dao.SysRoleMenuDao;
import com.basic.integrate.dao.SysRoleOfficeDao;
import com.basic.integrate.dao.SysUserDao;
import com.basic.integrate.dao.SysUserRoleDao;
import com.basic.integrate.dto.SysAreaDTO;
import com.basic.common.equipment.entity.Result;
import com.basic.common.integrate.entity.SysDict;
import com.basic.common.integrate.entity.SysMenu;
import com.basic.common.integrate.entity.SysOffice;
import com.basic.common.integrate.entity.SysRole;
import com.basic.common.integrate.entity.SysRoleMenu;
import com.basic.common.integrate.entity.SysRoleOffice;
import com.basic.common.integrate.entity.SysUser;
import com.basic.common.integrate.entity.SysUserRole;
import com.basic.integrate.service.SysLogService;
import com.basic.integrate.service.SysService;
import com.basic.integrate.util.BaiduMap;
import com.basic.integrate.util.JWTUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


@Service
public class SysServiceImpl implements SysService {

    private static Logger log = LoggerFactory.getLogger(SysServiceImpl.class);


    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysOfficeDao sysOfficeDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysRoleOfficeDao sysRoleOfficeDao;

    @Autowired
    private SysDictDao sysDictDao;

    @Autowired
    private  SysAreaDao sysAreaDao;

	@Autowired
	private SysLogService sysLogService;
	
	@Autowired
	private BaiduMap baiduMap;
	

	


    @Override
    public Result login(String loginName, String password) {

        SysUser sysUser = sysUserDao.findUserByLoginName(loginName);
        if (sysUser != null) {

            Integer loginFlag =  sysUser.getLoginFlag();
            if (loginFlag != null && loginFlag == 1) {
                return Result.freedom(ResultEnum.PROHIBIT_LOGIN.getStateCode(), ResultEnum.PROHIBIT_LOGIN.getMessage());
            }

            ////根据规则加密密码
            String encodePwd = DigestUtils.sha1Hex(password);
            if (sysUser.getPassword().equals(encodePwd)) {
                String token = JWTUtil.createToken(sysUser.getId(),sysUser.getOfficeId(), sysUser.getNo(), sysUser.getName());
                SysRole sysRole = sysRoleDao.selectByUserId(sysUser.getId());
                sysUser.setPassword("******");
                log.info("basic-ingegrate token："+token);
                sysUserDao.changeLoginDate(sysUser.getId());
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("token", token);
                resultMap.put("userInfo", sysUser);
                resultMap.put("roleInfo", sysRole);
                resultMap.put("map", baiduMap);
                
                sysLogService.addSysLog(sysUser.getId(), "登录",sysUser.getName()+"登录了系统");
                checkwallet(sysUser);
        
                return Result.success(resultMap);
            } else {
                log.info("密码校验失败", sysUser.getLoginName());
                return Result.freedom(ResultEnum.LOGIN_PASSWORD_ERROR.getStateCode(), ResultEnum.LOGIN_PASSWORD_ERROR.getMessage());
            }

        } else {
            return Result.freedom(ResultEnum.USER_NOT_EXIST.getStateCode(), ResultEnum.USER_NOT_EXIST.getMessage());
        }
    }
    
    //检查是否存在钱包账户，不存在就创建
    public void checkwallet(SysUser sysUser) {
    	//paymentFeginService.addUser(null);
    }


    @Override
    public SysUser selectById(String id) {
        return sysUserDao.selectById(id);
    }

    @Override
    public String getCurrOfficeId(String currUserId) {
        return sysUserDao.getCurrOfficeId(currUserId);
    }

    @Override
    public int changePwd(Map map) {

        return sysUserDao.changePwd(map);
    }


    @Override
    public int addOffice(SysOffice sysOffice) {

        return sysOfficeDao.addOffice(sysOffice);
    }

    @Override
    public void addRoleOffice(SysRoleOffice sysRoleOffice) {

        sysRoleOfficeDao.addRoleOffice(sysRoleOffice);
    }

    @Override
    public int addUser(SysUser sysUser) {
    	if(StringUtils.isEmpty(sysUser.getPassword())) {
    		sysUser.setPassword(sysUser.getLoginName());
    	}
        String newPwdEncode = DigestUtils.sha1Hex(sysUser.getPassword());
        sysUser.setPassword(newPwdEncode);
        return sysUserDao.addUser(sysUser);
    }

    @Override
    public void addUserRole(SysUserRole sysUserRole) {

        sysUserRoleDao.addUserRole(sysUserRole);
    }

    @Override
    public int addRole(SysRole sysRole) {
        return sysRoleDao.addRole(sysRole);
    }

    @Override
    public void addRoleMenu(SysRoleMenu sysRoleMenu) {
        sysRoleMenuDao.addRoleMenu(sysRoleMenu);
    }

    @Override
    public Boolean deletedRoleMenu(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuDao.deletedByRoleId(sysRoleMenu.getRoleId()) > 0;
    }

    @Override
    public int addMenu(SysMenu sysMenu) {
        return sysMenuDao.addMenu(sysMenu);
    }


    @Override
    public void deletedUser(String userIds, String currUserId) {

        String[] userIdAry = userIds.split(",");
        for (String userId : userIdAry) {
            if (sysUserDao.deletedUser(userId, currUserId) > 0) {
                sysUserRoleDao.deletedByUserId(userId);
            }
        }
    }

    @Override
    public void deletedMenu(String menuIds, String currUserId) {

        String[] menuIdAry = menuIds.split(",");
        for (String menuId : menuIdAry) {
            if (sysMenuDao.deletedMenu(menuId, currUserId) > 0) {
                sysRoleMenuDao.deletedByMenuId(menuId);
            }
        }
    }

    @Override
    public void deletedRole(String roleIds, String currUserId) {

        String[] roleIdAry = roleIds.split(",");
        for (String roleId : roleIdAry) {
            if (sysRoleDao.deletedRole(roleId, currUserId) > 0) {
                sysRoleMenuDao.deletedByRoleId(roleId);
                sysRoleOfficeDao.deletedByRoleId(roleId);
                sysUserRoleDao.deletedByRoleId(roleId);
            }
        }
    }

    @Override
    public void deletedOffice(String officeIds, String currUserId) {

        String[] officeIdAry = officeIds.split(",");
        for (String officeId : officeIdAry) {
            List<String> childIdList = sysOfficeDao.selectAllChildById(officeId);
            List<String> delOfficeIdList = new ArrayList<>();
            delOfficeIdList.add(officeId);
            delOfficeIdList.addAll(childIdList);
            if (sysOfficeDao.deletedOffice(officeId,currUserId) > 0) {
                for (String delOfficeId : delOfficeIdList) {
                    // 删除机构-角色绑定关系
                    sysRoleOfficeDao.deletedByOfficeId(delOfficeId);
                    // 将该机构下的角色归属机构置空
                    sysRoleDao.updateOfficeId(delOfficeId, currUserId);
                    // 将该机构下的用户归属机构置空
                    sysUserDao.updateOfficeId(delOfficeId, currUserId);
                    // 将该机构下的设备归属机构置空
//                    equipmentInfoDao.updateOfficeId(delOfficeId, currUserId);
                    // 将该机构下的分组归属置空
//                    List<String> groupIdList = equipmentGroupingDao.selectIdByOfficeId(delOfficeId);
//                    if (groupIdList != null && groupIdList.size() > 0) {
//                        equipmentInfoDao.emptyGroupId(groupIdList, currUserId);
//                    }
//                    equipmentGroupingDao.updateOfficeId(delOfficeId, currUserId);
                    // 删除该机构下的定时任务
//                    List<String> taskIdList = equipmentTaskMapper.selectByOfficeId(delOfficeId);
//                    if (taskIdList != null && taskIdList.size() > 0) {
//                        String[] taskIdAry = new String[taskIdList.size()];
//                        taskIdAry = taskIdList.toArray(taskIdAry);
//                        taskEquListDao.deleteBytaskId(taskIdAry);
//                        equipmentTaskMapper.deleteEquTask(taskIdAry);
//                    }
                }
            }
        }
    }

    @Override
    public String selectRoleIdByUserId(String userId) {
        return sysUserRoleDao.selectRoleIdByUserId(userId);
    }

    @Override
    public SysRole selectByUserId(String userId) {
        return sysRoleDao.selectByUserId(userId);
    }

    @Override
    public PageInfo<SysUser> getUserListByOfficeId(Integer pageNo, Integer pageSize, String officeId, String loginName) {

        List<SysUser> sysUserList = new ArrayList<>();
            List<String> officeIds = sysOfficeDao.selectAllChildById(officeId);
            officeIds.add(0,officeId);
            PageHelper.startPage(pageNo, pageSize);
            sysUserList = sysUserDao.getUserListByOfficeId(officeIds, loginName);
            for (SysUser sysUser : sysUserList) {
                if (!StringUtils.isEmpty(sysUser.getOfficeId())) {
                    SysOffice sysOffice = sysOfficeDao.selectById(sysUser.getOfficeId());
                    if (sysOffice != null) {
                        sysUser.setOfficeName(sysOffice.getName());
                    }
                }
            }


        PageInfo<SysUser> page = new PageInfo<>(sysUserList);

        return page;
    }

    @Override
    public List<SysOffice> getOfficeListByCurrUser(String officeId, String name, String type) {

        List<SysDict> officeTypeList = sysDictDao.getOfficeTypeList();
        List<SysOffice> officeList = new ArrayList<>();
        SysOffice currOffice = sysOfficeDao.selectById(officeId);
        if (currOffice != null) {
            if (!StringUtils.isEmpty(name) || !StringUtils.isEmpty(type)) {
                officeList = sysOfficeDao.selectByCondition(officeId, name, type);
                for (SysOffice office : officeList) {
                    for (SysDict officeType : officeTypeList) {
                        if (office.getType().equals(officeType.getValue())) {
                            office.setTypeName(officeType.getLabel());
                        }
                    }
                }
            } else {
                SysOffice parentOffice = sysOfficeDao.selectById(currOffice.getParentId());
                if (parentOffice != null) {
                    currOffice.setParentName(parentOffice.getName());
                }
                for (SysDict officeType : officeTypeList) {
                    if (currOffice.getType().equals(officeType.getValue())) {
                        currOffice.setTypeName(officeType.getLabel());
                    }
                }
                currOffice.setChildList(getChildOffice(currOffice, officeTypeList));
                officeList.add(currOffice);
            }
        }
        return officeList;
    }


    private List<SysOffice> getChildOffice(SysOffice sysOffice, List<SysDict> officeTypeList) {

        List<SysOffice> childOfficeList = sysOfficeDao.selectChildById(sysOffice.getId());
        if (childOfficeList != null && childOfficeList.size() > 0) {
            for (SysOffice childOffice : childOfficeList) {
                childOffice.setParentName(sysOffice.getName());
                for (SysDict officeType : officeTypeList) {
                    if (childOffice.getType().equals(officeType.getValue())) {
                        childOffice.setTypeName(officeType.getLabel());
                    }
                }
            }
            sysOffice.setChildList(childOfficeList);
            for (SysOffice childOffice : childOfficeList) {
                getChildOffice(childOffice, officeTypeList);
            }
        }
        return childOfficeList;
    }


    private List<SysOffice> getChildOfficeByType(SysOffice sysOffice, String type, String currOfficeId) {

        List<SysOffice> resultList = new ArrayList<>();
        List<SysOffice> childOfficeList = sysOfficeDao.selectChildById(sysOffice.getId());
        if (childOfficeList != null && childOfficeList.size() > 0) {
            for (SysOffice childOffice : childOfficeList) {
                childOffice.setParentName(sysOffice.getName());
            }
            for (SysOffice childOffice : childOfficeList) {
                if (!childOffice.getType().equals(type)) {
                    String parentIds = childOffice.getParentIds();
                    if (currOfficeId.equals(childOffice.getId()) || parentIds.contains(currOfficeId)) {
                        resultList.add(childOffice);
                    }
                }
            }
            if (resultList.size() > 0) {
                sysOffice.setChildList(resultList);
                for (SysOffice tempOffice : resultList) {
                    getChildOfficeByType(tempOffice, type, currOfficeId);
                }
            }
        }
        return resultList;
    }


    @Override
    public PageInfo<SysRole> getRoleListByOfficeId(Integer pageNo, Integer pageSize, String name, String officeId,String conditionOfficeId) {

        List<SysRole> tempList = new ArrayList<>();
            SysOffice currOffice = sysOfficeDao.selectById(officeId);
            List<SysOffice> officeList = sysOfficeDao.selectAllChildInfoById(officeId);
            officeList.add(currOffice);
            List<String> officeIdList = new ArrayList<>();
            for (SysOffice office : officeList) {
            	if(office!=null) {
            		officeIdList.add(office.getId());
            	}
                
            }

            PageHelper.startPage(pageNo, pageSize);
            tempList = sysRoleDao.selectByOfficeId(name, officeIdList);
        PageInfo<SysRole> page = new PageInfo<>(tempList);

        return page;

    }

	@Override
	public SysUser findInfoById(String id) {
		SysUser sysUser = sysUserDao.findInfoById(id);
		sysUser.setPassword("******");
		if (sysUser != null && !StringUtils.isEmpty(sysUser.getRoleId())) {
		    SysRole role = sysRoleDao.selectById(sysUser.getRoleId());
		    if (role != null) {
		        sysUser.setRoleName(role.getName());
            }
        }
        if (sysUser != null && !StringUtils.isEmpty(sysUser.getOfficeId())) {
            SysOffice sysOffice = sysOfficeDao.selectById(sysUser.getOfficeId());
            String parentIds = sysOffice.getParentIds();
            if (parentIds.equals("0")) {
                parentIds = sysOffice.getId();
            } else {
                parentIds = parentIds + "," + sysUser.getOfficeId();
            }
            String[] officeIds = parentIds.split(",");
            List<String> officeIdList = Arrays.asList(officeIds);
            sysUser.setOfficeIdList(officeIdList);
        }
        return sysUser;
	}

	@Override
	public SysOffice getOfficeInfoById(String id) {
        SysOffice sysOffice = sysOfficeDao.getOfficeInfoById(id);
        String parentIds = sysOffice.getParentIds();
        if (!parentIds.equals("0")) {
            String[] officeIds = parentIds.split(",");
            List<String> officeIdList = Arrays.asList(officeIds);
            sysOffice.setOfficeIdList(officeIdList);
        }
        List<SysDict> officeTypeList = sysDictDao.getOfficeTypeList();
        for (SysDict officeType : officeTypeList) {
            if (sysOffice.getType().equals(officeType.getValue())) {
                sysOffice.setTypeName(officeType.getLabel());
            }
        }
		return sysOffice;
	}


	@Override
    public SysRole getSysRoleInfoById(String id) {

        SysRole role = sysRoleDao.selectById(id);
        if (role != null) {
            SysOffice sysOffice = sysOfficeDao.selectById(role.getOfficeId());
            if (sysOffice != null) {
                String parentIds = sysOffice.getParentIds();
                if (parentIds.equals("0")) {
                    parentIds = sysOffice.getId();
                } else {
                    parentIds = parentIds + "," + role.getOfficeId();
                }
                String[] officeIds = parentIds.split(",");
                List<String> officeIdList = Arrays.asList(officeIds);
                role.setOfficeIdList(officeIdList);
                role.setOfficeName(sysOffice.getName());
            }
            List<String> menuIdList = sysRoleMenuDao.selectMenuIdByRoleId(role.getId());
            role.setMenuIdList(menuIdList);
        }
        return role;

    }


    @Override
    public List<SysUser> selectUserListByRoleId(String roleId) {

        List<SysUser> userList = new ArrayList<>();
        List<String> userIdList = sysUserRoleDao.selectUserIdByRoleId(roleId);
        for (String userId : userIdList) {
            SysUser sysUser = sysUserDao.findInfoById(userId);
            userList.add(sysUser);
        }
        return userList;
    }


	@Override
    public List<SysOffice> getAllOfficeTree() {

        List<SysDict> officeTypeList = sysDictDao.getOfficeTypeList();
        List<SysOffice> maxParentOfficeList = sysOfficeDao.getMaxParentOffice();
        for (SysOffice maxParentOffice : maxParentOfficeList) {
            maxParentOffice.setChildList(getChildOffice(maxParentOffice, officeTypeList));
        }

        return maxParentOfficeList;
    }


    @Override
    public List<SysOffice> getCurrOfficeTree(String officeId) {
    	List<SysOffice>  sysOfficeList=sysOfficeDao.selectByCondition(officeId, null, null);
    	List<SysOffice> sysOfficeTree = new ArrayList<>();
    	boolean flag= false;

       	if(sysOfficeList!=null&&sysOfficeList.size()>0) {
    		for(SysOffice office:sysOfficeList) {
        		if(office.getParentId().equals("0")) {
        			sysOfficeTree.add(office);
        			flag = true;
        		}
        	}
    	}
       	if(!flag){
            if(sysOfficeList!=null&&sysOfficeList.size()>0) {
                int [] is = new int[sysOfficeList.size()];
                for(int i=0;i< sysOfficeList.size();i++) {
                    is[i] = sysOfficeList.get(i).getParentIds().split(",").length;
                }
                int j=-1;
                int k=0;
                for(int i=0;i<is.length;i++){
                    if(j==-1){
                        j=is[i];
                        k=i;
                    }else{
                        if(is[i]<j){
                            j=is[i];
                            k=i;
                        }
                    }
                }
                sysOfficeTree.add(sysOfficeList.get(k));
            }
        }
    	if(sysOfficeTree!=null&&sysOfficeTree.size()>0) {
    		for (SysOffice parentMenu : sysOfficeTree) {
    			sysOfficeTree(sysOfficeList, parentMenu);
			}
    	}	
        return sysOfficeTree;
    }

    @Override
    public List<SysOffice> getCurrOfficeTreeByType(String officeId, String type) {
        List<SysOffice> maxParentOfficeList = sysOfficeDao.getMaxParentOffice();
        for (SysOffice maxParentOffice : maxParentOfficeList) {
            List<SysOffice> childOfficeList = getChildOfficeByType(maxParentOffice, type, officeId);
            if (childOfficeList != null && childOfficeList.size() > 0) {
                maxParentOffice.setChildList(childOfficeList);
            }
        }

        return maxParentOfficeList;
    }


/*    @Override
    public List<SysMenu> getAllMenuTree() {

        List<SysMenu> maxParentMenuList = sysMenuDao.getMaxParentMenu();
        for (SysMenu maxParentMenu : maxParentMenuList) {
            maxParentMenu.setChildList(getChildMenu(maxParentMenu));
        }

        return maxParentMenuList;
    }*/

/*    private List<SysMenu> getChildMenu(SysMenu sysMenu) {

        List<SysMenu> childMenuList = sysMenuDao.selectChildById(sysMenu.getId(), null, null);
        if (childMenuList != null && childMenuList.size() > 0) {
            sysMenu.setChildList(childMenuList);
            for (SysMenu childMenu : childMenuList) {
                getChildMenu(childMenu);
            }
        }
        return childMenuList;
    }*/


    @Override
    public List<String> getCurrMenuTree(String currUserId) {

        String roleId = sysUserRoleDao.selectRoleIdByUserId(currUserId);
        if (!StringUtils.isEmpty(roleId)) {
            List<String> menuIds = sysRoleMenuDao.selectMenuIdByRoleId(roleId);
            return menuIds;
        } else {
            return null;
        }
    }


    @Override
    public int updateSysUserInfo(SysUser sysUser) {
    	if(!StringUtils.isEmpty(sysUser.getPassword())) {
    		String newPwdEncode = DigestUtils.sha1Hex(sysUser.getPassword());
            sysUser.setPassword(newPwdEncode);
    	}
        return sysUserDao.updateSysUserInfo(sysUser);
    }


    @Override
    public int updateSysOfficeInfo(SysOffice sysOffice) {
        return sysOfficeDao.updateSysOfficeInfo(sysOffice);
    }


    @Override
    public int updateSysMenuInfo(SysMenu sysMenu) {
        return sysMenuDao.updateSysMenuInfo(sysMenu);
    }


    @Override
    public void updateSysRoleInfo(SysRole sysRole) {
        sysRoleDao.updateSysRoleInfo(sysRole);
    }


    @Override
    public void updateRoleOfficeByRoleId(SysRoleOffice roleOffice) {
        sysRoleOfficeDao.updateRoleOfficeByRoleId(roleOffice);
    }


    @Override
    public SysRoleMenu selectByRoleMenu(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuDao.selectByRoleMenu(sysRoleMenu);
    }


    @Override
    public void updateRoleMenuByRoleId(SysRoleMenu sysRoleMenu) {
        sysRoleMenuDao.updateRoleMenuByRoleId(sysRoleMenu);
    }

    @Override
    public void updateUserRole(SysUserRole sysUserRole) {

        if (sysUserRoleDao.selectByUserRole(sysUserRole.getUserId()) != null) {
            sysUserRoleDao.updateRoleIdByUserId(sysUserRole);
        } else {
            sysUserRoleDao.addUserRole(sysUserRole);
        }
    }


    @Override
    public List<SysDict> selectAllOfficeTypeList() {
        return sysDictDao.selectAllOfficeTypeList();
    }

    @Override
    public List<SysDict> selectCurrOfficeTypeList(String officeId) {

        SysOffice office = sysOfficeDao.selectById(officeId);
        return sysDictDao.selectCurrOfficeTypeList(office.getType());
    }

    @Override
    public List<SysAreaDTO> getAreas(Map map) {
        return sysAreaDao.getAreas(map);
    }

    @Override
    public List<SysDict> getEquWarnTypeListByEquType(Integer equType) {

        List<SysDict> warnTypeList = new ArrayList<>();
        if (equType != null) {
            if (1 == equType) {
                warnTypeList = sysDictDao.selectWarnTypeByType("wm_warn_type");
            } else if (4 == equType) {
                warnTypeList = sysDictDao.selectWarnTypeByType("gsm_warn_type");
            } else if (5 == equType) {
                warnTypeList = sysDictDao.selectWarnTypeByType("pre_warn_type");
            }
        }

        return warnTypeList;
    }

    @Override
    public SysMenu getSysMenuInfo(String id) {
        return sysMenuDao.getSysMenuInfo(id, null);
    }

    @Override
    public List<SysMenu> getSysMenuListByUserId(String currUserId, String name, String isShow) {
        return null;
    }


/*    private SysMenu getChildMenu(SysMenu parentMenu, List<String> menuIdList, String name, String isShow) {
        return null;
    }*/



    @Override
    public List<SysMenu> getMenu(String currUserId) {
    	List<SysMenu> menuList = sysMenuDao.selectMenusByUid(currUserId);
    	List<SysMenu> parentMenuList = new ArrayList<>();
    	if(menuList!=null&&menuList.size()>0) {
    		for(SysMenu menu:menuList) {
        		if(menu.getParentId().equals("0")) {
        			parentMenuList.add(menu);
        		}
        	}
    	}
    	if(parentMenuList!=null&&parentMenuList.size()>0) {
    		for (SysMenu parentMenu : parentMenuList) {
    			menuRouteTree(menuList, parentMenu);
			}
    	}	
    	
        return parentMenuList;
    }

    public  SysMenu menuRouteTree(List<SysMenu> menulist,SysMenu parentMenu) {
    	if(parentMenu.getChildren()==null) {
    		parentMenu.setChildren(new ArrayList<>());
    	}
    	if(menulist!=null&&menulist.size()>0) {
    		for (SysMenu sysMenu : menulist) {
    			 if(sysMenu.getParentId().equals(parentMenu.getId())) {
    				 parentMenu.getChildren().add(menuRouteTree(menulist, sysMenu));
    			 }
    		}
    	}  	
    	return parentMenu;
    }
    
    public SysOffice sysOfficeTree(List<SysOffice> sysOfficeList,SysOffice parentOffice) {
    	if(parentOffice.getChildList()==null) {
    		parentOffice.setChildList(new ArrayList<>());
    	}
    	if(sysOfficeList!=null&&sysOfficeList.size()>0) {
    		for (SysOffice sysOffice : sysOfficeList) {
				if(sysOffice.getParentId().equals(parentOffice.getId())) {
					parentOffice.getChildList().add(sysOfficeTree(sysOfficeList, sysOffice));
				}
			}
    	}
    	return parentOffice;
    }

/*    private MenuRoute menuGetChild(MenuRoute parentMenu, List<String> menuIdList) {
        return null;
    }


    private void handleMenuRedirect(MenuRoute parentMenu) {

        for (MenuRoute childMenu : parentMenu.getChildren()) {
            if (!childMenu.getHidden()) {
                parentMenu.setRedirect(childMenu.getRedirect());
                if (childMenu.getChildren() != null && childMenu.getChildren().size() > 0) {
                    for (MenuRoute childChildMenu : childMenu.getChildren()) {
                        if (!childChildMenu.getHidden()) {
                            childMenu.setRedirect(childChildMenu.getRedirect());
                            handleMenuRedirect(childChildMenu);
                            parentMenu.setRedirect(childMenu.getRedirect());
                            break;
                        }
                    }
                }
                break;
            }
        }
    }*/


    @Override
    public List<SysDict> getEquTypeList() {
        return sysDictDao.getEquTypeList();
    }

    @Override
    public String selectUserRoleByUserId(String userId) {
        return sysUserRoleDao.selectRoleIdByUserId(userId);
    }

    @Override
    public List<String> getHiddenMenu() {
        return sysMenuDao.getHiddenMenu();
    }

    @Override
    public Boolean checkPath(String roleId, String path) {

        List<String> menuIdList = sysRoleMenuDao.selectMenuIdByRoleId(roleId);
        SysMenu sysMenu = sysMenuDao.checkPath(menuIdList, path);
        if (sysMenu != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<String> getIsSysRoleId() {

        return sysRoleDao.getIsSysRoleId();
    }

	@Override
	public List<SysUser> getListSysUserInfo(SysUser sysUser) {
		return sysUserDao.getListSysUserInfo(sysUser);
	}

	@Override
	public List<SysMenu> selectBySysMenu(SysMenu sysMenu) {
		return sysMenuDao.selectBySysMenu(sysMenu);
	}

	@Override
	public List<SysRole> selectBySysRole(SysRole sysRole) {
		return sysRoleDao.selectBySysRole(sysRole);
	}

	@Override
	public SysUser selectByNameImport(String name) {
		return sysUserDao.selectByNameImport(name);
	}

	@Override
	public int insertUserImport(SysUser sysUser) {
		return sysUserDao.insertUserImport(sysUser);
	}

	@Override
	public List<SysMenu> getMeunsByUser(String userid) {
		return sysMenuDao.selectMenusByUid(userid);
	}


	@Override
	public SysUser selectByuserNo(String userNo) {
		return sysUserDao.selectByuserNo(userNo);
	}


	@Override
	public List<SysUser> getUserListByName(String officeId, String name) {
        List<SysUser> sysUserList = new ArrayList<>();
        List<String> officeIds = sysOfficeDao.selectAllChildById(officeId);
        officeIds.add(0,officeId);
        sysUserList = sysUserDao.getUserListByName(officeIds, name);
        for (SysUser sysUser : sysUserList) {
            if (!StringUtils.isEmpty(sysUser.getOfficeId())) {
                SysOffice sysOffice = sysOfficeDao.selectById(sysUser.getOfficeId());
                if (sysOffice != null) {
                    sysUser.setOfficeName(sysOffice.getName());
                }
            }
        }
    return sysUserList;
	}


	@Override
	public SysUser selectByuserIdCard(String idcard) {
		return sysUserDao.selectByuserIdCard(idcard);
	}


	@Override
	public List<SysMenu> getAllMenuTree() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
