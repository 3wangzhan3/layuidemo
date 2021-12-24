package com.offcn.mapper;

import com.offcn.dto.StudentDto;
import com.offcn.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    List<Student> findAll(@Param("dto") StudentDto dto);

    int regist(Student student);

    int getCount(@Param("dto") StudentDto dto);

    Student findUserByName(String username);
}
