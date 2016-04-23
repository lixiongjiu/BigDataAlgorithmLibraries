package com.sniper.bigdata.dao;

import com.sniper.bigdata.database.JdbcUtils;
import com.sniper.bigdata.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/16.
 */
public class UserDAOImpl implements IUserDAO {
    private JdbcUtils jdbcUtils=null;

    public JdbcUtils getJdbcUtils() {
        return jdbcUtils;
    }

    public void setJdbcUtils(JdbcUtils jdbcUtils) {
        this.jdbcUtils = jdbcUtils;
    }

    public void add(User user) {
        jdbcUtils.executeCmd("insert into users (user_name,password,is_admin) values(?,?,?)",new Object[]{
                user.getUserName(),
                user.getPassword(),
                user.getIsAdmin()
                }
        );
    }

    public void delUserById(int id) {
        jdbcUtils.executeCmd("delete from users where user_id=?",new Object[]{id});

    }

    public void delUserByName(String userName) {
        jdbcUtils.executeCmd("delete from users where user_name like ?",new Object[]{userName});

    }
    //查询输出所有的非管理者
    public User[] queryMoreUsers() {
        List<Map> resultList=jdbcUtils.executeQueryMore("select * from users where is_admin=false",null);
        if(resultList!=null && resultList!=null){
            User[] users=new User[resultList.size()];
            for(int i=0;i<resultList.size();i++){
                users[i]=new User();
                users[i].setUserId( (Integer)resultList.get(i).get("user_id"));
                users[i].setIsAdmin((Boolean)(resultList.get(i).get("is_admin")));
                users[i].setPassword((String)(resultList.get(i).get("password")));
                users[i].setUserName((String)(resultList.get(i).get("user_name")));
            }
            return users;
        }
       else
            return null;
    }

    public User queryOneUserByName(String userName) {
        Map resultMap=jdbcUtils.executeQueryOne("select * from users where user_name like ?",
                new Object[]{userName}
        );
        if(resultMap!=null){
            User item=new User();
            item.setUserId( (Integer)resultMap.get("user_id"));
            item.setIsAdmin((Boolean)(resultMap.get("is_admin")));
            item.setPassword((String)(resultMap.get("password")));
            item.setUserName((String)(resultMap.get("user_name")));
            return item;
        }
        else
            return null;
    }

    public User queryOneUserById(int id) {
        Map resultMap=jdbcUtils.executeQueryOne("select * from users where user_id="+id,null);
        if(resultMap!=null){
            User item=new User();
            item.setUserId(id);
            item.setIsAdmin((Boolean)(resultMap.get("is_admin")));
            item.setPassword((String)(resultMap.get("password")));
            item.setUserName((String)(resultMap.get("user_name")));
            return item;
        }
        else
            return null;
    }

    public boolean updateUser(User user) {
        if(user.getUserId()>0){
            if(jdbcUtils.executeUpdate("update users set user_name=?,password=?,is_admin=? where user_id=?",new Object[]{
                    user.getUserName(),
                    user.getPassword(),
                    user.getIsAdmin(),
                    user.getUserId(),
            })){
                System.out.println("更新成功");
                return true;
            }
            else
                System.out.println("更新失败");
        }
        else
            System.out.println("用户id不合法");
        return false;
    }
    public int getTotalItems(){
        Map result=jdbcUtils.executeQueryOne("select count(*) as count from users",null);
        if(result!=null && result.size()==1)
            return (Integer)result.get("count");
        else
            return 0;
    }

}
