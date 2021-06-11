package com.dev.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.ItemService;
import com.dev.vo.ItemVO;

public class ShowSelectedOrderController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderer = (String)req.getSession().getAttribute("id");
		String[] prodSOption = (String[])req.getParameterValues("prod"); // pNum,sOption

		ArrayList<String> prod = new ArrayList<String>();
		
		for(int i = 0; i<prodSOption.length; ++i) {
			String[] prodTemp = prodSOption[i].split(",");
			prod.add(prodTemp[0]);
			prod.add(prodTemp[1]);
		}
		
		ArrayList<ItemVO> itemList = ItemService.getInstance().getSelected(orderer, prod);
		
		req.setAttribute("itemList", itemList);
		String path = "orderform.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
