package com.myorg.javacourse.model;

import com.myorg.javacourse.Stock;
import java.util.*;

public class Portfolio {

	private String title;
	public Stock stocks[];
	public int portfolioSize = 0;

	private final static int MAX_PORTFOLIO_SIZE = 5;

	private int recommendation;
	private int stockQuantity;
	private int BUY = 0;
	private int SELL = 1;
	private int REMOVE = 2;
	private int HOLD = 3;

	// Creates new stock array
	public Portfolio() {
		Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
		setStocks(stocks);
	}

	// Creates portfolio from portfolio params
	public Portfolio(Portfolio portfolio) {
		this(portfolio.getTitle(), portfolio.getStocks(), portfolio
				.getPortfolioSize());
	}

	// Creates portfolio with portfolio and title params
	public Portfolio(Portfolio portfolio, String title) {
		this(title, portfolio.getStocks(), portfolio.getPortfolioSize());
	}

	// Creates portfolio from title and stocks array
	public Portfolio(String title, Stock[] stocksToCopy, int portfolioSize) {
		this();
		setTitle(title);
		setPortfolioSize(0);
		for (int i = 0; i < portfolioSize; i++) {
			Stock stockToCopy = new Stock(stocksToCopy[i]);
			addStock(stockToCopy);
		}
	}

	// Setters
	public void addStock(Stock Stock) {
		if (portfolioSize < MAX_PORTFOLIO_SIZE) {
			stocks[portfolioSize] = Stock;
			portfolioSize++;
			setPortfolioSize(portfolioSize);
		}
	}

	public void removeStock(int stockIndex) {
		for (int i = stockIndex; i < portfolioSize - 1; i++) {
			stocks[i] = stocks[i + 1];
		}

		setStocks(stocks);
		setPortfolioSize(portfolioSize - 1);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	// Getters

	public String getTitle() {
		return title;
	}

	public Stock[] getStocks() {
		return stocks;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public String getHtmlString() {
		String portfolioHtml = "<h1>" + getTitle() + "</h1>";
		for (int i = 0; i < portfolioSize; i++) {
			portfolioHtml += stocks[i].getHtmlDescription() + "<br>";
		}
		return portfolioHtml;
	}

	public void setNewBid(int stockIndex, float newBid) {
		stocks[stockIndex] = new Stock(stocks[stockIndex], newBid);

	}
}
