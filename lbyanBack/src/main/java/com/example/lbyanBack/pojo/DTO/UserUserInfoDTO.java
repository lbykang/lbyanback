package com.example.lbyanBack.pojo.DTO;

public class UserUserInfoDTO {

    /** 用户id */
    private String id;

    /** 姓名 */
    private String username;

    /** 电话号码 */
    private String num;

    /** 邮箱 */
    private String email;

    /** 密码 */
    private String password;

    /** 唯一账号-前端显示 */
    private String account;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public UserUserInfoDTO(String id, String password, String account, String username, String num, String email) {
        this.id = id;
        this.password = password;
        this.account = account;
        this.username = username;
        this.num = num;
        this.email = email;
    }

    public UserUserInfoDTO() {
    }

}
