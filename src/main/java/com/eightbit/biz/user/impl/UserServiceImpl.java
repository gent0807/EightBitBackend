package com.eightbit.biz.user.impl;

import com.eightbit.biz.user.inter.UserService;
import com.eightbit.biz.user.persistence.UserMyBatisDAO;
import com.eightbit.biz.user.persistence.UserSpringDAO;
import com.eightbit.biz.user.vo.TempVO;
import com.eightbit.biz.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userSpringDAO")
    private UserSpringDAO userSpringDAO;

    @Autowired
    @Qualifier("userMyBatisDAO")
    private UserMyBatisDAO userMyBatisDAO;


    @Autowired
    private BCryptPasswordEncoder encoder;

    public String alreadyEmailRegisterCheck(String email){
        System.out.println(email);
        return userMyBatisDAO.alreadyEmailRegisterCheck(email);
    }
    public String alreadyNickRegisterCheck(String nickname){
        System.out.println(nickname);
        return userMyBatisDAO.alreadyNickRegisterCheck(nickname);
    }
    public String insertUser(UserVO userVO){
        userVO.setPassword(encoder.encode(userVO.getPassword()));
        return userMyBatisDAO.insertUser(userVO);
    }
    public String loginCheck(UserVO userVO){
        return userMyBatisDAO.loginCheck(userVO);
    }

    public String alreadyPasswordUsingCheck(UserVO userVO){
            return userMyBatisDAO.alreadyPasswordUsingCheck(userVO);
    }

    public String updateUserPw(UserVO userVO){
        userVO.setPassword(encoder.encode(userVO.getPassword()));
        return userMyBatisDAO.updateUserPw(userVO);
    }

    public void deleteUser(String param){
        userMyBatisDAO.deleteUser(param);
    }

    public String findRoleFromNick(String userName){
        return userMyBatisDAO.findRoleFromNick(userName);
    }

    public String checkRightAuthNum(TempVO tempVO){
        return userMyBatisDAO.checkRightAuthNum(tempVO);
    }


}
