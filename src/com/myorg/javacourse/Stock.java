package com.myorg.javacourse;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Stock {
	private String symbol;
	private float ask;
	private float bid;	
	private java.util.Calendar date;
	private String DateToStr;
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
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

	public String getDate() {
		DateToStr = dateFormat.format(this.date.getTime());
		return DateToStr;
	}

	public void setDate(Long dateModified) {
		Calendar newDate = Calendar.getInstance();
		newDate.setTimeInMillis(dateModified);
		this.date = newDate;
	}

	public String getHtmlDescription() {

		String stockHtmlDetailsString = "<b>Stock symbol</b>: " + getSymbol();
		stockHtmlDetailsString += ", <b>ask</b>: " + getAsk();
		stockHtmlDetailsString += ", <b>bid</b>: " + getBid();
		stockHtmlDetailsString += ", <b>date</b>: " + getDate();

		return stockHtmlDetailsString;
	}

}
