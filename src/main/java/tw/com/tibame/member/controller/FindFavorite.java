package tw.com.tibame.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import tw.com.tibame.event.model.EventVO;
import tw.com.tibame.member.model.CollectService;
import tw.com.tibame.member.model.MemberVO;

@WebServlet("/FindFavorite")
public class FindFavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindFavorite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
		//getting current logged in members memberid
		HttpSession session1 = req.getSession();
		MemberVO vo = (MemberVO) session1.getAttribute("memberVO");
		CollectService cs1 = new CollectService();
		List<EventVO> list1 = new ArrayList<EventVO>();
		//using memberid to get their favorite events from collect DB
		if(vo != null) {
			int memberId = vo.getNumber();
			list1 = cs1.getFavorite(memberId);
			
		}else {
			System.out.println("vo is null");
		}
		
		
		JsonObject respBody = new JsonObject();
		if (list1 == null) {
			respBody.addProperty("successful", false);
		} else {
			respBody.addProperty("successful", true);
		}
		
		res.getWriter().write(respBody.toString());
		
	}

}
