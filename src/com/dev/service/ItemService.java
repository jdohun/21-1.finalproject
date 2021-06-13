package com.dev.service;

import java.util.ArrayList;

import com.dev.dao.ItemDAO;
import com.dev.vo.CartVO;
import com.dev.vo.ItemVO;
import com.dev.vo.ODetailVO;
import com.dev.vo.OSheetVO;
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

	public UserVO orderComplete(String orderer, ArrayList<String> prod, ODetailVO oDetail) {
		return dao.orderComplete(orderer, prod, oDetail);
	}
	
	public ArrayList<OSheetVO> getOrdered(String orderer, int prodCount) {
		return dao.getOrdered(orderer, prodCount);
	}

	public ODetailVO getODetail(String oNum) {
		return dao.getODetail(oNum);
	}

	public ArrayList<ItemVO> getCompletedProd(ArrayList<OSheetVO> osList) {
		return dao.getCompletedProd(osList);
	}

	public ArrayList<OSheetVO> getOrderList(String orderer) {
		return dao.getOrderedList(orderer);
	}

	public ArrayList<ItemVO> getDirect(ArrayList<CartVO> cartList) {
		return dao.getDirect(cartList);
	}

	public void deleteCart(String orderer, ArrayList<String> prod) {
		dao.deleteCart(orderer, prod);
	}

	public OSheetVO getOSheet(String oNum) {
		return dao.getOSheet(oNum);
	}
}
