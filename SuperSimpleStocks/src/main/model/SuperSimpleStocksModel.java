package main.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.controller.SuperSimpleStocksController;
import main.model.stocks.PreferredStock;
import main.model.stocks.Stock;
import main.model.stocks.StockBuilder;
import main.model.stocks.StockType;
import main.model.trades.Trade;
import main.model.trades.TradeType;

/**
 * The SimpleStockModel class persists all the data held for the application. 
 * Calculations using both trades and stocks are defined within this class.
 * 
 * @author andersarchibald
 * @version 1.0
 *
 */
public class SuperSimpleStocksModel {
	
	private static final int TRADE_LAG = 15;

	private List<Stock> stocks = new ArrayList<Stock>();
	
	//Using arraydeque as it will retain the order of the trades as they are made
	private ArrayDeque<Trade> trades = new ArrayDeque<Trade>();
	
	/**
	 * The intialise stocks method populates the application with an initial set of data. No trades are created, only the specified Stocks.
	 */
	public void initialiseStocks(){
		//builder design pattern used to help cater for the different stock types
		StockBuilder stockBuilder = new StockBuilder();
		Stock tea = stockBuilder.createNewStock(StockType.COMMON, "TEA");
		Stock pop = stockBuilder.createNewStock(StockType.COMMON, "POP");
		Stock ale = stockBuilder.createNewStock(StockType.COMMON, "ALE");
		Stock gin = stockBuilder.createNewStock(StockType.PREFERRED, "GIN");
		Stock joe = stockBuilder.createNewStock(StockType.COMMON, "JOE");
		
		//set lastDividends
		stockBuilder.setLastDividend(tea, 0);
		stockBuilder.setLastDividend(pop, 8);
		stockBuilder.setLastDividend(ale, 23);
		stockBuilder.setLastDividend(gin, 8);
		stockBuilder.setLastDividend(joe, 13);
		
		//set fixed Dividends on preferred stocks
		stockBuilder.setFixedDividend((PreferredStock)gin, 2);
		
		//set PAR Values
		stockBuilder.setPARValue(tea, 100);
		stockBuilder.setPARValue(pop, 100);
		stockBuilder.setPARValue(ale, 100);
		stockBuilder.setPARValue(gin, 100);
		stockBuilder.setPARValue(joe, 100);
	
		stocks.add(tea);
		stocks.add(pop);
		stocks.add(ale);
		stocks.add(gin);
		stocks.add(joe);
		
		Collections.sort(stocks, new Comparator<Stock>() {
	        @Override
	        public int compare(Stock stock1, Stock stock2)
	        {

	            return  stock1.getSymbol().compareTo(stock2.getSymbol());
	        }
	    });
	}

	/**
	 * This method finds all trades registered in the last 15 minutes for a given stock.
	 * @param stock The stock for which trades are to be found.
	 * @return A list of trades.
	 */
	private List<Trade> findLatestTrades(Stock stock) {
		//find trades that have taken place in the last 15 minutes for the stock
		List<Trade> latestTrades =  new ArrayList<Trade>();
		for(Trade t : trades){
			if(t.getStock().equals(stock)){
				if(t.getTimestamp().until(LocalDateTime.now(), ChronoUnit.MINUTES) <= TRADE_LAG){
					latestTrades.add(t);
				}
			}
		}
		
		return latestTrades;
	}
	
	
	/**
	 * This method calculates the stock price based on trades made in the last 15 minutes.
	 * @param stock The stock for which a price must be found.
	 * @return The stock price.
	 */
	public double calculateStockPrice(Stock stock){
		List<Trade> latestTrades = findLatestTrades(stock);
		
		float value = 0;
		int quantity = 0;
		
		for(Trade t : latestTrades){
			value += (t.getPrice() * t.getQuantity());
			quantity += t.getQuantity();
		}
		
		return  value/quantity;
	}
	
	
	/**
	 * @return The all share index value.
	 */
	public double calculateGBCEAllShareIndex(){
		double allShareIndex = 0;
		double priceParameter = 0;
		for(Trade t : trades){
			if(priceParameter == 0){
				priceParameter = t.getPrice();
			}else{
				priceParameter = (priceParameter * t.getPrice());
			}
		}
		
		if(priceParameter > 0){
			allShareIndex = Math.pow(priceParameter, 1.0 / trades.size());
		}
		
		return allShareIndex;
	}
	
	
	/**
	 * This method executes a stock trade, based upon the stock selected, the quantity of stocks, the stock price entered and the type of trade (BUY or SELL).
	 * @param stock This is the selection index of the stock to be traded.
	 * @param quantity The number of stocks to be traded.
	 * @param price The price of the trade.
	 * @param type The trade type BUY or SELL.
	 * @return Trade success boolean.
	 */
	public boolean addTrade(Stock stock, int quantity, double price, TradeType type){
		Trade newTrade = new Trade();
		newTrade.setStock(stock);
		newTrade.setQuantity(quantity);
		newTrade.setPrice(price);
		newTrade.setTradeType(type);
		
		return trades.add(newTrade);
	}

	public List<String> findAllStockSymbols() {
		List<String> symbols = new ArrayList<String>();
		for(Stock stock : stocks){
			symbols.add(stock.getSymbol());
		}
		return symbols;
	}
	
	//GETTERS AND SETTERS
	public List<Stock> getStocks() {
		return stocks;
	}

	public ArrayDeque<Trade> getTrades() {
		return trades;
	}
}
