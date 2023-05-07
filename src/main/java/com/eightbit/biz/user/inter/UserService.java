package com.eightbit.biz.user.inter;

import com.eightbit.biz.user.vo.UserVO;

public interface UserService {

    public String alreadyEmailRegisterCheck(UserVO userVO, String alreadyEmailRegister);
    public String alreadyNickRegisterCheck(UserVO userVO, String alreadyNickRegister);
    public void insertUser(UserVO userVO);
    public String loginCheck(UserVO userVO,String loginPossible);

    public void updateUser(UserVO userVO);

    public void deleteUser(UserVO userVO);


}
