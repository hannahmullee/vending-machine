package com.techelevator.items;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class CandyTest {

	@Test
	public void purchase_method_buy_1_subtract_1() {
		Candy candy = new Candy("D3", "Reeces", new BigDecimal("1.75"), 5);
		
		assertEquals(4, candy.purchase());
	}
	
	@Test
	
	public void dispensingMessage_return_Munch_Munch_Yum() {
		Candy candy = new Candy("D3", "Reeces", new BigDecimal("1.75"), 5);
		
		assertEquals("Munch Munch, Yum!", candy.dispensingMessage());
	}
	
	@Test
	public void get_slot_identifier() {
		Candy candy = new Candy("D3", "Reeces", new BigDecimal("1.75"), 5);
		
		assertEquals("D3", candy.getSlotIdentifier());
	}
	
	@Test
	public void get_purchase_price() {
		Candy candy = new Candy("D3", "Reeces", new BigDecimal("1.75"), 5);
		
		assertEquals(new BigDecimal("1.75"), candy.getPurchasePrice());
	}
	
	@Test
	public void get_product_name() {
		Candy candy = new Candy("D3", "Reeces", new BigDecimal("1.75"), 5);
		
		assertEquals("Reeces", candy.getProductName());
	}
	
	@Test
	public void get_product_total() {
		Candy candy = new Candy("D3", "Reeces", new BigDecimal("1.75"), 5);
		
		assertEquals(5, candy.getProductTotal());
	}

}
