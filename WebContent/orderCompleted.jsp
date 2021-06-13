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
<%@ include file="Header_user.jsp"%>
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
				<thead>
					<tr>
						<th>주문정보</th>
						<th>이미지</th>
						<th>상품정보</th>
						<th>수량</th>
						<th>가격</th>
						<th>주문처리상태</th>
					</tr>
				</thead>
				<tbody>
					<%
						for(int i = 0; i< osList.size(); ++i){
							OSheetVO osheet = osList.get(i);
							ItemVO item = itemList.get(i);
					%>
					<tr>
						<td>
							주문번호 : [<%=osheet.getoNum() %>]<br>
							주문시간 : <%=osheet.getOrderTime() %>
						</td>
						<td>
							<a href="description.show?pNum=<%=item.getName() %>">
								<img src="<%=item.getUrl() %>" alt="주문상품" style="height:80px;">
							</a>
						</td>
						<td>
							<%=item.getName() %><br>
							옵션 : <%=item.getsOptions() %>
						</td>
						<td><%=osheet.getQuantity() %></td>
						<td><%=osheet.getBill() %></td>
						<%if(osheet.getRemark().equals("")){ %>
						<td>결제완료</td>
						<%}else{ %>
						<td><%=osheet.getRemark() %></td>
						<%} %>
					</tr>
					<%}%>
				</tbody>
			</table>
		</div>
		<div class="orderArea">
			<div class="title"><h3>배송지 정보</h3></div>
			<table class="orderAreaTable">
				<tr>
					<th>받으시는 분</th>
					<td><span><%=oDetail.getrName() %></span></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><span><%=oDetail.getrAddr() %></span></td>
				</tr>
				<tr>
					<th>휴대전화</th>
					<td><span><%=oDetail.getrPhone() %></span></td>
				</tr>
				<tr>
					<th>배송 메시지</th>
					<td><span><%=oDetail.getrText() %></span></td>
				</tr>
			</table>
		</div>
		<div class="buttonPlace">
			<span class="buttonShpae">
				<a href="orderList.show" id="showOrderList">주문목록보기</a>
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