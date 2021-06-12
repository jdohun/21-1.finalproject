package com.dev.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.ItemService;
import com.dev.vo.ItemVO;
import com.dev.vo.ODetailVO;
import com.dev.vo.OSheetVO;
import com.dev.vo.UserVO;

public class OrderCompletedController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderer = (String)req.getSession().getAttribute("id");
		String[] prodValue = (String[])req.getParameterValues("prodValue"); // pNum,sOption,quantity
		
		SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmss");
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String oNum = format.format(now);
		
		ODetailVO oDetail = new ODetailVO();
		oDetail.setoName((String)req.getParameter("oName"));
		oDetail.setoAddr((String)req.getParameter("oAddr"));
		oDetail.setoPhone((String)req.getParameter("oPhone"));
		oDetail.setoEmail1((String)req.getParameter("oemail1"));
		oDetail.setoEmail2((String)req.getParameter("oemail2"));
		oDetail.setrName((String)req.getParameter("receiverName"));
		oDetail.setrAddr((String)req.getParameter("receiverAddr"));
		oDetail.setrPhone((String)req.getParameter("receiverPhone"));
		oDetail.setrText((String)req.getParameter("receiverMessage"));
		
		ArrayList<String> prod = new ArrayList<String>();
		for(int i = 0; i< prodValue.length; ++i) {
			String[] prodTemp = prodValue[i].split(",");
			prod.add(prodTemp[0]); //pNum
			prod.add(prodTemp[1]); //sOption
			prod.add(prodTemp[2]); // quantity
		}
		int prodCount = prodValue.length;
		ItemService service = ItemService.getInstance();
		UserVO user = service.orderComplete(orderer, prod, oDetail);
		ArrayList<OSheetVO> osList = service.getOrdered(orderer, prodCount);
		ArrayList<ItemVO> itemList = service.getCompletedProd(osList);
		req.getSession().setAttribute("user", user);
		req.setAttribute("itemList", itemList);
		req.setAttribute("osList", osList);
		req.setAttribute("oDetail", oDetail);
		
		String path = "orderCompleted.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
