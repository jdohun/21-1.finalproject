package com.dev.service;

import com.dev.dao.UserDAO;
import com.dev.vo.UserVO;

public class UserService {
	UserDAO dao = UserDAO.getInstance();
	private static UserService userService = new UserService();
	
	private UserService(){};
	
	public static UserService getInstance() {
		return userService;
	}
	
	public void signUp(UserVO user) {
		dao.signUp(user);
	}

	public boolean login(String id, String pwd) {
		return dao.login(id, pwd);
	}
}
