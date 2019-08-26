package com.example.lbyanBack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.lbyanBack.pojo.DTO.LjxxDTO;
import com.example.lbyanBack.service.ILjxxService;

@Controller
@RequestMapping("hello")
public class Hello {

    @Autowired
    private ILjxxService ljxxService;

    @RequestMapping("hello")
    @ResponseBody
    public List<LjxxDTO> mian() {
        return ljxxService.getAllLjxx();
    }

}
