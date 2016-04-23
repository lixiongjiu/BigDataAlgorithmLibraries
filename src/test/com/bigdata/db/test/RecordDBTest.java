package com.bigdata.db.test;

import com.sniper.bigdata.dao.IRecordDAO;
import com.sniper.bigdata.dao.RecordDAOImpl;
import com.sniper.bigdata.database.JdbcUtils;
import com.sniper.bigdata.model.Record;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.sql.Date;

/**
 * Created by Administrator on 2016/3/17.
 */
public class RecordDBTest {
    private JdbcUtils jdbcUtils=new JdbcUtils();
    @Before
    public void setUp(){
        jdbcUtils.openConnection();
    }

    @Test
    public void testAdd(){
        IRecordDAO recordDAO=new RecordDAOImpl();
        recordDAO.setJdbcUtils(jdbcUtils);
        Record item=new Record(2,"test4.jar",null,null,2);
        item.setStoreName("asdf13123123.jar");
        item.setJarStorePath("/fasdfa/dsafads/fadsj/.jar");
        recordDAO.addRecord(item);
    }
    @Test
    public void testDel(){
        IRecordDAO recordDAO=new RecordDAOImpl();
        recordDAO.setJdbcUtils(jdbcUtils);
        recordDAO.delRecordByUserId(2);
        recordDAO.delRecordByStatusId(2);
        recordDAO.delRecordByStoreName("asdfsdf.docx");
    }
    @Test
    public void testUpdate(){
        IRecordDAO recordDAO=new RecordDAOImpl();
        recordDAO.setJdbcUtils(jdbcUtils);
        Record item=new Record(2,"k_means.1.0.jar",null,new Date(System.currentTimeMillis()),2);
        item.setStoreName("jasdf1232.jar");
        item.setJarStorePath("/fdsaf/sadfa.jar");
        recordDAO.updateRecord(item);
    }
    @Test
    public void testQuery(){
        IRecordDAO recordDAO=new RecordDAOImpl();
        recordDAO.setJdbcUtils(jdbcUtils);
        /*Record[] result=recordDAO.queryRecordsByUserId();
        if(result!=null)
            System.out.println("result_length:"+result[0].getJar_name());*/
        Record[] result = recordDAO.queryRecordsByStatusId(3);
        for(Record record:result){
            System.out.println(record.getUserName());
            System.out.println(record.getCommit_time());
            System.out.println(record.getJarStorePath());
            System.out.println(record.getStoreName());
        }
    }
    @After
    public void tearDown(){
        jdbcUtils.releaseConnection();
    }
}
