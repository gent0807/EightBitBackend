package com.eightbit.biz.user.inter;

public interface MailSendService {
    public String sendAuthNumToEmail(String email);
    public void sendPasswordToEmail(String email);
}
