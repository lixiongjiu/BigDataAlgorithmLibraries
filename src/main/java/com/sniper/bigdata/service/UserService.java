package com.sniper.bigdata.service;

import com.sniper.bigdata.dao.IUserDAO;
import com.sniper.bigdata.model.User;

/**
 * Created by Administrator on 2016/3/18.
 */
public class UserService implements IUserService {
    private IUserDAO userDAO;

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String login(User item) {
        User result=userDAO.queryOneUserByName(item.getUserName());
        if(result==null)
            return "用户名不存在";
        if(!result.getPassword().equals(item.getPassword()))
            return "密码错误";
        else
            return "登录成功";
    }

    public String register(User item) {
        if(userDAO.queryOneUserByName(item.getUserName())!=null)
            return "用户名已存在";
        else {
            userDAO.add(item);
            return "注册成功";
        }
    }

    public String update(User item) {
        //更新只能根据用户id来更新，所以要确保用户id有效
        if(userDAO.updateUser(item))
            return "更新成功";
        else
            return "更新失败";
    }

    public void delete(User item) {
        if(item.getUserId()>0){
            userDAO.delUserById(item.getUserId());
        }
        else userDAO.delUserByName(item.getUserName());
    }

    public User[] list() {
        return userDAO.queryMoreUsers();
    }

    public User show(String userName) {
        return userDAO.queryOneUserByName(userName);
    }

    public int getTotalItems(){
        return userDAO.getTotalItems();
    }
}
