package com.dev.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.ItemService;
import com.dev.vo.ItemVO;

public class ShowAllController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<ItemVO> List = null;
		List = ItemService.getInstance().showAll();
		req.setAttribute("List", List);

		String path = null;
		String reqPath = req.getServletPath();
		if(reqPath.equals("/index.show")) path = "/index.jsp";
		else if(reqPath.equals("/Home.show")) path = "/Home.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
