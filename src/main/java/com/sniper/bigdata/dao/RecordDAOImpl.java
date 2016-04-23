package com.sniper.bigdata.dao;

import com.sniper.bigdata.database.JdbcUtils;
import com.sniper.bigdata.model.Record;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/17.
 */
public class RecordDAOImpl implements IRecordDAO {
    private final String baseQueryStr="select user_name,user_id,status.status_id,status_name,commit_time,jar_name,jar_desc,jar_store_name,jar_store_path from " +
            "(select user_name,users.user_id,status_id,jar_name,jar_desc,commit_time,jar_store_name,jar_store_path from users join records\n" +
            " on users.user_id=records.user_id) a" +
            " join status on a.status_id=status.status_id ";

    private JdbcUtils jdbcUtils=null;
    public void setJdbcUtils(JdbcUtils jdbcUtils) {
        this.jdbcUtils=jdbcUtils;

    }

    public void addRecord(Record record) {
        jdbcUtils.executeCmd("insert into records (user_id,jar_name,jar_desc,commit_time,status_id,jar_store_path,jar_store_name) values(?,?,?,?,?,?,?)",
                new Object[]{
                        record.getUserId(),
                        record.getJar_name(),
                        record.getJar_desc(),
                        record.getCommit_time(),
                        record.getStatus_id(),
                        record.getJarStorePath(),
                        record.getStoreName()
                });
    }

    public String  delRecordByUserId(int userId) {
        if(userId>0){
            jdbcUtils.executeCmd("delete from records where user_id=?",new Object[]{userId});
            return "插入成功";
        }
        else
            return "id不合法，插入失败";

    }

    public String delRecordByStoreName(String storeName) {
        if(storeName!=null) {
            jdbcUtils.executeCmd("delete from records where jar_store_name like ?", new Object[]{storeName});
            return "删除成功";
        }
        else
            return "文件名不存在，删除失败";
    }

    public String delRecordByStatusId(int statusId) {
        if(statusId>0){
            jdbcUtils.executeCmd("delete from records where status_id=?",new Object[]{statusId});
            return "删除成功";
        }
        else{
            return "id不合法，删除失败";
        }
    }

    public String delRecordByUserIdAndStatusId(int userId, int statusId) {
        if(userId>0 && statusId>0){
            jdbcUtils.executeCmd("delete from records where user_id=? and status_id=?",
                    new Object[]{
                            userId,
                            statusId
                    });
            return "删除成功";
        }
        else
            return "id不合法，删除失败";

    }

    public String delRecordByRecordId(int recordId){
        if(recordId>0){
            jdbcUtils.executeCmd("delete from records where record_id=?",new Object[]{recordId});
            return "删除成功";
        }
        else
            return "id不合法，删除失败";
    }
    public String updateRecord(Record record) {
        if(record!=null && record.getStatus_id()>0 && record.getUserId()>0) {
            if(jdbcUtils.executeUpdate("update records set jar_name=?,jar_desc=?,commit_time=?,jar_store_name=?,jar_store_path=?" +
                    " where user_id=? and status_id=?",
                    new Object[]{
                            record.getJar_name(),
                            record.getJar_desc(),
                            record.getCommit_time(),
                            record.getStoreName(),
                            record.getJarStorePath(),
                            record.getUserId(),
                            record.getStatus_id()
                    })){
                return "更新成功";
            }else
                return "更新失败";
        }else
            return "id不合法，更新失败";
    }


    public Record[] queryRecordsByUserId(int userId) {
        List<Map> resultMap=jdbcUtils.executeQueryMore(baseQueryStr+"where user_id=?",new Object[]{userId});
        if(resultMap!=null && resultMap.size()>0){
            Record[] records=new Record[resultMap.size()];
            for(int i=0;i<resultMap.size();i++){
                records[i]=new Record();
                records[i].setUserId( (Integer)resultMap.get(i).get("user_id"));
                records[i].setUserName( (String)resultMap.get(i).get("user_name"));
                records[i].setJar_name( (String)resultMap.get(i).get("jar_name"));
                records[i].setJar_desc( (String)resultMap.get(i).get("jar_desc"));
                records[i].setCommit_time( (Date)resultMap.get(i).get("commit_time"));
                records[i].setStatus_id( (Integer)resultMap.get(i).get("status_id"));
                records[i].setStatusName( (String)resultMap.get(i).get("status_name"));
                records[i].setJarStorePath( (String)resultMap.get(i).get("jar_store_path"));
                records[i].setStoreName( (String)resultMap.get(i).get("jar_store_name"));
            }
            return records;
        }
        else
            return null;
    }

    public Record[] queryRecordsByStatusId(int statusId) {
        List<Map> resultMap=jdbcUtils.executeQueryMore(baseQueryStr+"where status.status_id=?",new Object[]{statusId});
        if(resultMap!=null && resultMap.size()>0){
            Record[] records=new Record[resultMap.size()];
            for(int i=0;i<resultMap.size();i++){
                records[i]=new Record();
                records[i].setUserId( (Integer)resultMap.get(i).get("user_id"));
                records[i].setJar_name( (String)resultMap.get(i).get("jar_name"));
                records[i].setJar_desc( (String)resultMap.get(i).get("jar_desc"));
                records[i].setCommit_time( (Date)resultMap.get(i).get("commit_time"));
                records[i].setStatus_id( (Integer)resultMap.get(i).get("status_id"));
                records[i].setJarStorePath( (String)resultMap.get(i).get("jar_store_path"));
                records[i].setStoreName( (String)resultMap.get(i).get("jar_store_name"));
                records[i].setUserName( (String)resultMap.get(i).get("user_name"));
                records[i].setStatusName( (String)resultMap.get(i).get("status_name"));
            }
            return records;
        }
        else
            return null;
    }

    public Record[] queryRecordsByUserIdAndStatusId(int userId, int statusId) {
        List<Map> resultMap=jdbcUtils.executeQueryMore(baseQueryStr+
                "where user_id=? and status.status_id=?",
                new Object[]{userId,statusId});
        if(resultMap!=null && resultMap.size()>0){
            Record[] records=new Record[resultMap.size()];
            for(int i=0;i<resultMap.size();i++){
                records[i]=new Record();
                records[i].setUserId( (Integer)resultMap.get(i).get("user_id"));
                records[i].setJar_name( (String)resultMap.get(i).get("jar_name"));
                records[i].setJar_desc( (String)resultMap.get(i).get("jar_desc"));
                records[i].setCommit_time( (Date)resultMap.get(i).get("commit_time"));
                records[i].setStatus_id( (Integer)resultMap.get(i).get("status_id"));
                records[i].setJarStorePath( (String)resultMap.get(i).get("jar_store_path"));
                records[i].setStoreName( (String)resultMap.get(i).get("jar_store_name"));
                records[i].setUserName( (String)resultMap.get(i).get("user_name"));
                records[i].setStatusName( (String)resultMap.get(i).get("status_name"));
            }
            return records;
        }
        else
            return null;
    }
}
