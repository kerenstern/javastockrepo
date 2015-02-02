package com.myorg.javacourse.service;

import com.myorg.javacourse.Stock;
import com.myorg.javacourse.model.Portfolio;

public class PortfolioManager {

	public Portfolio portfolio;
	public Portfolio getPortfolio() {
				
		Portfolio portfolio = new Portfolio();
		
		String portfolioTitle = "A Very Descriptively Named Stock Portfolio";
		portfolio.setTitle(portfolioTitle);
		
		String symbol1 = "PIH";
		Float ask1 = (float) 13.1;
		Float bid1 = (float) 12.4;
		Long date1 = 1416002400000L;
		
		Stock PIHStock = new Stock(symbol1, ask1, bid1, date1);
		portfolio.addStocks(PIHStock);
		
		String symbol2 = "AAL";
		Float ask2 = (float) 5.78;
		Float bid2 = (float) 5.5;	
		Long date2 = 1416002400000L;	
		
		Stock AALStock = new Stock(symbol2, ask2, bid2, date2);
		portfolio.addStocks(AALStock);
		
		String symbol3 = "CAAS";
		Float ask3 = (float) 32.2;
		Float bid3 = (float) 31.5;	
		Long date3 = 1416002400000L;		
		
		Stock CAASStock = new Stock(symbol3, ask3, bid3, date3);
		
		portfolio.addStocks(CAASStock);
		
		return portfolio;
		
	}

}
