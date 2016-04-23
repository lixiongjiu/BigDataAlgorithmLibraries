package com.sniper.bigdata.model;

import java.util.Date;

/**
 * Created by Administrator on 2016/3/16.
 */
public class Record {
    private int recordId=-1;
    private int userId=-1;
    private int status_id=-1;
    private String jar_name=null;
    private String jar_desc=null;
    private Date commit_time=null;
    private String userName=null;
    private String statusName=null;
    private String storeName=null;
    private String jarStorePath=null;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getJarStorePath() {
        return jarStorePath;
    }

    public void setJarStorePath(String jarStorePath) {
        this.jarStorePath = jarStorePath;
    }

    public Record() {
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Record(Date commit_time, String jar_desc, String jar_name) {
        this.commit_time = commit_time;
        this.jar_desc = jar_desc;
        this.jar_name = jar_name;
    }

    public Record(int userId,String jar_name,String jar_desc,Date commit_time,int status_id) {
        this.commit_time = commit_time;
        this.jar_desc = jar_desc;
        this.jar_name = jar_name;
        this.status_id = status_id;
        this.userId = userId;
    }

    public Date getCommit_time() {
        return commit_time;
    }

    public void setCommit_time(Date commit_time) {
        this.commit_time = commit_time;
    }

    public String getJar_desc() {
        return jar_desc;
    }

    public void setJar_desc(String jar_desc) {
        this.jar_desc = jar_desc;
    }

    public String getJar_name() {
        return jar_name;
    }

    public void setJar_name(String jar_name) {
        this.jar_name = jar_name;
    }
}
