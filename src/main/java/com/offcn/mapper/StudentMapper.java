package com.offcn.mapper;

import com.offcn.dto.StudentDto;
import com.offcn.pojo.Role;
import com.offcn.pojo.Student;
import com.offcn.pojo.Tuser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    List<Student> findAll(@Param("dto") StudentDto dto);

    void  regist(Tuser user);

    int getCount(@Param("dto") StudentDto dto);

    Tuser findUserByName(String username);

    List<Role> getRoleName();

    void setRoleUser(@Param("userId") String id,@Param("roleId") String roleId);

    int addMail(@Param("uuid") String uuid,@Param("mailTo") String mailTo,@Param("code") String code);
}
