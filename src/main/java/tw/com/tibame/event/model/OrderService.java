package tw.com.tibame.event.model;

import java.util.List;

import tw.com.tibame.staff.model.StaffVO;

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
	};
}
