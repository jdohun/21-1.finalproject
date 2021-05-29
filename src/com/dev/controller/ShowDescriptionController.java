package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.ItemService;
import com.dev.vo.ItemVO;

public class ShowDescriptionController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pNum = req.getParameter("pNum");
		
		ItemVO item = ItemService.getInstance().showItem(pNum);
		req.setAttribute("item", item);
		
		HttpUtil.forward(req, resp, "./description.jsp");
	}
}
