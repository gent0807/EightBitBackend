package com.eightbit.view_controller.user_controller;

import com.eightbit.biz.user.inter.MailSendService;
import com.eightbit.biz.user.inter.UserService;
import com.eightbit.biz.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
        return userService.alreadyEmailRegisterCheck(userVO.getEmail());
    }

    @PostMapping(value = "/alreadyNickRegisterCheck")
    public String alreadyNickRegisterCheck(@RequestBody UserVO userVO){
        System.out.println("닉네임 존재 확인 요청 접수");
        System.out.println(userVO);
        return userService.alreadyNickRegisterCheck(userVO.getNickname());
    }
    @PostMapping(value = "/insert")
    public void insertUser(@RequestBody UserVO userVO){
        System.out.println(userVO);
        userService.insertUser(userVO);
    }


    @PostMapping(value = "/loginCheck")
    public String loginCheck(@RequestBody UserVO userVO){
        System.out.println("로그인 시도 요청 접수");
        System.out.println(userVO);
        return userService.loginCheck(userVO);
    }

    @PostMapping(value = "/alreadyPasswordUsingCheck")
    public String alreadyPasswordUsingCheck(@RequestBody UserVO userVO){
        System.out.println("패스워드 중복 검사 요청 접수");
        System.out.println(userVO);
        return userService.alreadyPasswordUsingCheck(userVO);
    }

    @PutMapping(value = "/updateUserPw")
    public void updateUserPw(@RequestBody UserVO userVO){
        userService.updateUserPw(userVO);
    }


    @DeleteMapping(value = "/delete/{param}")
    public void deleteUser(@PathVariable String param){
        userService.deleteUser(param);
    }


    @PostMapping(value = "/send_auth_key_to_email")
    public String sendAuthNumToEmail(@RequestBody UserVO userVO){
         String authkey=mailSendService.mailSend(userVO.getEmail());
         return authkey;

    }





}
