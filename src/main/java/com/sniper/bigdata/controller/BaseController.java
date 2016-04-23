package com.sniper.bigdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/4/4.
 */
@Controller
public abstract class BaseController{
    @ExceptionHandler
    public String catchException(HttpServletRequest request,Exception e,Model model){
        String url=request.getRequestURI();
        if(url!=null)
            model.addAttribute("url",url);
        model.addAttribute("exceptionMessage",e.getMessage());
        return "main/exception";
    }
}
