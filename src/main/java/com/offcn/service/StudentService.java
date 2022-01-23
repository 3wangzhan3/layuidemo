package com.offcn.service;

import com.offcn.dto.StudentDto;
import com.offcn.pojo.Role;
import com.offcn.pojo.Student;
import com.offcn.pojo.Tuser;

import java.util.List;

public interface StudentService {
    List<Student> findAll(StudentDto studentDto);

    String regist(Tuser user);

    int getCount(StudentDto studentDto);

    Tuser findUserName(String username);

    List<Role> getRoleName();
}
