package com.dyjs.meeting.controller;


import com.dyjs.meeting.dao.UserDto;
import com.dyjs.meeting.service.UserService;
import com.dyjs.meeting.util.BaseResponse;
import com.dyjs.meeting.util.BaseResponseUtil;
import com.dyjs.meeting.util.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("登陆相关接口")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "登陆接口", notes = "登陆接口")
    @ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),})
    @PostMapping("login")
    public BaseResponse login(String username,String password){
        UserDto userDao = userService.selectByUsername(username);
        if(userDao!=null){
            if(password.equals(userDao.getPassword())){
                return BaseResponseUtil.success(200,"登陆成功",userDao);

            }else{
                return BaseResponseUtil.error(ResultEnum.LOGIN_PASSWORD_ERROR.getStateCode(),"密码错误！");
            }
        }
        return BaseResponseUtil.error(ResultEnum.USER_NOT_EXIST.getStateCode(),"用户不存在");
    }

}
