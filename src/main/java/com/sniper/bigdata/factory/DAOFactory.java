package com.sniper.bigdata.factory;

/**
 * Created by Administrator on 2016/3/17.
 */
public class DAOFactory {
    public static Object getDAOInstance(String DAOName){
        try {
            return Class.forName(DAOName).newInstance();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
