package com.eightbit.biz.user.impl;

import com.eightbit.biz.user.inter.UserService;
import com.eightbit.biz.user.persistence.UserSpringDAO;
import com.eightbit.biz.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userSpingDAO")
    private UserSpringDAO userSpingDAO;

    public String alreadyEmailRegisterCheck(String email,String alreadyEmailRegister){
        System.out.println(email);
        alreadyEmailRegister=userSpingDAO.alreadyEmailRegisterCheck(email,alreadyEmailRegister);
        return alreadyEmailRegister;
    }
    public String alreadyNickRegisterCheck(String nickname,String alreadyNickRegister){
        System.out.println(nickname);
        alreadyNickRegister=userSpingDAO.alreadyNickRegisterCheck(nickname,alreadyNickRegister);
        return alreadyNickRegister;
    }
    public void insertUser(UserVO userVO){

        userSpingDAO.insertUser(userVO);

    }
    public String loginCheck(UserVO userVO,String loginPossible){
            loginPossible=userSpingDAO.loginCheck(userVO,loginPossible);
            return loginPossible;
    }

    public void updateUser(UserVO userVO){
        userSpingDAO.updateUser(userVO);
    }

    public void deleteUser(String param){
        userSpingDAO.deleteUser(param);
    }


}
