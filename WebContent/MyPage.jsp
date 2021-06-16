<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="css/BasicSet.css?after">
	<link rel="stylesheet" href="css/Header.css">
	<link rel="stylesheet" href="css/nav.css">
	<link rel="stylesheet" href="css/Footer.css">
	<link rel="stylesheet" href="css/MyPage.css?after">
</head>
<%	
	String id = (String)request.getSession().getAttribute("id");
	if(id == null){
		response.sendRedirect("login.jsp");
	}
%>
<body>
<!--Wrapper-->
<div id="Wrapper">
<!--header-->
<%@ include file="Header_user.jsp" %>
<!--/header-->
<!--main-->
<div id="Container">
	<div class="contents">
		<div class="titleArea">
			<h3>MY PAGE</h3>
		</div>
		<ul id="boxZone">
			<li>
				<a href="orderList.show" class="mypages">
					<div class="box">
							<h3><strong>ORDER</strong></h3>
							<p>고객님께서 주문하신 상품의 주문내역을<br> 확인하실 수 있습니다.</p>
					</div>
				</a>
			</li>
			<li>
				<a href="modify.jsp" class="mypages">
					<div class="box">
							<h3><strong>PROFILE</strong></h3>
							<p>회원이신 고객님의 개인정보를 관리하는 공간입니다.</p>
					</div>
				</a>
			</li>
			<li>
				<a href="charge.jsp" class="mypages">
					<div class="box">
							<h3><strong>CHARGE</strong></h3>
							<p>고객님께서 보유중인 금액을 관리하는 공간입니다.</p>
					</div>
				</a>
			</li>
		</ul>
	</div>
</div>
<!--/main-->
<!--footer-->
<%@ include file="Footer.jsp" %>
<!--/footer-->
</div>
<!--/Wrapper-->
</body>
</html>