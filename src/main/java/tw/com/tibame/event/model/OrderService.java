package tw.com.tibame.event.model;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class OrderService {

	private OrderDAO_interface dao;
	QRCodeService qrTool = new QRCodeService();
	public OrderService() {
		dao = new OrderJDBCDAO();
	}
	
	public boolean updateTicketToUsed(int ticketNumber) {
		
		SoldTicketsJDBCDAO soldTicketDao = new SoldTicketsJDBCDAO();
		
	    SoldTicketsVO vo =  soldTicketDao.queryById(ticketNumber);
	    OrderVO order = dao.queryByOrderId(vo.getOrderID());
	    if(vo.isUse() || ! order.getOrderType().equals("已繳費")) {
	        return false;
	    }else {
	        vo.setUse(true);
	        soldTicketDao.updateUsed(ticketNumber, true);
	    }
	    
	    return true;
	}
	
	public Map<String,Object> checkTicket(int eventNumber ,List<Map<String,Object>> ticketSelectList){
	    Map<String,Object> rtn = new HashMap<>();
	    rtn.put("success", false);
	    TicketJDBCDAO ticketDao = new TicketJDBCDAO();
	    //檢查該票種是否已售完，或不符合票種販售標準
	    List<String> msgList = new ArrayList<>();
	    for(Map<String,Object> selectTicket : ticketSelectList) {
	        
	        String id = String.valueOf(selectTicket.get("id"));
	        String val = String.valueOf(selectTicket.get("val"));
	        
	        int ticketid = Integer.parseInt(id);
	        int selectVal = Integer.parseInt(val);
	        
	        TicketVO ticketVO = ticketDao.queryById(ticketid);
	        
	        if(ticketVO.getTicketMAX() > 0 && selectVal > ticketVO.getTicketMAX()) {
                msgList.add(String.format("票種:%s 單次購買上限為%d筆，請更換票種或調整購入筆數",ticketVO.getTicketName() ,ticketVO.getTicketMAX()));
            }else {
            	
            	SoldTicketsJDBCDAO soldTicketDao = new SoldTicketsJDBCDAO();
            	
                List<SoldTicketsVO> soldList = soldTicketDao.selectByEventAndTicket(eventNumber, ticketid);
                int soldCount = soldList.size();
                if(soldCount + Integer.parseInt(val) > ticketVO.getTicketQuantity()) {
                    int diff = ticketVO.getTicketQuantity() - soldCount;
                    msgList.add(String.format("票種:%s 剩餘票數不足(剩餘%d筆)，請更換票種或調整購入筆數",ticketVO.getTicketName() ,diff));
                }
            }
	        
	    }
	    if(msgList.size() == 0) {
	        rtn.put("success", true);
	    }else {
	        rtn.put("msgList", msgList);
	    }
	    
	    return rtn;
	}
	
	public Map<String,Object> updateSeatSetByEventNumber(int eventNumber , List<Integer> selectSeatList){
	    Map<String,Object> rtn = new HashMap<>();
        rtn.put("success", false);
        SeatJDBCDAO seatDao = new SeatJDBCDAO();
        //檢查座位重複
        //EventVO event = queryEventByEventNumber(eventNumber);
        List<SeatVO> curSeatList = seatDao.selectByEventNumber(eventNumber);
        
        List<Integer> occupySeatList = new ArrayList<>();
        
        for(SeatVO curVO : curSeatList) {
            boolean occupy = curVO.getSeatType();
            Integer seatSet = curVO.getSeatSet();
            if(selectSeatList.contains(seatSet) && occupy) {
                occupySeatList.add(curVO.getSeatNumber());
            }
        }
        
        
        
        return rtn;
	}
	
	public List<Integer> checkOccupySeat(int eventNumber , List<Integer> selectSeatList , Map<String,Object> selectEventInfo){
	    EventVO event = queryEventByEventNumber(eventNumber);
	    SeatJDBCDAO seatDao = new SeatJDBCDAO();
        List<SeatVO> curSeatList = seatDao.selectByEventNumber(eventNumber);
        
        OrderVO order = dao.queryByOrderId(Integer.parseInt(String.valueOf(selectEventInfo.get("orderId"))));
        
        SoldTicketsJDBCDAO soldTicketDao = new SoldTicketsJDBCDAO();
    	
        List<SoldTicketsVO> soldts = soldTicketDao.selectByOrderID(order.getOrderID());
        
        List<Integer> occupySeatList = new ArrayList<>();
        
        for(SeatVO curVO : curSeatList) {
            boolean occupy = curVO.getSeatType();
            Integer seatSet = curVO.getSeatSet();
            if(selectSeatList.contains(seatSet) && occupy) {
                
                boolean isSelfSelect = false;
                for(SoldTicketsVO so : soldts) {
                    //本來就選擇的座位不要納入檢查範圍
                    if(selectSeatList.contains(so.getSeatID())) {
                        isSelfSelect = true;
                        break;
                    }
                }
                if(!isSelfSelect) {
                    occupySeatList.add(curVO.getSeatNumber());
                }
                
            }
        }
        
        return occupySeatList;
	}
	
	public void createOrUpdateOrder (Map<String,Object> selectEventInfo) {
	    
	    if(selectEventInfo.get("orderId") != null) {
	        //int orderId = (Integer) selectEventInfo.get("orderId");
	        updateOrder(selectEventInfo);
	    }else {
	        createOrder(selectEventInfo);
	    }
	}
	
	public void createOrder(Map<String,Object> selectEventInfo) {
	    
	    OrderVO order = new OrderVO();
	    //EventVO eventvo = queryEventByEventNumber((Integer) selectEventInfo.get("eventNumber"));
	    order.setEventNumber((Integer) selectEventInfo.get("eventNumber"));
	    order.setNumber(1);
	    order.setOrderDate(Timestamp.valueOf(LocalDateTime.now()));
	    order.setOrderType("未繳費");
	    order.setTotal((Integer) selectEventInfo.get("totalPrice"));
	    List<Map<String , Object>> ticketSelect = (List<Map<String , Object>>)selectEventInfo.get("ticketSelect");
	    int totalTicket = 0;
	    for(Map<String , Object> t : ticketSelect) {
	        totalTicket += Integer.parseInt(String.valueOf(t.get("val")));
	    }
	    order.setTotalTicket(totalTicket);
	    order.setReasonMoney(0);
	    order.setpData("");
	    
	    TicketJDBCDAO ticketDao = new TicketJDBCDAO();
        
	    //票種
	    List<SoldTicketsVO> soldList = new ArrayList<>();
	    for(Map<String , Object> ts : ticketSelect) {
	        
	        int count = Integer.parseInt(String.valueOf(ts.get("val")));
	        int ticketID = Integer.parseInt(String.valueOf(ts.get("id")));
	        
	        
	        TicketVO ticket = ticketDao.queryById(ticketID);
	        
	        count = count * ticket.getTicketMIN();
	        for(int i = 0 ; i < count ; i ++) {
	            SoldTicketsVO vo = new SoldTicketsVO();
	            //vo.setOrderID(orderId);
	            //vo.setSeatID(null);
	            vo.setTicketID(Integer.parseInt(String.valueOf(ts.get("id"))));
	            vo.setUse(false);
	            vo.setOrderPrice(Integer.parseInt(String.valueOf(ts.get("price"))));
	            soldList.add(vo);
	        }
	        
	        
	        //ts.put("ticketID", ticketID);
	    }
	    int orderId = dao.insert(order,soldList);
	    
	    //輸入訂單編號，同個session在結帳前使用同一組訂單
	    selectEventInfo.put("orderId", orderId);
	    
	}
	
	public OrderVO queryOrderById(int orderId) {
	    return dao.queryByOrderId(orderId);
	}
	
	public void changeOrderType(int orderId,String orderType) {
	    
	    OrderVO order = dao.queryByOrderId(orderId);
	    order.setOrderType(orderType);
	    dao.updateOrder(order);
	}
	
	public String getOrgName(int organizerNumber) {
		EventJDBCDAO eventDao = new EventJDBCDAO();
	    return eventDao.getOrganizerName(organizerNumber);
	}
	
	
	public void updateOrder(Map<String,Object> selectEventInfo) {
	    int orderId = (Integer) selectEventInfo.get("orderId");
	    OrderVO order = dao.queryByOrderId(orderId);
	    
	    order.setOrderDate(Timestamp.valueOf(LocalDateTime.now()));
	    order.setOrderType("未繳費");
	    order.setTotal((Integer) selectEventInfo.get("totalPrice"));
	    
	    if(order != null) {
	        //更新票種
	    	SoldTicketsJDBCDAO soldTicketDao = new SoldTicketsJDBCDAO();
	    	
	        soldTicketDao.deleteByOrderId(orderId);
	        List<Map<String,Object>> ticketSelect = (List<Map<String,Object>>)selectEventInfo.get("ticketSelect");
        	
	        List<SoldTicketsVO> soldList = new ArrayList<>();
	        for(Map<String,Object> t : ticketSelect) {
	            int count = Integer.parseInt(String.valueOf(t.get("val")));
	            for(int i = 0 ; i < count ; i ++) {
	                SoldTicketsVO vo = new SoldTicketsVO();
	                vo.setOrderID(orderId);
	                vo.setTicketID(Integer.parseInt(String.valueOf(t.get("id"))));
	                vo.setUse(false);
	                vo.setOrderPrice(Integer.parseInt(String.valueOf(t.get("price"))));
	                //vo.setSeatID(Integer.parseInt(String.valueOf(t.get("seatID"))));
	                soldList.add(vo);
	                //int id = soldTicketDao.insert(vo);
	                
	            }
	        }
	        
	        dao.update(order, soldList);
	        
	    }
	    
	    
	    
	}
	
	public void updateSeat(Map<String,Object> selectEventInfo) {
	    
	    List<String> selectSeat = (List<String>)selectEventInfo.get("selectSeat");
	    
	    System.out.println(String.format("selectSeat:%s",selectSeat));
	    
        if(selectSeat != null && selectSeat.size() > 0) {
        	SoldTicketsJDBCDAO soldTicketDao = new SoldTicketsJDBCDAO();
        	SeatJDBCDAO seatDao = new SeatJDBCDAO();
            //要先把這張訂單的原始座位直接洗掉
            OrderVO order = dao.queryByOrderId(Integer.parseInt(String.valueOf(selectEventInfo.get("orderId"))));
            List<SoldTicketsVO> soldts = soldTicketDao.selectByOrderID(order.getOrderID());
            for(SoldTicketsVO so : soldts) {
                if(so.getSeatID() != null && so.getSeatID() != 0) {
                    
                    SeatVO seatvo =  seatDao.queryById(so.getSeatID());
                    if(seatvo != null) {
                        seatvo.setSeatType(false);
                        
                        System.out.println("set "+seatvo.getSeatID()+" to false");
                        
                        seatDao.updateSeat(seatvo);
                    }
                    
                }
                
            }
            
            //List<SoldTicketsVO> list = soldTicketDao.selectByOrderID(Integer.parseInt(String.valueOf(selectEventInfo.get("orderId"))));
            int eventNumber = order.getEventNumber();
            for(int i = 0 ; i < selectSeat.size() ; i ++) {
                Integer t = Integer.parseInt(selectSeat.get(i));
                SeatVO seatvo = seatDao.queryBySetAndEventNumber(t, eventNumber);
                SoldTicketsVO vo = soldts.get(i);
                vo.setSeatID(seatvo.getSeatID());
                soldTicketDao.update(vo);
                
                seatDao.updateBySeatSetAndEventNumver(t,eventNumber,true);
                
                System.out.println("set seatSet:"+t+" evet:"+eventNumber+" to true");
            }
        }
	}
	
	public void updateUserData(Map<String,Object> selectEventInfo) {
	    Map<String,Object> userData = (Map<String,Object>)selectEventInfo.get("userData");
	    
	    Gson gson = new Gson();
	    
	    int orderId = (Integer) selectEventInfo.get("orderId");
	    
	    OrderVO order = dao.queryByOrderId(orderId);
	    order.setpData(gson.toJson(userData));
	    dao.updateOrder(order);
	    SoldTicketsJDBCDAO soldTicketDao = new SoldTicketsJDBCDAO();
    	
	    //產生該訂單所有票種的QRCODE
	    List<SoldTicketsVO> soldList = soldTicketDao.selectByOrderID(orderId);
	    String url = "http://192.168.1.108:8080/TGA104G5/FrontendCheckTicketServlet?ticket=%s";
	    
	    for(SoldTicketsVO soldvo : soldList) {
	        
	        Map hMap = new HashMap<>();
	        hMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	        try {
	            String path = String.format("C:/tmp/qrCode_%s.png", soldvo.getTicketNumber());
	            qrTool.createQRCode(String.format(url, soldvo.getTicketNumber()), path, "UTF-8", hMap, 200,200);
	            File file = new File(path);
	            
	            byte[] qrBinary = Files.readAllBytes(Paths.get(path));
	            soldvo.setTicketQR(qrBinary);
	            soldTicketDao.updateQRData(soldvo.getTicketNumber(),qrBinary);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	    }
	    
	}
	
	public void updateUserDataForFinish(int orderId , Map<String,Object> userData) {
        
        Gson gson = new Gson();
        OrderVO order = dao.queryByOrderId(orderId);
        order.setpData(gson.toJson(userData));
        dao.updateOrder(order);
        
    }
	
	public List<Map<String,String>> getOrderTicketQRCode(int orderId){
	    
	    List<Map<String,String>> info = new ArrayList<>();
	    SoldTicketsJDBCDAO soldTicketDao = new SoldTicketsJDBCDAO();
    	
	    OrderVO order = queryOrderById(orderId);
        EventVO event = queryEventByEventNumber(order.getEventNumber());
	    List<SoldTicketsVO> solds = soldTicketDao.selectByOrderID(orderId);
	    TicketJDBCDAO ticketDao = new TicketJDBCDAO();
	    for(SoldTicketsVO vo : solds) {
	        if(vo.getTicketQR() != null) {
	            byte[] qrByte = vo.getTicketQR();
	            String base64 = Base64.getEncoder().encodeToString(qrByte);
	            TicketVO tvo = ticketDao.queryById(vo.getTicketID());
	            Map<String,String> soldData = new HashMap<>();
	            soldData.put("base64Data", base64);
	            soldData.put("ticketName", tvo.getTicketName());
	            soldData.put("eventName", event.getEventName());
	            info.add(soldData);
	        }
	    }
	    
	    return info;
	}
	
	
	public EventVO queryEventByEventNumber(int eventNumber) {
		EventJDBCDAO eventDao = new EventJDBCDAO();
	    return eventDao.selectByeventNumber(eventNumber);
	}
	
	public List<TicketVO> queryTicketByEventNumber(int eventNumber){
	    TicketJDBCDAO ticketDao = new TicketJDBCDAO();
	    List<TicketVO> voList = ticketDao.selectByeventNumber(eventNumber);
	    SoldTicketsJDBCDAO soldTicketDao = new SoldTicketsJDBCDAO();
	    for(TicketVO vo : voList) {
	        String ticketType = vo.getTicketType();
	        if( ! "已下架".equals(ticketType)) {
	            
	            //檢查是否已售完
	            List<SoldTicketsVO> soldTicketsVOs = soldTicketDao.selectByEventAndTicket(eventNumber, eventNumber);
	            if(soldTicketsVOs.size() >= vo.getTicketQuantity()) {
	                ticketType = "已售罊";
	            }
	            //檢查活動日期
	            //未開賣 販售中 已售罊 已結束
	            Timestamp startticket=vo.getTicketStartTime();
	            Timestamp endticket=vo.getTicketEndTime();
	            Timestamp toDay = new Timestamp(System.currentTimeMillis());
	            
	            if(startticket.compareTo(toDay) > 0) {
	                ticketType = "未開賣";
	            }else if(startticket.compareTo(toDay) <=0 && endticket.compareTo(toDay) > 0) {
	                ticketType = "販售中";
	            }else {
	                ticketType = "已結束";
	            }
	            
	            if( ! ticketType.equals(vo.getTicketType())) {
	                vo.setTicketType(ticketType);
	                ticketDao.updateTicket(vo);
	            }
	            
	        }
	        
	    }
	    
	    return voList;
	}
	

	
	

	
	
}
