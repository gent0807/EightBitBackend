package com.eightbit.biz.user.persistence;

import com.eightbit.biz.user.util.JWTUtil;
import com.eightbit.biz.user.vo.TempVO;
import com.eightbit.biz.user.vo.UserVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userMyBatisDAO")
@PropertySource("classpath:auth.properties")
public class UserMyBatisDAO {
    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSessionTemplate mybatis;

    @Value("${jwt.secret}")
    private String secretKey;
    private Long expiredMs= 1000*60*60l;

    @Value("${jwt.temp1}")
    private String temp1;

    @Value("${jwt.temp2}")
    private String temp2;

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

    public String alreadyEmailTempCheck(String email){
        String alreadyEmailRegister="no";
        List<TempVO> tempVOList=mybatis.selectList("UserMyBatisDAO.getTempList");
        System.out.println(tempVOList);
        for(TempVO temp:tempVOList){
            if(temp.getEmail().equals(email)){
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
    public String insertUser(UserVO userVO) {
        mybatis.delete("UserMyBatisDAO.deleteTempRow", userVO.getEmail());
        mybatis.insert("UserMyBatisDAO.insertUser", userVO);
        return "";
    }

    public String loginCheck(UserVO userVO){
        String result="no";
        List<UserVO> userVOList=mybatis.selectList("UserMyBatisDAO.getUserList");
        String email=userVO.getEmail();
        String password=userVO.getPassword();
        for(UserVO user:userVOList){
            if(user.getEmail().equals(email)){
                result="emailok";
                if(user.getPassword().equals(password)){
                    result=JWTUtil.createJWT(userVO.getNickname(), secretKey, expiredMs); //JWTUtil 클래스의 static 메소드
                    break;
                }
                else{
                    break;
                }
            }
        }
        return result;
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

    public String updateTempAuthNum(TempVO tempVO){
        mybatis.update("UserMyBatisDAO.updateTempAuthNum",tempVO);
        return JWTUtil.createJWT(temp1,secretKey,expiredMs);
    }
    public void deleteUser(String email){
        mybatis.delete("UserMyBatisDAO.deleteUser", email);
    }

    public String findRoleFromNick(String userName){
        return mybatis.selectOne("UserMyBatisDAO.getRole", userName);
    }

    public String insertTempUser(TempVO tempVO){
        mybatis.insert("UserMyBatisDAO.insertTempUser", tempVO);
        return JWTUtil.createJWT(temp1, secretKey, expiredMs);
    }

    public String checkRightAuthNum(TempVO tempVO){
        int auth=mybatis.selectOne("UserMyBatisDAO.getAuthNum", tempVO.getEmail());
        if(auth==tempVO.getAuthNum()){
            return JWTUtil.createJWT(temp2, secretKey, expiredMs);
        }
        else{
            return "no";
        }
    }
}
