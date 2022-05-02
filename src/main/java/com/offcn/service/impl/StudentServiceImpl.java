package com.offcn.service.impl;

import com.github.pagehelper.PageHelper;
import com.offcn.dto.StudentDto;
import com.offcn.mapper.StudentMapper;
import com.offcn.pojo.Role;
import com.offcn.pojo.Student;
import com.offcn.pojo.Tuser;
import com.offcn.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll(StudentDto studentDto) {
        PageHelper.startPage(studentDto.getPage(),studentDto.getLimit());
        return studentMapper.findAll(studentDto);
    }

    @Override
    public String regist(Tuser user) {
        try {
            studentMapper.regist(user);
            studentMapper.setRoleUser(user.getId(),user.getRoleId());
            return "添加成功";
        }catch (Exception e){
            System.out.println(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return "添加失败";
    }

    @Override
    public int getCount(StudentDto studentDto) {
        return studentMapper.getCount(studentDto);
    }

    @Override
    public Tuser findUserName(String username) {
        return studentMapper.findUserByName(username);
    }

    @Override
    public List<Role> getRoleName() {
        return studentMapper.getRoleName();
    }

    @Override
    public String addMail(String uuid, String mailTo, String code) {
        try {
            int ret = studentMapper.addMail(uuid,mailTo,code);
            if (ret>0){
                return "成功";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "失败";
    }
}
