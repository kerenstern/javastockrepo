package com.myorg.javacourse.stock.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myorg.javacourse.service.*;
import com.myorg.javacourse.model.*;

public class PortfolioServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		PortfolioManager portfolioManager = new PortfolioManager();

		Portfolio portfolio = portfolioManager.getPortfolio();

		resp.getWriter().println(portfolio.getHtmlString());

	}

}
