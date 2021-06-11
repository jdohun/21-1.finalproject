let controlAll = function(selectAll){	// 전체 선택, 해제
	// 전체 체크박스
	let checkboxes = document.getElementsByName("prod");
	//각각의 체크박스에 체크
	checkboxes.forEach((checkbox)=>{
		// 각각의 체크박스에 현재 체크박스(자기자신)의 값을 대입
		checkbox.checked = selectAll.checked;
	});
};

let checkSelectAll = function(){ //각각 전체가 체크되면 전체 체크 선택 아니면 해제
	// 전체 체크박스
	let checkboxes = document.querySelectorAll("input[name='prod']");
	// 선택된 체크박스
	let checked = document.querySelectorAll("input[name='prod']:checked");
	// 전체 선택 체크박스
	let selectAll = document.querySelector("input[name='selectAllProd']");

	// 전체 체크 박스의 개수 === 선택된 체크박스의 개수이면
	if(checkboxes.length === checked.length) {
		selectAll.checked = true; // 전체 선택 체크박스에 체크
	}
	else{
		selectAll.checked = false; // 아니면 해제
	}
};

let DeleteOrderProd = function() { // 선택한 체크박스 해제
	// 테이블
	let Table = document.getElementsByClassName("orderListTable")[0];
	let row = Table.rows.length;

	for(let i = 1; i < row-1; ++i){ //두번째 행부터 시작 마지막 행 제외
		// Table의 i번째행에서 0번째 셀의 0번째 객체의 checked 값 (bool);
		let checked = Table.rows[i].cells[0].childNodes[0].checked;

		if(checked){ // 선택되어있으면 true 이므로 삭제
			Table.deleteRow(i);
			// 행이 삭제되었으므로 뒷 순서의 행이 현재 번호로 지정됨
			--i;	// 현재 행부터 다시 검사
			--row;	// 행이 삭제되어 전체 행 갯수가 줄어듦
		}
	}
};

let SameInfor = function(radioSame){
		if(radioSame.checked) {
			let oName = document.getElementById("oname");
			let receiverName = document.getElementsByName("receiverName")[0];
			receiverName.setAttribute("value", oName.value);
			
			let oAddr = document.getElementsByName("oAddr")[0];
			let receiverAddr = document.getElementsByName("receiverAddr")[0];
			receiverAddr.setAttribute("value", oAddr.value);
			
			let oPhone = document.getElementsByName("oPhone")[0];
			let receiverPhone = document.getElementsByName("receiverPhone")[0];
			receiverPhone.setAttribute("value", oPhone.value);
		}
	};