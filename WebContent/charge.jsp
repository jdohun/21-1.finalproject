<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dev.vo.UserVO" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="css/BasicSet.css">
	<link rel="stylesheet" href="css/Header.css">
	<link rel="stylesheet" href="css/nav.css">
	<link rel="stylesheet" href="css/Footer.css">
	<link rel="stylesheet" href="css/charge.css">
<style>
	.plus {
		width:100px;
		height:40px;
		background: #ggg;
		margin: 30px 0;
	}
	.plus:hover {
		opacity: 0.7;
		cursor: pointer;
	}
	
	#money {
		font-size: 15px;
		margin:8px;
	}
	
	#submit {
		width:180px;
		height:50px;
		background: #000;
		color: #fff;
	}
	
	#submit:hover {
		opacity: 0.7;
		cursor: pointer;
	}
</style>
</head>
<%	
	String id = (String)request.getSession().getAttribute("id");
	if(id == null)	response.sendRedirect("/login.jsp");
	UserVO user = (UserVO)request.getSession().getAttribute("user");
	HttpSession session2 = request.getSession();
	DecimalFormat commas = new DecimalFormat("###,###");
	int nowMoney = user.getMoney();
	String sNowMoney = commas.format(nowMoney); 
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
		<div class="titleArea" style="margin-bottom: 30px; ">
			<h3>CHARGE</h3>
		</div>
		<p id="money">보유중인 금액 : <%=sNowMoney %></p>
		<!-- <p>충전 후 금액 : <span></span></p> -->
		<form action="charge.do" method="post">
			<input type="number" id="charge" name="charge" placeholder="금액을 입력해주세요" value="0"> <button type="button" id="reset">X</button>
			<br>
			<button type="button" id="plus1" class="plus">+1만원</button>
			<button type="button" id="plus5" class="plus">+5만원</button>
			<button type="button" id="plus10" class="plus">+10만원</button>
			<button type="button" id="plus30" class="plus">+30만원</button>
			<br><input type="submit" id="submit" value="충전하기">
		</form>
	</div>
</div>
<!--/main-->
<!--footer-->
<%@ include file="Footer.jsp" %>
<!--/footer-->
</div>
<!--/Wrapper-->
</body>
<script defer src="js/charge.js"></script>
</html>