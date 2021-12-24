package com.offcn.service.impl;

import com.github.pagehelper.PageHelper;
import com.offcn.dto.StudentDto;
import com.offcn.mapper.StudentMapper;
import com.offcn.pojo.Student;
import com.offcn.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll(StudentDto studentDto) {
        PageHelper.startPage(studentDto.getPage(),studentDto.getLimit());
        return studentMapper.findAll(studentDto);
    }

    @Override
    public int regist(Student student) {
        return studentMapper.regist(student);
    }

    @Override
    public int getCount(StudentDto studentDto) {
        return studentMapper.getCount(studentDto);
    }
}
