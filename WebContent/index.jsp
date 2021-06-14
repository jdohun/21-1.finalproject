<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dev.vo.ItemVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
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
	String id = (String)request.getSession().getAttribute("id");
	ArrayList<ItemVO> List = (ArrayList<ItemVO>)request.getAttribute("List");
	DecimalFormat commas = new DecimalFormat("###,###");
%>
<body>
<!--Wrapper-->
<div id="Wrapper">
<!--header-->
 <%
	if(id == null){
%>
<%@ include file="Header_guest.jsp" %>
<% }else{%>
<%@ include file="Header_user.jsp" %>	
<%} %>
<!--/header-->
<!--main-->
<div id="Container">
	<div class="contents">
		<div class="titleArea">
			<h3 style="margin-top: 100px;margin-bottom: 30px;font-size: 30px;">MAIN</h3>
		</div>
		<div class="itemList">
			<ul>
				<%
					if(!List.isEmpty()){
						for(int i = 0; i < List.size(); ++i){
							ItemVO item = List.get(i);
							int iPrice = item.getPrice();
							String sPrice = commas.format(iPrice);
				%>
				<li>
					<a href="description.show?pNum=<%=item.getpNum() %>">
						<div class="itembox">
							<img src=<%=item.getUrl()%> alt="상품사진" class="thumb">
						</div>
						<ul class="caption">
							<li>
								<p class="item_name"><%=item.getName() %></p>
							</li>
							<li>
								<p class="price"><%=sPrice %></p>
							</li>
							<li>
								<p class="size"><%=item.getsOptions() %></p>
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