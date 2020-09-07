package com.example.lbyanBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.lbyanBack.pojo.Ljlx;
import com.example.lbyanBack.pojo.Ljxx;

@Mapper
public interface LjxxMapper {

    /**
     * 批量插入
     * @param bthrDOList 被谈话人批量插入
     */
    @Select(" select C_ID AS id, c_pid as pid,c_url as url ,c_img as img,c_sfyx as sfyx,c_mc as mc,c_jj as jj "
            + " from T_LbyBack_Ljxx")
    public List<Ljxx> getAllLjxx();

    /**
     * 批量插入
     * @param bthrDOList 被谈话人批量插入
     */
    @Select(" select C_ID AS id, n_xh as xh,c_mc as mc " + " from T_LJLX order by xh asc")
    public List<Ljlx> getAllLjlx();
}
