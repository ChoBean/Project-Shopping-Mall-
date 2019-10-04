package com.myproject.shop.dao;

import org.springframework.stereotype.Repository;

import com.myproject.shop.util.RegisterRequest;
import com.myproject.shop.vo.UserVO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO{
	
	public UserVO selectByEmail(String email) {
		return (UserVO)selectOne("user.selectByEmail", email);
	}
	 
	public UserVO selectById(String userid) {
		return (UserVO)selectOne("user.selectById", userid);
	}
	 
	public void insertUser(RegisterRequest regReq) {
		insert("user.register", regReq);
	}
	
    //해당 email에 인증 키 업데이트
    public void createAuthKey(String email, String authkey) throws Exception {
        UserVO vo = new UserVO();
        vo.setAuthkey(authkey);
        vo.setEmail(email);
        update("user.createAuthKey", vo);
    }
    
    //이메일 인증 코드 확인
    public UserVO chkAuth(String email) throws Exception {
        return (UserVO)selectOne("user.chkAuth", email);
    }
    
    //인증 후 계정 활성화
    public void userAuth(UserVO vo) throws Exception {
        update("user.userAuth", vo);
    }

}
