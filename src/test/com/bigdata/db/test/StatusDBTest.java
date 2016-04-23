package com.bigdata.db.test;

import com.sniper.bigdata.dao.IStatusDAO;
import com.sniper.bigdata.dao.IUserDAO;
import com.sniper.bigdata.dao.StatusDAOImpl;
import com.sniper.bigdata.dao.UserDAOImpl;
import com.sniper.bigdata.database.JdbcUtils;
import com.sniper.bigdata.model.Status;
import com.sniper.bigdata.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2016/3/16.
 */
public class StatusDBTest {
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
        IStatusDAO statusDAO=new StatusDAOImpl();
        statusDAO.setJdbcUtils(jdbcUtils);
        Status item=new Status("test3",null);
        statusDAO.addStatus(item);
    }
    @Test
    public void testUpdate(){
        IStatusDAO statusDAO=new StatusDAOImpl();
        statusDAO.setJdbcUtils(jdbcUtils);
        Status item=new Status(0,"successfully","The algorithm you commited has finished successfully");
        statusDAO.updateStatus(item);
    }
    @Test
    public void testQueryAll(){
        IStatusDAO statusDAO=new StatusDAOImpl();
        statusDAO.setJdbcUtils(jdbcUtils);
        Status[] statuses=statusDAO.queryMoreStatus();
        if(statuses.length>0)
            System.out.println(statuses.length);
    }
    @Test
    public void testDel(){
        IStatusDAO statusDAO=new StatusDAOImpl();
        statusDAO.setJdbcUtils(jdbcUtils);
        statusDAO.delStatusByName("test3");
    }
    @Test
    public void testQueryOne(){
        IStatusDAO statusDAO=new StatusDAOImpl();
        statusDAO.setJdbcUtils(jdbcUtils);
        Status result=statusDAO.queryOneStatusByName("failed");
        if(result!=null)
            System.out.println(result.getStatus_name());

        result=statusDAO.queryOneStatusById(1);
        if(result!=null)
            System.out.println(result.getStatus_name());
    }

    @After
    public void tearDown(){
        jdbcUtils.releaseConnection();
    }
}
