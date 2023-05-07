package com.eightbit.view_controller.user_controller;

import com.eightbit.biz.user.inter.MailSendService;
import com.eightbit.biz.user.inter.UserService;
import com.eightbit.biz.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("mailSendService")
    private MailSendService mailSendService;

    @GetMapping(value = "/alreadyEmailRegisterCheck", produces = "text/plain; charset=UTF-8")
    public String alreadyEmailRegisterCheck(UserVO userVO){
        String alreadyEmailRegister="no";
        alreadyEmailRegister=userService.alreadyEmailRegisterCheck(userVO,alreadyEmailRegister);
        return alreadyEmailRegister;
    }

    @GetMapping(value = "/alreadyNickRegisterCheck", produces = "text/plain; charset=UTF-8")
    public String alreadyNickRegisterCheck(UserVO userVO){
        String alreadyNickRegister="no";
        alreadyNickRegister=userService.alreadyNickRegisterCheck(userVO,alreadyNickRegister);
        return alreadyNickRegister;
    }
    @PostMapping(value = "/insert")
    public void insertUser(UserVO userVO){
        userService.insertUser(userVO);
    }

    @GetMapping(value = "/loginCheck", produces = "text/plain; charset=UTF-8")
    public String loginCheck(UserVO userVO){
        String loginPossible="no";
        loginPossible=userService.loginCheck(userVO,loginPossible);
        return loginPossible;
    }

    @PutMapping(value = "/update")
    public void updateUser(UserVO userVO){
        userService.updateUser(userVO);
    }


    @DeleteMapping(value = "/delete")
    public void deleteUser(UserVO userVO){
        userService.deleteUser(userVO);
    }

    @GetMapping(value = "/send_num_to_email", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE})
    public int sendAuthNumToEmail(@RequestParam("email") String email){
        return mailSendService.sendAuthNumToEmail(email);
    }



}
