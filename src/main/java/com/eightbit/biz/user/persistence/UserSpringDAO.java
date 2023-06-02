package com.eightbit.biz.user.persistence;

import com.eightbit.biz.board.vo.BoardVO;
import com.eightbit.biz.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userSpringDAO")
public class UserSpringDAO {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    private final String USER_INSERT = "insert into user(email, password, nickname) values(?, ?, ?)";
    private final String USER_PW_UPDATE = "update user set password=? where email=?";
    private final String USER_DELETE = "delete user where email=?";
    private final String USER_GET = "select * from user where email=?";
    private final String USER_LIST = "select * from user";

    public String alreadyEmailRegisterCheck(String email){
        String alreadyEmailRegister="no";
        List<UserVO> userVOList=jdbcTemplate.query(USER_LIST,new UserRowMaper());
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
        List<UserVO> userVOList=jdbcTemplate.query(USER_LIST,new UserRowMaper());
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

        jdbcTemplate.update(USER_INSERT, userVO.getEmail(), userVO.getPassword(), userVO.getNickname());
    }

    public String loginCheck(UserVO userVO){
        String loginPossible="no";
        List<UserVO> userVOList=jdbcTemplate.query(USER_LIST,new UserRowMaper());
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
        List<UserVO> userVOList=jdbcTemplate.query(USER_LIST,new UserRowMaper());
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
        jdbcTemplate.update(USER_PW_UPDATE, userVO.getPassword(),userVO.getEmail());
    }

    public void deleteUser(String param){
        jdbcTemplate.update(USER_DELETE, param);
    }
}
