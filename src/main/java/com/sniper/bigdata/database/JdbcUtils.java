package com.sniper.bigdata.database;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/16.
 */
public class JdbcUtils {
    private Logger logger=null;

    //??????????????
    private Connection connection=null;
    //????sql??????????
    private PreparedStatement preparedStatement=null;
    private Statement statement=null;
    //?????????????????
    private ResultSet resultSet=null;
    //???????????????????
    private String url="jdbc:mysql://localhost:3306/bigdataalgorithmslibraries";
    //???????????
    private String user="root";
    //??????????
    private String password="123456";
    private String sql=null;
    //?????????????????
    private final String DRIVER="com.mysql.jdbc.Driver";
    /*private Driver driver=null;*/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JdbcUtils(){
        logger=Logger.getLogger(JdbcUtils.class);
        try {
            logger=Logger.getLogger(JdbcUtils.class);
           /* PropertyConfigurator.configure("src/log4j.properties");*/
            //???mysql jdbc??????
            Class.forName(DRIVER);
            logger.info("Driver register successfully.");
            openConnection();

        }catch (ClassNotFoundException e){
            logger.error("Driver register failed.");
            e.printStackTrace();
        }

    }

    public Connection openConnection(){
        try {
            if(connection==null || connection.isClosed()) {
                connection= DriverManager.getConnection(url,user,password);
                logger.info("Database to "+url+" has connected.");
            }
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Database to "+url+" connects failed.");
        }
        return connection;
    }
    public PreparedStatement getPreparedStatement(String sql){
        try {
            preparedStatement=connection.prepareStatement(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return preparedStatement;
    }
    public boolean executeUpdate(String sql,Object[] columns){
        logger.info("Execute update_cmd ["+sql+"].");
        boolean flag=false;
        int result=-1;
        try {
            preparedStatement=connection.prepareStatement(sql);

            //???sql???????
            if(columns!=null && columns.length>0){
                for(int i=0;i<columns.length;i++)
                    preparedStatement.setObject(i+1,columns[i]);
            }

            result=preparedStatement.executeUpdate();
            flag=result>0?true:false;
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Execute update_cmd ["+sql+"] failed.");
        }
        finally {
            releaseStatement();
        }
        return flag;
    }
    public boolean executeCmd(String sql,Object[] columns){
        logger.info("Execute [insert|delete|create]_cmd ["+sql+"].");
        boolean flag=false;
        try {
            preparedStatement=connection.prepareStatement(sql);
            //???sql???????
            if( columns!=null && columns.length>0 ) {
                for (int i = 0; i < columns.length; i++)
                    preparedStatement.setObject(i + 1, columns[i]);
            }

            flag=preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Execute [insert|delete|create]_cmd ["+sql+"] failed.");
        }
        finally {
            releaseStatement();
        }
        return flag;
    }
    public List<Map> executeQueryMore(String sql, Object[] columns){
        logger.info("Execute query_cmd ["+sql+"].");
        List<Map> resultList=null;
        try {
            preparedStatement=connection.prepareStatement(sql);

            //???sql???????
            if( columns!=null && columns.length>0 ) {
                for (int i = 0; i < columns.length; i++)
                    preparedStatement.setObject(i + 1, columns[i]);
            }
            //?????????
            resultSet=preparedStatement.executeQuery();
            //?????????
            if(resultSet.next()){
                resultSet.beforeFirst();
                ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
                int col_len=resultSetMetaData.getColumnCount();
                resultList=new ArrayList<Map>();
                while(resultSet.next()){
                    Map itemMap=new HashMap();
                    for(int i=0;i<col_len;i++){
                        String field_name=resultSetMetaData.getColumnName(i+1);
                        Object field_value=resultSet.getObject(field_name);
                        itemMap.put(field_name,field_value);
                    }
                    resultList.add(itemMap);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Execute query_cmd ["+sql+"] failed.");
        }
        finally {
            releaseStatement();
            return resultList;
        }
    }
    public Map executeQueryOne(String sql, Object[] columns){
        logger.info("Execute query_cmd ["+sql+"].");
        Map resultMap=null;
        try {
            preparedStatement=connection.prepareStatement(sql);

            //填充sql参数
            if( columns!=null && columns.length>0 ) {
                for (int i = 0; i < columns.length; i++)
                    preparedStatement.setObject(i + 1, columns[i]);
            }
            //执行查询
            resultSet=preparedStatement.executeQuery();
            //解析查询结果
            if(resultSet.next()){
                resultSet.beforeFirst();
                ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
                int col_len=resultSetMetaData.getColumnCount();

                resultMap=new HashMap();
                while(resultSet.next()){
                    for(int i=0;i<col_len;i++){
                        String field_name=resultSetMetaData.getColumnName(i+1);
                        Object field_value=resultSet.getObject(field_name);
                        resultMap.put(field_name, field_value);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Execute query_cmd ["+sql+"] failed.");
        }
        finally {
            releaseStatement();
            return resultMap;
        }
    }
    public void releaseStatement(){
        try {
            if(resultSet!=null && !resultSet.isClosed()){
                resultSet.close();
                resultSet=null;
            }
            if(preparedStatement!=null && !preparedStatement.isClosed()){
                preparedStatement.close();
                preparedStatement=null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void releaseConnection(){
        if(resultSet!=null){
            try {
                resultSet.close();
                resultSet=null;
                logger.info("ResultSet closed.");
            }catch (SQLException e){
                e.printStackTrace();
                logger.error("ResultSet close failed.");
            }
        }
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
                preparedStatement=null;
                logger.info("PreparedStatement has closed.");
            }catch (SQLException e){
                e.printStackTrace();
                logger.error("PreparedStatement close failed.");
            }
        }
        if(connection!=null){
            try {
                connection.close();
                connection=null;
                logger.info("Connection has closed.");
            }catch (SQLException e){
                e.printStackTrace();
                logger.error("Connection close failed.");
            }
        }
    }
    @Override
    protected void finalize() throws Throwable {
        releaseConnection();
        Class.forName(null);
        super.finalize();
    }
}
