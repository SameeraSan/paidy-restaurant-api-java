package com.paidy.restaurant.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Items {

	private int itemNo;
	private String itemName;
	private int status;
	private int cookTime;
	private int quantity;
	
	public Items() {
		
	}
	
	public Items(int itemNo, String itemName, int status, int cookTime, int quantity) {
		super();
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.status = status;
		this.cookTime = cookTime;
		this.quantity = quantity;
	}

	
	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCookTime() {
		return cookTime;
	}

	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
