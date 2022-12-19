package tw.com.tibame.event.model;

import java.util.List;


public interface OrderDAO_interface {
	public int insert(OrderVO vo,List<SoldTicketsVO> solList);
	public int update(OrderVO vo,List<SoldTicketsVO> solList);
	public int updateOrder(OrderVO vo);
	public OrderVO queryByOrderId(int OrderId);
	public List<OrderVO> selectByEventNumber(int eventNumber);
	public List<OrderVO> selectByDate();
	public List<OrderVO> selectByOrderType();
	public List<OrderVO> selectByNumber(int number);
	
}
