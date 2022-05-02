package com.offcn.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.offcn.config.DefaultConfig;
import com.offcn.dto.PageDto;
import com.offcn.dto.StudentDto;
import com.offcn.pojo.Role;
import com.offcn.pojo.Student;
import com.offcn.pojo.Tuser;
import com.offcn.service.StudentService;
import com.offcn.util.MailUtil;
import com.sun.tools.javac.jvm.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@Transactional
public class StudentController extends Result{

    @Autowired
    private StudentService studentService;
    @Autowired
    private MailUtil mailUtil;

    @RequestMapping("/LOGIN")
    public String LOGIN(){
        return "page/menu.html";
    }

    //获取rolename集合
    @RequestMapping("getRoleName")
    @ResponseBody
    public List<Role> getRoleName(){
        List<Role> roles = studentService.getRoleName();
        return roles;
    }

    //获取用户名
    @GetMapping("getUserName")
    @ResponseBody
    public String getSession(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return JSONUtils.toJSONString(username);
    }


    @RequestMapping("test")
    @ResponseBody
    public PageDto test(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam(value = "username",required = false) String username){

        StudentDto studentDto = new StudentDto();
        studentDto.setPage(page);
        studentDto.setLimit(limit);
        if(username!=null &&!username.equals("")){
            studentDto.setUsername("%"+username+"%");
        }
        List<Student> list = studentService.findAll(studentDto);
        int count = studentService.getCount(studentDto);
        if (list.size()>0){
            return new PageDto(0,"获取数据成功",count,list);
        }
        return new PageDto(201,"数据库暂无数据");
    }

    @PostMapping("/regist")
    @ResponseBody
    public Result regist(@RequestBody Tuser user){
        String msg=studentService.regist(user);
        if (msg.equals("添加成功")){
            return this.tranSuccess("成功");
        }
        return this.tranError("失败");
    }
    //首页登录成功后session中存储用户名
    @RequestMapping("/setSession")
    @ResponseBody
    public void setSession(@RequestParam("account") String username,HttpSession session){
        session.setAttribute("account",username);
    }

    //重置密码
    @RequestMapping("resetpsd")
    @ResponseBody
    public String resetpsd(@RequestParam("psd") String email,HttpSession session){

        String username = (String)session.getAttribute("account");
        Tuser user =studentService.findUserName(username);
        if (user !=null){

        }
        return "";
    }

    @RequestMapping("/getEmailCode")
    @ResponseBody
    public Result getEmailCode(@RequestParam String mailTo){
        try {
            int code = new Random().nextInt(999999);
            mailUtil.sendMail(DefaultConfig.mailFrom,mailTo,"获取验证码",String.valueOf(code));
            UUID uuid = UUID.randomUUID();
            String msg = studentService.addMail(uuid.toString(),mailTo, String.valueOf(code));
            if (msg.indexOf("成功")>-1){
                return new Result(1,"成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new Result(0,"失败");
    }
}
