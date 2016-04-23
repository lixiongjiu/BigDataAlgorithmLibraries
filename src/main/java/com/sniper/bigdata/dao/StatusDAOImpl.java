package com.sniper.bigdata.dao;

import com.sniper.bigdata.database.JdbcUtils;
import com.sniper.bigdata.model.Status;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/17.
 */
public class StatusDAOImpl implements IStatusDAO {
    private JdbcUtils jdbcUtils=null;

    public void setJdbcUtils(JdbcUtils jdbcUtils) {
        this.jdbcUtils = jdbcUtils;
    }

    public void addStatus(Status status) {
        jdbcUtils.executeCmd("insert into status (status_name,status_desc) values(?,?)",
                new Object[]{
                        status.getStatus_name(),
                        status.getStatus_desc(),
                });

    }

    public void delStatusById(int id) {
        if(id>0){
            jdbcUtils.executeCmd("delete from status where status_id=?",new Object[]{id});
            System.out.println("删除成功");
        }else
            System.out.println("id不合法，删除失败");

    }

    public void delStatusByName(String statusName) {
        jdbcUtils.executeCmd("delete from status where status_name like ?",new Object[]{statusName});
    }

    public void updateStatus(Status status) {
        if(status.getStatus_id()>0){
            if(jdbcUtils.executeUpdate("update status set status_name=?,status_desc=? where status_id=?",new Object[]{
                    status.getStatus_name(),
                    status.getStatus_desc(),
                    status.getStatus_id(),
            }))
                System.out.println("更新成功");
            else
                System.out.println("更新失败");
        }
        else
            System.out.println("id不合法，更新失败");
    }

    public Status[] queryMoreStatus() {
        List<Map> resultList=jdbcUtils.executeQueryMore("select * from status",null);
        if(resultList!=null && resultList.size()>0){
            Status[] statuses=new Status[resultList.size()];
            for (int i=0;i<resultList.size();i++){
                statuses[i]=new Status();
                statuses[i].setStatus_desc( (String) resultList.get(i).get("status_desc") );
                statuses[i].setStatus_name( (String) resultList.get(i).get("status_name"));
                statuses[i].setStatus_id( (Integer) resultList.get(i).get("status_id"));
            }
            return statuses;
        }
        else
            return null;
    }

    public Status queryOneStatusByName(String statusName) {
        Map resultMap=jdbcUtils.executeQueryOne("select * from status where status_name like ?",
                new Object[]{statusName});
        if(resultMap!=null){
            Status item=new Status();
            item.setStatus_desc( (String)resultMap.get("status_desc") );
            item.setStatus_id( (Integer)resultMap.get("status_id") );
            item.setStatus_name( (String)resultMap.get("status_name") );
            return item;
        }
        else
            return null;
    }

    public Status queryOneStatusById(int statusId) {
        Map resultMap=jdbcUtils.executeQueryOne("select * from status where status_id=?",
                new Object[]{statusId});
        if(resultMap!=null){
            Status item=new Status();
            item.setStatus_desc( (String)resultMap.get("status_desc") );
            item.setStatus_id( (Integer)resultMap.get("status_id") );
            item.setStatus_name((String) resultMap.get("status_name"));
            return item;
        }
        else
            return null;
    }
}
