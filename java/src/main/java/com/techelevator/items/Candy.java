package com.techelevator.items;

import java.math.BigDecimal;

public class Candy extends MachineItem{

	public Candy(String slotIdentifier, String productName, BigDecimal purchasePrice, int productTotal) {
		super(slotIdentifier, productName, purchasePrice,  productTotal);
	}



	@Override
	public String dispensingMessage() {
		return "Munch Munch, Yum!";
	}

}
