package com.eightbit.biz.user.inter;

import com.eightbit.biz.user.vo.UserVO;

    public interface UserService {

        public String alreadyEmailRegisterCheck(String email, String alreadyEmailRegister);
        public String alreadyNickRegisterCheck(String nickname, String alreadyNickRegister);
        public void insertUser(UserVO userVO);
        public String loginCheck(UserVO userVO,String loginPossible);
        public void updateUser(UserVO userVO);
        public void deleteUser(String param);


}
