package com.dev.vo;

public class CartVO {
	private String orderer;
	private String pNum ;
	private String sOption;
	private int quantity;
	
	public String getOrderer() {
		return orderer;
	}
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	public String getpNum() {
		return pNum;
	}
	public void setpNum(String pNum) {
		this.pNum = pNum;
	}
	public String getsOption() {
		return sOption;
	}
	public void setsOption(String sOption) {
		this.sOption = sOption;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
