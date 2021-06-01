<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dev.vo.ItemVO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/BasicSet.css">
	<link rel="stylesheet" href="css/Header.css">
	<link rel="stylesheet" href="css/nav.css">
	<link rel="stylesheet" href="css/Footer.css">
	<link rel="stylesheet" href="css/main.css">
	<title>어쩌구</title>
</head>
<%
	ArrayList<ItemVO> List = (ArrayList<ItemVO>)request.getAttribute("List");
%>
<body>
<!--Wrapper-->
<div id="Wrapper">
<!--header-->
 <%@ include file="Header_user.jsp" %>
<!--/header-->
<!--main-->
<div id="Container">
	<div class="titleArea">
			<h3>MAIN</h3>
	</div>
	<div class="contents">
		<div class="itemList">
			<ul>
				<%
					if(!List.isEmpty()){
						for(int i = 0; i < List.size(); ++i){
							ItemVO item = List.get(i);
				%>
				<li>
					<a href="description.show?name=<%=item.getName()%>">
						<div class="itembox">
							<img src=<%=item.getUrl()%> alt="상품사진" class="thumb">
						</div>
						<ul class="caption">
							<li>
								<p class="item_name"><%=item.getName() %></p>
							</li>
							<li>
								<p class="price"><%=item.getPrice() %></p>
							</li>
							<li>
								<p class="size"><%=item.getSize() %></p>
							</li>
						</ul>
					</a>
				</li>
				<%
						}
					}
					else{
						String msg = (String)request.getAttribute("msg");
					%>
						<p>등록된 상품이 없습니다.</p>
					<%	
					}
				%>
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
</html>