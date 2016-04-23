package com.bigdata.factory.test;

import com.sniper.bigdata.dao.IUserDAO;
import com.sniper.bigdata.database.JdbcUtils;
import com.sniper.bigdata.factory.DAOFactory;
import org.junit.Test;

/**
 * Created by Administrator on 2016/3/17.
 */
public class FactoryTest {
    @Test
    public void testDAOFacotry(){
        JdbcUtils jdbcUtils=new JdbcUtils();
        IUserDAO userDAO= (IUserDAO)DAOFactory.getDAOInstance("com.sniper.bigdata.dao.UserDAOImpl");
        userDAO.setJdbcUtils(jdbcUtils);
    }
}
