package com.offcn.config;

import com.offcn.mapper.StudentMapper;
import com.offcn.pojo.Tuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private StudentMapper studentMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Tuser user = studentMapper.findUserByName(username);
        if (user != null){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
            List<SimpleGrantedAuthority> list = new ArrayList<>();
            list.add(authority);
            return new User(username,"{noop}"+user.getPassword(),list);
        }
        return null;
    }
}
