package tw.com.tibame.organizer.controller;

import java.awt.GraphicsEnvironment.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import tw.com.tibame.main.JOptionTest;

import static javax.swing.JOptionPane.showMessageDialog;


@WebServlet("/CheckRegister")
public class CheckRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		HttpSession s1 = req.getSession();
		String url;

		if(action.equals("verify")) {
			String authCode = (String) s1.getAttribute("authCode");
			System.out.println("authCode" + authCode);
			String veriCode = req.getParameter("veriCode");
			System.out.println("veriCode" + veriCode);
			
			if(veriCode.equals(authCode)) {
				System.out.println("veri == auth");
				System.out.println("verified");
				//should add alert window here but is headless 
				url = "/back-organizer-end/register-login/OrganizerLogin1.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;
			}else {
				System.out.println("auth != veri");
				url = "/back-organizer-end/register-login/RegisterVerify.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;
			}
			
			
			
			
			
		}else {
			System.out.println("action does not equal verify");
		}
		
		
	
	
	}
	
	   
}

