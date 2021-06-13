<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dev.vo.UserVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="css/BasicSet.css">
	<link rel="stylesheet" href="css/Header.css">
	<link rel="stylesheet" href="css/nav.css">
	<link rel="stylesheet" href="css/Footer.css">
	<link rel="stylesheet" href="css/SignUp.css">
</head>
<%	
	String id = (String)request.getSession().getAttribute("id");
	if(id == null){
		response.sendRedirect("login.jsp");
	}
	UserVO user = (UserVO)request.getSession().getAttribute("user");
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
			<h3>PROFILE</h3>
		</div>
		<div>
		<form class="signupForm" action="" method="post">
			<h3>기본정보</h3>
			<table>
				<tbody>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="MID" maxlength="16" required readonly="readonly" value=<%=user.getId() %>></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="MPW" required value=<%=user.getPwd() %>></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" name="MPWCheck" value=<%=user.getPwd() %> required></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="MName" maxlength="6" required readonly="readonly" value=<%=user.getName() %>></td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input type="text" name="MAddr" value=<%=user.getAddr() %>></td>
					</tr>
					<tr>
						<th>휴대전화</th>
						<td>
							<input type="number" name="MPhone" maxlength="11" placeholder="공백과 '-' 없이 숫자만" oninput="numberMaxLength(this);" value=<%=user.getPhone() %>>
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input type="text" name="Memail1" value=<%=user.getEmail1()%>>
							@
							<input type="text" name="Memail2" value=<%=user.getEmail2()%>>
						</td>
					</tr>
					<tr>
						<th>충전된 금액</th>
						<td>
							<p><span style="margin-right:10px;"><%=user.getMoney() %></span>
							<a href="charge.jsp"><button type="button" id="charge">충전하기</button></a></p>
						</td>
					</tr>
				</tbody>
			</table>
			<input type="submit" id="submit" value="수정하기" onclick="javascript:form.action='modify.do'">
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