package com.dev.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.dev.vo.CartVO;
import com.dev.vo.ItemVO;
import com.dev.vo.UserVO;

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
			System.out.println("ItemDAO-connect:ClassNotFound " + e);
		} catch (SQLException e) {
			System.out.println("ItemDAO-connect:SQL " + e);
		}
		
		return con;
	}

	private void close(PreparedStatement pstmt, Connection con) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("ItemDAO-close pstmt:" + e);
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("ItemDAO-close con:" + e);
		}
	}
	
	private void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			rs.close();
		} catch (SQLException e) {
			System.out.println("ItemDAO-close rs:" + e);
		}
	}
	
	public ArrayList<ItemVO> showAll() {
		ArrayList<ItemVO> List = new ArrayList<ItemVO>();
		Connection con = connect();
		String sql = "select *, group_concat(sOption) as sOptions from item group by name;";
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
				item.setPrice(rs.getInt("price"));
				item.setsOptions(rs.getString("sOptions"));
				item.setUrl(rs.getString("url"));
				List.add(item);
			}
			
		} catch (SQLException e) {
			System.out.println("ItemDAO showAll: " + e);
		} finally {
			if(rs != null) close(rs, pstmt, con);
			else close(pstmt, con);
		}
		
		return List;
	}

	public ItemVO showItem(String pNum) {
		Connection con = connect();
		String sql = "select *, group_concat(sOption) as sOptions from item where name=? group by name;";
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
				item.setPrice(rs.getInt("price"));
				item.setsOptions(rs.getString("sOptions"));
				item.setUrl(rs.getString("url"));
			}
			
		} catch (SQLException e) {
			System.out.println("ItemDAO showItem: " + e);
		} finally {
			if(rs != null) close(rs, pstmt, con);
			else close(pstmt, con);
		}
		
		return item;
	}

	public void orderOnList(ArrayList<CartVO> cartList) { // 선택한 상품 장바구니에 넣기
		Connection con = connect();
		String sql = "insert into cart values(?,?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			for(int i = 0; i< cartList.size(); ++i) {
				pstmt = con.prepareStatement(sql);
				CartVO cart = cartList.get(i);
				pstmt.setString(1, cart.getOrderer());
				pstmt.setString(2, cart.getpNum());
				pstmt.setString(3, cart.getsOption());
				pstmt.setInt(4, cart.getQuantity());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("ItemDAO OrderOnList: " + e);
		} finally {
			close(pstmt, con);
		}
	}

	public ArrayList<ItemVO> showCartAll(String orderer) {
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		Connection con = connect();
		String sql  = "select i.category, i.pNum, i.name, c.sOption, i.price, i.url, c.quantity from item i, cart c where c.orderer = ? and c.pNum = i.pNum group by pNum, sOption;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO item = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderer);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				// 장바구니 안에 있는 물건 목록을 가져옴
				item = new ItemVO();
				item.setCategory(rs.getString("category"));
				item.setpNum(rs.getString("pNum"));
				item.setName(rs.getString("name"));
				item.setsOptions(rs.getString("sOption"));
				item.setPrice(rs.getInt("price"));
				item.setUrl(rs.getString("url"));
				item.setQuantity(rs.getInt("quantity"));
				itemList.add(item);
			}
		} catch (SQLException e) {
			System.out.println("showCartAll: " + e);
		} finally {
			if(rs != null) close(rs, pstmt, con);
			else close(pstmt, con);
		}
		return itemList;
	}

	public ArrayList<ItemVO> getSelected(String orderer, ArrayList<String> prod) {
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		Connection con = connect();
		String sql  = "select i.category, i.pNum, i.name, c.sOption, i.price, i.url, c.quantity from item i, cart c where c.orderer = ? and c.pNum=? and c.sOption = ?  and c.pNum = i.pNum group by sOption;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO item = null;
		
		try {
			for(int i = 0; i < prod.size(); i+=2) {
				String pNum = prod.get(i);
				String sOption = prod.get(i+1);
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, orderer);
				pstmt.setString(2, pNum);
				pstmt.setString(3, sOption);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					// 장바구니 안에 있는 물건 목록을 가져옴
					item = new ItemVO();
					item.setCategory(rs.getString("category"));
					item.setpNum(rs.getString("pNum"));
					item.setName(rs.getString("name"));
					item.setsOptions(rs.getString("sOption"));
					item.setPrice(rs.getInt("price"));
					item.setQuantity(rs.getInt("quantity"));
					item.setUrl(rs.getString("url"));
					itemList.add(item);
				}
			}
		} catch (SQLException e) {
			System.out.println("getSelected: " + e);
		} finally {
			if(rs != null) close(rs, pstmt, con);
			else close(pstmt, con);
		}
		return itemList;
	}

	public UserVO orderComplete(String date, String orderer, ArrayList<String> prod) {
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		String oNumber = date;
		int result = 0;
		Connection con = connect();
		String sql  = "insert into oSheet(oNum, orderer, pNum, sOption, quantity) values(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		ItemVO item = null;
		
		try {
			for(int i = 0; i < prod.size(); i+=3) {
				String pNum = prod.get(i);
				String sOption = prod.get(i+1);
				int quantity = Integer.parseInt(prod.get(i+2));
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, oNumber);
				pstmt.setString(2, orderer);
				pstmt.setString(3, pNum);
				pstmt.setString(4, sOption);
				pstmt.setInt(5, quantity);
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("orderComplete: " + e);
		} finally {
			close(pstmt, con);
		}
		if(result == 1) {
			int bill = checkBill(orderer, prod);
			UserVO user = payBill(bill, orderer);
			deleteCart(orderer, prod);
			return user;
		}
		
		return null;
	}

	public int checkBill(String orderer, ArrayList<String> prod) {
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		Connection con = connect();
		String sql  = "select i.category, i.pNum, i.name, c.sOption, i.price, i.url, c.quantity from item i, cart c where c.orderer = ? and c.pNum=? and c.sOption = ?  and c.pNum = i.pNum group by sOption;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO item = null;
		int bill = 0;
		
		try {
			for(int i = 0; i < prod.size(); i+=3) {
				String pNum = prod.get(i);
				String sOption = prod.get(i+1);
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, orderer);
				pstmt.setString(2, pNum);
				pstmt.setString(3, sOption);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					// 장바구니 안에 있는 물건 목록을 가져옴
					item = new ItemVO();
					item.setCategory(rs.getString("category"));
					item.setpNum(rs.getString("pNum"));
					item.setName(rs.getString("name"));
					item.setsOptions(rs.getString("sOption"));
					item.setPrice(rs.getInt("price"));
					item.setQuantity(rs.getInt("quantity"));
					item.setUrl(rs.getString("url"));
					itemList.add(item);
				}
			}
			
			for(int i = 0; i < itemList.size(); ++i) {
				item = itemList.get(i);
				bill += item.getPrice() * item.getQuantity();
			}
		} catch (SQLException e) {
			System.out.println("checkBill: " + e);
		} finally {
			if(rs != null) close(rs, pstmt, con);
			else close(pstmt, con);
		}
		return bill;
	}
	
	public UserVO payBill(int bill, String orderer) {
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		Connection con = connect();
		String sql = "update user set money = money - ? where id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO item = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bill);
			pstmt.setString(2, orderer);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("payBill: " + e);
		} finally {
			close(pstmt, con);
		}
		return UserDAO.getInstance().getUser(orderer);
	}
	
	private void deleteCart(String orderer, ArrayList<String> prod) {
		Connection con = connect();
		String sql = "delete from cart where orderer = ? and pNum=? and sOption = ?";
		PreparedStatement pstmt = null;
		
		try {
			for(int i = 0; i < prod.size(); i+=3) {
				String pNum = prod.get(i);
				String sOption = prod.get(i+1);
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, orderer);
				pstmt.setString(2, pNum);
				pstmt.setString(3, sOption);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("deleteCart: " + e);
		} finally {
			close(pstmt, con);
		}
	}
}