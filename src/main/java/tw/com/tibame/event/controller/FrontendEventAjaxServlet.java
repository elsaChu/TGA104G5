package tw.com.tibame.event.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import tw.com.tibame.event.model.EventService;
import tw.com.tibame.event.model.EventVO;
import tw.com.tibame.event.model.OrderService;
import tw.com.tibame.event.model.SeatService;
import tw.com.tibame.event.model.SeatVO;
import tw.com.tibame.event.model.TicketVO;

@WebServlet("/FrontendEventAjaxServlet")
public class FrontendEventAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    
	    String action = request.getParameter("action");
	    
	    HttpSession session = request.getSession();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Gson gson = new Gson();
        OrderService orderService = new OrderService();
        
        Map<String,Object> result = new HashMap<>();
        result.put("success", false);
        
        if(action != null && ! action.equals("")) {
            String eventNumberStr = "";
            if("selectAllEvent".equals(action)) {
                EventService eventService = new EventService();
                List<Map<String,Object>> eventList = eventService.findAllForDebug();
                result.put("eventList", eventList);
                result.put("success", true);
                
            }else if("getEventTickets".equals(action)) {
                eventNumberStr = request.getParameter("eventNumber");
                if(eventNumberStr != null && ! eventNumberStr.equals("")) {
                    List<TicketVO> voList = orderService.queryTicketByEventNumber(Integer.parseInt(eventNumberStr));
                    result.put("voList", voList);
                    result.put("success", true);
                }
            }else if("confirmTicket".equals(action)) {
                
                //這邊後續應該會有個是否已登入會員的檢查
                //TODO
                
                eventNumberStr = request.getParameter("eventNumber");
                int eventNumber = Integer.parseInt(eventNumberStr);
                String ticketSelectStr = request.getParameter("ticketSelect");
                System.out.println(String.format("ticketSelectStr:%s", ticketSelectStr));
                List<Map<String,Object>> ticketSelect = (List<Map<String,Object>>)gson.fromJson(ticketSelectStr, List.class);
                System.out.println(String.format("selectTicket:%s", ticketSelect));
                
                //檢查各票種剩餘數量
                result = orderService.checkTicket(eventNumber, ticketSelect);
                
                if("true".equals(String.valueOf(result.get("success")))) {
                    //檢核完成，根據活動設定，決定是否要進入劃位或資料輸入頁面
                    EventVO eventvo = orderService.queryEventByEventNumber(eventNumber);
                    
                    //計算總價
                    int totalPrice = 0;
                    for(Map<String,Object> t : ticketSelect) {
                        Integer price = Integer.parseInt(String.valueOf(t.get("price")));
                        Integer count = Integer.parseInt(String.valueOf(t.get("val")));
                        
                        totalPrice += ( price * count );
                    }
                    
                    //儲存資訊至 session
                    Map<String,Object> selectEventInfo = new HashMap<>();
                    if(session.getAttribute("selectEventInfo") != null) {
                        selectEventInfo = (Map<String,Object>)session.getAttribute("selectEventInfo");
                        
                    }
                    
                    //selectEventInfo.put("time", dft.format(LocalDateTime.now()) );
                    selectEventInfo.put("ticketSelect", ticketSelect);
                    selectEventInfo.put("eventNumber", eventvo.getEventNumber());
                    selectEventInfo.put("needSeat", eventvo.getNeedSeat());
                    selectEventInfo.put("totalPrice", totalPrice);
                    //這邊後續應該會有個會員物件要放入(或是更之前就要放)
                    //TODO
                    
                    //建立訂單
                    orderService.createOrUpdateOrder(selectEventInfo);
                    //移除selectSeat
                    selectEventInfo.remove("selectSeat");
                    session.setAttribute("selectEventInfo", selectEventInfo);
                    
                    result.put("selectEventInfo", selectEventInfo);
                    
                    
                }
            }else if("checkStep".equals(action)) {
                
                Map<String,Object> selectEventInfo = null;
                if(session.getAttribute("selectEventInfo") != null) {
                    selectEventInfo = (Map<String,Object>)session.getAttribute("selectEventInfo");
                    result.put("selectEventInfo", selectEventInfo);
                }
                
                //甚麼都沒有，步驟1
                if(selectEventInfo == null) {
                    System.out.println("selectEventInfo is null");
                    result.put("step", 1);
                }else {
                    
                    
                    if(selectEventInfo.get("eventNumber") == null) {
                        System.out.println("event is null");
                        //還沒正式選擇一個活動+票種
                        result.put("step", 1);
                    }else {
                        Integer eventNumber = Integer.parseInt(String.valueOf(selectEventInfo.get("eventNumber")));
                        EventVO eventvo = orderService.queryEventByEventNumber(eventNumber);
                        List<Map<String,Object>> ticketSelect = (List<Map<String,Object>>) selectEventInfo.get("ticketSelect");
                        if(ticketSelect == null) {
                            //還沒正式選擇票種
                            System.out.println("ticketSelect is null");
                            result.put("step", 1);
                        }else if(eventvo.getNeedSeat() && selectEventInfo.get("seatInfo") == null ) {
                            System.out.println("needSeeat , seatInfo is null");
                            //要劃位但沒有已選擇的劃位資訊
                            result.put("step", 2);
                        }else {
                            //停在最後一步
                            result.put("step", 3);
                        }
                    }
                    
                }
                
                result.put("success", true);
                
            }else if("getSeat".equals(action)) {
                
                Map<String,Object> selectEventInfo = (Map<String,Object>)session.getAttribute("selectEventInfo");
                if(selectEventInfo == null) {
                    result.put("success", false);
                    result.put("noSession", true);
                }
                
                result.put("selectEventInfo", selectEventInfo);
                
                eventNumberStr = request.getParameter("eventNumber");
                int eventNumber = Integer.parseInt(eventNumberStr);
                EventVO eventvo = orderService.queryEventByEventNumber(eventNumber);
                SeatService seatService = new SeatService();
                List<SeatVO> seatList = seatService.selectSeatByEventNumber(eventNumber);
                
                result.put("seatData", seatList);
                result.put("eventInfo", eventvo);
                result.put("success", true);
                
            }else if("confirmSeat".equals(action)) {
                
                Map<String,Object> selectEventInfo = (Map<String,Object>)session.getAttribute("selectEventInfo");
                if(selectEventInfo == null) {
                    result.put("success", false);
                    result.put("noSession", true);
                }
                
                eventNumberStr = request.getParameter("eventNumber");
                String selectSeatStr = request.getParameter("selectSeat");
                int eventNumber = Integer.parseInt(eventNumberStr);
                
                List<Integer> selectSeat = gson.fromJson(selectSeatStr, List.class);
                
                List<Integer> occupySeats = orderService.checkOccupySeat(eventNumber, selectSeat ,selectEventInfo);
                
                if(occupySeats.size() > 0) {
                    result.put("occupySeats", occupySeats);
                }else {
                    //儲存session
                    
                    selectEventInfo.put("time", dft.format(LocalDateTime.now()) );
                    selectEventInfo.put("selectSeat", selectSeat);
                    
                    orderService.updateSeat(selectEventInfo);
                    
                    session.setAttribute("selectEventInfo",selectEventInfo);
                    result.put("selectEventInfo", selectEventInfo);
                    result.put("success", true);
                }
                
                
            }else if("getSelectEventInfo".equals(action)) {
                
                Map<String,Object> selectEventInfo = (Map<String,Object>)session.getAttribute("selectEventInfo");
                if(selectEventInfo == null) {
                    result.put("success", false);
                    result.put("noSession", true);
                }
                result.put("selectEventInfo", selectEventInfo);
                result.put("success", true);
                
            }else if("confirmUserData".equals(action)) {
                
                Map<String,Object> selectEventInfo = (Map<String,Object>)session.getAttribute("selectEventInfo");
                if(selectEventInfo == null) {
                    result.put("success", false);
                    result.put("noSession", true);
                }
                Map<String,Object> userData = new HashMap<>();
                userData.put("inputName", request.getParameter("inputName"));
                userData.put("inputEmail", request.getParameter("inputEmail"));
                userData.put("inputPhone", request.getParameter("inputPhone"));
                userData.put("inputRocid", request.getParameter("inputRocid"));
                selectEventInfo.put("userData", userData);
                
                orderService.updateUserData(selectEventInfo);
                
                
                session.setAttribute("selectEventInfo", selectEventInfo);
                
                result.put("selectEventInfo", selectEventInfo);
                
                result.put("returnUrl", "http://127.0.0.1:8080/TGA104G5");
                
                //產生隨機繳費交換用密碼，用於金流平台交換時使用
//                String httpPath =request.getScheme() + "://" + request.getServerName() + request.getServerPort() + request.getContextPath();
//                System.out.println(httpPath);
//                String callbackUrl = String.format(httpPath+"/FrontendEventOrderProcessServlet?return=%s_tickit",
//                        String.valueOf(selectEventInfo.get("orderId")));
                String callbackUrl = String.format("http://127.0.0.1:8080/TGA104G5/FrontendEventOrderProcessServlet?return=%s_tickit",
                        String.valueOf(selectEventInfo.get("orderId")));
                result.put("callbackUrl", callbackUrl);
                
                
                result.put("success", true);
            }else if("confirmUserDataForFinish".equals(action)) {
                
                String orderIdStr = request.getParameter("orderId");
                
                Map<String,Object> userData = new HashMap<>();
                userData.put("inputName", request.getParameter("inputName"));
                userData.put("inputEmail", request.getParameter("inputEmail"));
                userData.put("inputPhone", request.getParameter("inputPhone"));
                userData.put("inputRocid", request.getParameter("inputRocid"));
                
                orderService.updateUserDataForFinish(Integer.parseInt(orderIdStr)  ,userData);
                
                result.put("success", true);
                
                result.put("userData", userData);
                
            }else if("cancelOrder".equals(action)) {
                String orderIdStr = request.getParameter("orderId");
                orderService.changeOrderType(Integer.parseInt(orderIdStr), "已取消");
                
                result.put("success", true);
            }
            
        }
	    
	    
	    PrintWriter out = response.getWriter();
        out.print(gson.toJson(result));
        out.flush();
	}

}
