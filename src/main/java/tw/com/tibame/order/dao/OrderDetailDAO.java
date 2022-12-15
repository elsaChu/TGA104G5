package tw.com.tibame.order.dao;

import java.util.List;

import tw.com.tibame.order.vo.OrderDetailVO;

public interface OrderDetailDAO {
	List<OrderDetailVO> getAll();
	
	public OrderDetailVO getByPrimaryKey(Integer itemNo);					
	public List<OrderDetailVO> getByProdOrderNo(Integer prodOrderNo);	// 會員中心:查詢單筆訂單
	public OrderDetailVO insert(OrderDetailVO orderDetailVO);					
	public OrderDetailVO update(OrderDetailVO orderDetailVO);
}
