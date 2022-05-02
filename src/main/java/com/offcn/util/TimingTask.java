package com.offcn.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Configuration
@EnableScheduling
public class TimingTask {

    @Autowired
    private JavaMailSender javaMailSender;

    @Scheduled(cron = "0/30 * * * * ?")
    public void testscheduled(){
//        System.out.println(123);
//        sendMail();
//        sendMail1();
//        sendMail2();
    }

    //发送普通邮件
    public void sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        //发送人
        message.setFrom("wangzhantest@163.com");
        //接收人
        message.setTo("2690547921@qq.com");
        //标题
        message.setSubject("Java发送邮件");
        //内容
        message.setText("你好，这是第一份邮件");
        //
        message.setCc("wangzhan@163.com");
        javaMailSender.send(message);
    }

    //发送Html格式的附件
    public void sendMail1(){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String context="<b>尊敬的用户：</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您好，管理员已为你申请了新的账号，"+
                "请您尽快通过<a href=\"http://www.liwz.top/\">链接</a>登录系统。"
                +"<br>修改密码并完善你的个人信息。<br><br><br><b>员工管理系统<br>Li，Wan Zhi</b>";
        try {
            helper.setFrom("wangzhantest@163.com");
            helper.setTo("2690547921@qq.com");
            helper.setSubject("带html格式的邮件");
            helper.setText(context,true);
            javaMailSender.send(message);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //带附件的邮件
    public void sendMail2(){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom("wangzhantest@163.com");
            helper.setTo("2690547921@qq.com");
            helper.setSubject("带附件的邮件");
            helper.setText("带有附件的邮件");
            helper.addAttachment("a.txt",new File("D:\\Log\\log\\info\\log-info-2022-05-02.0.log"));
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
