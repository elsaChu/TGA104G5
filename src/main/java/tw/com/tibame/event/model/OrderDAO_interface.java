package tw.com.tibame.event.model;

import java.sql.Timestamp;
import java.util.List;


public interface OrderDAO_interface {
	public int insert(OrderVO vo,List<SoldTicketsVO> solList);
	public int update(OrderVO vo,List<SoldTicketsVO> solList);
	public int updateOrder(OrderVO vo);
	public OrderVO queryByOrderId(int OrderId);
	public OrderVO queryByOrderIdAndMember(int OrderId,int memberId);
	public List<OrderVO> selectByEventNumber(int eventNumber);
	public List<OrderVO> selectByDate();
	public List<OrderVO> selectByOrderType();
	public List<OrderVO> selectByNumber(int number);
	
	public int insert(OrderVO orderVO);
	public int update(OrderVO orderVO);
	public List<OrderVO> selectByEventNumber(Integer eventNumber); // 這個廠商的這個活動有幾筆訂單
	
	public List<OrderVO> selectByOrderDate(Timestamp orderDate); // 用訂單日期篩選
	public List<OrderVO> selectByOrderType(String orderType); // 用訂單狀態篩選
	public List<OrderVO> selectByNumber(Integer number); // 用會員編號篩選
	public List<OrderVO> searchByOrderID(Integer orderID); // 用訂單編號篩選
	
	public List<OrderEventVO> findByNumber(); //票券訂單
	public List<EventVO> findByOrganizerNumber(); //活動列表(這個廠商有什麼活動)
	
	
}
