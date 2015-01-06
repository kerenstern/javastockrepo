package com.myorg.javacourse;
import java.text.SimpleDateFormat;

public class Stock {
	private String symbol;
	private float ask;
	private float bid;	
	java.util.Date date;
	String DateToStr;
	
	public Stock(String symbol, float ask, float bid, java.util.Date date) {
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);		
		setDate(date);
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

	public String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateToStr = dateFormat.format(date);
		return DateToStr;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public String getHtmlDescription() {

		String stockHtmlDetailsString = "<b>Stock symbol</b>: " + getSymbol();
		stockHtmlDetailsString += ", <b>ask</b>: " + getAsk();
		stockHtmlDetailsString += ", <b>bid</b>: " + getBid();
		stockHtmlDetailsString += ", <b>date</b>: " + getDate();

		return stockHtmlDetailsString;
	}

}
