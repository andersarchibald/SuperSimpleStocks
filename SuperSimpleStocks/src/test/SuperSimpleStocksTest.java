package test;

import static org.junit.Assert.assertTrue;
import main.SuperSimpleStocks;
import main.model.SuperSimpleStocksModel;
import main.model.stocks.Stock;
import main.model.trades.Trade;
import main.model.trades.TradeType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SuperSimpleStocksTest {
	private SuperSimpleStocks superSimpleStocks;
	private SuperSimpleStocksModel superSimpleStocksModel;

	@Before
	public void setUp() throws Exception {
		superSimpleStocks = new SuperSimpleStocks();
		superSimpleStocksModel = superSimpleStocks.getModel();
		
		//create some trades
		Trade t1 =  new Trade();
		t1.setStock(superSimpleStocksModel.getStocks().get(0));
		t1.setPrice(2.3);
		t1.setQuantity(10);
		t1.setTradeType(TradeType.BUY);
		
		
		Trade t2 =  new Trade();
		t2.setStock(superSimpleStocksModel.getStocks().get(0));
		t2.setPrice(2.8);
		t2.setQuantity(10);
		t2.setTradeType(TradeType.BUY);
		
		
		Trade t3 =  new Trade();
		t3.setStock(superSimpleStocksModel.getStocks().get(0));
		t3.setPrice(3.2);
		t3.setQuantity(15);
		t3.setTradeType(TradeType.SELL);
		
		superSimpleStocksModel.getTrades().add(t1);
		superSimpleStocksModel.getTrades().add(t2);
		superSimpleStocksModel.getTrades().add(t3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void checkStocksIntialised() {
		assert(superSimpleStocksModel.getStocks().size() > 0);
	}
	
	@Test
	public void checkStocksSorted() {
		System.out.println("CHECKING STOCKS ARE ORDERED ALPHABETICALLY - START");
		for(Stock s : superSimpleStocksModel.getStocks()){
			System.out.println(s.getSymbol());
		}
		System.out.println("CHECKING STOCKS ARE ORDERED ALPHABETICALLY - FINISH");
		assert(true);
	}
	
	@Test
	public void checkAvailableTrades() {
		System.out.println("CHECKING AVAILABLE TRADES - START");
		for(Trade t : superSimpleStocksModel.getTrades()){
			System.out.println(t.getStock().getSymbol()+" "+t.getPrice()+" "+t.getQuantity()+" "+t.getTimestamp().toString());
		}
		System.out.println("CHECKING AVAILABLE TRADES - FINISH");
		assert(superSimpleStocksModel.getTrades().size() > 0);
	}	
	
	@Test
	public void checkStockPrice() {
		Stock firstStock = superSimpleStocksModel.getStocks().get(0);
		double stockPrice = superSimpleStocksModel.calculateStockPrice(firstStock);
		System.out.println("STOCK PRICE "+stockPrice);
		assertTrue(stockPrice == 2.828571319580078);
	}
	
	@Test
	public void checkCommonStockDividendYield() {
		Stock joeStock = superSimpleStocksModel.getStocks().get(2);
		joeStock.calculateDividendYield(2.5);
		double dividendYield = joeStock.getDividendYield();
		System.out.println("DIVIDEND YIELD FOR "+joeStock.getSymbol()+" = "+dividendYield);
		assertTrue(dividendYield == 5.2);
	}
	
	@Test
	public void checkPreferredStockDividendYield() {
		Stock ginStock = superSimpleStocksModel.getStocks().get(1);
		ginStock.calculateDividendYield(2.5);
		double dividendYield = ginStock.getDividendYield();
		System.out.println("DIVIDEND YIELD FOR "+ginStock.getSymbol()+" = "+dividendYield);
		assertTrue(dividendYield == 0.8);
	}
	
	@Test
	public void checkGBCEAllShareIndex() {
		System.out.println("The GBCE All Share Index is : "+superSimpleStocksModel.calculateGBCEAllShareIndex());
		assertTrue(superSimpleStocksModel.calculateGBCEAllShareIndex() == 2.7416496007330498);
	}
	
	@Test
	public void buyStock() {
		Stock joeStock = superSimpleStocksModel.getStocks().get(2);
		superSimpleStocksModel.addTrade(joeStock, 10, 350.30, TradeType.BUY);
		Trade latestTrade = superSimpleStocksModel.getTrades().getLast();
		Stock testStock = latestTrade.getStock();
		assertTrue(testStock.getSymbol().equals("JOE"));
	}
	
	@Test
	public void sellStock() {
		Stock ginStock = superSimpleStocksModel.getStocks().get(1);
		superSimpleStocksModel.addTrade(ginStock, 1000, 6.80, TradeType.SELL);
		Trade latestTrade = superSimpleStocksModel.getTrades().getLast();
		Stock testStock = latestTrade.getStock();
		assertTrue(testStock.getSymbol().equals("GIN"));
	}
	
}
