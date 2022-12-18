package tw.com.tibame.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.tibame.management.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/OrganizerVerification")
public class OrganizerVerification extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson1;
	
	
	@Override
	public void init() throws ServletException {
		gson1 = new Gson();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Object obj1 = gson1.fromJson(req.getReader(), Object.class);
		
		int value1 = (int) obj1 ;
		HttpSession s1 = req.getSession();
		String authCode = (String) s1.getAttribute("authCode");
		System.out.println("authCode" + authCode);
		
		JsonObject resBody = new JsonObject();
		if (value1 == Integer.parseInt(authCode)) {
			resBody.addProperty("successful", false);
		} else {
			resBody.addProperty("successful", true);
		}
		
		res.getWriter().write(resBody.toString());
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
