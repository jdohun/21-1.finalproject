package com.dev.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.ItemService;
import com.dev.vo.ItemVO;

public class ShowCartController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderer = (String)req.getSession().getAttribute("id");
		ArrayList<ItemVO> itemList = ItemService.getInstance().showCartAll(orderer); 
		req.setAttribute("itemList", itemList);
		String path = "cart.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
