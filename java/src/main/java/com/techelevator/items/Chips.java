package com.techelevator.items;

import java.math.BigDecimal;

public class Chips extends MachineItem{

	public Chips(String slotIdentifier, String productName, BigDecimal purchasePrice, int productTotal) {
		super(slotIdentifier, productName, purchasePrice,  productTotal);
	}



	@Override
	public String dispensingMessage() {
		return "Crunch Crunch, Yum!";
	}

}
