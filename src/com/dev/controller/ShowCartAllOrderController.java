package com.dev.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.service.ItemService;
import com.dev.vo.ItemVO;
import com.dev.vo.UserVO;

public class ShowCartAllOrderController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserVO user = (UserVO)session.getAttribute("user");
		session.setAttribute("user", user);
		
		String orderer = (String)req.getSession().getAttribute("id");
		ArrayList<ItemVO> itemList = ItemService.getInstance().showCartAll(orderer);
		
		req.setAttribute("itemList", itemList);
		
		String path = "orderform.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
