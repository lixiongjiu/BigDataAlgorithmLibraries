package com.sniper.bigdata.filter;

import com.sniper.bigdata.utils.CommonUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/3/25.
 */
public class SessionFilter extends OncePerRequestFilter {

    //放过的url
    private String[] passUrls=new String[]{"login","home","register","notice"};
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        //请求的url
        String targetUrl=request.getRequestURI();
        boolean doFilter=true;
        String method=request.getMethod();

        for (String uriItem:passUrls){
            //放行
            if(targetUrl.indexOf(uriItem)!=-1){
                doFilter=false;
                break;
            }
        }
        if(doFilter){
            //session中没有记录
            String userName= CommonUtils.getUserName(request);
            if(userName!=null)
                filterChain.doFilter(request,response);
            else
                response.sendRedirect("/user/notice");

        }
        else
        //继续
            filterChain.doFilter(request,response);

    }
}
