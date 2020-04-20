/**
 * 
 */
package com.basic.integrate.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.basic.common.equipment.entity.Result;
import com.basic.integrate.config.enums.ResultEnum;
import com.basic.integrate.service.SysService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author sunjinpeng
 * @description
 * @creatDate 2019年3月25日
 * @lastModifyDate 2019年3月25日
 */
@Controller
@Api(value = "登录退出")
@SuppressWarnings(value = { "rawtypes" })
public class SysUserController {
	
	@Autowired
    private SysService sysService;
	


    @ApiOperation(value="用户登录", notes="登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户登录名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String")
    })
	@PostMapping("/login")
	@ResponseBody
	public Result login(@RequestParam(required=true,value="loginName")String loginName,String password) {
		 try {

	            if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
	                return Result.freedom(ResultEnum.INVALID_LOGINNAME_PASSWORD.getStateCode(), ResultEnum.INVALID_LOGINNAME_PASSWORD.getMessage());
	            }

	           
	            return sysService.login(loginName, password);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return Result.fail();
	}
    

    
}
