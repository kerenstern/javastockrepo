package com.myorg.javacourse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Stock {
	private String symbol;
	private float ask;
	private float bid;	
	private String DateToStr;
	static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	Calendar newDate = Calendar.getInstance();
	private long date;
	
	public Stock() {
		this("", 0, 0, System.currentTimeMillis());
	}
	
	public Stock(Stock stock) {
		this(stock.getSymbol(), stock.getAsk(), stock.getBid(), stock.getDate());
	}
	
	public Stock(Stock stock, float bid) {
		this(stock.getSymbol(), stock.getAsk(), bid, stock.getDate());
	}

	public Stock(String symbol, float ask, float bid, Long dateModified) {
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);		
		setDate(dateModified);
	}
	

	private String getSymbol() {
		return symbol;
	}

	private void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	private float getAsk() {
		return ask;
	}

	private void setAsk(float ask) {
		this.ask = ask;
	}

	private float getBid() {
		return bid;
	}

	private void setBid(float bid) {
		this.bid = bid;
	}

	public long getDate() {
		return date;
	}

	public void setDate(Long dateInMills) {
		this.date = dateInMills;
	}
	
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

		return stockHtmlDetailsString;
	}

}
