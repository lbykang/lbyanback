package com.example.lbyanBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.lbyanBack.pojo.UserInfo;

@Mapper
public interface UserInfoMapper {

    /**
     * 保存用户个人信息
     * @param userInfo
     * @return
     */
    @Insert("INSERT INTO T_LbyBack_UserInfo (c_id,c_user_id,c_username,c_email,dt_cjsj,c_num)  VALUES(#{id},#{userid},#{username},#{email},#{cjsj},#{num})")
    public int insertUserInfo(UserInfo userInfo);

    /**
     * 通过用户id查询用户详细信息
     * @param id
     * @return
     */
    @Select("SELECT C_ID AS id,C_USERNAME as username, C_EMAIL AS email,C_USER_ID as userid,DT_CJSJ as cjsj,C_NUM AS num FROM T_LbyBack_UserInfo where C_USER_ID = #{id}")
    public UserInfo getuserinfoById(@Param("id") String id);

    @Select("SELECT C_ID AS id,C_USERNAME as username, C_EMAIL AS email,C_USER_ID as userid,DT_CJSJ as cjsj,C_NUM AS num FROM T_LbyBack_UserInfo where C_EMAIL = #{email}")
    public List<UserInfo> getUserInfo(@Param("email") String email);

    @Update("")
    public int updateUserInfo(UserInfo userInfo);
}
