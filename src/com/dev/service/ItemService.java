package com.dev.service;

import java.util.ArrayList;

import com.dev.dao.ItemDAO;
import com.dev.vo.CartVO;
import com.dev.vo.ItemVO;
import com.dev.vo.UserVO;

public class ItemService {
	private ItemDAO dao = ItemDAO.getInstance();
	private static ItemService service = new ItemService(); 
	
	private ItemService() {}
	
	public static ItemService getInstance() {
		return service;
	}
	
	public ArrayList<ItemVO> showAll() {
		return dao.showAll();
	}

	public ItemVO showItem(String pNum) {
		return dao.showItem(pNum);
	}

	public void orderOnList(ArrayList<CartVO> cartList) {
		dao.orderOnList(cartList);
	}

	public ArrayList<ItemVO> showCartAll(String orderer) {
		return dao.showCartAll(orderer);
	}

	public ArrayList<ItemVO> getSelected(String orderer, ArrayList<String> prod) {
		return dao.getSelected(orderer, prod);
	}

	public UserVO orderComplete(String date, String orderer, ArrayList<String> prod) {
		return dao.orderComplete(date, orderer, prod);
	}
}
