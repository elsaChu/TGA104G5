package com.event.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddEvent2Servlet")
public class AddEvent2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
		String action = request.getParameter("action");
        
		Map<String,Object> result = new HashMap<>();
        result.put("success", false);
        
        if("save".equals(action)) {
        	String seatIdList  = request.getParameter("seatIdList");
	        String xVal  = request.getParameter("xVal");
	        String yVal  = request.getParameter("yVal");
	        String otherPageData  = request.getParameter("otherPageData");
	        
	        System.out.println("seatIdList:" + seatIdList);
	        System.out.println("xVal:" + xVal);
	        System.out.println("yVal:" + yVal);
	        System.out.println("otherPageData:" + otherPageData);

        }
        

	}

}
