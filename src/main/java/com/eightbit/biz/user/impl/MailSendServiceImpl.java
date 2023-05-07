package com.eightbit.biz.user.impl;

import com.eightbit.biz.user.inter.MailSendService;
import org.springframework.stereotype.Service;

@Service("mailSendService")
public class MailSendServiceImpl implements MailSendService {
    public int sendAuthNumToEmail(String email){
        return 1;
    }
}
