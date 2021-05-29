<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dev.vo.ItemVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<% ItemVO item = (ItemVO)request.getAttribute("item"); %>
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
		</div>
			<span><%=item.getName() %></span>
			<span><%=item.getPrice() %></span>
			<span><%=item.getSize() %></span>
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