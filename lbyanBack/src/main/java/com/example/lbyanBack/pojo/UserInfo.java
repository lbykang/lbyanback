package com.example.lbyanBack.pojo;

import java.util.Date;

public class UserInfo {

    /** 用户信息id */
    private String id;

    /** 姓名 */
    private String username;

    /** 电话号码 */
    private String num;

    /** 邮箱 */
    private String email;

    /** 用户id */
    private String userid;

    /** 创建人 */
    private String cjr;

    /** 创建时间 */
    private Date cjsj;

    /** 更新时间 */
    private Date gxsj;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

}
