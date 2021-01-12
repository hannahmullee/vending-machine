package com.techelevator.items;

import java.math.BigDecimal;

public class Drinks extends MachineItem {

	public Drinks(String slotIdentifier, String productName, BigDecimal purchasePrice, int productTotal) {
		super(slotIdentifier, productName, purchasePrice, productTotal);
	}



	@Override
	public String dispensingMessage() {
		return "Glug Glug, Yum!";
	}

	
	
}
