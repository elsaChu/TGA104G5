package com.event.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.model.EventService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/AddEvent2Servlet")
public class AddEvent2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
		String action = request.getParameter("action");
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Map<String,Object> result = new HashMap<>();
        result.put("success", false);
        
        if("save".equals(action)) {
        	String seatIdList  = request.getParameter("seatIdList");
			if (seatIdList == null || seatIdList.trim().length() == 0) {
				result.put("seatIdList","座位設定有異");
			}
			
			String xVal=request.getParameter("xVal");
			Integer xval = null;
			try {
				xval = Integer.valueOf(xVal);
			}catch (NumberFormatException e) {
				result.put("xValmsg","只能是數字");
				result.put("xVal",xVal);
			}
			
			String yVal=request.getParameter("yVal");
			Integer yval = null;
			try {
				yval = Integer.valueOf(yVal);
			}catch (NumberFormatException e) {
				result.put("yValmsg","只能是數字");
				result.put("yVal",yVal);
			}
	        
	        String otherPageData  = request.getParameter("otherPageData");
        	
	        System.out.println("seatIdList:" + seatIdList);
	        System.out.println("xVal:" + xval);
	        System.out.println("yVal:" + yval);
	        System.out.println("otherPageData:" + otherPageData);
	        
	        String[] seatIdListary = seatIdList.split(",");
	        
	        
			if(result.size() > 1) {
				System.out.println("go error "+result.size());
			    PrintWriter out = response.getWriter();
		        out.print(gson.toJson(result));
		        out.flush();
		        return;
				
			}else{
				System.out.println("in suc true"+result.size());
				result.put("success", true);
			}


	        // call service insert data
			EventService eventSvc = new EventService();
			int eventInsertOK = eventSvc.addEvent(otherPageData,xval,yval,seatIdListary);
        }
        
        
	    PrintWriter out = response.getWriter();
        out.print(gson.toJson(result));
        out.flush();
	}
}
