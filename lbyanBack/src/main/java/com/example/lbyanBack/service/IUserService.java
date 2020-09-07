package com.example.lbyanBack.service;

import com.example.lbyanBack.pojo.Result;
import com.example.lbyanBack.pojo.DTO.UserUserInfoDTO;

public interface IUserService {

    public String register(UserUserInfoDTO user);

    public Result login(String userinfo, String password);

}
