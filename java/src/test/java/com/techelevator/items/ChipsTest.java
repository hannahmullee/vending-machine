package com.techelevator.items;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ChipsTest {

	@Test
	public void purchase_method_buy_1_subtract_1(){
		Chips chips = new Chips("B4", "Crunchies", new BigDecimal("1.75"), 5);
		
		assertEquals(4, chips.purchase());
	}
	
	@Test
	public void dispensingMessage_return_Crunch_Crunch_Yum() {
		Chips chips = new Chips("B4", "Crunchies", new BigDecimal("1.75"), 5);
		
		assertEquals("Crunch Crunch, Yum!", chips.dispensingMessage());
	}
	
	@Test
	public void get_slot_identifier() {
		Chips chips = new Chips("B4", "Crunchies", new BigDecimal("1.75"), 5);
		
		assertEquals("B4", chips.getSlotIdentifier());
	}
	
	@Test
	public void get_purchase_price() {
		Chips chips = new Chips("B4", "Crunchies", new BigDecimal("1.75"), 5);
		
		assertEquals(new BigDecimal("1.75"), chips.getPurchasePrice());
	}
	
	@Test
	public void get_product_name() {
		Chips chips = new Chips("B4", "Crunchies", new BigDecimal("1.75"), 5);
		
		assertEquals("Crunchies", chips.getProductName());
	}
	
	@Test
	public void get_product_total() {
		Chips chips = new Chips("B4", "Crunchies", new BigDecimal("1.75"), 5);
		
		assertEquals(5, chips.getProductTotal());
	}

}
