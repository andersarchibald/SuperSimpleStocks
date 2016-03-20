package main.model.stocks;

/**
 * A common stock differs from a preferred stock due to the 
 * fact that the dividend yield is calculated using the last dividend.
 * 
 * @author andersarchibald
 * @version 1.0
 *
 */
public class CommonStock extends Stock {

	@Override
	public void calculateDividendYield(double tickerPrice) {
		dividendYield = lastDividend / tickerPrice;
	}

}
