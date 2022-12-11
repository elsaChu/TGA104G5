package tw.com.tibame.order.dao;

import java.util.List;

import tw.com.tibame.order.vo.ShoppingCartVO;

public interface ShoppingCartDAO {
	List<ShoppingCartVO> getAll();
	
	public ShoppingCartVO getPrimaryKey(Integer shoppingCartNo);
	public void insert(ShoppingCartVO shoppingCartVO);
	public void update(ShoppingCartVO shoppingCartVO);
	public boolean delete(Integer shoppingCartNo);
}
