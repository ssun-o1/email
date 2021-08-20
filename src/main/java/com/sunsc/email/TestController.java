package com.sunsc.email;

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunshaocong
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String from;

    public TestController(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        String[] toRecive = to.split(",");
        message.setFrom(from);
        message.setTo(toRecive);
        message.setSubject(subject);
        message.setText(content);
        sender.send(message);
    }

    @GetMapping("")
    public void sendEmail() {
        String code = RandomStringUtils.randomNumeric(4);
        sendSimpleMail("153xxxxxxxx@163.com", "验证码：", code);
    }
}
