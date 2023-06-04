package com.eightbit.view_controller.user_controller;

import com.eightbit.biz.user.inter.MailSendService;
import com.eightbit.biz.user.inter.UserService;
import com.eightbit.biz.user.vo.TempVO;
import com.eightbit.biz.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    /*
    @Autowired
    @Qualifier("phoneSendService")
    private PhoneSendService phoneSendService;
     */

    @PostMapping(value = "/check/email/already")
    public ResponseEntity<String> alreadyEmailRegisterCheck(@RequestBody UserVO userVO){
        System.out.println("이메일 존재 확인 요청 접수");
        System.out.println(userVO);
        return ResponseEntity.ok().body(userService.alreadyEmailRegisterCheck(userVO.getEmail()));
    }

    @PostMapping(value = "/check/nick/already")
    public ResponseEntity<String> alreadyNickRegisterCheck(@RequestBody UserVO userVO){
        System.out.println("닉네임 존재 확인 요청 접수");
        System.out.println(userVO);
        return ResponseEntity.ok().body(userService.alreadyNickRegisterCheck(userVO.getNickname()));
    }

    @PostMapping(value = "/check/password/already")
    public ResponseEntity<String> alreadyPasswordUsingCheck(@RequestBody UserVO userVO){
        System.out.println("패스워드 중복 검사 요청 접수");
        System.out.println(userVO);
        return ResponseEntity.ok().body(userService.alreadyPasswordUsingCheck(userVO));
    }

    @PostMapping(value="/check/authkey")
    public ResponseEntity<String> checkRightAuthNum(@RequestBody TempVO tempVO){
        return ResponseEntity.ok().body(userService.checkRightAuthNum(tempVO));
    }

    @PostMapping(value = "/user")
    public ResponseEntity<String> insertUser(@RequestBody UserVO userVO){
        System.out.println("회원가입 요청 접수");
        System.out.println(userVO);
        return ResponseEntity.ok().body(userService.insertUser(userVO));
    }


    @PostMapping(value = "/check/login")
    public ResponseEntity<String> loginCheck(@RequestBody UserVO userVO){
        System.out.println("로그인 시도 요청 접수");
        System.out.println(userVO);
        return ResponseEntity.ok().body(userService.loginCheck(userVO));
    }

    @PutMapping(value = "/password")
    public String updateUserPw(@RequestBody UserVO userVO){
        return userService.updateUserPw(userVO);
    }


    @DeleteMapping(value = "/{param}")
    public void deleteUser(@PathVariable String param){
        userService.deleteUser(param);
    }


    @PostMapping(value = "/authkey/email")
    public ResponseEntity<String> sendAuthNumToEmail(@RequestBody UserVO userVO){
         return ResponseEntity.ok().body(mailSendService.mailSend(userVO.getEmail()));
    }



    /*
    @PostMapping(value = "/authkey/phone")
    public ResponseEntity<String> sendAuthNumToPhone(@RequestBody UserVO userVO){
        String authkey=phoneSendService.phoneSend(userVO.getEmail());
        return ResponseEntity.ok().body(authkey);
    }
    */



}
