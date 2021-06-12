<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dev.vo.ItemVO" %>
<%@ page import="com.dev.vo.OSheetVO" %>
<%@ page import="com.dev.vo.ODetailVO" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/BasicSet.css">
	<link rel="stylesheet" href="css/Header.css">
	<link rel="stylesheet" href="css/Footer.css">
	<link rel="stylesheet" href="css/nav.css">
	<link rel="stylesheet" href="css/orderCompleted.css">
	<title>주문완료</title>
</head>
<%
	String id = (String)request.getSession().getAttribute("id");
	if(id == null){
		response.sendRedirect("login.jsp");
	}
	DecimalFormat commas = new DecimalFormat("###,###");
	ArrayList<ItemVO> itemList = (ArrayList<ItemVO>)request.getAttribute("itemList");
	ArrayList<OSheetVO> osList = (ArrayList<OSheetVO>)request.getAttribute("osList");
	ODetailVO oDetail = (ODetailVO)request.getAttribute("oDetail");
%>
<body>
<!--Wrapper-->
<div id="Wrapper">
<!--header-->
<%@ include file="Header_guest.jsp"%>
<!--/header-->
<!--main-->
<div id="Container">
	<div class="contents">
		<div class="titleArea">
			<h2>ORDER COMPLETED</h2>
		</div>
		<div class="orderInfo">
			<div class="title"><h3>주문정보</h3></div>
			<table class="orderInfoTable">
				<%for(int i = 0; i<osList.size(); ++i){ %>
				<tr>
					<th>주문번호</th>
					<td><%= %></td>
				</tr>
				<tr>
					<th>주문일자</th>
					<td><%= %></td>
				</tr>
				<tr>
					<th>주문자</th>
					<td><%= %></td>
				</tr>
				<tr>
					<th>주문처리상태</th>
					<td>결제완료 <button>주문취소</button></td>
				</tr>
				
				<%} %>
			</table>
		</div>
		<div id="orderListArea">
			<div class="title"><h3>주문내역</h3></div>
			<table class="orderListTable">
				<thead>
					<tr>
						<th>이미지</th>
						<th>상품정보</th>
						<th>판매가</th>
						<th>수량</th>
						<th>주문처리상태</th>
						<th>합계</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href=""><img src="#" alt="주문상품"></a></td>
						<td>
							<a href="">
								<strong class="name">버핑레더 라이더자켓</strong>
							</a>
							<div class="option">[옵션: S]</div>
						</td>
						<td>24,000</td>
						<td>1</td>
						<td>결제완료</td>
						<td><strong><span>24,000</span></strong></td>
					</tr>
				</tbody>
				<tfoot>
					<td></td>
					<td colspan="8">
						<span>
							상품구매금액 <strong>24,000</strong> + 배송비 0 (무료) = 합계:  <strong>24,000</strong>
						</span>
					</td>
				</tfoot>
			</table>
		</div>
		<div class="orderArea">
			<div class="title"><h3>배송지 정보</h3></div>
			<table class="orderAreaTable">
				<tr>
					<th>받으시는 분</th>
					<td><span>정도훈</span></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><span>서울시 은평구</span></td>
				</tr>
				<tr>
					<th>휴대전화</th>
					<td><span>010-1234-5678</span></td>
				</tr>
				<tr>
					<th>배송 메시지</th>
					<td><span>호잇</span></td>
				</tr>
			</table>
		</div>
		<div class="buttonPlace">
			<span class="buttonShpae">
				<a href="" id="showOrderList">주문목록보기</a>
			</span>
		</div>
	</div>
</div>
<!--/main-->
<!--footer-->
<%@ include file="Footer.jsp"%>
<!--/footer-->
</div>
<!--/Wrapper-->
</body>
</html>