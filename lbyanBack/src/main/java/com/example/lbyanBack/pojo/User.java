package com.example.lbyanBack.pojo;

public class User {

    /**
     * 用户id
     */
    private String id;

    /**
     * 密码
     */
    private String password;

    /**
     * 唯一账号-前端显示
     */
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User(String id, String password, String account) {
        this.id = id;
        this.password = password;
        this.account = account;
    }

    public User() {
    }
}
