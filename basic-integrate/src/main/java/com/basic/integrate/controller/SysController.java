package com.basic.integrate.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.alibaba.druid.util.StringUtils;
import com.basic.common.integrate.entity.SysLog;
import com.basic.common.integrate.entity.SysUser;
import com.basic.integrate.config.enums.ResultEnum;
import com.basic.common.equipment.entity.Result;
import com.basic.common.integrate.entity.SysDict;
import com.basic.common.integrate.entity.SysMenu;
import com.basic.common.integrate.entity.SysOffice;
import com.basic.common.integrate.entity.SysRole;
import com.basic.common.integrate.entity.SysRoleMenu;
import com.basic.common.integrate.entity.SysRoleOffice;
import com.basic.common.integrate.entity.SysUserRole;
import com.basic.integrate.service.SysLogService;
import com.basic.integrate.service.SysService;
import com.basic.integrate.util.IDUtils;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sys")
@Api(value = "系统相关接口")
@SuppressWarnings(value = { "rawtypes"})
public class SysController {



	@Autowired
	private SysService sysService;
	
	@Autowired
	SysLogService sysLogService;


	@ApiOperation(value="添加机构", notes="机构管理添加新机构")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "机构名称", required = true, dataType = "String"),
		@ApiImplicitParam(name = "code", value = "机构编码", required = false, dataType = "String"),
		@ApiImplicitParam(name = "type", value = "机构类型(1:公司 2:部门 3:片区)", required = true, dataType = "String"),
		@ApiImplicitParam(name = "parentId", value = "归属机构id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "parentIds", value = "所有归属机构id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "primaryPerson", value = "主负责人", required = false, dataType = "String"),
		@ApiImplicitParam(name = "phone", value = "联系电话", required = false, dataType = "String"),
	})
	@PostMapping("/addOffice")
	public Result addOffice(@RequestBody SysOffice sysOffice, String currUserId) {

		if (StringUtils.isEmpty(sysOffice.getName()) || StringUtils.isEmpty(sysOffice.getType()) || StringUtils.isEmpty(sysOffice.getParentId()) || StringUtils.isEmpty(sysOffice.getParentIds())) {
			return Result.freedom(ResultEnum.INVALID_PARAMETER.getStateCode(), ResultEnum.INVALID_PARAMETER.getMessage());
		}
		if(sysOffice.getId()==null||"".equals(sysOffice.getId())){
			sysOffice.setId(IDUtils.createUUID());
			sysOffice.setCreateBy(currUserId);
			sysOffice.setCreateTime(new Date());
		}
		if (sysService.addOffice(sysOffice) > 0) {
			if(currUserId==null){
				currUserId="1";
			}
			sysLogService.addSysLog(currUserId, "组织机构","添加了"+sysOffice.getName()+"组织机构");
			return Result.success();
		}

		return Result.fail();

	}


	@ApiOperation(value="添加用户", notes="用户管理添加新用户")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "officeId", value = "归属机构的id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "no", value = "用户编号", required = true, dataType = "String"),
		@ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String"),
		@ApiImplicitParam(name = "loginName", value = "登录名", required = true, dataType = "String"),
		@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
		@ApiImplicitParam(name = "mobile", value = "手机", required = false, dataType = "String"),
		@ApiImplicitParam(name = "idCard", value = "身份证号", required = false, dataType = "String"),
		@ApiImplicitParam(name = "loginFlag", value = "是否允许登陆(0:允许 1:不允许)", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "String"),
	})
	@PostMapping("/no")
	public Result addUser(SysUser sysUser, String roleId,String currUserId) {

		if (StringUtils.isEmpty(sysUser.getName()) || StringUtils.isEmpty(sysUser.getOfficeId()) || StringUtils.isEmpty(sysUser.getMobile()) || StringUtils.isEmpty(sysUser.getPassword()) || StringUtils.isEmpty(sysUser.getRoleId())) {
			return Result.freedom(ResultEnum.INVALID_PARAMETER.getStateCode(), ResultEnum.INVALID_PARAMETER.getMessage());
		}
		sysUser.setId(IDUtils.createUUID());
		sysUser.setCreateBy(currUserId);
		sysUser.setCreateTime(new Date());
		sysUser.setLoginName(sysUser.getMobile());
		if(sysService.selectByuserNo(sysUser.getNo())!=null) {
			return Result.fail("用户编号已存在");
		}
		
		if(sysService.selectByuserIdCard(sysUser.getIdcard())!=null) {
			return Result.fail("身份证号已存在");
		}
		
		if (sysService.addUser(sysUser) > 0) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(sysUser.getId());
			sysUserRole.setRoleId(roleId);
			sysService.addUserRole(sysUserRole);
			sysLogService.addSysLog(currUserId, "用户","添加了"+sysUser.getName()+"用户");
			return Result.success();
		}

		return Result.fail();

	}


	@ApiOperation(value="添加角色", notes="角色管理添加新角色")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "角色名称", required = true, dataType = "String"),
		@ApiImplicitParam(name = "officeId", value = "归属机构", required = true, dataType = "String"),
		@ApiImplicitParam(name = "useable", value = "是否可用", required = false, dataType = "String"),
		@ApiImplicitParam(name = "menuIds", value = "权限菜单的ids", required = true, dataType = "String"),
	})
	@PostMapping("/addRole")
	@Transactional
	public Result addRole(SysRole sysRole, String menuIds, String currUserId) {

		if (StringUtils.isEmpty(sysRole.getName()) || StringUtils.isEmpty(sysRole.getOfficeId())) {
			return Result.freedom(ResultEnum.INVALID_PARAMETER.getStateCode(), ResultEnum.INVALID_PARAMETER.getMessage());
		}

		sysRole.setId(IDUtils.createUUID());
		sysRole.setCreateBy(currUserId);
		sysRole.setCreateTime(new Date());
		if (sysService.addRole(sysRole) > 0) {
			SysRoleOffice sysRoleOffice = new SysRoleOffice();
			sysRoleOffice.setRoleId(sysRole.getId());
			sysRoleOffice.setOfficeId(sysRole.getOfficeId());
			sysService.addRoleOffice(sysRoleOffice);
			String[] menuIdAry = menuIds.split(",");
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setRoleId(sysRole.getId());
			for (String menuId : menuIdAry) {
				sysRoleMenu.setMenuId(menuId);
				sysService.addRoleMenu(sysRoleMenu);
			}
			List<String> hiddenMenuList = sysService.getHiddenMenu();
			for (String hiddenMenuId : hiddenMenuList) {
				sysRoleMenu.setMenuId(hiddenMenuId);
				sysService.addRoleMenu(sysRoleMenu);
			}
			sysLogService.addSysLog(currUserId, "角色","新建了"+sysRole.getName()+"角色");
			return Result.success();
		}

		return Result.fail();

	}



	@ApiOperation(value="添加菜单", notes="菜单管理添加新菜单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "name", value = "名称", required = false, dataType = "String"),
		@ApiImplicitParam(name = "parentId", value = "上级菜单id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "parentIds", value = "所有上级菜单id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "component", value = "链接", required = false, dataType = "String"),
		@ApiImplicitParam(name = "path", value = "目标", required = false, dataType = "String"),
		@ApiImplicitParam(name = "sort", value = "排序", required = false, dataType = "BigDecimal"),
		@ApiImplicitParam(name = "hidden", value = "状态", required = false, dataType = "String"),
		@ApiImplicitParam(name = "redirect", value = "排序", required = false, dataType = "BigDecimal"),
		@ApiImplicitParam(name = "meta", value = "状态", required = false, dataType = "String"),
	})
	@PostMapping("/addMenu")
	public Result addMenu(SysMenu sysMenu, String currUserId) {

		if (StringUtils.isEmpty(sysMenu.getName()) || StringUtils.isEmpty(sysMenu.getParentId()) || StringUtils.isEmpty(sysMenu.getParentIds())) {
			return Result.freedom(ResultEnum.INVALID_PARAMETER.getStateCode(), ResultEnum.INVALID_PARAMETER.getMessage());
		}
		sysMenu.setId(IDUtils.createUUID());
		sysMenu.setCreateBy(currUserId);
		sysMenu.setSort(new BigDecimal("0.0"));
		sysMenu.setCreateTime(new Date());
		if (sysService.addMenu(sysMenu) > 0) {
			List<String> isSysRoleIdList = sysService.getIsSysRoleId();
			SysRoleMenu roleMenu = new SysRoleMenu();
			roleMenu.setMenuId(sysMenu.getId());
			for (String id : isSysRoleIdList) {
				roleMenu.setRoleId(id);
				sysService.addRoleMenu(roleMenu);
			}
			return Result.success();
		}

		return Result.fail();

	}



	@ApiOperation(value="删除用户、菜单、角色、机构", notes="系统管理删除的统一接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ids", value = "逗号分隔的id字符串", required = true, dataType = "String"),
		@ApiImplicitParam(name = "type", value = "删除内容的类型（1、用户 2、菜单 3、角色 4、机构）", required = true, dataType = "String"),
		@ApiImplicitParam(name = "currUserId", value = "当前登录人ID", required = true, dataType = "String")
	})
	@PostMapping("/deletedSys")
	public Result deletedSys(String ids, String type, String currUserId,@RequestBody Map map) {
			if(ids==null){
			ids= (String) map.get("ids");
		}
		if(type==null){
			type= (String) map.get("type");
		}
		if(currUserId==null){
			currUserId= (String) map.get("currUserId");
		}

		if (StringUtils.isEmpty(ids) || StringUtils.isEmpty(type)) {
			return Result.freedom(ResultEnum.INVALID_PARAMETER.getStateCode(), ResultEnum.INVALID_PARAMETER.getMessage());
		}

			if (type.equals("1")) {
				sysService.deletedUser(ids, currUserId);
			} else if (type.equals("2")) {
				sysService.deletedMenu(ids, currUserId);
			} else if (type.equals("3")) {
				sysService.deletedRole(ids, currUserId);
			} else if (type.equals("4")) {
				sysService.deletedOffice(ids, currUserId);
			} else {
				return Result.fail();
			}
			return Result.success();
	}


	@ApiOperation(value="获取组织机构树", notes="获取组织机构树")
	@PostMapping("/getSysOfficeTree")
	public Result<List<SysOffice>> getSysOfficeTree(String currOfficeId) {
			return Result.success(sysService.getCurrOfficeTree(currOfficeId));
	}



	@ApiOperation(value="获取组织机构树(添加机构专用)", notes="根据机构类型获取上层机构树")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "type", value = "机构类型", required = true, dataType = "String"),
	})
	@PostMapping("/getSysOfficeTreeByType")
	public Result<List<SysOffice>> getSysOfficeTreeByType(String type, String currOfficeId) {
			return Result.success(sysService.getCurrOfficeTreeByType(currOfficeId, type));
	}



