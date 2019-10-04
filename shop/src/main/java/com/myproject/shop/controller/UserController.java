package com.myproject.shop.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping(value="/joinConfirm", method=RequestMethod.POST)
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
	
	//이메일 인증 코드 검정
	@RequestMapping(value="/emailConfirm", method = RequestMethod.GET)
	public void emailConfirm(UserVO user, Model model, HttpServletResponse response) throws Exception{
		UserVO vo = new UserVO();
		response.setContentType("text/html; charset = utf-8"); 
		PrintWriter writer;
		writer = response.getWriter();
		vo = userServ.userAuth(user);
		if(vo == null) {
			writer.println("<script> alert('비정상적인 접근 입니다. 다시 시도해 주세요.'); location.href='/'</script>");
			writer.flush();
		}
		writer.println("<script> alert('인증이 완료되었습니다. 로그인 후 이용해 주세요.'); location.href='/'</script>");
		writer.flush();
		model.addAttribute("login",vo);
	}
	
	//회원가입 아이디 중복체크
	@ResponseBody
	@RequestMapping(value="/idCheck", method = RequestMethod.POST)
	public int postIdCheck(HttpServletRequest req) throws Exception{
		String userId = req.getParameter("userId");
		UserVO idCheck = userServ.idCheck(userId);
		
		int result = 0;
		if(idCheck != null) {
			result = 1;
		}
		return result;
	}
		
	
}
