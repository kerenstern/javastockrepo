package com.myorg.javacourse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise3 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3579465335288217297L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		Calculation 1

		int radius = 50;
		double area = Math.PI * radius * radius;
		String circleAreaString = new String(
				"Calculation 1: Area of circle with radius " + radius
						+ "cm is: " + area + " square-cm");

//		Calculation 2
		
		int angleB = 30;
		int hypotenuse = 50;
		double angleBRadians = Math.toRadians(angleB);
		double oppositeLength = Math.sin(angleBRadians) * hypotenuse;
		String oppositeLengthString = new String(
				"Calculation 2: Length of opposite where angle B is: " + angleB + "degrees and Hypotenuse length is " + hypotenuse + " cm is: "
						+ oppositeLength + "cm");

//		Calculation 3
		
		int baseInt = 20;
		int expInt = 13;
		double powCalc = Math.pow(baseInt, expInt);
		String powCalcString = new String("Calculation 3: Power of " + baseInt + " with exp of " + expInt + " is: "
				+ powCalc);

//		Printing calculations
		
		String calculationsString = circleAreaString + "<br>" + oppositeLengthString
				+ "<br/>" + powCalcString;
		
		resp.setContentType("text/html");
		resp.getWriter().println(calculationsString);

	}

}