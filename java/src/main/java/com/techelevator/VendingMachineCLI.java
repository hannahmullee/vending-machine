package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.techelevator.items.Candy;
import com.techelevator.items.Chips;
import com.techelevator.items.Drinks;
import com.techelevator.items.Gum;
import com.techelevator.items.MachineItem;
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT };
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static BigDecimal currentMoneyProvided = new BigDecimal("0.00");
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };
	
	String path = "log.txt";
	
	File auditFile = new File(path);
	
	LocalDateTime now = LocalDateTime.now();
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");
	
	String dateTimeString = now.format(formatter);
	

	private Scanner userInput = new Scanner(System.in);

	private Menu menu;
	private List<MachineItem> items; // globally craeted item AL

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public List<MachineItem> loadInventory() throws FileNotFoundException {
		List<MachineItem> items = new ArrayList<>();
		String vendingFilePath = "vendingmachine.csv";
		File vendingMachineFile = new File(vendingFilePath);

		try (Scanner fileScanner = new Scanner(vendingMachineFile)) {
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] itemArray = line.split("\\|");
				String slot = itemArray[0];
				String name = itemArray[1];
				BigDecimal price = new BigDecimal(itemArray[2]);
				String type = itemArray[3];

				if (type.equals("Chip")) {
					items.add(new Chips(slot, name, price, 5));
				} else if (type.equals("Candy")) {
					items.add(new Candy(slot, name, price, 5));
				} else if (type.equals("Drink")) {
					items.add(new Drinks(slot, name, price, 5));
				} else if (type.equals("Gum")) {
					items.add(new Gum(slot, name, price, 5));
				}
			}
		}

		return items;

	}

	public void feedMoney() throws IOException {
		
		try(Logger logger = new Logger(path)){
		String moreMoneyResponse = "Y";
			
		

		while (moreMoneyResponse.equalsIgnoreCase("Y")) {
			System.out.println("\n" + "How much money will you put in? ");
			int moneyGivenInt = 0;
			try{moneyGivenInt = Integer.parseInt(userInput.nextLine());}
			catch (NumberFormatException e){
				System.out.println("\nThat is not a whole dollar amount!");
			}

			BigDecimal additionalMoney = new BigDecimal(moneyGivenInt);
			currentMoneyProvided = currentMoneyProvided.add(additionalMoney);
			logger.write(dateTimeString + " FEED MONEY: $" + additionalMoney + " $" + currentMoneyProvided);
			
			System.out.println("\n" + "Current Balance: " + "$" + currentMoneyProvided);
			System.out.println("\n" + "Do you want to put more money in? (Y/N) ");
			moreMoneyResponse = userInput.nextLine();
			}
		}
		
	}

	public void selectProduct() throws IOException {

		try(Logger logger = new Logger(path)){
		System.out.println(items.toString().replace("[", "").replace("]", "").replace(",", ""));
		System.out.println("\n" + "Enter code for the product you'd like to purchase: ");
		String slot = userInput.nextLine();
		boolean found = false;  // flag 

		for (int i = 0; i < items.size(); i++) {

			if (items.get(i).getSlotIdentifier().equalsIgnoreCase(slot)) {
				found = true;
				if (currentMoneyProvided.compareTo(items.get(i).getPurchasePrice()) == -1) {
					System.out.println("You need to add money!");
					break;
				}
				if (items.get(i).getProductTotal() <= 0) {
					System.out.println("Sorry, that product is sold out!\n" + "Please choose another option: ");
					break;
				}
				items.get(i).purchase();
				
				BigDecimal balanceRemaining = new BigDecimal("0.00");
				
				balanceRemaining = currentMoneyProvided.subtract(items.get(i).getPurchasePrice());
				logger.write(dateTimeString + " " + items.get(i).getProductName() + " " + items.get(i).getSlotIdentifier() + " $" + currentMoneyProvided + " $" + balanceRemaining);
				currentMoneyProvided = balanceRemaining;
				
				System.out.println("\n" + "Purchase successful! " + "\n" + "Item: " + items.get(i).getProductName()
						+ "\n" + "Item price: " + "$" + items.get(i).getPurchasePrice() + "\n" + "Money remaining: "
						+ "$" + currentMoneyProvided + "\n" + items.get(i).dispensingMessage());

			}
		}
		if (!found) {
			System.out.println("\n" + "This product code does not exist. ");
			}
		}
	}

	public void finishTransaction() throws IOException {
		try(Logger logger = new Logger(path)){
			
		BigDecimal quarter = new BigDecimal("0.25");
		BigDecimal dime = new BigDecimal("0.10");
		BigDecimal nickel = new BigDecimal("0.05");

		BigDecimal[] quarterRemainder = currentMoneyProvided.divideAndRemainder(quarter);
		BigDecimal[] dimeRemainder = quarterRemainder[1].divideAndRemainder(dime);
		BigDecimal[] nickelRemainder = dimeRemainder[1].divideAndRemainder(nickel);

		System.out.println("\n" + "Your change is: $" + currentMoneyProvided + "\n" + "Quarters: " + quarterRemainder[0] + "\n" + "Dimes: "
				+ dimeRemainder[0] + "\n" + "Nickels: " + nickelRemainder[0]);

		BigDecimal remainingBalance = new BigDecimal("0.00");
		remainingBalance = currentMoneyProvided.subtract(currentMoneyProvided);
		System.out.println("Current Balance: $" + remainingBalance);
		
		logger.write(dateTimeString + " GIVE CHANGE: $" + currentMoneyProvided + " $" + remainingBalance);
		}
	}
	

	public void run() throws IOException {
		items = loadInventory();
		auditFile.createNewFile();

		while (true) { // main menu while loop
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println(items.toString().replace("[", "").replace("]", "").replace(",", ""));

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {  // purchase menu while loop
					System.out.println("\n" + "Current Balance: $" +  currentMoneyProvided);
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					

					if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						feedMoney();

					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						selectProduct();

					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						finishTransaction();
						break;  // breaks out of purchase menu while loop back to main menu while loop
					}

				}

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();

	}

}
