package com.kacper.musicapp.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService
{
    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("$(spring.mail.username)")
    private String fromMail;

    public void sendMail(String mail, MailStructure mailStructure) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject(mailStructure.getSubject());
        simpleMailMessage.setText(mailStructure.getMessage());
        simpleMailMessage.setTo(mail);

        javaMailSender.send(simpleMailMessage);
    }

    public void sendActivationCode(String email, Integer activationCode) {
        MailStructure mailStructure = new MailStructure();
        mailStructure.setSubject("Activation code");
        mailStructure.setMessage("Your activation code is: " + activationCode);

        sendMail(email, mailStructure);
    }
}
