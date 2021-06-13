package com.dev.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController extends HttpServlet {
	HashMap<String, Controller> list = new HashMap<String, Controller>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		list.put("/index.show", new ShowAllController());
		list.put("/description.show",  new ShowDescriptionController());
		list.put("/orderform.show",  new ShowOrderFormController());
		list.put("/cart.show",  new ShowCartController());
		list.put("/cartAllOrder.show",  new ShowCartAllOrderController());
		list.put("/selectedOrder.show",  new ShowSelectedOrderController());
		list.put("/orderList.show",  new ShowOrderListController());
		list.put("/deleteCart.show",  new ShowDeleteCartController());
		list.put("/odetail.show",  new ShowOrderDetailController());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		Controller subController = list.get(path);
		subController.execute(req, resp);
	}
}
