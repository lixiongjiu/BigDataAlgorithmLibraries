package com.sniper.bigdata.controller;

import com.sniper.bigdata.io.FileOperateUtil;
import com.sniper.bigdata.model.Record;
import com.sniper.bigdata.service.IRecordService;
import com.sniper.bigdata.utils.CommonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/20.
 */
@Controller
@RequestMapping(value = "/io")
public class FileOperateController extends BaseController{
    @Autowired
    private IRecordService recordService;
    private Logger logger=Logger.getLogger(FileOperateController.class);

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String toUpload(Model model,HttpServletRequest request){
        String userName= CommonUtils.getUserName(request);
        if(userName!=null)
            model.addAttribute("userName",userName);
        return "io/upload";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(HttpServletRequest request,Model model){
        List<Map<String,Object>> result= FileOperateUtil.upload(request);

        //将上传的jar包信息存储到数据库

        //获取用户名
        String userName=CommonUtils.getUserName(request);
        if(userName!=null)
            model.addAttribute("userName",userName);
        for(Map item:result)
        {
            if(item.get("status").equals("ok")){
                Record record=new Record();
                record.setUserName(userName);
                record.setStatusName("unfinished");
                record.setJar_name( (String)item.get(FileOperateUtil.REALNAME) );
                record.setStoreName( (String)item.get(FileOperateUtil.STORENAME) );
               /* record.setJarStorePath( (String)item.get("storePath") );*/
                record.setCommit_time( (Date)item.get(FileOperateUtil.CREATETIME) );
               /* System.out.println(record.getCommit_time());
                System.out.println(record.getStoreName());
                System.out.println(record.getUserName());*/
                recordService.addRecord(record);
            }

        }
        model.addAttribute("file_list",result);
        return "io/list";
    }

    @RequestMapping(value ="/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map delJarFile(@RequestParam(value = "fileName",required = true)String fileName,
                           HttpServletRequest request,
                           HttpServletResponse response){
        //从磁盘删除文件
        String resultFile=FileOperateUtil.delFile(request, fileName);
        logger.info("Deleting file "+fileName+" result:"+resultFile);

        //从数据库删除记录
        String resultDb=recordService.delRecordByJarName(fileName);
        logger.info("Deleting record from database,result:"+resultDb);
        Map ret=new HashMap();
        if(resultDb.equals("删除成功") && resultFile.equals("删除成功"))
            ret.put("state","ok");
        else
            ret.put("state","err");
        return ret;
    }
    @RequestMapping(value = "/download")
    public void download(@RequestParam(value = "storeName",required = true)String storeName,
                           @RequestParam(value = "realName",required = false)String realName,
                           @RequestParam(value = "type",required = false)String type,
                            HttpServletRequest request,
                           HttpServletResponse response){
        if(realName==null)
            realName=storeName;
        FileOperateUtil.download(request,response,storeName,realName,type);
    }
}
