package com.sniper.bigdata.service;

import com.sniper.bigdata.dao.IRecordDAO;
import com.sniper.bigdata.dao.IStatusDAO;
import com.sniper.bigdata.dao.IUserDAO;
import com.sniper.bigdata.model.Record;
import com.sniper.bigdata.model.Status;
import com.sniper.bigdata.model.User;

/**
 * Created by Administrator on 2016/3/28.
 */
public class RecordService implements IRecordService {
    private IRecordDAO recordDAO=null;
    private IStatusDAO statusDAO=null;
    private IUserDAO userDAO=null;

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setStatusDAO(IStatusDAO statusDAO) {
        this.statusDAO = statusDAO;
    }

    public void setRecordDAO(IRecordDAO recordDAO) {
        this.recordDAO=recordDAO;
    }

    public void addRecord(Record record) {

        //确保用户id和状态id都存在
        if(record.getStatus_id()<=0) {
            Status status = statusDAO.queryOneStatusByName(record.getStatusName());
            record.setStatus_id(status.getStatus_id());
        }
        if(record.getUserId()<=0){
            User user=userDAO.queryOneUserByName(record.getUserName());
            record.setUserId(user.getUserId());
        }
        recordDAO.addRecord(record);
    }
    public String delRecordByJarName(String storeName){
        return recordDAO.delRecordByStoreName(storeName);
    }

    public String delRecordItem(Record record) {
        return recordDAO.delRecordByRecordId(record.getRecordId());
    }

    public String delSuccessfulRecords(User user) {
        if(user.getUserId()<=0){
            user=userDAO.queryOneUserByName(user.getUserName());
            if(user==null)
                return "用户id无效，无法删除";
        }
        int successId=statusDAO.queryOneStatusByName("successful").getStatus_id();
        if(successId<=0)
            return "未找到成功记录";
        return recordDAO.delRecordByUserIdAndStatusId(user.getUserId(),successId);
    }

    public String delUnfinishedRecords(User user) {
        if(user.getUserId()<=0){
            user=userDAO.queryOneUserByName(user.getUserName());
            if(user==null)
                return "用户id无效，无法删除";
        }
        int unfinishedId=statusDAO.queryOneStatusByName("unfinished").getStatus_id();
        if(unfinishedId<=0)
            return "未找到没有完成的记录";
        return recordDAO.delRecordByUserIdAndStatusId(user.getUserId(),unfinishedId);
    }

    public String delFailedRecords(User user) {
        if(user.getUserId()<=0){
            user=userDAO.queryOneUserByName(user.getUserName());
            if(user==null)
                return "用户id无效，无法删除";
        }
        int failedId=statusDAO.queryOneStatusByName("failed").getStatus_id();
        if(failedId<=0)
            return "未找到失败记录";
        return recordDAO.delRecordByUserIdAndStatusId(user.getUserId(),failedId);
    }

    public String delUserAllRecords(User user) {
        if(user.getUserId()<=0){
            user=userDAO.queryOneUserByName(user.getUserName());
            if(user==null)
                return "用户id无效，无法删除";
        }
        return recordDAO.delRecordByUserId(user.getUserId());
    }

    public Record[] queryAllRecords(User user) {
        if(user.getUserId()<=0){
            user=userDAO.queryOneUserByName(user.getUserName());
            if(user==null)
                return null;
        }
        return recordDAO.queryRecordsByUserId(user.getUserId());
    }

    public Record[] querySuccessfulRecords(User user) {
        if(user.getUserId()<=0){
            user=userDAO.queryOneUserByName(user.getUserName());
            if(user==null)
                return null;
        }
        int successId=statusDAO.queryOneStatusByName("successful").getStatus_id();
        if(successId<=0)
            return null;
        return recordDAO.queryRecordsByUserIdAndStatusId(user.getUserId(),successId);
    }

    public Record[] queryUnfinishedRecords(User user) {
        if(user.getUserId()<=0){
            user=userDAO.queryOneUserByName(user.getUserName());
            if(user==null)
                return null;
        }
        int unfinishedId=statusDAO.queryOneStatusByName("unfinished").getStatus_id();
        if(unfinishedId<=0)
            return null;
        return recordDAO.queryRecordsByUserIdAndStatusId(user.getUserId(),unfinishedId);
    }

    public Record[] queryFailedRecords(User user) {
        if(user.getUserId()<=0){
            user=userDAO.queryOneUserByName(user.getUserName());
            if(user==null)
                return null;
        }
        int failedId=statusDAO.queryOneStatusByName("failed").getStatus_id();
        if(failedId<=0)
            return null;
        return recordDAO.queryRecordsByUserIdAndStatusId(user.getUserId(),failedId);
    }

    private User checkUserId(User user){
        if(user.getUserId()<=0){
            User item=userDAO.queryOneUserByName(user.getUserName());
            return item;
        }
        else
            return user;
    }
    private int getStatusId(String statusName){
        return statusDAO.queryOneStatusByName(statusName).getStatus_id();
    }
    public String updateRecord(Record record) {
        return null;
    }
}
