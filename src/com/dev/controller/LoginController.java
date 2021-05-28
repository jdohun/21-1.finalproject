package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.service.UserService;

public class LoginController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		boolean result = UserService.getInstance().login(id, pwd);
		
		String path = null;
		if(result = true) {
			HttpSession session = req.getSession();
			session.setAttribute("id", id);
			path = "/Home.jsp";
		} else {
			path = "/login.jsp";
			String msg = "아이디 또는 비밀번호가 틀렸습니다.";
			req.setAttribute("msg", msg);
		}
		
		HttpUtil.forward(req, resp, path);
	}
}
