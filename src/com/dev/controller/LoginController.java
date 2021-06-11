package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.service.UserService;
import com.dev.vo.UserVO;

public class LoginController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = (String)req.getParameter("id");
		String pwd = req.getParameter("pwd");
		UserVO user = UserService.getInstance().login(id, pwd);
		
		String path = null;
		if(user.getId() != null) {
			HttpSession session = req.getSession();
			session.setAttribute("id", id);
			session.setAttribute("user", user);
			path = "index.show";
			resp.sendRedirect(path);
		} else {
			String msg = "아이디 또는 비밀번호가 틀렸습니다.";
			req.setAttribute("msg", msg);
			path = "/login.jsp";
			HttpUtil.forward(req, resp, path);
		}
	}
}
