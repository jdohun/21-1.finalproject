package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.service.UserService;
import com.dev.vo.UserVO;

public class ModifyController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserVO user = new UserVO(); 
		user.setId(req.getParameter("MID"));
		user.setName(req.getParameter("MName"));
		user.setPwd(req.getParameter("MPW"));
		user.setAddr(req.getParameter("MAddr"));
		user.setPhone(req.getParameter("MPhone"));
		user.setEmail1(req.getParameter("Memail1"));
		user.setEmail2(req.getParameter("Memail2"));
		UserService service = UserService.getInstance();
		int result = service.modify(user);
		if(result == 1) {
			user = service.charge(user, "0");
			session.setAttribute("user", user);
		}
		String path = "modify.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
