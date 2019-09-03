package com.example.lbyanBack.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.lbyanBack.pojo.DTO.LjxxDTO;
import com.example.lbyanBack.service.ILjxxService;

@Controller
@RequestMapping("hello")
public class Hello {
    public final static Logger logger = LoggerFactory.getLogger(Hello.class);

    @Autowired
    private ILjxxService ljxxService;

    @RequestMapping("hello")
    @ResponseBody
    public List<LjxxDTO> mian() {
        logger.info("获取连接内容");
        return ljxxService.getAllLjxx();
    }

}
