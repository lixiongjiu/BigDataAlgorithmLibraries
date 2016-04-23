package com.sniper.bigdata.dao;

import com.sniper.bigdata.database.JdbcUtils;
import com.sniper.bigdata.model.User;

import javax.jws.soap.SOAPBinding;

/**
 * Created by Administrator on 2016/3/16.
 */
public interface IUserDAO {
    public void setJdbcUtils(JdbcUtils jdbcUtils);
    public JdbcUtils getJdbcUtils();
    public void add(User user);
    public void delUserById(int id);
    public void delUserByName(String userName);
    public User[] queryMoreUsers();
    public User queryOneUserByName(String userName);
    public User queryOneUserById(int id);
    public boolean updateUser(User user);
    public int getTotalItems();
}
