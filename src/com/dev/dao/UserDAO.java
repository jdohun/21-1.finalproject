package com.dev.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dev.vo.UserVO;

public class UserDAO {
	// 자기 자신을 객체로 생성
	private static UserDAO dao = new UserDAO();
	
	// 디폴트 생성자 접근 제한
	private UserDAO() {};
	
	// 객체 반환
	public static UserDAO getInstance() {
		return dao;
	}
	
	private Connection connect() {
		Connection con = null;
		String url = "jdbc:mysql://localhost/shop?characterEncoding=UTF-8&&Timezone=UTC";
		String user = "root";
		String passwd = "1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException e) {
			System.out.println("UserDAO-connect:ClassNotFound " + e);
		} catch (SQLException e) {
			System.out.println("UserDAO-connect:SQL " + e);
		}
		
		return con;
	}

	private void close(PreparedStatement pstmt, Connection con) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("UserDAO-close pstmt:" + e);
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("UserDAO-close con:" + e);
		}
	}
	
	private void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			rs.close();
		} catch (SQLException e) {
			System.out.println("UserDAO-close rs:" + e);
		}
	}
	
	public void signUp(UserVO user) {
		Connection con = connect();
		String sql = "insert into user values(?,?,?,?,?,?);";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getAddr());
			pstmt.setString(6, user.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("UserDAO-signUp: " + e);
		} finally {
			close(pstmt, con);
		}
	}

	public boolean login(String id, String pwd) {
		boolean result = false;
		
		Connection con = connect();
		String sql = "select * from user where id=? and pwd=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) result = true;
			
		} catch (SQLException e) {
			System.out.println("UserDAO-login: " + e);
		} finally {
			try {
				if(rs.next()) {
					close(rs, pstmt, con);
				}
				else {
					close(pstmt, con);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}