/*	@ApiOperation(value="获取菜单树", notes="获取菜单树")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "isAll", value = "是否获取全部菜单？(0:获取全部 1:获取当前用户权限可见)", required = true, dataType = "String"),
		@ApiImplicitParam(name = "name", value = "菜单名称", required = false, dataType = "String"),
		@ApiImplicitParam(name = "isShow", value = "菜单状态（0:展示 1:不展示）", required = false, dataType = "String"),
	})
	@PostMapping("/getSysMenuTree")
	public Result<List<SysMenu>> getSysMenuTree(String isAll, String name, String isShow,String currUserId) {

			if (isAll.equals("0")) {
				List<SysMenu> sysMenuList = sysService.getAllMenuTree();
				return Result.success(sysMenuList);
			} else {
				//List<String> menuIds = sysService.getCurrMenuTree(currUserId);
				List<SysMenu> sysMenuList = sysService.getSysMenuListByUserId(currUserId, name, isShow);
				return Result.success(sysMenuList);
			}
		
	}*/

	
	@PostMapping(value = "/getSysMenus")
	public List<SysMenu> getSysMenus(String currUserId) {
		return sysService.getMeunsByUser(currUserId);
	}

	@ApiOperation(value="获取侧边栏菜单", notes="获取侧边栏菜单")
	@PostMapping(value = "/getMenu")
	public Result<List<SysMenu>> getMenu(String currUserId) {
		return Result.success(sysService.getMenu(currUserId));

	}



	@ApiOperation(value="分页获取用户列表", notes="获取用户列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNo", value = "第几页", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页的数量", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "officeId", value = "组织结构id", required = false, dataType = "String"),
		@ApiImplicitParam(name = "loginName", value = "登录名", required = false, dataType = "String"),
	})
	@PostMapping("/getSysUserList")
	public Result<PageInfo<SysUser>> getSysUserList(Integer pageNo, Integer pageSize, String loginName, 
			@RequestParam(value="officeId",required=false)String officeId,String currOfficeId,String currUserId) {
			
		if(StringUtils.isEmpty(officeId)) {
				officeId = currOfficeId;
			}
			PageInfo<SysUser> pageInfo = new PageInfo<>();
			pageNo = pageNo == null ? 1 : pageNo;
			pageSize = pageSize == null ? 10 : pageSize;

			pageInfo = sysService.getUserListByOfficeId(pageNo, pageSize, officeId, loginName);
			return Result.success(pageInfo);
	}
	
	@ApiOperation(value="获取用户列表", notes="获取用户列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "officeId", value = "组织结构id", required = false, dataType = "String"),
		@ApiImplicitParam(name = "name", value = "姓名", required = false, dataType = "String"),
	})
	@PostMapping("/getSysUserListByname")
	public Result<List<SysUser>> getSysUserList( String name, 
			@RequestParam(value="officeId",required=false)String officeId,String currOfficeId) {
			if(StringUtils.isEmpty(officeId)) {
				officeId = currOfficeId;
			}
			return Result.success(sysService.getUserListByName(officeId, name));
	}


