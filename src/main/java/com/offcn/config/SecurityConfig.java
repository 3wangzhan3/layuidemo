package com.offcn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/index.html") //自定义登录页面
                .loginProcessingUrl("/login")//自定义登录页面的form表单的地址
                .defaultSuccessUrl("/LOGIN")//登录成功后访问后台地址
                .failureUrl("/index.html?error=true")//失败跳回登录页，判断url的参数提示
                .and()//结束标志
                .authorizeRequests()
                .antMatchers("/index.html","/page/regist.html","/getRoleName","/regist").permitAll()//放行页面/资源
                .antMatchers("/layui/**").permitAll()//放行页面/资源
                .anyRequest()//任何的请求
                .authenticated()//都需要身份验证
                .and()
                .logout()
                .logoutUrl("/exit")//退出访问的地址
                .logoutSuccessUrl("/index.html")//退出成功后跳转的页面
                .and()
                .headers().frameOptions().disable()//关闭iframe页面嵌套
                .and()
                .csrf().disable(); //关闭csrf
    }
}
