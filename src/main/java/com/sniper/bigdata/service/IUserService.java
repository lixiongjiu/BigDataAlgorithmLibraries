package com.sniper.bigdata.service;

import com.sniper.bigdata.dao.IUserDAO;
import com.sniper.bigdata.model.User;

/**
 * Created by Administrator on 2016/3/18.
 */
public interface IUserService {
    public IUserDAO getUserDAO();
    public void setUserDAO(IUserDAO userDAO);
    public String login(User item);
    public String register(User item);
    public String update(User item);
    public void delete(User item);
    public User[] list();
    public User show(String userName);
    public int getTotalItems();
}
