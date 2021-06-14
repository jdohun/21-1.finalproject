<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dev.vo.ItemVO" %>
<%@ page import="com.dev.vo.UserVO" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/BasicSet.css">
	<link rel="stylesheet" href="css/Header.css">
	<link rel="stylesheet" href="css/nav.css">
	<link rel="stylesheet" href="css/Footer.css">
	<link rel="stylesheet" href="css/OrderForm.css">
	<title>주문하기</title>
</head>
<%	
	String id = (String)request.getSession().getAttribute("id");
	if(id == null){
		response.sendRedirect("login.jsp");
	}
	
	UserVO user = (UserVO)request.getSession().getAttribute("user");
	int money = user.getMoney();
	ArrayList<ItemVO> cartList = (ArrayList<ItemVO>)request.getAttribute("itemList");
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
			<h2>ORDER FORM</h2>
		</div>
		<form class="orderForm" id="orderForm" action="" method="post">
		<div id="orderListArea">
			<div class="title"><h3>주문내역</h3></div>
			<table class="orderListTable">
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
					</tr>
				</thead>
				<tbody>
					<% 
						DecimalFormat commas = new DecimalFormat("###,###");
						String sAllTotal = null;
						int allTotal = 0;
						for(int i = 0; i < cartList.size(); ++i){
							ItemVO cartItem = cartList.get(i);
							String perPrice = (String)commas.format(cartItem.getPrice());
							int total = cartItem.getPrice() * cartItem.getQuantity();
							String totalPrice = (String)commas.format(total);
							allTotal += total;
							sAllTotal = (String)commas.format(allTotal);
							String value1 = cartItem.getpNum() + ","+ cartItem.getsOptions();
							String value2 = cartItem.getpNum() + ","+ cartItem.getsOptions() + "," + cartItem.getQuantity();
					%>
						<tr>
							<td>
								<input type="checkbox" name="prod" onclick="checkSelectAll()" value=<%=value1 %>>
								<input type="hidden" name="prodValue" value=<%=value2 %>>
							</td>
							<td>
								<a href="description.show?pNum=<%=cartItem.getpNum()%>">
									<img class="thumb" src=<%=cartItem.getUrl() %> alt="주문상품" style="height:80px;">
								</a>
							</td>
							<td>
								<a href="description.show?pNum=<%=cartItem.getpNum()%>">
									<strong class="name"><%=cartItem.getName() %></strong>
								</a>
								<div class="option">[옵션: <%=cartItem.getsOptions() %>]</div>
							</td>
							<td><%=perPrice %></td>
							<td><%=cartItem.getQuantity() %></td>
							<td>-</td>
							<td>[무료]</td>
							<td><strong><span><%= totalPrice %></span></strong></td>
						</tr>
					<% }%>
				</tbody>
				<tfoot>
					<td></td>
					<td colspan="8">
						<span>
							상품구매금액 <strong><%=sAllTotal %></strong> + 배송비 0 (무료) = 합계:  <strong><%=sAllTotal %></strong>
						</span>
					</td>
				</tfoot>
			</table>
		</div>
		<div>
			<p class="orderInfo">상품의 옵션 및 수량 변경은 상품상세 또는 장바구니에서 가능합니다.</p>
		</div>
		<div id="deleteListArea">
			<strong>선택상품을</strong>
			<a class="deleteList"><button type="button" onclick="DeleteOrderProd()">X 삭제하기</button></a>
		</div>
		<div class="orderArea">
			<div class="title"><h3>주문 정보</h3></div>
			<table id="orderAreaTable">
				<tr>
					<th>주문하시는 분*</th>
					<td><input id="oname" name="oName" value="<%=user.getName() %>" required></td>
				</tr>
				<tr>
					<th>주소*</th>
					<td><input name="oAddr" required value="<%=user.getAddr() %>"></td>
				</tr>
				<tr>
					<th>휴대전화*</th>
					<td><input type="text" name="oPhone" maxlength="11" placeholder="공백과 '-' 없이 숫자만" required value="<%=user.getPhone() %>"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="text" name="oemail1" class="mailId" id="email1" value="<%=user.getEmail1()%>">
						@
						<input type="text" name="oemail2" class="mailAddr" id="email2" value="<%=user.getEmail2() %>">
						<select id="email3">
							<option value selected>- 이메일 선택 -</option>
							<option value="naver.com">naver.com</option>
							<option value="daum.net">daum.net</option>
							<option value="nate.com">nate.com</option>
							<option value="hotmail.com">hotmail.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="etc">직접입력</option>
						</select>
					</td>
				</tr>
			</table>
		</div>
		<div class="orderArea">
			<div class="title"><h3>배송 정보</h3></div>
			<table id="deliveryTable">
				<tr>
					<th>배송지 선택*</th>
					<td>
						<input type="radio" name="sameAddr" id="sameAddr" readonly required onclick="SameInfor(this)">
						<label for="sameAddr">주문자 정보와 동일</label>
						<input type="radio" name="sameAddr" id="newAddr">
						<label for="newAddr">새로운 배송지</label>
					</td>
				</tr>
				<tr>
					<th>받으시는 분*</th>
					<td><input type="text" name="receiverName" minlength="4" maxlength="16"></td>
				</tr>
				<tr>
					<th>주소*</th>
					<td><input type="text" name="receiverAddr"></td>
				</tr>
				<tr>
					<th>휴대전화*</th>
					<td><input type="text" name="receiverPhone" maxlength="11" placeholder="공백과 '-' 없이 숫자만"></td>
				</tr>
				<tr>
					<th>배송 메시지</th>
					<td><textarea id="receiverMessage" name="receiverMessage" maxlength="255" cols="70"></textarea></td>
				</tr>
			</table>
		</div>
		<div>
			<table id="oFinalPrice">
				<tr>
					<th>총 주문 금액</th>
					<th>총 할인 + 부가결제 금액</th>
					<th>총 결제예정 금액</th>
				</tr>
				<tr>
					<td><%=sAllTotal %></td>
					<td>- 0</td>
					<td><%=sAllTotal %></td>
				</tr>
			</table>
		</div>
		<div>
			<table id="oFinal">
				<thead>
				<tr>
					<th>보유중인 금액</th>
					<th>결제 후 금액</th>
				</tr>
				</thead>
				<tbody>
					<tr>
						<% String sMoney = (String)commas.format(money); %>
						<td id="myMoney" style="font-size:28px; font-weight:bold;"><%=sMoney %></td>
						<%
							money = money - allTotal;
							String sResult = (String)commas.format(money);
							if(money < 0){
						%>
							<td id="resultMoney" style="font-size:28px; font-weight:bold; color:red;"><%=sResult %></td>
						<%} else{%>
							<td id="resultMoney" style="font-size:28px; font-weight:bold;"><%=sResult %></td>
						<%} %>
					</tr>
				</tbody>
			</table>
				<% if(money > allTotal){	%>
				<input type="submit" id="submit" value="결제하기" onclick="javascript:form.action='orderCompleted.order'">
				<% }else {%>
				<input type="submit" id="submit" value="결제하기" onclick="checkMoney();">
				<%} %>
			</div>
			<div>
				<span>총 적립예정금액</span>
				<span>원</span>
			</div>
		</div>
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
<script defer src="js/orderform.js"></script>
</html>
