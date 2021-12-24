package com.offcn.controller;

import com.offcn.dto.PageDto;
import com.offcn.dto.StudentDto;
import com.offcn.pojo.Student;
import com.offcn.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

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

    @PostMapping("regist")
    @ResponseBody
    public String regist(@RequestBody Student student){
        int ret =studentService.regist(student);
        if (ret>0){
            return "添加成功";
        }
        return "添加失败";
    }
}
