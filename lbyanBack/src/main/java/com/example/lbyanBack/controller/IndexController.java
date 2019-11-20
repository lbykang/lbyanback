package com.example.lbyanBack.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.lbyanBack.pojo.DTO.LjxxDTO;
import com.example.lbyanBack.service.ILjxxService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("index")
public class IndexController {
    public final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ILjxxService ljxxService;

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("getLinkDate")
    @ApiOperation(value = "获取连接数据", httpMethod = "GET", notes = "获取连接数据")
    @ResponseBody
    public List<LjxxDTO> mian() {
        logger.info("获取连接内容");
        return ljxxService.getAllLjxx();
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("redisTest")
    @ApiOperation(value = "redis测试", httpMethod = "GET", notes = "redis测试")
    @ResponseBody
    private Object redisTest() { // TODO Auto-generated method stub
        ValueOperations zSetOps = redisTemplate.opsForValue();
        //zSetOps.set("wz:1815", 0);
        zSetOps.increment("wz:1815");
        Object data =zSetOps.get("wz:1815");
        return data;
    }

}
