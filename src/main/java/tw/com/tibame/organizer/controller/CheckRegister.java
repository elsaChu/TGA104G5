package tw.com.tibame.organizer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

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
//				showMessageDialog(null, "This is even shorter");
				
//				SwingUtilities.invokeLater(new Runnable() {
//		            @Override
//		            public void run() {
//		                new JOptionTest().displayGUI();
//		            }
//		        });
				JOptionPane optionPane = new JOptionPane("驗證成功，登入已啟用",JOptionPane.WARNING_MESSAGE);
				JDialog dialog = optionPane.createDialog("Verification Status");
				dialog.setAlwaysOnTop(true); // to show top of all other application
				dialog.setVisible(true); // to visible the dialog
				
				System.out.println("verified");
				url = "/back-organizer-end/OrganizerLogin1.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;
			}else {
				System.out.println("auth != veri");
				url = "/back-organizer-end/RegisterVerify.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;
			}
			
			
			
			
			
		}else {
			System.out.println("action does not equal verify");
		}
		
		
	
	
	}
	
	   
}

