<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.user a {
	font-size: 22px;
}

#nav a {
	font-size: 22px;
}
</style>
<div id="Header" class="Header">
	<div class="Header_inner">
		<div class="catego">
			<ul>
				<li><a href="index.show"><img id="logo" src="images/_Aeoggeo9 .png" alt="쇼핑몰로고"></a></li>
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
				<li><a href="LogOut.jsp">LOGOUT</a></li>
				<li><a href="modify.jsp">MODIFY</a></li>
				<li><a href="MyPage.jsp">MYPAGE</a></li>
				<li><a href="#">BOARD</a></li>
				<li><a href="cart.show">CART</a></li>
				<li id="search_box"><input type="text" name="search"><button id="search_button">Search</button></li>
			</ul>
		</div>
	</div>
</div>