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
		<div class="titleArea">
			Charge
		</div>
		<p>보유중인 금액 : <%=sNowMoney %></p>
		<p>충전 후 금액 : <span></span></p>
		<form action="charge.do" method="post">
			<input type="number" id="charge" name="charge" placeholder="금액을 입력해주세요" value="0"> <button type="button" id="reset">X</button>
			<hr>
			<button type="button" id="plus1">+1만원</button>
			<button type="button" id="plus5">+5만원</button>
			<button type="button" id="plus10">+10만원</button>
			<button type="button" id="plus30">+30만원</button>
			<br><input type="submit" value="충전하기">
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