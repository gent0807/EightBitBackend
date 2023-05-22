package com.eightbit.biz.user.inter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;

public interface MailSendService {
<<<<<<< HEAD
    public void sendAuthNumToEmail(String setFrom, String toMail, String title,String content);
    public void makeRandomNumber();
    public String joinEmail(String email);


=======
    //public String sendAuthKey(String email);
    public void makeRandomNumber();



    public String mailSend(String email);

>>>>>>> test2
}
