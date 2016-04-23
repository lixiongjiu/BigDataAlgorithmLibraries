package com.sniper.bigdata.controller;

import com.sniper.bigdata.model.Record;
import com.sniper.bigdata.model.User;
import com.sniper.bigdata.service.IRecordService;
import com.sniper.bigdata.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/3/28.
 */
@Controller
@RequestMapping("/record")
public class RecordsController extends BaseController{
    @Autowired
    private IRecordService recordService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listAllRecords(HttpServletRequest request,Model model){
        String userName= CommonUtils.getUserName(request);

        if(userName!=null){
            model.addAttribute("userName",userName);
            User user=new User();
            user.setUserName(userName);
            Record[] records=recordService.queryAllRecords(user);
            if(records!=null){
                model.addAttribute("recordList",records);
                model.addAttribute("recordNum",records.length);
            }
        }
        return "record/list";
    }

}
