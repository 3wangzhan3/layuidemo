package com.offcn.service;

import com.offcn.dto.StudentDto;
import com.offcn.pojo.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll(StudentDto studentDto);

    int regist(Student student);

    int getCount(StudentDto studentDto);
}
