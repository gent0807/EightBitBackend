package com.eightbit.biz.user.persistence;

import com.eightbit.biz.user.vo.UserVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userMyBatisDAO")
public class UserMyBatisDAO {
    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSessionTemplate mybatis;

    public String alreadyEmailRegisterCheck(String email){
        String alreadyEmailRegister="no";
        List<UserVO> userVOList=mybatis.selectList("UserMyBatisDAO.getUserList");
        System.out.println(userVOList);
        for(UserVO user:userVOList){
            if(user.getEmail().equals(email)){
                alreadyEmailRegister="yes";
                break;
            }
        }
        return alreadyEmailRegister;
    }
    public String alreadyNickRegisterCheck(String nickname){
        String alreadyNickRegister="no";
        List<UserVO> userVOList=mybatis.selectList("UserMyBatisDAO.getUserList");
        System.out.println(userVOList);
        for(UserVO user:userVOList){
            if(user.getNickname().equals(nickname)){
                alreadyNickRegister="yes";
                break;
            }
        }
        return alreadyNickRegister;
    }
    public void insertUser(UserVO userVO) {
        mybatis.insert("UserMyBatisDAO.insertUser", userVO);
    }

    public String loginCheck(UserVO userVO){
        String loginPossible="no";
        List<UserVO> userVOList=mybatis.selectList("UserMyBatisDAO.getUserList");
        String email=userVO.getEmail();
        String password=userVO.getPassword();
        for(UserVO user:userVOList){
            if(user.getEmail().equals(email)){
                loginPossible="emailok";
                if(user.getPassword().equals(password)){
                    loginPossible="allok";
                    break;
                }
                else{
                    break;
                }
            }
        }
        return loginPossible;
    }

    public String alreadyPasswordUsingCheck(UserVO userVO){
        String alreadyPasswordUsing="no";
        List<UserVO> userVOList=mybatis.selectList("UserMyBatisDAO.getUserList");
        for(UserVO user:userVOList){
            if(user.getEmail().equals(userVO.getEmail())){
                if(user.getPassword().equals(userVO.getPassword())){
                    return alreadyPasswordUsing="yes";
                }
            }
        }
        return alreadyPasswordUsing;
    }
    public void updateUserPw(UserVO userVO){
        mybatis.update("UserMyBatisDAO.updateUserPw", userVO);
    }

    public void deleteUser(String param){
        mybatis.delete("UserMyBatisDAO.deleteUser", param);
    }
}
