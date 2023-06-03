package com.eightbit.biz.user.inter;

import com.eightbit.biz.user.vo.TempVO;
import com.eightbit.biz.user.vo.UserVO;

    public interface UserService {

        public String alreadyEmailRegisterCheck(String email);
        public String alreadyNickRegisterCheck(String nickname);
        public String insertUser(UserVO userVO);
        public String loginCheck(UserVO userVO);
        public String alreadyPasswordUsingCheck(UserVO userVO);
        public void updateUserPw(UserVO userVO);
        public void deleteUser(String param);

        public String checkRightAuthNum(TempVO tempVO);


}
