package com.dev.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.service.ItemService;
import com.dev.vo.CartVO;
import com.dev.vo.UserVO;

public class OrderOnListController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserVO user = (UserVO)session.getAttribute("user");
		session.setAttribute("user", user);
		
		String job = req.getParameter("job");
		
		String id = (String)req.getSession().getAttribute("id");
		if(id == null) HttpUtil.forward(req, resp, "login.jsp");
		
		String selectedItem = (String)req.getParameter("selectedItem");
		
		String[] selected = (String[])req.getParameterValues("selected[]");
		int length = selected.length; 
		String[] selectedCount = new String[length];
		for(int i = 0; i < length; ++i) { // 선택된 옵션의 주문 수량
			selectedCount[i]= req.getParameter(selected[i]+"Count");
		}
		
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		
		for(int i = 0; i < length; ++i) {
			CartVO cart = new CartVO();
			cart.setOrderer(id);
			cart.setpNum(selectedItem);
			cart.setsOption(selected[i]);
			cart.setQuantity(Integer.parseInt(selectedCount[i]));
			cartList.add(cart);
		}
		
		ItemService.getInstance().orderOnList(cartList);
		String path = null;
		
		if(job.equals("buy")) {
			path = "orderform.show";
			req.setAttribute("cartList", cartList);
		}
		else if(job.equals("cart")) path = "cart.show";
		
		HttpUtil.forward(req, resp, path);
	}
}
