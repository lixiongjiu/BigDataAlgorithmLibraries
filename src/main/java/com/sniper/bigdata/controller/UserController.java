package com.sniper.bigdata.controller;

import com.sniper.bigdata.model.User;
import com.sniper.bigdata.service.IUserService;
import com.sniper.bigdata.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/18.
 */
@Controller
@RequestMapping("/user")

public class UserController extends BaseController{
    private int cookieLife;
    private int pageSize;

    public void setCookieLife(int cookieLife) {
        this.cookieLife = cookieLife;
    }

    @Autowired
    private IUserService userService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model,HttpServletRequest request){
        String userName=CommonUtils.getUserName(request);
        if(userName!=null)
            model.addAttribute("userName",userName);
        model.addAttribute("result",null);
        model.addAttribute("user",new User());
        return "user/login";
    }
    @RequestMapping(value = "/{userName}",method = RequestMethod.GET)
    public String showPersonnel(@PathVariable(value = "userName")String userName,Model model){
        if(userName!=null) {
            model.addAttribute("userName", userName);
            return "user/personnel";
        }
        else
            return "main/index";

    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String loginAction(Model model,@Valid User user,BindingResult br,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws UnsupportedEncodingException{
        if(!br.hasErrors()) {
            String result = userService.login(user);
            boolean valid=request.getSession().getAttribute("code").toString()
                    .equalsIgnoreCase(
                            user.getValidCode()
                    );
            /*System.out.println("session code:"+request.getSession().getAttribute("code"));
            System.out.println("input code:"+user.getValidCode());
            System.out.println(result);*/

            if(valid){
                if (result.equals("登录成功")) {

                    //生成cookie并添加到响应当中
                    CommonUtils.addCookie("user",user.getUserName(),cookieLife,"/",request,response);

                    model.addAttribute("userName",user.getUserName());
                    return "redirect:/user/list";
                }
                else {
                    model.addAttribute("result", result);
                    return "user/login";
                }
            }
            else {
                result = "验证码错误";
                model.addAttribute("result", result);
                return "user/login";
            }
        }
        else
            return "user/login";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Map list(Model model,HttpServletRequest request){
        String userName=CommonUtils.getUserName(request);
        if(userName!=null)
            model.addAttribute("userName",userName);

        List<User> users= Arrays.asList(userService.list());
        Map tbContents=new HashMap();
        tbContents.put("pageDate",users);
        tbContents.put("total",users.size());
        return tbContents;

    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(Model model,HttpServletRequest request){
        String userName=CommonUtils.getUserName(request);
        if(userName!=null)
            model.addAttribute("userName",userName);

        model.addAttribute("user",new User());
        return "user/register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String registerAction(@Valid User user,BindingResult br,
                                 HttpServletRequest request,HttpServletResponse response,Model model)
    throws UnsupportedEncodingException{

        String userName=CommonUtils.getUserName(request);
        if(!br.hasErrors()){
            String result=userService.register(user);
            boolean codeValid=(request.getSession()).getAttribute("code").
                    toString().
                    equalsIgnoreCase
                            (user.getValidCode());
            if(result.equals("注册成功") && codeValid) {
                //生成cookie并添加
                CommonUtils.addCookie("user",user.getUserName(), cookieLife, "/",request, response);

                model.addAttribute("userName",user.getUserName());
                return "user/list";
            }
            else {
                if(userName!=null)
                    model.addAttribute("userName",userName);
                return "user/register";
            }
        }
        else {
            if(userName!=null)
                model.addAttribute("userName",userName);
            return "user/register";
        }
    }
    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    public String modify(Model model){
        model.addAttribute("user",new User());
        return "user/modify";
    }
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){

        CommonUtils.delCookie("user",request,response);
        return "main/index";
    }
    
    @RequestMapping(value = "/validate",method = RequestMethod.POST)
    @ResponseBody
    public Map validate(@RequestParam(value = "userName",required = true)String userName){
       /* System.out.println("Ajax请求已经被捕获---------------"+userName);*/
        if(userService.show(userName)!=null) {
            Map response=new HashMap();
            response.put("response", "该用户名已存在");
            return response;
        }
        else
            return null;
    }
    @RequestMapping(value = "/notice",method = RequestMethod.GET)
    public String loginNotice(){
        return "user/notice";
    }
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String showHome(Model model,HttpServletRequest request){
        String userName=CommonUtils.getUserName(request);
        if(userName!=null)
            model.addAttribute("userName",userName);
        return "main/index";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public void delete(@RequestParam(value = "id")int id){

        if(id>0){
            User target=new User();
            target.setUserId(id);
            userService.delete(target);
        }
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
