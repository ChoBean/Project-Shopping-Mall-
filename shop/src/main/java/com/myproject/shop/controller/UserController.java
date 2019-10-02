package com.myproject.shop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.exception.AlreadyExistingEmailException;
import com.myproject.exception.AlreadyExistingIdException;
import com.myproject.shop.service.UserService;
import com.myproject.shop.util.RegisterRequest;
import com.myproject.shop.util.RegisterRequestValidator;

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
	        } catch (AlreadyExistingIdException e) {
	            errors.rejectValue("id", "duplicate", "이미 가입된 아이디입니다.");
	            ModelAndView mv = new ModelAndView("/register/join");
	            return mv;
	        }
	        ModelAndView mv = new ModelAndView("/register/joinconfirm");
	        return mv;
	}
}
