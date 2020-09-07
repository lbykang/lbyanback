package com.example.lbyanBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.lbyanBack.pojo.User;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO T_LbyBack_User (c_id,C_PWD,c_account)  VALUES(#{id},#{password},#{account})")
    public void register(User user);

    @Select("SELECT C_ID AS id,C_PWD as password,C_ACCOUNT AS account FROM T_LbyBack_User WHERE C_ACCOUNT =#{account}")
    public List<User> getUserInfo(@Param("account") String account);

    @Select("SELECT C_ID AS id,C_PWD as password,C_ACCOUNT AS account FROM T_LbyBack_User where C_USER_NAME = #{username}")
    public User getuserinfoByUserName(@Param("username") String username);

    @Select("SELECT C_ID AS id,C_PWD as password,C_ACCOUNT AS account FROM T_LbyBack_User where C_ID = #{id}")
    public User getuserinfoById(@Param("id") String id);

    @Select("SELECT C_ID AS id,C_PWD as password,C_ACCOUNT AS account FROM T_LbyBack_User where C_ACCOUNT = #{account}")
    public User getuserinfoByAccount(@Param("account") String account);

    @Select("SELECT C_ID AS id, C_PWD as password,C_ACCOUNT AS account FROM T_LbyBack_User where C_ACCOUNT = #{loginInfo}")
    public User getuserinfoByMailOrAccount(@Param("loginInfo") String loginInfo);
}
