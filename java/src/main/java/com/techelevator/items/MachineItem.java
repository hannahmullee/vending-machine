package com.techelevator.items;

import java.math.BigDecimal;

public abstract class MachineItem {

	private String slotIdentifier;
	private BigDecimal purchasePrice;
	private int productTotal;
	private String productName;

	public MachineItem() {}
	
	
	public MachineItem(int productTotal) {
		this.productTotal = productTotal;
	}
	

	public MachineItem(String slotIdentifier,  String productName, BigDecimal purchasePrice, int productTotal) {
		this.slotIdentifier = slotIdentifier;
		this.purchasePrice = purchasePrice;
		this.productName = productName;
		this.productTotal = productTotal;
	}
	
	public abstract String dispensingMessage();
	
	public String toString() {
		return "\n" + slotIdentifier + ": " + productName + " | " + "$" + purchasePrice + " | " + productTotal + " remaining"; 
	}

	
	public String getSlotIdentifier() {
		return slotIdentifier;
	}
	
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	
	
	public int getProductTotal() {
		return productTotal;
	}
	
	
	public String getProductName() {
		return productName;
	}
	
	
	
	public int purchase() {
		return productTotal -= 1;
	}
	
	
}
