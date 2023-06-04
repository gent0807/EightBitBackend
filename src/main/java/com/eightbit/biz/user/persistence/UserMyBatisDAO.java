package com.eightbit.biz.user.persistence;

import com.eightbit.biz.user.util.JWTUtil;
import com.eightbit.biz.user.vo.TempVO;
import com.eightbit.biz.user.vo.UserVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userMyBatisDAO")
@PropertySource("classpath:auth.properties")
public class UserMyBatisDAO {
    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSessionTemplate mybatis;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Value("${jwt.secret}")
    private String secretKey;
    private Long expiredMs= 1000*60*60l;

    public String alreadyEmailRegisterCheck(String email){
        String alreadyEmailRegister="no";
        List<UserVO> userVOList=mybatis.selectList("UserMyBatisDAO.getUserList");
        System.out.println(userVOList);
        for(UserVO user:userVOList){
            if(encoder.matches(email, user.getEmail())){
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
            if(encoder.matches(email, temp.getEmail())){
                return temp.getEmail();
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
        List<TempVO> tempVOList=mybatis.selectList("UserMyBatisDAO.getTempList");
        for(TempVO temp:tempVOList){
            if(encoder.matches(userVO.getEmail(), temp.getEmail())){
                mybatis.delete("UserMyBatisDAO.deleteTempRow", temp.getEmail());
                userVO.setEmail(encoder.encode(userVO.getEmail()));
                mybatis.insert("UserMyBatisDAO.insertUser", userVO);
            }
        }
        return "";
    }

    public String loginCheck(UserVO userVO){
        String result="no";
        List<UserVO> userVOList=mybatis.selectList("UserMyBatisDAO.getUserList");
        String email=userVO.getEmail();
        String password=userVO.getPassword();
        System.out.println(password);
        for(UserVO user:userVOList){
            if(encoder.matches(email, user.getEmail())){
                result="emailok";
                String key=mybatis.selectOne("UserMyBatisDAO.getPassword", user.getEmail());
                System.out.println(key);
                if(encoder.matches(password,key)){
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
            if(encoder.matches(userVO.getEmail(),user.getEmail())){
                String key=mybatis.selectOne("UserMyBatisDAO.getPassword", user.getEmail());
                if(encoder.matches(userVO.getPassword(), key)){
                    return alreadyPasswordUsing="yes";
                }
            }
        }
        return alreadyPasswordUsing;
    }
    public String updateUserPw(UserVO userVO){
        List<UserVO> userVOList=mybatis.selectList("UserMyBatisDAO.getUserList");
        for(UserVO user:userVOList){
            if(encoder.matches(userVO.getEmail(),user.getEmail())){
                userVO.setEmail(user.getEmail());
                mybatis.update("UserMyBatisDAO.updateUserPw", userVO);
            }
        }
        return "";
    }

    public String updateTempAuthNum(TempVO tempVO){
        mybatis.update("UserMyBatisDAO.updateTempAuthNum", tempVO);
        return JWTUtil.createJWT("TEMP1",secretKey,expiredMs);
    }
    public void deleteUser(String email){
        mybatis.delete("UserMyBatisDAO.deleteUser", email);
    }

    public String findRoleFromNick(String userName){
        return mybatis.selectOne("UserMyBatisDAO.getRole", userName);
    }

    public String insertTempUser(TempVO tempVO){
        mybatis.insert("UserMyBatisDAO.insertTempUser", tempVO);
        return JWTUtil.createJWT("TEMP1", secretKey, expiredMs);
    }

    public String checkRightAuthNum(TempVO tempVO){
        System.out.println(tempVO.getAuthNum());
        System.out.println(tempVO.getEmail());
        List<TempVO> tempVOList=mybatis.selectList("UserMyBatisDAO.getTempList");
        for(TempVO temp:tempVOList){
            if(encoder.matches(tempVO.getEmail(), temp.getEmail())){
                String auth_key=mybatis.selectOne("UserMyBatisDAO.getAuthNum", temp.getEmail());
                if(encoder.matches(tempVO.getAuthNum(),auth_key)){
                    return JWTUtil.createJWT("TEMP2", secretKey, expiredMs);
                }
            }
        }
        return "no";

    }
}
