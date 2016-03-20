package main.controller;

import java.util.List;

import main.model.SuperSimpleStocksModel;
import main.model.stocks.Stock;
import main.model.trades.TradeType;
import main.view.SuperSimpleStocksView;

/**
 * The SuperSimpleStocksController  acts as the marshaling point between the SuperSimpleStocksModel and SuperSimpleStocksView.
 * System behavior is defined within this class.
 * 
 * @author andersarchibald
 * @version 1.0
 *
 */
public class SuperSimpleStocksController {
	
	
	private SuperSimpleStocksModel model;
	private SuperSimpleStocksView view;
	
	/**
	 * This method runs the following calculations for a selected stock:  Dividend Yield, P/E Ration and Stock Price based on trades made in the last 15 minutes. 
	 * The user must also have entered a ticker price for this to work.
	 * 
	 * @param selectionIndex The index number of the selected stock.
	 * @param tickerPrice The ticker price entered by the user.
	 * @return An analysis message for the stock.
	 */
	public String analyseStock(int selectionIndex, double tickerPrice){
		Stock stock = model.getStocks().get(selectionIndex - 1);
		
		stock.calculateDividendYield(tickerPrice);
		stock.calculatePERatio(tickerPrice);
		
		double stockPrice = model.calculateStockPrice(stock);//model calculates the stock price as it requires an analysis of the last 15 mins of trades
		String stockPriceOutput = Double.toString(stockPrice);
		if(Double.isNaN(stockPrice)){
			stockPriceOutput = "No trades made in last 15 minutes";
		}
		
		return "Stock analysis : \n\tSymbol : "+stock.getSymbol()+"\n\tticker price : "+tickerPrice+"\n\tdividend yield : "+stock.getDividendYield()+"\n\tPE Ratio : "+stock.getPERatio()+"\n\tstock price : "+stockPriceOutput;
	}
	
	
	/**
	 * This method executes a stock trade, based upon the stock selected, the quantity of stocks, the stock price entered and the type of trade (BUY or SELL).
	 * @param selectionIndex This is the selection index of the stock to be traded.
	 * @param quantity The number of stocks to be traded.
	 * @param price The price of the trade.
	 * @param tradeType The trade type BUY or SELL.
	 * @return Trade completion message.
	 */
	public String tradeStock(int selectionIndex, int quantity, double price, TradeType tradeType){
		Stock stock = model.getStocks().get(selectionIndex - 1);
		if(model.addTrade(stock, quantity, price, tradeType)){
			String action = (tradeType.equals(TradeType.BUY))? "bought" : "sold";
			return "Trade completed for "+quantity+" "+stock.getSymbol()+" shares "+action+" at £"+price;
		}else{
			return "Trade failed!";
		}
	}
	
	/**
	 * Method used to execute the GBCE all share index calculation.
	 * @return The GBCE All Share index value is returned.
	 */
	public String allShareIndex() {
		if(model.getTrades().size() == 0 ){
			return "No trades have been made to calculate the index!";
		}else {
			return Double.toString(model.calculateGBCEAllShareIndex());
		}
	}

	public List<String> getStockSymbols() {
		return model.findAllStockSymbols();
	}


	public String getSymbolForStock(int i) {
		Stock stock = model.getStocks().get(i - 1);
		return stock.getSymbol();
	}

	public void setModel(SuperSimpleStocksModel model) {
		this.model = model;
	}

	public void setView(SuperSimpleStocksView view) {
		this.view = view;
	}


	


}
