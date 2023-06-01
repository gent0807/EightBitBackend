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
@RequestMapping("/Users/*")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("mailSendService")
    private MailSendService mailSendService;

    @PostMapping(value = "/email/already")
    public ResponseEntity<String> alreadyEmailRegisterCheck(@RequestBody UserVO userVO){
        System.out.println("이메일 존재 확인 요청 접수");
        System.out.println(userVO);
        return ResponseEntity.ok().body(userService.alreadyEmailRegisterCheck(userVO.getEmail()));
    }

    @PostMapping(value = "/nick/already")
    public ResponseEntity<String> alreadyNickRegisterCheck(@RequestBody UserVO userVO){
        System.out.println("닉네임 존재 확인 요청 접수");
        System.out.println(userVO);
        return ResponseEntity.ok().body(userService.alreadyNickRegisterCheck(userVO.getNickname()));
    }

    @PostMapping(value = "/password/already")
    public ResponseEntity<String> alreadyPasswordUsingCheck(@RequestBody UserVO userVO){
        System.out.println("패스워드 중복 검사 요청 접수");
        System.out.println(userVO);
        return ResponseEntity.ok().body(userService.alreadyPasswordUsingCheck(userVO));
    }

    @PostMapping(value = "/user")
    public void insertUser(@RequestBody UserVO userVO){
        System.out.println("회원가입 요청 접수");
        System.out.println(userVO);
        userService.insertUser(userVO);
    }


    @PostMapping(value = "/login")
    public ResponseEntity<String> loginCheck(@RequestBody UserVO userVO){
        System.out.println("로그인 시도 요청 접수");
        System.out.println(userVO);
        return ResponseEntity.ok().body(userService.loginCheck(userVO));
    }

    @PutMapping(value = "/password")
    public void updateUserPw(@RequestBody UserVO userVO){
        userService.updateUserPw(userVO);
    }


    @DeleteMapping(value = "/{param}")
    public void deleteUser(@PathVariable String param){
        userService.deleteUser(param);
    }


    @PostMapping(value = "/authkey")
    public ResponseEntity<String> sendAuthNumToEmail(@RequestBody UserVO userVO){
         String authkey=mailSendService.mailSend(userVO.getEmail());
         return ResponseEntity.ok().body(authkey);

    }





}
