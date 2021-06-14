<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dev.vo.UserVO" %>
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
	UserVO user = (UserVO)request.getAttribute("user");
	ODetailVO oDetail = (ODetailVO)request.getAttribute("oDetail");
	ItemVO item = (ItemVO)request.getAttribute("item");
	OSheetVO oSheet = (OSheetVO)request.getAttribute("oSheet");
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
				<tr>
					<th>주문번호</th>
					<td><%=oSheet.getoNum() %></td>
				</tr>
				<tr>
					<th>주문일자</th>
					<td><%=oSheet.getOrderTime() %></td>
				</tr>
				<tr>
					<th>주문자</th>
					<td><%=oDetail.getoName() %></td>
				</tr>
				<tr>
					<th>주문처리상태</th>
					<%if(oSheet.getRemark().equals("")){ %>
					<td>결제완료 <button>주문취소</button></td>
					<% }else{%>
					<td><%=oSheet.getRemark() %></td>
					<%} %>
				</tr>
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
						<td>
							<a href="description.show?pNum=<%=item.getpNum() %>">
								<img src="<%=item.getUrl() %>" alt="주문상품" style="height:80px;">
							</a>
						</td>
						<td>
							<a href="description.show?pNum=<%=item.getpNum() %>">
								<strong class="name"><%=item.getName() %></strong>
							</a>
							<div class="option">[옵션: <%=item.getsOptions() %>]</div>
						</td>
						<td><%=item.getPrice() %></td>
						<td><%=oSheet.getQuantity() %></td>
						<%if(oSheet.getRemark().equals("")){ %>
						<td>결제완료</td>
						<% }else{%>
						<td><%=oSheet.getRemark() %></td>
						<%} %>
						<td><strong><span><%=oSheet.getBill() %></span></strong></td>
					</tr>
				</tbody>
				<tfoot>
					<td></td>
					<td colspan="8">
						<span>
							상품구매금액 <strong><%=oSheet.getBill() %></strong> + 배송비 0 (무료) = 합계:  <strong><%=oSheet.getBill() %></strong>
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
					<td><span><%=oDetail.getrName() %></span></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><span><%=oDetail.getrAddr()%><</span></td>
				</tr>
				<tr>
					<th>휴대전화</th>
					<td><span><%=oDetail.getrPhone() %><</span></td>
				</tr>
				<tr>
					<th>배송 메시지</th>
					<td><span><%=oDetail.getrText() %><</span></td>
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