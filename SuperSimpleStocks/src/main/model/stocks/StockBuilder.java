package main.model.stocks;

/**
 * The StockBuilder class provides a controlled way to create both kinds of stock
 * and set stock properties.
 * 
 * @author andersarchibald
 * @version 1.0
 *
 */
public class StockBuilder {
	
	
	public Stock createNewStock(StockType type, String symbol){
		Stock newStock;
		
		if(type == StockType.PREFERRED){
			newStock = new PreferredStock();
		}else{
			newStock = new CommonStock();
		}
		
		newStock.setSymbol(symbol);
		
		return newStock;
	}
	
	public void setLastDividend(Stock stock, int dividendValue){
		stock.setLastDividend(dividendValue);
	}
	
	public void setFixedDividend(PreferredStock stock, int dividendPercentage){
		stock.setFixedDividend(dividendPercentage);
	}
	
	public void setPARValue(Stock stock, int parValue){
		stock.setParValue(parValue);
	}
}
