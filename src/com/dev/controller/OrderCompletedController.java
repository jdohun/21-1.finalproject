package com.dev.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.ItemService;
import com.dev.vo.UserVO;

public class OrderCompletedController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderer = (String)req.getSession().getAttribute("id");
		String[] prodValue = (String[])req.getParameterValues("prodValue"); // pNum,sOption,quantity
		int length = prodValue.length;

		ArrayList<String> prod = new ArrayList<String>();
		
		for(int i = 0; i< length; ++i) {
			String[] prodTemp = prodValue[i].split(",");
			prod.add(prodTemp[0]); //pNum
			prod.add(prodTemp[1]); //sOption
			prod.add(prodTemp[2]); // quantity
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmss");
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String date = format.format(now);
		
		UserVO user = ItemService.getInstance().orderComplete(date, orderer, prod);
		req.getSession().setAttribute("user", user);
		
		String path = "orderCompleted.show";
		HttpUtil.forward(req, resp, path);
	}
}
