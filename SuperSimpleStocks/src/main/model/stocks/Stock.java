package main.model.stocks;

/**
 * The stock abstract class provides common functionality utilised for the
 * preferred and common stock classes. Calculations pertaining to individual stocks are 
 * provided by this class and it's sub-classes.
 * 
 * @author andersarchibald
 * @version 1.0
 *
 */
public abstract class Stock {
	
	private String symbol;
	protected int lastDividend;
	protected int parValue;
	protected double dividendYield;
	private double peRatio;
	
	//CALCS
	public abstract void calculateDividendYield(double tickerPrice);
	
	public void calculatePERatio(double tickerPrice){
		peRatio = tickerPrice / lastDividend;
	};
	
	//GETTERS AND SETTERS
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(int lastDividend) {
		this.lastDividend = lastDividend;
	}

	public int getParValue() {
		return parValue;
	}

	public void setParValue(int parValue) {
		this.parValue = parValue;
	}

	public double getDividendYield() {
		return dividendYield;
	}

	public double getPERatio() {
		return peRatio;
	}
	
}
