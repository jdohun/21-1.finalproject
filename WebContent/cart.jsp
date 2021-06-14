<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dev.vo.ItemVO" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
		<link rel="stylesheet" href="css/BasicSet.css">
	<link rel="stylesheet" href="css/Header.css">
	<link rel="stylesheet" href="css/nav.css">
	<link rel="stylesheet" href="css/Footer.css">
	<link rel="stylesheet" href="css/cart.css">
</head>
<%	
	String id = (String)request.getSession().getAttribute("id");
	if(id == null){
		response.sendRedirect("login.jsp");
	}
	ArrayList<ItemVO> itemList = (ArrayList<ItemVO>)request.getAttribute("itemList");
%>
<body>
<!--Wrapper-->
<div id="Wrapper">
<!--header-->
 <%
	if(id == null){
%>
<%-- <%@ include file="Header_guest.jsp" %> --%>
<% response.sendRedirect("login.jsp"); %>
<% }else{%>
<%@ include file="Header_user.jsp" %>	
<%} %>
<!--/header-->
<!--main-->
<div id="Container">
	<div class="contents">
		<div class="titleArea">
			<h2>CART</h2>
		</div>
		<div class="cart">
		<form action="" method="post">
			<table >
				<thead>
					<tr>
						<th><input type="checkbox" class="CheckAll" name="selectAllProd" onclick="controlAll(this)"></th>
						<th>이미지</th>
						<th>상품정보</th>
						<th>판매가</th>
						<th>수량</th>
						<th>적립금</th>
						<th>배송비</th>
						<th>합계</th>
						<th>선택</th>
					</tr>
				</thead>
				<tbody>
				<%
					if(itemList == null || itemList.isEmpty()){
				%>
					<tr>
						<td colspan="9"><p id="empty">장바구니가 비어 있습니다.</p></td>
					</tr>
				<% }else{%>
				<% 	
					DecimalFormat commas = new DecimalFormat("###,###");
					for(int i = 0; i < itemList.size(); ++i){
						ItemVO cartItem = itemList.get(i);
						String perPrice = (String)commas.format(cartItem.getPrice());
						int total = cartItem.getPrice() * cartItem.getQuantity();
						String totalPrice = (String)commas.format(total);
						String value = cartItem.getpNum() + ","+ cartItem.getsOptions();
				%>
					<tr>
						<td>
							<input type="checkbox" name="prod" onclick="checkSelectAll()" value=<%=value%>>
						</td>
						<td>
							<a href="description.show?pNum=<%=cartItem.getpNum() %>">
								<img src=<%=cartItem.getUrl() %> class="thumb" style="height:80px;">
							</a>
						</td>
						<td>
							<a href="description.show?pNum=<%=cartItem.getpNum() %>">
								<strong><%=cartItem.getName() %></strong>
							</a><br>
							옵션 : <%=cartItem.getsOptions() %>
						</td>
						<td><strong><%=perPrice %></strong></td>
						<td><%=cartItem.getQuantity() %></td>
						<td>-</td>
						<td>무료</td>
						<td><strong><%= totalPrice %></strong></td>
						<td>
							<a href=""><button onclick="selectThis(this);javascript: form.action='selectedOrder.show';">주문하기</button></a><br>
							<a href=""><button>관심상품등록</button></a><br>
							<a href=""><button type="submit" onclick="selectThis(this);javascript: form.action='deleteCart.show';">삭제</button></a><br>
						</td>
					</tr>
				<%} 
				}%>
				</tbody>
			</table>
			<a href="" id="btnBuyAlll"><button type="submit" onclick="BselectAll();javascript: form.action='cartAllOrder.show';">전체상품주문</button></a>
			<a href="" id="btnBuySelected"><button type="submit" onclick=";javascript: form.action='selectedOrder.show';">선택상품주문</button></a>
			<a href="index.jsp" id="btnHome"><button>쇼핑계속하기</button></a>
		</form>
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
<script defer src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script defer src="js/cart.js"></script>
<script type="text/javascript">
let controlAll = function(selectAll){	// 전체 선택, 해제
	// 전체 체크박스
	let checkboxes = document.getElementsByName("prod");
	//각각의 체크박스에 체크
	checkboxes.forEach((checkbox)=>{
		// 각각의 체크박스에 현재 체크박스(자기자신)의 값을 대입
		checkbox.checked = selectAll.checked;
	})
	
};

let checkSelectAll = function(){ //각각 전체가 체크되면 전체 체크 선택 아니면 해제
	// 전체 체크박스
	let checkboxes = document.querySelectorAll("input[name='prod']");
	// 선택된 체크박스
	let checked = document.querySelectorAll("input[name='prod']:checked");
	// 전체 선택 체크박스
	let selectAll = document.querySelector("input[name='selectAllProd']");

	// 전체 체크 박스의 개수 === 선택된 체크박스의 개수이면
	if(checkboxes.length === checked.length) {
		selectAll.checked = true; // 전체 선택 체크박스에 체크
	}
	else{
		selectAll.checked = false; // 아니면 해제
	}
}

let BselectAll = function(){
	let checkboxes = document.getElementsByName("prod");
	checkboxes.forEach((checkbox)=>{
		checkbox.checked = true;
	});
}

let selectThis = function(button) {
	let checkboxes = document.getElementsByName("prod");
	checkboxes.forEach((checkbox)=>{
		checkbox.checked = false;
	});
	button.parentElement.parentElement.parentElement.firstElementChild.children[0].checked = true
}
</script>
</html>