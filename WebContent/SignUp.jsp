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
	<link rel="stylesheet" href="css/SignUp.css">
	<title>어쩌구</title>
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
			<h2>SIGN UP</h2>
		</div>
		<div>
		<form class="signupForm" action="signUp.do" method="post">
			<h3>기본정보</h3>
			<table>
				<tbody>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="SignUpID" maxlength="16" required></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="SignUpPW" required></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" name="SignUpPWCheck" required></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="SignUpName" maxlength="6" required></td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input type="text" name="SignUpAddr" ></td>
					</tr>
					<tr>
						<th>휴대전화</th>
						<td><input type="number" name="SignUpPhone" maxlength="11" placeholder="공백과 '-' 없이 숫자만"  oninput="numberMaxLength(this);"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" name="email1">@<input type="text" name="email2"></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" id="submit" value="SUBMIT">
		</form>
		</div>
	</div>
</div>
<!--/main-->
<!--footer-->
<%@ include file="Footer.jsp" %>
<!--/footer-->
</div>
<!--/Wrapper-->
</body>
<script defer src="js/numberMaxLength.js"></script>
</html>