package com.dev.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.service.ItemService;
import com.dev.vo.ItemVO;
import com.dev.vo.OSheetVO;
import com.dev.vo.UserVO;

public class ShowOrderListController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserVO user = (UserVO)session.getAttribute("user");
		session.setAttribute("user", user);
		
		String orderer = (String)req.getSession().getAttribute("id");
	
		ItemService service = ItemService.getInstance();
		ArrayList<OSheetVO> osList = service.getOrderList(orderer);
		if(osList.isEmpty()) {
			String msg = "주문 내역이 없습니다";
			req.setAttribute("msg", msg);
		}
		else {
			ArrayList<ItemVO> itemList = service.getCompletedProd(osList);
			req.setAttribute("osList", osList);
			req.setAttribute("itemList", itemList);
		}
		
		String path = "orderList.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
