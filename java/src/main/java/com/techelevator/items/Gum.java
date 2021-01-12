package com.techelevator.items;

import java.math.BigDecimal;

public class Gum extends MachineItem{	

	public Gum(String slotIdentifier, String productName, BigDecimal purchasePrice, int productTotal) {
		super(slotIdentifier, productName, purchasePrice, productTotal);
	}



	@Override
	public String dispensingMessage() {
		return "Chew Chew, Yum!";
	}

}
