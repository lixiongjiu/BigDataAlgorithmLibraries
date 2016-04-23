package com.bigdata.service.test;

import com.sniper.bigdata.dao.IUserDAO;
import com.sniper.bigdata.dao.UserDAOImpl;
import com.sniper.bigdata.database.JdbcUtils;
import com.sniper.bigdata.model.User;
import com.sniper.bigdata.service.IUserService;
import com.sniper.bigdata.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2016/3/18.
 */
public class UserServiceTest {
    JdbcUtils jdbcUtils=new JdbcUtils();
    IUserDAO userDAO=new UserDAOImpl();
    IUserService modelservice=new UserService();
    @Before
    public void setUp(){
        jdbcUtils.openConnection();
        userDAO.setJdbcUtils(jdbcUtils);
        modelservice.setUserDAO(userDAO);
    }
    @Test
    public void testLogin(){
        User item=new User();
        item.setUserName("张三1");
        item.setPassword("asfd");
        System.out.println(modelservice.login(item));
    }
    @Test
    public void testRegister(){
        User item=new User();
        item.setUserName("张三1");
        item.setPassword("asfd");
        System.out.println(modelservice.register(item));
    }
    @Test
    public void testDelete(){
        User item=new User();
        item.setUserName("张三1");
        item.setPassword("asfd");
        modelservice.delete(item);
    }
    @Test
    public void testUpdate(){
        User item=new User();
        item.setUserId(2);
        item.setUserName("张三");
        item.setPassword("asfd");
        System.out.println(modelservice.update(item));
    }
    @Test
    public void testList(){
        for(User item:modelservice.list()){
            System.out.println(item.getUserName());

        }
    }
    @After
    public void tearDown(){
        jdbcUtils.releaseConnection();
    }
}
