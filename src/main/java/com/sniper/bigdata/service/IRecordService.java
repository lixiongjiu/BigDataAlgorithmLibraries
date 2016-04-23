package com.sniper.bigdata.service;

import com.sniper.bigdata.dao.IRecordDAO;
import com.sniper.bigdata.dao.IStatusDAO;
import com.sniper.bigdata.dao.IUserDAO;
import com.sniper.bigdata.model.Record;
import com.sniper.bigdata.model.User;

/**
 * Created by Administrator on 2016/3/28.
 */
public interface IRecordService {
    //setter
    public void setRecordDAO(IRecordDAO recordDAO);
    public void setStatusDAO(IStatusDAO statusDAO);
    public void setUserDAO(IUserDAO userDAO);

    //crud
    public void addRecord(Record record);
    public String delRecordByJarName(String storeName);
    public String delRecordItem(Record record);
    public String delSuccessfulRecords(User user);
    public String delUnfinishedRecords(User user);
    public String delFailedRecords(User user);
    public String delUserAllRecords(User user);
    public Record[] queryAllRecords(User user);
    public Record[] querySuccessfulRecords(User user);
    public Record[] queryUnfinishedRecords(User user);
    public Record[] queryFailedRecords(User user);
    public String updateRecord(Record record);
}
