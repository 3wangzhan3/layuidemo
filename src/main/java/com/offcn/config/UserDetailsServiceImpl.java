package com.offcn.config;

import com.offcn.mapper.StudentMapper;
import com.offcn.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private StudentMapper studentMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Student stu = studentMapper.findUserByName(username);
        if (stu != null){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
            List<SimpleGrantedAuthority> list = new ArrayList<>();
            list.add(authority);
            return new User(username,"{noop}"+stu.getPassword(),list);
        }
        return null;
    }
}
