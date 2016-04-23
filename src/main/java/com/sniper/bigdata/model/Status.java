package com.sniper.bigdata.model;

/**
 * Created by Administrator on 2016/3/16.
 */
public class Status {
    private int status_id=-1;
    private String status_name=null;
    private String status_desc=null;

    public Status( String status_name,String status_desc) {
        this.status_desc = status_desc;
        this.status_name = status_name;
    }

    public Status(int status_id, String status_name,String status_desc) {
        this.status_desc = status_desc;
        this.status_id = status_id;
        this.status_name = status_name;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public Status() {

    }

    public String getStatus_desc() {
        return status_desc;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_desc(String status_desc) {
        this.status_desc = status_desc;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
