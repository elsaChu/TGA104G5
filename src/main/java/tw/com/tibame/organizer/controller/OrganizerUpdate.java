package tw.com.tibame.organizer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.tibame.organizer.model.*;



	//@WebServlet("/OrganizerSelectAll")
@WebServlet("/OrganizerUpdate")
public class OrganizerUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		//不要儲存快取
//		res.setHeader("Cache-Control", "no-store");
//		res.setHeader("Pragma", "no-cache");
//		res.setDateHeader("Expires", 0 );
		//這樣設定時從其他葉面返回會抓不到 東西 顯示錯誤訊息如以下: (因此先關掉)
//		「確認重新提交表單這個網頁需要使用你先前輸入的資料才能正確顯示。你可以重新傳送這些資料，不過這麼做會重複執行這個網頁先前執行過的任何動作。
//		按下重新載入按鈕，重新提交載入網頁所需的資料」
		
//		OrganizerVO ov1 = new OrganizerVO();
		OrganizerService oi1 = new OrganizerService();
		List<OrganizerVO> saList = oi1.selectAll();
//		System.out.println(saList);
		
//		使用SESSION來儲存attribute(setAttribute()),只要網頁不關下面會一直跑出SELECTALL的結果，不論刷新幾次
//		HttpSession session = req.getSession();
//		session.setAttribute("saAttribute", saList);
//		但用request來儲存ATTRIBUTE，RD.FORWARD()出去之後就抓不到了 該怎辦
//		(已解決 只是再JSP打錯字幹 req-> request (OK) )
		
		String referer = req.getHeader("referer");
		boolean fromSame = referer.equals("http://localhost:8080/JHWorkspace/OrganizerUpdate");
		System.out.println("fromSame: " + fromSame);
		if(!fromSame) {
			//dont show
			System.out.println("Update: set saA with Value");
			req.setAttribute("saAttribute", saList);
			String url = "/back-organizer-end/OrganizerUpdate.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}else {
			System.out.println("Update: set saA as null");
			req.setAttribute("saAttribute", null);
			res.sendRedirect(req.getContextPath() + "/back-organizer-end/OrganizerUpdate.jsp");
			//show
//			String url = "/back-organizer-end/OrganizerSelectAll.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);
		}
//		System.out.println(req.getAttribute("saAttribute"));
	}

}

	
	

