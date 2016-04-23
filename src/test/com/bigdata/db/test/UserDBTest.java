package com.bigdata.db.test;

import com.sniper.bigdata.dao.IUserDAO;
import com.sniper.bigdata.dao.UserDAOImpl;
import com.sniper.bigdata.database.JdbcUtils;
import com.sniper.bigdata.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Administrator on 2016/3/16.
 */
public class UserDBTest {
    JdbcUtils jdbcUtils=null;
    @Before
    public void setUp(){
        jdbcUtils=new JdbcUtils();
        jdbcUtils.openConnection();
    }
    @Test
    public void testConn(){
        jdbcUtils.openConnection();
        jdbcUtils.releaseConnection();
    }
    @Test
    public void testInsert(){
        IUserDAO userDAO=new UserDAOImpl();
        userDAO.setJdbcUtils(jdbcUtils);
        User user=new User("李四","2468",false);
        userDAO.add(user);
    }
    @Test
    public void testUpdate(){
        IUserDAO userDAO=new UserDAOImpl();
        userDAO.setJdbcUtils(jdbcUtils);
        User user=new User("李四","246",false);
        user.setUserId(1);
        userDAO.updateUser(user);
    }
    @Test
    public void testQuery(){
        Map result=jdbcUtils.executeQueryOne("select count(*) as count from users",null);
        if(result!=null && result.size()==1){
            System.out.println(result.get("count"));
        }
    }
    @Test
    public void testDel(){
        IUserDAO userDAO=new UserDAOImpl();
        userDAO.setJdbcUtils(jdbcUtils);
        userDAO.delUserById(1);
    }

    @After
    public void tearDown(){
        jdbcUtils.releaseConnection();
    }
}
