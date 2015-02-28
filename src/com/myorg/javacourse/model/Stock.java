package com.myorg.javacourse.model;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.myorg.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * Stock model
 * 
 * @author Keren
 *
 */
public class Stock {
	private String symbol;
	private float ask;
	private float bid;	
	
//	Date format
	private String DateToStr;
	static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	Calendar newDate = Calendar.getInstance();
	private long date;
	
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	// ******************
    //	Constructors
	// ******************

	public Stock() {
		this("", 0, 0, System.currentTimeMillis());
	}
	
	/**
	 * @param stock
	 */
	public Stock(Stock stock) {
		this(stock.getSymbol(), stock.getAsk(), stock.getBid(), stock.getDate());
	}
	
	/**
	 * @param stock
	 * @param bid
	 */
	public Stock(Stock stock, float bid) {
		this(stock.getSymbol(), stock.getAsk(), bid, stock.getDate());
	}

	/**
	 * @param symbol
	 * @param ask
	 * @param bid
	 * @param dateModified
	 */
	public Stock(String symbol, float ask, float bid, Long dateModified) {
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);		
		setDate(dateModified);
	}
	

	// ******************
    //	Setters
	// ******************
	
	private void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	private void setAsk(float ask) {
		this.ask = ask;
	}
	
	private void setBid(float bid) {
		this.bid = bid;
	}
	
	public void setDate(Long dateInMills) {
		this.date = dateInMills;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	/**
	 * @param stockQuantity
	 * 
	 * Updates number of stocks in portfolio (can be negative)
	 */
	public void updateStockQuantity(int stockQuantity) {
		stockQuantity = this.getStockQuantity() + stockQuantity;
		setStockQuantity(stockQuantity);
	}

	// ******************
    //	Getters
	// ******************

	String getSymbol() {
		return symbol;
	}

	public float getAsk() {
		return ask;
	}

	public float getBid() {
		return bid;
	}

	public long getDate() {
		return date;
	}
	
	public int getStockQuantity() {
		return stockQuantity;
	}
	
	/**
	 * Format date according to calendar SimpleDateFormat("MM/dd/yyyy");
	 */
	public String getFormattedDate() {
		newDate.setTimeInMillis(this.date);
		DateToStr = dateFormat.format(newDate.getTime());
		return DateToStr;
	}

	public String getHtmlDescription() {

		String stockHtmlDetailsString = "<b>Stock symbol</b>: " + getSymbol();
		stockHtmlDetailsString += ", <b>ask</b>: " + getAsk();
		stockHtmlDetailsString += ", <b>bid</b>: " + getBid();
		stockHtmlDetailsString += ", <b>date</b>: " + getFormattedDate();
		stockHtmlDetailsString += ", <b>quantity</b>: " + getStockQuantity();


		return stockHtmlDetailsString;
	}

}
