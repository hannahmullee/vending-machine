package com.techelevator.items;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class DrinksTest {

	@Test
	public void purchase_method_buy_1_subtract_1() {
		Drinks drinks = new Drinks("C4", "Dr. Salt", new BigDecimal("1.50"), 5);
		
		assertEquals(4, drinks.purchase());
	}
	
	@Test
	public void dispensingMessage_return_Glug_Glug_Yum() {
		Drinks drinks = new Drinks("C4", "Dr. Salt", new BigDecimal("1.50"), 5);
		
		assertEquals("Glug Glug, Yum!", drinks.dispensingMessage());
	}
	
	@Test
	public void get_slot_identifier() {
		Drinks drinks = new Drinks("C4", "Dr. Salt", new BigDecimal("1.50"), 5);
		
		assertEquals("C4", drinks.getSlotIdentifier());
	}
	
	@Test
	public void get_purchase_price() {
		Drinks drinks = new Drinks("C4", "Dr. Salt", new BigDecimal("1.50"), 5);
		
		assertEquals(new BigDecimal("1.50"), drinks.getPurchasePrice());
	}
	
	@Test
	public void get_product_name() {
		Drinks drinks = new Drinks("C4", "Dr. Salt", new BigDecimal("1.50"), 5);
		
		assertEquals("Dr. Salt", drinks.getProductName());
	}
	
	@Test
	public void get_product_total() {
		Drinks drinks = new Drinks("C4", "Dr. Salt", new BigDecimal("1.50"), 5);
		
		assertEquals(5, drinks.getProductTotal());
	}
	

}
