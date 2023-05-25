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

    public String alreadyEmailRegisterCheck(String email){
        System.out.println(email);
        return userSpringDAO.alreadyEmailRegisterCheck(email);
    }
    public String alreadyNickRegisterCheck(String nickname){
        System.out.println(nickname);
        return userSpringDAO.alreadyNickRegisterCheck(nickname);
    }
    public void insertUser(UserVO userVO){

        userSpringDAO.insertUser(userVO);

    }
    public String loginCheck(UserVO userVO){
            return userSpringDAO.loginCheck(userVO);
    }

    public String alreadyPasswordUsingCheck(UserVO userVO){
            return userSpringDAO.alreadyPasswordUsingCheck(userVO);
    }

    public void updateUserPw(UserVO userVO){
        userSpringDAO.updateUserPw(userVO);
    }

    public void deleteUser(String param){
        userSpringDAO.deleteUser(param);
    }


}
