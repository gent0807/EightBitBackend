package com.eightbit.biz.user.impl;

import com.eightbit.biz.user.inter.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service("mailSendService")
public class MailSendServiceImpl implements MailSendService{

    @Autowired
    @Qualifier("mailSender")
    private JavaMailSenderImpl mailSender;
    private int authNumber;

    public void makeRandomNumber() {
        // 난수의 범위 111111 ~ 999999 (6자리 난수)
        Random r = new Random();
        int checkNum = r.nextInt(888888) + 111111;
        System.out.println("인증번호 : " + checkNum);
        authNumber = checkNum;
    }
    //이메일 전송 메소드
    public String mailSend(String email)  {
        makeRandomNumber();
        String setFrom = "theloopholesnk@gmail.com";
        String toMail = email;
        String title = "회원 가입 인증 이메일 입니다.";
        String content =
                "안녕하세요. 8비트입니다.방문해주셔서 감사합니다." +     //html 형식으로 작성 !
                        "<br><br>" +
                        "요청하신 인증 번호는 " + authNumber + "입니다." +
                        "<br>" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; //이메일 내용 삽입
        MimeMessage message = mailSender.createMimeMessage();
        // true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            System.out.println("메시기 객체 만듬");
            helper.setFrom(setFrom);
            System.out.println("시작 주소 삽입");
            helper.setTo(toMail);
            System.out.println("타겟 주소 삽입");
            helper.setSubject(title);
            System.out.println("제목 삽입");
            helper.setText(content,true);
            System.out.println("내용 삽입");
            mailSender.send(message);
            System.out.println("이메일 전송 성공");
            return Integer.toString(authNumber);
        } catch (MessagingException e) {
            System.out.println("이메일 전송 실패");
            String errormsg="인증번호 전송에 실패했습니다";
            e.printStackTrace();
            return errormsg;
        }
    }


}
