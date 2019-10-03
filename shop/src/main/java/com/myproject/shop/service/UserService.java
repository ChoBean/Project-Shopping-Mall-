package com.myproject.shop.service;

import com.myproject.shop.util.RegisterRequest;
import com.myproject.shop.vo.UserVO;


public interface UserService {
	 void register(RegisterRequest regReq) throws Exception;
	 
	 UserVO idCheck(String userid) throws Exception;
}
