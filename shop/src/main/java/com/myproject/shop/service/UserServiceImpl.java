package com.myproject.shop.service;


import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myproject.exception.AlreadyExistingEmailException;
import com.myproject.shop.dao.UserDAO;
import com.myproject.shop.util.MailHandler;
import com.myproject.shop.util.RegisterRequest;
import com.myproject.shop.util.TempKey;
import com.myproject.shop.vo.UserVO;

@Service("UserService")
public class UserServiceImpl implements UserService{
	@Resource(name="userDAO")
    private UserDAO userDAO;
	
	@Inject
	PasswordEncoder passwordEncoder;
	@Inject
	private JavaMailSender mailSender;
	
	@Override
	public void register(RegisterRequest regReq) throws Exception {
	       UserVO email = userDAO.selectByEmail(regReq.getEmail());
	        if(email!=null) {
	            throw new AlreadyExistingEmailException(regReq.getEmail()+" is duplicate email.");
	        }
	        //비밀번호 암호화
	        String encPassword = passwordEncoder.encode(regReq.getUserpw());
	        regReq.setUserpw(encPassword);
	        //회원가입 메서드
	        userDAO.insertUser(regReq);
	        //인증키 생성
	        String key = new TempKey().getKey(50, false);
	        //인증키 db저장
	        userDAO.createAuthKey(regReq.getEmail(), key);
	        System.out.println("db저장성공");
	        //메일전송
	        MailHandler sendMail = new MailHandler(mailSender);
	        sendMail.setSubject("메일 인증 서비스 입니다.");
	        sendMail.setText(new StringBuffer().append(""
	        		+ "<h1>메일 인증 서비스</h1>").append(
	        		"<a href ='http://localhost:8080/emailConfirm?email=").append(
	        				regReq.getEmail()).append(""
	        						+ "&authkey=").append(key).append(
	        								"'target='_blank'>이메일 인증 확인</a>").toString());
	        sendMail.setFrom("sbjo7714@gmail.com", "Football Mall");
	        
	        sendMail.setTo(regReq.getEmail());
	        sendMail.send();
	}

	@Override
	public UserVO idCheck(String userid) throws Exception {
		return userDAO.selectById(userid);
	}

	//이메일 인증 키 검증
	@Override
	public UserVO userAuth(UserVO user) throws Exception {
		UserVO vo = new UserVO();

		System.out.println("Service에서의 user 값은 :"+user);
		System.out.println("user의 이메일 값은 : "+user.getEmail());
		vo = userDAO.chkAuth(user.getEmail());
		System.out.println("검사 결과는" + vo);
		if(vo!=null) {
			try {
				System.out.println("검사성공");
				System.out.println(vo+"vo");
				userDAO.userAuth(user);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	
}
