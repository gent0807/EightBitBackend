package com.eightbit.biz.user.persistence;

import com.eightbit.biz.user.vo.UserVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMaper implements RowMapper<UserVO> {
    public UserVO mapRow(ResultSet rs,int rowNum)throws SQLException{
        UserVO userVO=new UserVO();
        userVO.setEmail(rs.getString("email"));
        userVO.setPassword(rs.getString("password"));
        userVO.setNickname(rs.getString("nickname"));
        return userVO;
    }
}
