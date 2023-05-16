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
    @Qualifier("userSpringDAO")
    private UserSpringDAO userSpringDAO;

    public String alreadyEmailRegisterCheck(String email,String alreadyEmailRegister){
        System.out.println(email);
        alreadyEmailRegister= userSpringDAO.alreadyEmailRegisterCheck(email,alreadyEmailRegister);
        return alreadyEmailRegister;
    }
    public String alreadyNickRegisterCheck(String nickname,String alreadyNickRegister){
        System.out.println(nickname);
        alreadyNickRegister= userSpringDAO.alreadyNickRegisterCheck(nickname,alreadyNickRegister);
        return alreadyNickRegister;
    }
    public void insertUser(UserVO userVO){

        userSpringDAO.insertUser(userVO);

    }
    public String loginCheck(UserVO userVO,String loginPossible){
            loginPossible= userSpringDAO.loginCheck(userVO,loginPossible);
            return loginPossible;
    }

    public void updateUser(UserVO userVO){
        userSpringDAO.updateUser(userVO);
    }

    public void deleteUser(String param){
        userSpringDAO.deleteUser(param);
    }


}
