package com.eightbit.view_controller.user_controller;

import com.eightbit.biz.user.inter.MailSendService;
import com.eightbit.biz.user.inter.UserService;
import com.eightbit.biz.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.awt.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("mailSendService")
    private MailSendService mailSendService;

    @PostMapping(value = "/alreadyEmailRegisterCheck")
    public String alreadyEmailRegisterCheck(@RequestBody UserVO userVO){
        System.out.println("이메일 존재 확인 요청 접수");
        System.out.println(userVO);
        String alreadyEmailRegister="no";
        alreadyEmailRegister=userService.alreadyEmailRegisterCheck(userVO.getEmail(),alreadyEmailRegister);
        return alreadyEmailRegister;
    }

    @PostMapping(value = "/alreadyNickRegisterCheck")
    public String alreadyNickRegisterCheck(@RequestBody UserVO userVO){
        System.out.println("닉네임 존재 확인 요청 접수");
        System.out.println(userVO);
        String alreadyNickRegister="no";
        alreadyNickRegister=userService.alreadyNickRegisterCheck(userVO.getNickname(),alreadyNickRegister);
        return alreadyNickRegister;
    }
    @PostMapping(value = "/insert")
    public void insertUser(@RequestBody UserVO userVO){
        userService.insertUser(userVO);
    }

    @PostMapping(value = "/loginCheck")
    public String loginCheck(@RequestBody UserVO userVO, HttpSession session){
        System.out.println("로그인 시도 요청 접수");
        System.out.println(userVO);
        String loginPossible="no";
        loginPossible=userService.loginCheck(userVO,loginPossible);
        return loginPossible;
    }

    @PutMapping(value = "/update")
    public void updateUser(@RequestBody UserVO userVO){
        userService.updateUser(userVO);
    }


    @DeleteMapping(value = "/delete/{param}")
    public void deleteUser(@PathVariable String param){
        userService.deleteUser(param);
    }

    @PostMapping(value = "/send_num_to_email")
    public String sendAuthNumToEmail(@RequestBody String email){
        return mailSendService.sendAuthNumToEmail(email);
    }

    @PostMapping(value = "/find_password")
    public void sendPasswordToEmail(@RequestBody String email){
        mailSendService.sendPasswordToEmail(email);
    }



}
