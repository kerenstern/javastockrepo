package com.myorg.javacourse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class StockDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		String 1
		String symbol1 = "PIH";
		Float ask1 = (float) 13.1;
		Float bid1 = (float) 12.4;
		java.util.Date date1 = new Date(114, 10, 15);		
		
		Stock stock1 = new Stock(symbol1, ask1, bid1, date1);
		String stockDetails1 = stock1.getHtmlDescription();
		
//		String 2
		String symbol2 = "AAL";
		Float ask2 = (float) 5.78;
		Float bid2 = (float) 5.5;	
		java.util.Date date2 = new Date(114, 10, 15);		
		
		Stock stock2 = new Stock(symbol2, ask2, bid2, date2);
		String stockDetails2 = stock2.getHtmlDescription();
				
//		String 3		
		String symbol3 = "CAAS";
		Float ask3 = (float) 32.2;
		Float bid3 = (float) 31.5;	
		java.util.Date date3 = new Date(114, 10, 15);
		
		Stock stock3 = new Stock(symbol3, ask3, bid3, date3);
		String stockDetails3 = stock3.getHtmlDescription();
		
//		Print to screen
		String stockString = stockDetails1 + "<br>" + stockDetails2 + "<br>" + stockDetails3;
		resp.setContentType("text/html");
		resp.getWriter().println(stockString);

	}

}
