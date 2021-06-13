package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.service.ItemService;
import com.dev.vo.ItemVO;
import com.dev.vo.ODetailVO;
import com.dev.vo.OSheetVO;
import com.dev.vo.UserVO;

public class ShowOrderDetailController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserVO user = (UserVO)session.getAttribute("user");
		session.setAttribute("user", user);
		
		String oNum = (String)req.getParameter("oNum");
		ItemService service = ItemService.getInstance();
		ODetailVO oDetail = service.getODetail(oNum);
		OSheetVO oSheet = service.getOSheet(oNum);
		ItemVO item = service.showItem(oSheet.getpNum());
		req.setAttribute("oDetail", oDetail);
		req.setAttribute("oSheet", oSheet);
		req.setAttribute("item", item);
		
		String path = "orderDetail.jsp";
		HttpUtil.forward(req, resp, path);
	}
}