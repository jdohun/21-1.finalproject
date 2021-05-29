<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/BasicSet.css">
	<link rel="stylesheet" href="css/Header.css">
	<link rel="stylesheet" href="css/Footer.css">
	<link rel="stylesheet" href="css/nav.css">
	<link rel="stylesheet" href="css/login.css">
	<title>로그인</title>
</head>

<body>
<!--Wrapper-->
<div id="Wrapper">
<!--header-->
<%@ include file="Header_guest.jsp" %>
<!--/header-->
<!--main-->
<div id="Container">
	<div class="contents">
		<div class="titleArea">
			<h3>LOGIN</h3>
		</div>
		<form class="login" method="post" action="login.do">
			<div>
				<ul>
					<li><input type="text" name="userId" id="userId" placeholder="아이디" autofocus></li>
					<li><input type="password" name="userPassword" id="userPassword" placeholder="비밀번호"></li>
				</ul>
			</div>
			<p class="save">
			<input type="checkbox" name="saveID" id="saveID">
			<label for="saveID">아이디 저장</label><br>
			<input type="checkbox" name="autoLogin" id="autoLogin">
			<label for="autoLogin">자동 로그인</label>
			</p>
			<input type="submit" id="login" value="LOGIN"><br>
		</form>
		<a href="./SignUp.jsp"><button id="JoinUs">JOIN US</button></a>
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