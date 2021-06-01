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
	<link rel="stylesheet" href="css/OrderForm.css">
	<title>주문하기</title>
</head>

<body>
<!--Wrapper-->
<div id="Wrapper">
<!--header-->
<div id="Header" class="Header">
	<div class="Header_inner">
		<div class="catego">
			<ul>
				<li><a href="index.html"><img id="logo" src="images/_Aeoggeo9 .png" alt="쇼핑몰로고"></a></li>
			</ul>
		</div>
		<nav id="nav">
		<ul>
			<li><a href="">BEST</a></li>
			<li><a href="">OUTER</a></li>
			<li><a href="">TOP</a></li>
			<li><a href="">PANTS</a></li>
			<li><a href="">ACC</a></li>
		</ul>
		</nav>
		<div class="user">
			<ul>
				<li><a href="login.html">LOGIN</a></li>
				<li><a href="SignUp.html">JOIN</a></li>
				<li><a href="">MYPAGE</a></li>
				<li><a href="">BOARD</a></li>
				<li><a href="">CART</a></li>
				<li id="search_box"><input type="text" name="search"> <button id="search_button">Search</button></li>
			</ul>
		</div>
	</div>
</div>
<!--/header-->
<!--main-->
<div id="Container">
	<div class="contents">
		<div class="titleArea">
			<h2>ORDER FORM</h2>
		</div>
		<form class="orderForm" action="" method="post">
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
					<tr>
						<td><input type="checkbox" name="prod" onclick="checkSelectAll()"></td>
						<td><a href=""><img src="#" alt="주문상품"></a></td>
						<td>
							<a href="">
								<strong class="name">버핑레더 라이더자켓</strong>
							</a>
							<div class="option">[옵션: S]</div>
						</td>
						<td>24,000</td>
						<td>1</td>
						<td>-</td>
						<td>[무료]</td>
						<td><strong><span>24,000</span></strong></td>
					</tr>
				</tbody>
				<tfoot>
					<td></td>
					<td colspan="8">
						<span>
							상품구매금액 <strong>24,000</strong> + 배송비 0 (무료) = 합계:  <strong>24,000</strong>
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
			<a class="deleteList"><button onclick="DeleteOrderProd()">X 삭제하기</button></a>
		</div>
		<div class="orderArea">
			<div class="title"><h3>주문 정보</h3></div>
			<table id="orderAreaTable">
				<tr>
					<th>주문하시는 분</th>
					<td><span id="oname" name="oName">정도훈</span></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><span name="oAddr">서울</span></td>
				</tr>
				<tr>
					<th>휴대전화</th>
					<td><input type="text" name="oPhone" maxlength="11" placeholder="공백과 '-' 없이 숫자만" required></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="text" name="oemail" class="mailId" id="email1" required>
						@
						<input type="text" name="oemail" class="mailAddr" id="email2" required readonly>
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
					<th>배송지 선택</th>
					<td>
						<input type="radio" name="sameAddr" id="sameAddr" readonly required onclick="SameInfor(this)">
						<label for="sameAddr">주문자 정보와 동일</label>
						<input type="radio" name="sameAddr" id="newAddr">
						<label for="newAddr">새로운 배송지</label>
					</td>
				</tr>
				<tr>
					<th>받으시는 분</th>
					<td><input type="text" name="receiverName" minlength="4" maxlength="16"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="receiverAddr"></td>
				</tr>
				<tr>
					<th>휴대전화</th>
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
					<td>24,000</td>
					<td>- 0</td>
					<td>= 24,000</td>
				</tr>
			</table>
		</div>
		<div class="oFinal">
			<div>
				<p>최종결제 금액</p>
			</div>
			<div>
				<p id="finalPrice" style="font-size:28px; font-weight:bold;">24,000</p>
				<input type="submit" id="submit" value="결제하기">
			</div>
			<div>
				<span>총 적립예정금액</span>
				<span>240원</span>
			</div>
		</div>
		</form>
	</div>
</div>
<!--/main-->
<!--footer-->
<div class="Footer">
	<div class="Footer_inner">
		<ul>
			<li>
				<div class="Footer_box">
					<h3>COMPANY. 주식회사 어쩌구</h3>
					<p>
						<span>owenr 정도훈</span>
						<span>tel. 010-1234-5678</span>
					</p>
					<p>
						<span>address. [17092] 경기도 용인시 처인구 용인대학로 134(삼가동) 용인대학교</span>
					</p>
					<p>
						<span>
							copyright © <b>정도훈</b>
						</span>
					</p>
				</div>
			</li>
		</ul>
	</div>
</div>
<!--/footer-->
</div>
<!--/Wrapper-->
</body>
<script defer src="js/orderform.js"></script>
<script type="text/javascript">
</script>
</html>