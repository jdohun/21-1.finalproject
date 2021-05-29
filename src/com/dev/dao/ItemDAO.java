package com.dev.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dev.vo.ItemVO;

public class ItemDAO {
	private static ItemDAO dao = new ItemDAO(); 
	private ItemDAO() {};
	
	public static ItemDAO getInstance() {
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
	
	public ArrayList<ItemVO> showAll() {
		ArrayList<ItemVO> List = new ArrayList<ItemVO>();
		Connection con = connect();
		String sql = "select * from itemview";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO item = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				item = new ItemVO();
				item.setCategory(rs.getString("Category"));
				item.setpNum(rs.getString("pNum"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getString("sPrice"));
				item.setSize(rs.getString("size"));
				item.setUrl(rs.getString("url"));
				List.add(item);
			}
			
		} catch (SQLException e) {
			System.out.println("showAll: " + e);
		} finally {
			if(rs != null) close(rs, pstmt, con);
			else close(pstmt, con);
		}
		
		return List;
	}

	public ItemVO showItem(String pNum) {
		Connection con = connect();
		String sql = "select * from itemview where pNum=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO item = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				item = new ItemVO();
				item.setCategory(rs.getString("Category"));
				item.setpNum(rs.getString("pNum"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getString("sPrice"));
				item.setSize(rs.getString("size"));
				item.setUrl(rs.getString("url"));
			}
			
		} catch (SQLException e) {
			System.out.println("showItem: " + e);
		} finally {
			if(rs != null) close(rs, pstmt, con);
			else close(pstmt, con);
		}
		
		return item;
	}
}
