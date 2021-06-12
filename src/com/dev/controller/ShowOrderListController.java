package com.dev.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.ItemService;
import com.dev.vo.ItemVO;
import com.dev.vo.OSheetVO;

public class ShowOrderListController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderer = (String)req.getSession().getAttribute("id");
	
		ItemService service = ItemService.getInstance();
		ArrayList<OSheetVO> osList = service.getOrderList(orderer);
		ArrayList<ItemVO> itemList = service.getCompletedProd(osList);
		
		req.setAttribute("osList", osList);
		req.setAttribute("itemList", itemList);
		
		String path = "orderList.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
