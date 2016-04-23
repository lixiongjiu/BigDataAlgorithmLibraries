package com.sniper.bigdata.controller;

import com.sniper.bigdata.service.IRunService;
import com.sniper.bigdata.threads.BufferReaders;
import com.sniper.bigdata.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2016/3/25.
 */
@Controller
@RequestMapping("/run")
public class RunController extends BaseController{
    @Autowired
    private IRunService runService;

    @RequestMapping(value = "/commit")
    public String commitAndRun(@RequestParam(value = "storeName",required = true)String storeName,
                               HttpServletRequest request){

        String contextPath=request.getSession().getServletContext()
                .getRealPath("/");

        runService.run(storeName,contextPath);
        return "run/status";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String startHadoop() {
        runService.startHadoop();
        return "run/status";

    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public String stopHadoop() {
        runService.stopHadoop();
        return "run/status";
    }

    @RequestMapping(value = "/shell", method = RequestMethod.GET)
    public String toRemoteShell(Model model,HttpServletRequest request) {
        String userName= CommonUtils.getUserName(request);
        model.addAttribute("userName",userName);
        return "run/remoteshell";
    }

    @RequestMapping(value = "/shell", method = RequestMethod.POST)
    @ResponseBody
    public String remoteShell(@RequestParam(value = "cmd", required = true) String cmd) {
        if (cmd == null || cmd == "") {
            System.out.println("执行命令"+cmd);
            return "命令行不能为空";
        } else {
            runService.setCmd(cmd);
            runService.run();
            return "ok";
        }
    }

    @RequestMapping(value = "/status",method = RequestMethod.GET)
    @ResponseBody
    public Map getStdout(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        Map log = new HashMap();
        try {
            while (BufferReaders.already == false)
                Thread.sleep(100);
            System.out.println("消费者已经获知可以读取");
            String line = null;
            if ((line = BufferReaders.stdout.readLine()) != null) {
                log.put("log", line);
                return log;
            } else {
                //己方消费完毕，通知对方关闭资源
                BufferReaders.end();
                runService.release();
                System.out.println("消费者已经告诉生产者：我读完了");
                log.put("log", "end");
                return log;
            }
        } catch (Exception e) {
            e.printStackTrace();

            //己方消费时出错，通知对方关闭资源
            BufferReaders.end();
            runService.release();
            log.put("log", "err");
            return log;
        }
    }
}
