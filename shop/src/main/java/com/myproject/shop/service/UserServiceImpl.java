package com.myproject.shop.service;

import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myproject.exception.AlreadyExistingEmailException;
import com.myproject.shop.dao.UserDAO;
import com.myproject.shop.util.RegisterRequest;
import com.myproject.shop.vo.UserVO;

@Service("UserService")
public class UserServiceImpl implements UserService{
	@Resource(name="userDAO")
    private UserDAO userDAO;
	
	@Inject
	PasswordEncoder passwordEncoder;
	
	@Override
	public void register(RegisterRequest regReq) throws Exception {
	       UserVO email = userDAO.selectByEmail(regReq.getEmail());
	        if(email!=null) {
	            throw new AlreadyExistingEmailException(regReq.getEmail()+" is duplicate email.");
	        }
	        //비밀번호 암호화
	        String encPassword = passwordEncoder.encode(regReq.getUserpw());
	        regReq.setUserpw(encPassword);
	        userDAO.insertUser(regReq);
	}

	@Override
	public UserVO idCheck(String userid) throws Exception {
		return userDAO.selectById(userid);
	}
	
}
