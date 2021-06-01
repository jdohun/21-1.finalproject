<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dev.vo.ItemVO" %>
<!DOCTYPE html>
<html>
<head>
<%
	ItemVO item = (ItemVO)request.getAttribute("item");
	String size = item.getSize();
	String[] arraySize = size.split(", ");
%>
<meta charset="UTF-8">
<title>어쩌구</title>
</head>
<link rel="stylesheet" href="css/Product.css">
<body>
<!--Wrapper-->
<div id="Wrapper">
<!--header-->
<%@ include file="Header_guest.jsp" %>
<!--/header-->
<!--main-->
<div id="Container">
	<div class="contents">
		<div class="detailArea">
			<div class="imgArea">
			<img src=<%=item.getUrl() %> alt=<%=item.getName() %>>
			</div>
			<div class="inforArea">
				<div class="headingArea">
					<h2><%=item.getName() %></h2>
					<div class="size">[<%=item.getSize()%>]</div>
				</div>
				<table class="option">
					<tr>
						<th><span>판매가</span></th>
						<td><span class="Price"><%=item.getPrice() %></span></td>
					</tr>
					<tr>
						<th><span>사이즈</span></th>
						<td>
						<select name="select_size" id="select_box" onchange="checkSelected()">
							<option value="*" selected>- [필수] 옵션을 선택해 주세요 -</option>
							<option value="*" disabled>----------------</option>
							<%
								for(int i = 0; i< arraySize.length; ++i){
							%>
								<option value="<%=arraySize[i] %>" name="size"><%=arraySize[i] %></option>
							<%
								}
							%>
						</select>
						</td>
					</tr>
				</table>
				<form action="" method="post">
					<input type="hidden" value=<%=item.getpNum() %> name="selectedItem">
					<table id="selected_option">
					</table>
				
				<div id="Total_Price" class="Total_Price">
					<strong>Total Price:</strong>
					<span class="Total">
						<strong>0</strong>
						(0개)
					</span>
				</div>
				<div id="product_action">
					<a href="" id="btnBuy"><button type="submit" onclick="javascript: form.action='search.order?job=buy';">BUY IT NOW</button></a>
					<a href="" id="btnAdd"><button type="submit" onclick="javascript: form.action='search.order?job=cart';">ADD TO CART</button></a>
					<a href="" id="btnWish"><button type="submit" onclick="javascript: form.action='';">WISH LIST</button></a>
				</div>
				</form>
			</div>
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
<script defer
	src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script defer src="js/description.js"></script>
<script defer type="text/javascript">
	//함수 선언
	let checkSelectedRow;
	let makeSelectedRow;
	let checkSelected;
	let deleteRow;
	let countProduct;
	let checkSelectedExist;
	let increaseCount;
	let decreaseCount;
	let showTotal;
	//전역변수
	let i = 1;
	let Total_Count = 0;
	let price = parseInt(document.querySelector("span[class='Price']").innerText.replace(/,/,""));
	//함수 정의
	checkSelectedRow = function(selected_value){ // 생성된 row의 값 검사하는 함수
		let generatedOptions = document.getElementsByClassName("generatedOptions");
		let i;
		for(i = 0; i < generatedOptions.length; ++i){
			if(selected_value == generatedOptions[i].innerText){
				alert("이미 선택된 옵션입니다.");
				return false;
			}
		}
	
		if(i == generatedOptions.length){
			return true;
		}
	};
	
	makeSelectedRow = function(selected_value){ 
		// true면 선택된 값에 해당하는 row 생성하는 함수
		let selected_option = document.getElementById("selected_option");
		let name = $(".headingArea").children("h2")[0].innerText;
		let newRow = $("#selected_option").append(
			`<tr>
				<td>
					<p></p>
				</td>
				<td>
					<span class="option_boxes">
						<input type="text" value="1" class="product_count" >
						<button type="button" class="count_up, product_updown" onclick="increaseCount(this.previousElementSibling), checkSelectedExist()">↑</button>
						<button type="button" class="count_down, product_updown" onclick="decreaseCount(this.previousElementSibling.previousElementSibling), checkSelectedExist()">↓</button>
					</span>
					<button type="button" class="option_delete" onclick="deleteRow(this.parentElement.parentElement)"><span>X</span></button>
				</td>
			</tr>`
		);
		//p 안에 값 넣기
		selected_option.lastChild.children[0].children[0].innerHTML = name + `<br><span class="generatedOptions" id="generatedOption` + i++ + `">` + selected_value + `</span>`;
		// 개수 값이 들어있는 input에 name 속성 주기
		selected_option.lastChild.children[1].children[0].children[0].setAttribute("name", selected_value+"Count");
	};
	
	checkSelected = function(){ // select 태그의 변화 검사
		let select_box = document.getElementById("select_box");
		let selected_value = select_box.options[select_box.selectedIndex].value;
		let result = false;
		if(selected_value != '*'){	// 값이 *이 아니면 값에 해당하는 row가 생성됐는지 확인
		   result = checkSelectedRow(selected_value);
		}
	
		if(result){
			makeSelectedRow(selected_value);
			checkSelectedExist(); // 새 row가 생성되면 개수를 셈
		}
	};
	
	deleteRow = function(deletedRow){ // 행을 삭제하는 함수
		deletedRow.remove();
		checkSelectedExist(); // row가 삭제되면 개수를 셈
	}
	
	countProduct = function(){ //주문 수량 세는 함수
		let option_boxes = document.getElementsByClassName("option_boxes");
		let totalCount = 0;
		for(let i = 0; i < option_boxes.length; ++i){
			totalCount += parseInt(option_boxes[i].children[0].value);
		}
		Total_Count = totalCount;
		showTotal();
		return totalCount;
	}
	
	checkSelectedExist = function(){ // 존재하면 수량 세는 함수를 부르는 함수
		let selected_option = $("#selected_option");
		if(selected_option.length != 0){
			return countProduct();
		}
		else{
			return alret("[필수] 옵션을 선택하세요.");
		}
	}
	
	increaseCount = function(thisRowInput){
		let nowCount = parseInt(thisRowInput.value);
		thisRowInput.value = ++nowCount;
		let changedCount = thisRowInput.value;
		thisRowInput.setAttribute("value",changedCount);
	}
	
	decreaseCount = function(thisRowInput){
		let nowCount = parseInt(thisRowInput.value);
		if(nowCount == 1) return alert("최소 주문량은 1개입니다.");
		thisRowInput.value = --nowCount;
		let changedCount = thisRowInput.value;
		thisRowInput.setAttribute("value",changedCount);
	}
	
	showTotal = function(){ // 총 주문량 표시
		let Total = document.querySelector("span[class='Total']");
		let Total_Price = price * Total_Count;
		let textTotal = Total_Price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		Total.innerHTML = "<strong>" + textTotal + "</strong>" + "(" + Total_Count +"개)";
	}
	
</script>
</html>