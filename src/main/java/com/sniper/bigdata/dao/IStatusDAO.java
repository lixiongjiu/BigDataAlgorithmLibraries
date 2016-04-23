package com.sniper.bigdata.dao;

import com.sniper.bigdata.database.JdbcUtils;
import com.sniper.bigdata.model.Status;

/**
 * Created by Administrator on 2016/3/16.
 */
public interface IStatusDAO {
    public void setJdbcUtils(JdbcUtils jdbcUtils);
    public void addStatus(Status status);
    public void delStatusById(int id);
    public void delStatusByName(String statusName);
    public void updateStatus(Status status);
    public Status[] queryMoreStatus();
    public Status queryOneStatusByName(String statusName);
    public Status queryOneStatusById(int statusId);
}
