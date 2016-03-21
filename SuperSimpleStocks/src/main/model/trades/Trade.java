package main.model.trades;

import java.time.LocalDateTime;
import main.model.stocks.Stock;

/**
 * A Trade represents a single stock trade. It can be of type BUY or SELL.
 * 
 * @author andersarchibald
 * @version 1.0
 *
 */
public class Trade {
	
	private Stock stock;
	private int quantity;
	private LocalDateTime timestamp;
	private TradeType tradeType;
	private double price; //For the purposes of keeping this app simple I'm using double for the price. If this app were to be developed I would look to represent money through an object.
	
	public Trade(){
		timestamp = LocalDateTime.now();
	}
	
	
	//GETTERS AND SETTERS
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public TradeType getTradeType() {
		return tradeType;
	}
	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
