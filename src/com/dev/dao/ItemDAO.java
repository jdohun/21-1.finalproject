package com.dev.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.tomcat.jni.OS;

import com.dev.vo.CartVO;
import com.dev.vo.ItemVO;
import com.dev.vo.ODetailVO;
import com.dev.vo.OSheetVO;
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
		String sql = "select *, group_concat(sOption) as sOptions from item where pNum=? group by name;";
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

	public UserVO orderComplete(String orderer, ArrayList<String> prod, ODetailVO oDetail) {
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		ArrayList<ODetailVO> oDetList = new ArrayList<ODetailVO>();
		int result = 0;
		
		Connection con = connect();
		String sql  = "insert into oSheet(oNum, orderer, pNum, sOption, quantity, bill) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		ItemVO item = null;
		int finalBill = 0;
		
		try {
			for(int i = 0; i < prod.size(); i+=3) {
				// 주문번호 생성
				SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmss");
				Timestamp now = new Timestamp(System.currentTimeMillis());
				String date = format.format(now);
				String oNum = date + "-" ;
				
				for(int j = 0; j<5; ++j) {
					double dValue = Math.random();
					char cValue = (char)((dValue * 26) + 65);   // 대문자
					oNum += cValue;
				}
				
				// 상품당 계산서 생성
				String pNum = prod.get(i);
				String sOption = prod.get(i+1);
				int quantity = Integer.parseInt(prod.get(i+2));
				ArrayList<String> thisOrder = new ArrayList<String>();
				thisOrder.add(pNum);
				thisOrder.add(sOption);
				thisOrder.add(prod.get(i+2));
				int bill = checkBill(orderer, thisOrder);
				
				ODetailVO OD = new ODetailVO();
				
				//
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, oNum);
				pstmt.setString(2, orderer);
				pstmt.setString(3, pNum);
				pstmt.setString(4, sOption);
				pstmt.setInt(5, quantity);
				pstmt.setInt(6, bill);
				result = pstmt.executeUpdate();
				
				OD.setoNum(oNum);
				OD.setoName(oDetail.getoName());
				OD.setoAddr(oDetail.getoAddr());
				OD.setoPhone(oDetail.getoPhone());
				OD.setoEmail1(oDetail.getoEmail1());
				OD.setoEmail2(oDetail.getoEmail2());
				OD.setrName(oDetail.getrName());
				OD.setrAddr(oDetail.getrAddr());
				OD.setrPhone(oDetail.getrPhone());
				OD.setrText(oDetail.getrText());
				
				oDetList.add(OD);
				finalBill += bill;
			}
		} catch (SQLException e) {
			System.out.println("orderComplete: " + e);
		} finally {
			close(pstmt, con);
		}
		if(result == 1) {
			UserVO user = payBill(finalBill, orderer);
			orderDetailComplete(oDetList);
			deleteCart(orderer, prod);
			return user;
		}
		else return null;
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
	
	public void deleteCart(String orderer, ArrayList<String> prod) {
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

	public void orderDetailComplete(ArrayList<ODetailVO> oDetList) {
		Connection con = connect();
		String sql = "insert into oDetail values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			for(int i = 0; i < oDetList.size(); ++i) {
				ODetailVO oDetail = oDetList.get(i);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, oDetail.getoNum());
				pstmt.setString(2, oDetail.getoName());
				pstmt.setString(3, oDetail.getoAddr());
				pstmt.setString(4, oDetail.getoPhone());
				pstmt.setString(5, oDetail.getoEmail1());
				pstmt.setString(6, oDetail.getoEmail2());
				pstmt.setString(7, oDetail.getrName());
				pstmt.setString(8, oDetail.getrAddr());
				pstmt.setString(9, oDetail.getrPhone());
				pstmt.setString(10, oDetail.getrText());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("orderDetailComplete: " + e);
		} finally {
			close(pstmt, con);
		}
	}

	public ArrayList<OSheetVO> getOrdered(String orderer, int prodCount) {
		ArrayList<OSheetVO> osList = new ArrayList<OSheetVO>();
		OSheetVO osheet = null;
		Connection con = connect();
		String sql = "select * from oSheet where orderer = ? order by orderTime desc limit ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderer);
			pstmt.setInt(2, prodCount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				osheet = new OSheetVO();
				osheet.setoNum(rs.getString("oNum"));
				osheet.setpNum(rs.getString("pNum"));
				osheet.setsOption(rs.getString("sOption"));
				osheet.setQuantity(rs.getInt("quantity"));
				osheet.setBill(rs.getInt("bill"));
				osheet.setOrderTime(rs.getString("orderTime"));
				osheet.setRemark(rs.getString("remark"));
				osList.add(osheet);
			}
		} catch (SQLException e) {
			System.out.println("getOrdered: " + e);
		} finally {
			if(rs != null) close(rs, pstmt, con);
			else close(pstmt, con);
		}
		return osList;
	}
	
	public ArrayList<OSheetVO> getOrderedList(String orderer) {
		ArrayList<OSheetVO> List = new ArrayList<OSheetVO>();
		OSheetVO osheet = null;
		Connection con = connect();
		String sql = "select * from osheet where orderer = ? order by orderTime desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderer);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				osheet = new OSheetVO();
				osheet.setoNum(rs.getString("oNum"));
				osheet.setOrderTime(rs.getString("orderTime"));
				osheet.setpNum(rs.getString("pNum"));
				osheet.setsOption(rs.getString("sOption"));
				osheet.setQuantity(rs.getInt("quantity"));
				osheet.setBill(rs.getInt("bill"));
				osheet.setRemark(rs.getString("remark"));
				List.add(osheet);
			}
		} catch (SQLException e) {
			System.out.println("getOrderedList: " + e);
		} finally {
			if(rs != null) close(rs, pstmt, con);
			else close(pstmt, con);
		}
		return List;
	}

	public ODetailVO getODetail(String oNum) {
		ODetailVO oDetail = null;
		Connection con = connect();
		String sql = "select * from oDetail where oNum = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, oNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				oDetail = new ODetailVO();
				oDetail.setoNum(oNum);
				oDetail.setoName(rs.getString("oName"));
				oDetail.setoAddr(rs.getString("oAddr"));
				oDetail.setoPhone(rs.getString("oPhone"));
				oDetail.setoEmail1(rs.getString("oEmail1"));
				oDetail.setoEmail2(rs.getString("oEmail2"));
				oDetail.setrName(rs.getString("rName"));
				oDetail.setrAddr(rs.getString("rAddr"));
				oDetail.setrPhone(rs.getString("rPhone"));
				oDetail.setrText(rs.getString("rText"));
			}
		} catch (SQLException e) {
			System.out.println("getODetail: " + e);
		} finally {
			if(rs != null) close(rs, pstmt, con);
			else if(pstmt != null) close(pstmt, con);
			else
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("getODetail: " + e);
				}
		}
		return oDetail;
	}

	public ArrayList<ItemVO> getCompletedProd(ArrayList<OSheetVO> osList) {
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		Connection con = connect();
		String sql  = "select * from item where pNum=? and sOption=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO item = null;
		
		try {
			for(int i = 0; i < osList.size(); ++i) {
				OSheetVO osheet = osList.get(i);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, osheet.getpNum());
				pstmt.setString(2, osheet.getsOption());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					// 장바구니 안에 있는 물건 목록을 가져옴
					item = new ItemVO();
					item.setCategory(rs.getString("category"));
					item.setpNum(rs.getString("pNum"));
					item.setName(rs.getString("name"));
					item.setsOptions(rs.getString("sOption"));
					item.setPrice(rs.getInt("price"));
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

	public ArrayList<ItemVO> getDirect(ArrayList<CartVO> cartList) {
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		Connection con = connect();
		String sql  = "select i.category, i.pNum, i.name, c.sOption, i.price, i.url, c.quantity from item i, cart c where c.orderer = ? and c.pNum=? and c.sOption = ?  and c.pNum = i.pNum group by sOption;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO item = null;
		String orderer = cartList.get(0).getOrderer();
		try {
			for(int i = 0; i < cartList.size(); ++i) {
				CartVO cartItem = cartList.get(i);
				String pNum = cartItem.getpNum();
				String sOption = cartItem.getsOption();
				
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
			System.out.println("getDirect: " + e);
		} finally {
			if(rs != null) close(rs, pstmt, con);
			else close(pstmt, con);
		}
		return itemList;
	}

	public OSheetVO getOSheet(String oNum) {
		Connection con = connect();
		String sql = "select * from oSheet where oNum=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OSheetVO oSheet = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, oNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				oSheet = new OSheetVO();
				oSheet.setoNum(oNum);
				oSheet.setOrderer(rs.getString("orderer"));
				oSheet.setpNum(rs.getString("pNum"));
				oSheet.setsOption(rs.getString("sOption"));
				oSheet.setQuantity(rs.getInt("quantity"));
				oSheet.setBill(rs.getInt("bill"));
				oSheet.setOrderTime(rs.getString("orderTime"));
				oSheet.setRemark(rs.getString("remark"));
			}
			
		} catch (SQLException e) {
			System.out.println("ItemDAO showItem: " + e);
		} finally {
			if(rs != null) close(rs, pstmt, con);
			else close(pstmt, con);
		}
		
		return oSheet;
	}
}