package com.techelevator.items;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class GumTest {

	@Test
	public void purchase_method_buy_1_subtract_1() {
		Gum gum = new Gum("A4", "Chewy", new BigDecimal("2.50"), 5);
		
		assertEquals(4, gum.purchase());
			
		
	}
	
	@Test
	public void dispensingMessage_return_Chew_Chew_yum() {
		Gum gum = new Gum("A4", "Chewy", new BigDecimal("2.50"), 5);
		
		assertEquals("Chew Chew, Yum!", gum.dispensingMessage());
	}
	
	@Test
	public void get_slot_identifier() {
		Gum gum = new Gum("A4", "Chewy", new BigDecimal("2.50"), 5);
		
		assertEquals("A4", gum.getSlotIdentifier());
	}
	
	@Test 
	public void get_purchase_price() {
		Gum gum = new Gum("A4", "Chewy", new BigDecimal("2.50"), 5);
		
		assertEquals(new BigDecimal("2.50"), gum.getPurchasePrice());
	}
	
	@Test 
	public void get_product_name() {
		Gum gum = new Gum("A4", "Chewy", new BigDecimal("2.50"), 5);
		
		assertEquals("Chewy", gum.getProductName());
	}
	
	@Test 
	public void get_product_total() {
		Gum gum = new Gum("A4", "Chewy", new BigDecimal("2.50"), 5);
		
		assertEquals(5, gum.getProductTotal());
	}

}
