package tw.com.tibame.event.model;

import java.sql.Timestamp;
import java.util.List;



public class OrderService {
	private OrderDAO_interface dao;

	public OrderService() {
		dao = new OrderJDBCDAO();
	}
	public List<OrderEventVO> findByNumber() {
		return dao.findByNumber();
	}
	
	public List<EventVO> organizerNumber(){
		return dao.findByOrganizerNumber();
	}
	
	public List<OrderVO> selectByEventNumber(Integer eventNumber){
		return dao.selectByEventNumber(eventNumber);
	}
		
	public List<OrderVO> selectByOrderDate(Timestamp orderDate){ // 用訂單日期篩選
		return dao.selectByOrderDate(orderDate);
	}
	
	public List<OrderVO> selectByOrderType(String orderType){ // 用訂單狀態篩選
		return dao.selectByOrderType(orderType);
	}
	
	public List<OrderVO> selectByNumber(Integer number){ // 用會員編號篩選
		return dao.selectByNumber(number);
	}
	
	public List<OrderVO> searchByOrderID(Integer orderID){ // 用訂單編號篩選
		return dao.searchByOrderID(orderID);
		
	}
}

