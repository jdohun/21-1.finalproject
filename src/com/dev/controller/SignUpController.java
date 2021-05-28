package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.UserService;
import com.dev.vo.UserVO;

public class SignUpController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); 
		//요청 정보 변수화
		String SignUpID = req.getParameter("SignUpID");
		String SignUpPW = req.getParameter("SignUpPW");
		String SignUpName = req.getParameter("SignUpName");
		String SignUpAddr = req.getParameter("SignUpAddr");
		String SignUpPhone = req.getParameter("SignUpPhone");
		String email = req.getParameter("email");
		
		// user 정보 저장
		UserVO user = new UserVO();
		user.setId(SignUpID);
		user.setPwd(SignUpPW);
		user.setName(SignUpName);
		user.setAddr(SignUpAddr);
		user.setPhone(SignUpPhone);
		user.setEmail(email);
		
		UserService service = UserService.getInstance();
		service.signUp(user);
		
		resp.sendRedirect("login.jsp");
	}
}
