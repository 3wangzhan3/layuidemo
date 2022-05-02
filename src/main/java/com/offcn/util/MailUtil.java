package com.offcn.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Component
public class MailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String from,String to,String title,String code){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String context="<b>尊敬的用户：</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您好，登录的验证码为："+code
                +"<br>若不是本人请忽略。<br><br><br><b>员工管理系统<br>Li，Wan Zhi</b>";
        try {
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(context,true);
            javaMailSender.send(message);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
