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

    public String alreadyEmailRegisterCheck(UserVO userVO,String alreadyEmailRegister){
        alreadyEmailRegister=userSpingDAO.alreadyEmailRegisterCheck(userVO,alreadyEmailRegister);
        return alreadyEmailRegister;
    }
    public String alreadyNickRegisterCheck(UserVO userVO,String alreadyNickRegister){
        alreadyNickRegister=userSpingDAO.alreadyNickRegisterCheck(userVO,alreadyNickRegister);
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

    public void deleteUser(UserVO userVO){
        userSpingDAO.deleteUser(userVO);
    }

    public void sendAuthNumToEmail(String email){

    }
}
