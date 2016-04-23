package com.sniper.bigdata.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/3/29.
 */
public class CommonUtils {
    public static String getUserName(HttpServletRequest request){

        String userName=(String)request.getSession().getAttribute("user");
        if(userName==null){
            Cookie[] cookies=request.getCookies();
            if(cookies!=null){
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("user")){
                        userName=cookie.getValue();
                        break;
                    }
                }
            }
        }
        return userName;
    }

    public static void addCookie(String key,String value,int maxAge,String path,
                                  HttpServletRequest request,
                                 HttpServletResponse response)
            throws UnsupportedEncodingException{
        //session
        request.getSession().setAttribute(key,value);

        //cookie
        Cookie cookie=new Cookie(key, URLEncoder.encode(value,"UTF-8"));
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        response.addCookie(cookie);
    }
    public static void delCookie(String key,HttpServletRequest request,HttpServletResponse response){
        //删除session
        request.getSession().removeAttribute(key);

        //清除cookie(通过设置该cookie的生存时间为0)
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals(key)) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
}
