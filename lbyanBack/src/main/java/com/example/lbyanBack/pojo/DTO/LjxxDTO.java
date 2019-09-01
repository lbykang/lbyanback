package com.example.lbyanBack.pojo.DTO;

import java.util.List;

import com.example.lbyanBack.pojo.Ljlx;
import com.example.lbyanBack.pojo.Ljxx;

public class LjxxDTO {

    private List<Ljxx> ljxx;

    private Ljlx lx;

    public List<Ljxx> getLjxx() {
        return ljxx;
    }

    public void setLjxx(List<Ljxx> ljxx) {
        this.ljxx = ljxx;
    }

    public Ljlx getLx() {
        return lx;
    }

    public void setLx(Ljlx lx) {
        this.lx = lx;
    }

}
