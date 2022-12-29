package tw.com.tibame.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import tw.com.tibame.event.model.EventService;
import tw.com.tibame.event.model.EventVO;
import tw.com.tibame.member.model.CollectService;
import tw.com.tibame.member.model.MemberVO;

@WebServlet("/AddFavorite")
public class AddFavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson1;
    public AddFavorite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
        System.out.println("add favorite got request");
        this.gson1 = new Gson();
        CollectService cs1 = new CollectService();
//        EventService es1 = new EventService(); 
		List<Integer> list1 = new ArrayList<Integer>();
		//get new event id to be added to favorite list 
		String event1 = req.getParameter("heartEvent");
		Boolean addSuccess = true;
		Boolean foundSameFav = false;
		System.out.println("new event to be added(from servlet):　" + event1);
		int event2 = Integer.parseInt(event1);
		//getting current logged in members memberid
		HttpSession session1 = req.getSession();
		if(session1.getAttribute("memberVO") != null) {
			MemberVO vo = (MemberVO) session1.getAttribute("memberVO");
			int memberId = vo.getNumber();
			list1 = cs1.getFavorite(memberId);
			for(Integer e1 : list1) {
				if(e1 == event2) {
					addSuccess = false;
					foundSameFav = true;
					//new added event already exists in favorite list
				}else {
				}
			}
			if(addSuccess) {
//				add event id and memeber id into collect DB
				cs1.addFavorite(memberId, event2);
			}
			
			
		}else {
			addSuccess = false;
			System.out.println("memberVO null");
		}
		
		JsonObject resBody = new JsonObject();
		if (addSuccess && !foundSameFav) {
			resBody.addProperty("addNewFav", "success");
		} else if(foundSameFav){
			resBody.addProperty("addNewFav", "foundSame");
//			resBody.addProperty("favEvents", gson1.toJson(voList));
		}else {
			resBody.addProperty("addNewFav", "fail");
		}
		
		res.getWriter().write(resBody.toString());
		
		
	}

}
