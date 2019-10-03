package com.myproject.shop.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myproject.exception.AlreadyExistingEmailException;
import com.myproject.exception.AlreadyExistingIdException;
import com.myproject.shop.dao.UserDAO;
import com.myproject.shop.util.RegisterRequest;
import com.myproject.shop.vo.UserVO;

@Service("UserService")
public class UserServiceImpl implements UserService{
	@Resource(name="userDAO")
    private UserDAO userDAO;

	@Override
	public void register(RegisterRequest regReq) throws Exception {
	       UserVO email = userDAO.selectByEmail(regReq.getEmail());
	        if(email!=null) {
	            throw new AlreadyExistingEmailException(regReq.getEmail()+" is duplicate email.");
	        }
	        UserVO id = userDAO.selectById(regReq.getUserid());
	        if(id!=null) {
	            throw new AlreadyExistingIdException(regReq.getUserid()+" is duplicate id.");
	        }
	        userDAO.insertUser(regReq);
	}
}
