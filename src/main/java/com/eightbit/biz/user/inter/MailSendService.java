package com.eightbit.biz.user.inter;

public interface MailSendService {
    public void sendAuthNumToEmail(String setFrom, String toMail, String title,String content);
    public void makeRandomNumber();
    public String joinEmail(String email);


}
