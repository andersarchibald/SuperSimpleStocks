package main.view;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import main.controller.SuperSimpleStocksController;
import main.model.trades.TradeType;

/**
 * The SuperSimpleStocksView provides all menu and display options defined for the application.
 * 
 * @author andersarchibald
 * @version 1.0
 *
 */
public class SuperSimpleStocksView {

	private SuperSimpleStocksController controller;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * This method displays the main menu utilized by the application.
	 */
	public void displayMenu() {
		System.out.println("\n");
		System.out.println("Welcome to Super Simple Stocks");
		System.out.println("Your options are:");
		System.out.println("1. Analyse a stock");
		System.out.println("2. Buy a Stock");
		System.out.println("3. Sell a Stock");
		System.out.println("4. View the GBCE All Share Index");
		System.out.println("5. View the help documentation");
		System.out.println("6. Exit");
		System.out.println("Enter option number:");

		try {
			int i = Integer.parseInt(reader.readLine());

			switch (i) {
			case 1:
				stockSelectionMenu();
			case 2 : 
				tradeAStock(TradeType.BUY);
			case 3 : 
				tradeAStock(TradeType.SELL);
			case 4:
				allShareIndexDisplay();
			case 5:
				help();
			case 6:
				System.exit(0);
			default:
				help();
			}
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid Selection! - please enter a number between 1 and 6");
			displayMenu();
		} catch (IOException e) {
			System.err.println("An error has occurred! - please try again.");
			displayMenu();
		}
	}

	
	/**
	 * This method prompts the user for the individual stock they wish to analyze.
	 * On completion of analysis the user is returned to the main menu. 
	 */
	private void stockSelectionMenu() {
		int count = displaySymbols("Please enter the number of the stock you wish to analyse:");

		try {
			int i = readAndCheckInput(count);
			//make sure the display menu option has not been selected
			if(i < count){
				System.out.println("Please enter the ticker price for "+controller.getSymbolForStock(i));
				
				double tickerPrice = Double.parseDouble(reader.readLine());
				
				System.out.println("\n");
				System.out.println(controller.analyseStock(i, tickerPrice));
				System.out.println("\n");
			}
			displayMenu();
			
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid Selection! - please enter a number between 1 and "+count);
			stockSelectionMenu();
		} catch (IOException e) {
			System.err.println("An error has occurred! - please try again.");
			displayMenu();
		}
	}

	
	/**
	 * This method retrieves the stock symbols within application memory and prompts the user to make a stock selection.
	 * @param promptMessage The message containing the action to be taken for the selected stock.
	 * @return The number of stock symbols found.
	 */
	private int displaySymbols(String promptMessage) {
		List<String> symbols = controller.getStockSymbols();
		System.out.println(promptMessage);
		int count = 1;
		for (String symbol : symbols) {
			System.out.println(count + ". " + symbol);
			count++;
		}
		System.out.println(count + ". Return to main menu.");
		return count;
	}
	
	
	/**
	 * This method displays the GBCE all share index.
	 */
	private void allShareIndexDisplay(){
		System.out.println("The GBCE All Share index is:  "+controller.allShareIndex());
		displayMenu();
	}
	
	/**
	 * This method takes a trade type (BUY or SELL) and prompts the user to enter the required information to record a stock trade within the application.
	 * @param tradeType This will be either BUY or SELL.
	 */
	private void tradeAStock(TradeType tradeType){
		int count = displaySymbols("Please enter the number of the stock you wish to "+tradeType.toString()+":");
		try {
			int i = readAndCheckInput(count);
			
			//make sure the display menu option has not been selected
			if(i < count){
				System.out.println("Please enter the "+tradeType.toString()+" price for "+controller.getSymbolForStock(i));
				
				double price = Double.parseDouble(reader.readLine());
				
				System.out.println("Please enter the quantity to "+tradeType.toString()+": ");
				
				int quantity = Integer.parseInt(reader.readLine());
				
				System.out.println("\n");
				System.out.println(controller.tradeStock(i, quantity, price, tradeType));
				System.out.println("\n");
			}
			
			displayMenu();
			
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid Selection! - please enter a number between 1 and "+count);
			stockSelectionMenu();
		} catch (IOException e) {
			System.err.println("An error has occurred! - please try again.");
			displayMenu();
		}
		
	}


	private int readAndCheckInput(int count) throws IOException {
		int i = Integer.parseInt(reader.readLine());
		//make sure the user hasn't entered a number greater than the selection
		if(i > count || i <= 0){
			throw new NumberFormatException();
		}
		return i;
	}
	
	/**
	 * This method displays help information for the user.
	 */
	private void help(){
		System.out.println("***********Super Simple Stock Help Menu***********");
		System.out.println("\n");
		System.out.println("To select an option from the menu, enter the number of the option and then press enter.");
		System.out.println("\n");
		System.out.println("1. Analyse a stock");
		System.out.println("This option will prompt you for a stock to analyse. Use the number of the stock symbol to make the selection and then press enter.");
		System.out.println("The analysis will display the stock symbol, dividend yield, PE Ratio and Stock Price.");
		System.out.println("\n");
		System.out.println("2. Buy a Stock");
		System.out.println("This option will prompt you for a stock to buy. Use the number of the stock symbol displayed to make a selection then press enter.");
		System.out.println("You will be prompted to enter a buy price, enter the price and press enter.");
		System.out.println("You will be prompted to enter the quantity of stocks you wish to buy, enter the quantity and press enter.");
		System.out.println("A message shall be shown indicating whether the trade has been successful.");
		System.out.println("\n");
		System.out.println("3. Sell a Stock");
		System.out.println("This option will prompt you for a stock to sell. Use the number of the stock symbol displayed to make a selection then press enter.");
		System.out.println("You will be prompted to enter a sell price, enter the price and press enter.");
		System.out.println("You will be prompted to enter the quantity of stocks you wish to sell, enter the quantity and press enter.");
		System.out.println("A message shall be shown indicating whether the trade has been successful.");
		System.out.println("\n");
		System.out.println("4. View the GBCE All Share Index");
		System.out.println("This option will display the GBCE All Share Index.");
		System.out.println("\n");
		System.out.println("****************************************");
		
		displayMenu();
	}

	public void setController(SuperSimpleStocksController controller) {
		this.controller = controller;
	}

}
