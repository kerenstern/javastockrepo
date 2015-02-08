package com.myorg.javacourse.model;

import com.myorg.javacourse.Stock;

public class Portfolio {

	private String title;
	private int MAX_PORTFOLIO_SIZE = 5;
	public Stock stocks[];
	public int portfolioSize = 0;

	private int recommendation;
	private int stockQuantity;
	private int BUY = 0;
	private int SELL = 1;
	private int REMOVE = 2;
	private int HOLD = 3;

	public Portfolio() {
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
	}

	// Setters
	public void addStocks(Stock Stock) {
		if (portfolioSize < MAX_PORTFOLIO_SIZE) {
			stocks[portfolioSize] = Stock;
			portfolioSize++;
		}
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// Getters

	public String getTitle() {
		return title;
	}

	public Stock[] getStocks() {
		return stocks;
	}

	public String getHtmlString() {
		String portfolioHtml = "<h1>" + getTitle() + "</h1>";
		for (int i = 0; i < portfolioSize; i++) {
			portfolioHtml += stocks[i].getHtmlDescription() + "<br>";
		}
		return portfolioHtml;
	}
}
