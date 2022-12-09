package tw.com.tibame.order.dao;

import java.util.List;

import tw.com.tibame.order.vo.OrderDetailVO;

public interface OrderDetailDAO {
	List<OrderDetailVO> getAll();
	
	public OrderDetailVO getPrimaryKey(Integer itemNo);					
	public List<OrderDetailVO> getByProdOrderNo(Integer prodOrderNo);	// 會員中心:查詢單筆訂單
	public boolean insert(OrderDetailVO orderDetailVO);					
	public boolean update(OrderDetailVO orderDetailVO);
	public boolean delete(Integer itemNo);
}
