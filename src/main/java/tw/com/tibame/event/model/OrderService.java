package tw.com.tibame.event.model;

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
}
