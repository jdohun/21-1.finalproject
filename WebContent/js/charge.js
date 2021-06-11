let charge = document.getElementById("charge");
let BReset = document.getElementById("reset");
let Bplus1 = document.getElementById("plus1");
let Bplus5 = document.getElementById("plus5");
let Bplus10 = document.getElementById("plus10");
let Bplus30 = document.getElementById("plus30");

let Fplus1 = function(){
	let value = parseInt(charge.getAttribute("value"));
	charge.setAttribute("value", value+=10000);
}

let Fplus5 = function(){
	let value = parseInt(charge.getAttribute("value"));
	charge.setAttribute("value", value+=50000);
}

let Fplus10 = function(){
	let value = parseInt(charge.getAttribute("value"));
	charge.setAttribute("value", value+=100000);
}

let Fplus30 = function(){
	let value = parseInt(charge.getAttribute("value"));
	charge.setAttribute("value", value+=300000);
}

let FReset = function(){
	let reset = 0;
	charge.setAttribute("value", reset);
}

let changeValue = function(){
	console.log(charge.textContent);
	charge.setAttribute("value", charge.textContent);
}

Bplus1.addEventListener("click", Fplus1);
Bplus5.addEventListener("click", Fplus5);
Bplus10.addEventListener("click", Fplus10);
Bplus30.addEventListener("click", Fplus30);
BReset.addEventListener("click", FReset);
charge.addEventListener("keypup", changeValue);