/*	@ApiOperation(value="获取机构列表", notes="获取机构列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "机构名称", required = false, dataType = "String"),
		@ApiImplicitParam(name = "type", value = "机构层级", required = false, dataType = "String"),
	})
	@PostMapping("/getSysOfficeList")
	public Result<List<SysOffice>> getSysOfficeList(String name, String type, String currOfficeId) {
			return Result.success(sysService.getOfficeListByCurrUser(currOfficeId, name, type));
	}*/


	@ApiOperation(value="获取角色列表", notes="获取角色列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNo", value = "第几页", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页的数量", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "name", value = "角色名称", required = false, dataType = "String"),
		@ApiImplicitParam(name = "officeId", value = "机构id (传000表示查询当前用户所在机构的角色列表)", required = true, dataType = "String")
	})
	@PostMapping("/getSysRoleList")
	public Result<PageInfo<SysRole>> getSysRoleList(Integer pageNo, Integer pageSize, String name, 
			@RequestParam(value="officeId",required=false)String officeId,  String currOfficeId,String currUserId) {
		
			PageInfo<SysRole> pageInfo = new PageInfo<>();
			pageNo = pageNo == null ? 1 : pageNo;
			pageSize = pageSize == null ? 10 : pageSize;

			if (StringUtils.isEmpty(officeId)) {
				officeId = currOfficeId;
			} 
			
			pageInfo = sysService.getRoleListByOfficeId(pageNo, pageSize, name, officeId, currOfficeId);
			return Result.success(pageInfo);

	}


	@ApiOperation(value="获取用户详情", notes="根据用户id获取用户详细信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
	})
	@PostMapping("/getSysUserInfo")
	public Result<SysUser> getSysUserInfo(@RequestParam("id") String id) {
			return Result.success(sysService.findInfoById(id));
	}
	
	@ApiOperation(value="获取用户详情", notes="根据用户id获取用户详细信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userNo", value = "用户编号", required = true, dataType = "String")
	})
	@PostMapping("/selectByuserNo")
	public Result<SysUser> selectByuserNo(@RequestParam("userNo") String userNo) {
			return Result.success(sysService.selectByuserNo(userNo));
	}


	@ApiOperation(value="修改用户信息", notes="根据用户id修改用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "officeId", value = "归属机构的id", required = false, dataType = "String"),
		@ApiImplicitParam(name = "no", value = "工号", required = false, dataType = "String"),
		@ApiImplicitParam(name = "name", value = "姓名", required = false, dataType = "String"),
		@ApiImplicitParam(name = "loginName", value = "登录名", required = false, dataType = "String"),
		@ApiImplicitParam(name = "password", value = "密码", required = false, dataType = "String"),
		@ApiImplicitParam(name = "mobile", value = "手机", required = false, dataType = "String"),
		@ApiImplicitParam(name = "loginFlag", value = "是否允许登陆", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "roleId", value = "角色id", required = false, dataType = "String"),
	})
	@PostMapping("/updateSysUserInfo")
	public Result updateSysUserInfo(SysUser sysUser, String roleId, String currUserId) {
		
			sysUser.setUpdateBy(currUserId);
			sysUser.setUpdateTime(new Date());
			sysUser.setLoginName(sysUser.getMobile());
			int result = sysService.updateSysUserInfo(sysUser);
			if (result > 0 && !StringUtils.isEmpty(roleId)) {
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setUserId(sysUser.getId());
				sysUserRole.setRoleId(roleId);
				sysService.updateUserRole(sysUserRole);
			}
			sysLogService.addSysLog(currUserId, "用户","修改了"+sysUser.getName()+"用户信息");
			return Result.success();
	}



	/**
	 * 机构详情
	 * @param id
	 * @return
	 */
	@ApiOperation(value="获取机构详情", notes="根据机构id获取机构详细信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "机构id", required = true, dataType = "String")
	})
	@PostMapping("/getSysOfficeInfo")
	public Result getSysOfficeInfo(String id) {
		try {
			SysOffice sysOffice = sysService.getOfficeInfoById(id);
			return Result.success(sysOffice);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Result.fail();

	}


	@ApiOperation(value="修改机构信息", notes="根据机构id修改机构信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "机构id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "name", value = "机构名称", required = false, dataType = "String"),
		@ApiImplicitParam(name = "code", value = "机构编码", required = false, dataType = "String"),
		@ApiImplicitParam(name = "type", value = "机构类型(1:公司 2:部门 3:片区)", required = true, dataType = "String"),
		@ApiImplicitParam(name = "parentId", value = "归属机构id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "parentIds", value = "所有归属机构id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "primaryPerson", value = "主负责人", required = false, dataType = "String"),
		@ApiImplicitParam(name = "phone", value = "联系电话", required = false, dataType = "String"),
	})
	@PostMapping("/updateSysOfficeInfo")
	public Result updateSysOfficeInfo(SysOffice sysOffice, String currUserId) {
		try {

			if (StringUtils.isEmpty(sysOffice.getId())) {
				return Result.freedom(ResultEnum.INVALID_PARAMETER.getStateCode(), ResultEnum.INVALID_PARAMETER.getMessage());
			}
			sysOffice.setUpdateBy(currUserId);
			sysOffice.setUpdateTime(new Date());

			if (sysService.updateSysOfficeInfo(sysOffice) > 0) {
				sysLogService.addSysLog(currUserId, "组织机构","修改了"+sysOffice.getName()+"组织信息");
				return Result.success();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Result.fail();

	}


	@ApiOperation(value="获取菜单详情", notes="根据菜单id获取菜单详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "String")
	})
	@PostMapping("/getSysMenuInfo")
	public Result getSysMenuInfo(String id) {
		return Result.success(sysService.getSysMenuInfo(id));
	}


	@ApiOperation(value="修改菜单信息", notes="根据菜单id修改菜单信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "name", value = "名称", required = false, dataType = "String"),
		@ApiImplicitParam(name = "parentId", value = "上级菜单id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "parentIds", value = "所有上级菜单id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "component", value = "链接", required = false, dataType = "String"),
		@ApiImplicitParam(name = "path", value = "目标", required = false, dataType = "String"),
		@ApiImplicitParam(name = "sort", value = "排序", required = false, dataType = "BigDecimal"),
		@ApiImplicitParam(name = "hidden", value = "状态", required = false, dataType = "String"),
		@ApiImplicitParam(name = "redirect", value = "排序", required = false, dataType = "BigDecimal"),
		@ApiImplicitParam(name = "meta", value = "状态", required = false, dataType = "String"),
	})
	@PostMapping("/updateSysMenuInfo")
	public Result updateSysMenuInfo(SysMenu sysMenu, String currUserId) {
		try {

			if (StringUtils.isEmpty(sysMenu.getId())) {
				return Result.freedom(ResultEnum.INVALID_PARAMETER.getStateCode(), ResultEnum.INVALID_PARAMETER.getMessage());
			}
			sysMenu.setUpdateBy(currUserId);
			sysMenu.setUpdateTime(new Date());

			if (sysService.updateSysMenuInfo(sysMenu) > 0) {
				return Result.success();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Result.fail();

	}


	@ApiOperation(value="获取角色详情", notes="根据角色id获取角色详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "String")
	})
	@PostMapping("/getSysRoleInfo")
	public Result<SysRole> getSysRoleInfo(String id) {
			return Result.success(sysService.getSysRoleInfoById(id));
	}


	@ApiOperation(value="获取角色详情中的用户列表", notes="根据角色id获取角色详情中的用户列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "String")
	})
	@PostMapping("/getRoleInfoUserList")
	public Result<List<SysUser>> getRoleInfoUserList(String roleId) {
			return Result.success(sysService.selectUserListByRoleId(roleId));
	}


	@ApiOperation(value="修改角色信息", notes="根据角色id修改角色信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "name", value = "角色名称", required = false, dataType = "String"),
		@ApiImplicitParam(name = "officeId", value = "归属机构", required = false, dataType = "String"),
		@ApiImplicitParam(name = "useable", value = "是否可用", required = false, dataType = "String"),
		@ApiImplicitParam(name = "menuIds", value = "权限菜单的ids", required = false, dataType = "String"),
	})
	@PostMapping("/updateSysRoleInfo")
	public Result updateSysRoleInfo(SysRole sysRole, String menuIds, String currUserId) {

			if (StringUtils.isEmpty(sysRole.getId())) {
				return Result.freedom(ResultEnum.INVALID_PARAMETER.getStateCode(), ResultEnum.INVALID_PARAMETER.getMessage());
			}
			sysRole.setUpdateBy(currUserId);
			sysRole.setUpdateTime(new Date());

			sysService.updateSysRoleInfo(sysRole);
			SysRoleOffice sysRoleOffice = new SysRoleOffice();
			sysRoleOffice.setRoleId(sysRole.getId());
			sysRoleOffice.setOfficeId(sysRole.getOfficeId());
			sysService.updateRoleOfficeByRoleId(sysRoleOffice);
			if (menuIds != null) {
				String[] menuIdAry = menuIds.split(",");
				SysRoleMenu sysRoleMenu = new SysRoleMenu();
				sysRoleMenu.setRoleId(sysRole.getId());
				if (sysService.deletedRoleMenu(sysRoleMenu)) {
					for (String menuId : menuIdAry) {
						sysRoleMenu.setMenuId(menuId);
						sysService.addRoleMenu(sysRoleMenu);
					}
/*					List<String> hiddenMenuList = sysService.getHiddenMenu();
					for (String hiddenMenuId : hiddenMenuList) {
						sysRoleMenu.setMenuId(hiddenMenuId);
						sysService.addRoleMenu(sysRoleMenu);
					}*/
				}
			}
			sysLogService.addSysLog(currUserId, "角色","修改了"+sysRole.getName()+"角色信息");
			return Result.success();
	}


	@ApiOperation(value="获取机构类型列表", notes="获取机构类型列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "isAll", value = "是否获取全部类型？(0:获取全部 1:获取当前用户权限可见)", required = true, dataType = "String"),
	})
	@PostMapping("/getOfficeTypeList")
	public Result<List<SysDict>> getOfficeTypeList(String isAll, String currOfficeId) {

			List<SysDict> officeTypeList = new ArrayList<>();
			if (isAll.equals("0")) {
				officeTypeList = sysService.selectAllOfficeTypeList();
			} else if (isAll.equals("1")) {
				officeTypeList = sysService.selectCurrOfficeTypeList(currOfficeId);
			}

			return Result.success(officeTypeList);
	}


	@ApiOperation(value="验证当前用户菜单中是否包含某页面", notes="验证当前用户菜单中是否包含某页面")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "path", value = "页面地址", required = true, dataType = "String"),
		@ApiImplicitParam(name = "currUserId", value = "当前登录人ID", required = true, dataType = "String")
	})
	@PostMapping("/checkPath")
	public Result<Boolean> checkPath(String path,String currUserId) {

			Boolean result = false;
			SysRole role = sysService.selectByUserId(currUserId);
			if (role != null) {
				String roleId = role.getId();
				result = sysService.checkPath(roleId, path);
			}

			return Result.success(result);
	}

	@ApiOperation(value="显示系统日志", notes="显示系统日志")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNo", value = "页面地址", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pageSize", value = "当前登录人ID", required = true, dataType = "String"),
		@ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "String"),
		@ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "String"),
		@ApiImplicitParam(name = "userName", value = "操作人", required = true, dataType = "String"),
		@ApiImplicitParam(name = "officeId", value = "组织机构id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "type", value = "操作类型", required = true, dataType = "String"),
	})
	@PostMapping("/getSystemLog")
	public Result<PageInfo<SysLog>> getSystemLog(Integer pageNo, Integer pageSize,
			String userName,String type,String startTime,String endTime,String officeId,String currOfficeId){
		if(StringUtils.isEmpty(officeId)) {
			officeId = currOfficeId;
		}
		return Result.success(sysLogService.selectSysLog(pageNo, pageSize, userName,type, startTime, endTime,officeId));
	}
	
	@ApiOperation(value="获取日志类型", notes="获取日志类型")
	@PostMapping(value = "/getLogType")
	public Result<List<String>> getLogType(){
		return Result.success(sysLogService.getLogType());
	}
}
