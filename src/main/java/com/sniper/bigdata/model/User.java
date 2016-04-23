package com.sniper.bigdata.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * Created by Administrator on 2016/3/16.
 */
public class User {
    private int userId =-1;
    private String userName =null;
    private String password=null;
    private boolean isAdmin =false;
    private String validCode=null;

    public User(){}

    public User(String userName, String password,boolean isAdmin) {
        this.isAdmin = isAdmin;
        this.password = password;
        this.userName = userName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    @NotEmpty(message = "验证码不能为空")
    @Size(min = 4,max = 4,message = "验证码长度为4位")
    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Size(min = 3,max = 6,message = "密码长度应该在3-6之间")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "用户名不能为空")
    @Size(min=2,max = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
