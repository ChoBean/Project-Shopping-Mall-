package com.myproject.shop.dao;

import org.springframework.stereotype.Repository;

import com.myproject.shop.util.RegisterRequest;
import com.myproject.shop.vo.UserVO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO{

	public UserVO selectByEmail(String email) {
		return (UserVO)selectOne("user.selectByEmail", email);
	}
	 
	public UserVO selectById(String id) {
		return (UserVO)selectOne("user.selectById", id);
	}
	 
	public void insertUser(RegisterRequest regReq) {
		insert("user.register", regReq);
	}
}
