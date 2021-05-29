package com.dev.service;

import java.util.ArrayList;

import com.dev.dao.ItemDAO;
import com.dev.vo.ItemVO;

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
}
