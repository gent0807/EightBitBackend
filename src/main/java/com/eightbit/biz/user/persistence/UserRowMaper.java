package com.eightbit.biz.user.persistence;

import com.eightbit.biz.user.vo.UserVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMaper implements RowMapper<UserVO> {
    public UserVO mapRow(ResultSet rs,int rowNum)throws SQLException{
        UserVO userVO=new UserVO();
        userVO.setEmail(rs.getString("EMAIL"));
        userVO.setEmail(rs.getString("PASSWORD"));
        userVO.setNickname(rs.getString("NICKNAME"));
        return userVO;
    }
}
