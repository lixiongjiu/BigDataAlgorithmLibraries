package com.sniper.bigdata.controller;

import com.sniper.bigdata.utils.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LXJ on 2016/4/14.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    @RequestMapping(value = "/contact")
    public String toContact(HttpServletRequest request,Model model){
        String userName= CommonUtils.getUserName(request);
        if(userName!=null)
            model.addAttribute("userName",userName);
        return "main/contact";
    }

}
