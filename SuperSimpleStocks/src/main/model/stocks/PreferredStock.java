package main.model.stocks;


/**
 * A preferred stock differs from a common stock due to the 
 * fact that the dividend yield is calculated using a fixed dividend and PAR Value.
 * 
 * @author andersarchibald
 * @version 1.0
 *
 */
public class PreferredStock extends Stock {
	
	private double fixedDividend;
	
	//CALCS
	@Override
	public void calculateDividendYield(double tickerPrice) {
		dividendYield = ((fixedDividend / 100) * parValue) / tickerPrice;
	}
	
	//GETTERS AND SETTERS
	public double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(int fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
}
