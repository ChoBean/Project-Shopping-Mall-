package com.myproject.shop.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.exception.AlreadyExistingEmailException;
import com.myproject.shop.service.UserService;
import com.myproject.shop.util.RegisterRequest;
import com.myproject.shop.util.RegisterRequestValidator;
import com.myproject.shop.vo.UserVO;

@Controller
public class UserController {

	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main() {
		return "index";
	}
	
	@Resource(name="UserService")
	private UserService userServ;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView Join() {
		ModelAndView mv = new ModelAndView("/register/join");
		mv.addObject("registerRequest", new RegisterRequest());
		return mv;
	}
	
	@RequestMapping(value="/register/joinconfirm", method=RequestMethod.POST)
	public ModelAndView joinconfirm(RegisterRequest regReq, Errors errors) throws Exception{
		new RegisterRequestValidator().validate(regReq, errors);
		if(errors.hasErrors()) {
			ModelAndView mv = new ModelAndView("/register/join");
			return mv;
		}
		  try {
	            userServ.register(regReq);
	        } catch (AlreadyExistingEmailException e) {
	            errors.rejectValue("email", "duplicate", "이미 가입된 이메일입니다.");
	            ModelAndView mv = new ModelAndView("/register/join");
	            return mv;
	        } 
	        ModelAndView mv = new ModelAndView("/register/joinconfirm");
	        return mv;
	}
	
	//회원가입 아이디 중복체크
	@ResponseBody
	@RequestMapping(value="/idCheck", method = RequestMethod.POST)
	public int postIdCheck(HttpServletRequest req) throws Exception{
		String userId = req.getParameter("userId");
		UserVO idCheck = userServ.idCheck(userId);
		System.out.println("파라미터 값 : " + userId);
		System.out.println("대조 값 : " + idCheck);
		
		int result = 0;
		if(idCheck != null) {
			result = 1;
		}
		return result;
	}
		
	
}
