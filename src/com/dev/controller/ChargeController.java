package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.service.UserService;
import com.dev.vo.UserVO;

public class ChargeController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String charge = (String)req.getParameter("charge");
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		UserVO user = (UserVO)session.getAttribute("user");
		user = UserService.getInstance().charge(user, charge);
		session.setAttribute("user", user);
		String path="charge.jsp";
		HttpUtil.forward(req, resp, path);
	}
}