package com.example.lbyanBack.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lbyanBack.mapper.LjxxMapper;
import com.example.lbyanBack.pojo.Ljlx;
import com.example.lbyanBack.pojo.Ljxx;
import com.example.lbyanBack.pojo.DTO.LjxxDTO;
import com.example.lbyanBack.service.ILjxxService;

@Service
public class LjxxServiceImpl implements ILjxxService {

    @Autowired
    private LjxxMapper ljxxmaper;

    @Override
    public List<LjxxDTO> getAllLjxx() {
        List<Ljxx> ljxx = ljxxmaper.getAllLjxx();
        List<Ljlx> ljlx = ljxxmaper.getAllLjlx();
        List<LjxxDTO> ljxxs = new ArrayList<>();
        for (Ljlx lx : ljlx) {
            LjxxDTO ljxxDTO = new LjxxDTO();
            List<Ljxx> ljxxlist = new ArrayList<>();
            for (Ljxx xx : ljxx) {
                if (xx.getPid().equals(lx.getId())) {
                    ljxxlist.add(xx);
                }
            }
            ljxxDTO.setLx(lx);
            ljxxDTO.setLjxx(ljxxlist);
            ljxxs.add(ljxxDTO);
        }
        return ljxxs;
    }

}
