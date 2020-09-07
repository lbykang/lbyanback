package com.example.lbyanBack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.lbyanBack.mapper.UserMapper;
import com.example.lbyanBack.pojo.Result;
import com.example.lbyanBack.pojo.User;
import com.example.lbyanBack.pojo.DTO.UserUserInfoDTO;
import com.example.lbyanBack.service.IUserService;
import com.example.lbyanBack.util.SystemUtil;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("user/")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;
    /**
     * 用户注册
     * @return
     */
    @RequestMapping(value = "register")
    @ApiOperation(value = "用户注册", httpMethod = "POST", notes = "用户注册")
    @ResponseBody
    public String register(String username, String password, String email, String num) {
        String account = SystemUtil.randomNum(8);
        User users = userMapper.getuserinfoByAccount(account);
        while (users != null) {
            account = SystemUtil.randomNum(8);
            users = userMapper.getuserinfoByAccount(account);
        }
        UserUserInfoDTO user = new UserUserInfoDTO(SystemUtil.getUuid(), password, SystemUtil.randomNum(8), username,
                num, email);
        return userService.register(user);
    }

    @RequestMapping("login")
    @ApiOperation(value = "用户登录", httpMethod = "GET", notes = "用户登录")
    @ResponseBody
    private Result login(String userinfo, String password) {
        return userService.login(userinfo, password);
    }
}
