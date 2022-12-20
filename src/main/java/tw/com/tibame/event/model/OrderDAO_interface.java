package tw.com.tibame.event.model;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDAO_interface {
	
	public int insert(OrderVO orderVO);
	public int update(OrderVO orderVO);
	public List<OrderVO> selectByEventNumber(Integer eventNumber); //(這個廠商的這個活動有幾筆訂單)
	public List<OrderVO> selectByOrderDate(Timestamp orderDate);
	public List<OrderVO> selectByOrderType(String orderType);
	public List<OrderVO> selectByNumber(Integer number);
	public List<OrderEventVO> findByNumber(); //票券訂單
	public List<EventVO> findByOrganizerNumber(); //活動列表(這個廠商有什麼活動)
	
}
