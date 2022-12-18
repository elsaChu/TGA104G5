package tw.com.tibame.order.dao;

import java.util.List;

import tw.com.tibame.order.vo.ShoppingCartVO;

public interface ShoppingCartDAO {
	List<ShoppingCartVO> getAll();
	
	public ShoppingCartVO getByPrimaryKey(Integer shoppingCartNo);
	public List<ShoppingCartVO> getByMemberNumber(Integer number);
	public ShoppingCartVO insert(ShoppingCartVO shoppingCartVO);
	public ShoppingCartVO update(ShoppingCartVO shoppingCartVO);
	public boolean delete(Integer shoppingCartNo);
	
}
