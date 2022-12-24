package tw.com.tibame.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import tw.com.tibame.member.model.MemberVO;

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;
	public void init() throws ServletException {
		System.out.println("init checkLogin Servlet");
		gson = new Gson();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("check login recieved do Get");
//		final String idStr = req.getParameter("id");
		HttpSession session = req.getSession();
		String loginDone = (String) session.getAttribute("loginStatus");
		
		//FAKE INFO start
		System.out.println("check login implemented fake member login info");
		MemberVO fakeVo = new MemberVO();
		fakeVo.setName("fakeName");
		fakeVo.setIdNumber("100");
		fakeVo.setNumber(1);
		session.setAttribute("memberVO", fakeVo);
		//FAKE INFO  end
		
		JsonObject resBody = new JsonObject();
		if(loginDone != null) {
			if(loginDone.equals("Success")) {
				System.out.println("login is done");
				resBody.addProperty("successful", true);
			}else {
				resBody.addProperty("successful", false);
				System.out.println("login not done");
			}
		}else {
			System.out.println("loginDone is null");
			resBody.addProperty("successful", false);
		}
		res.getWriter().write(resBody.toString());
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
	
	
}
