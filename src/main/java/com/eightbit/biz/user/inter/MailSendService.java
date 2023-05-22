package com.eightbit.biz.user.inter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;

public interface MailSendService {

    public void makeRandomNumber();



    public String mailSend(String email);


}
