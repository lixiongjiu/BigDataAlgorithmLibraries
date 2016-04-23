package com.sniper.bigdata.dao;

import com.sniper.bigdata.database.JdbcUtils;
import com.sniper.bigdata.model.Record;
import com.sniper.bigdata.model.User;

/**
 * Created by Administrator on 2016/3/16.
 */
public interface IRecordDAO {
    public void setJdbcUtils(JdbcUtils jdbcUtils);
    public void addRecord(Record record);
    public String delRecordByRecordId(int recordId);
    public String delRecordByUserId(int userId);
    public String  delRecordByStatusId(int statusId);
    public String delRecordByUserIdAndStatusId(int userId,int statusId);
    public String delRecordByStoreName(String storeName);
    public String updateRecord(Record record);
    public Record[] queryRecordsByUserId(int userId);
    public Record[] queryRecordsByStatusId(int statusId);
    public Record[] queryRecordsByUserIdAndStatusId(int userId,int statusId);
}
