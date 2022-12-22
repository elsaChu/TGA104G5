package tw.com.tibame.order.dao;

import java.util.List;

import tw.com.tibame.order.vo.ShoppingCartVO;
import tw.com.tibame.order.vo.ShowShoppingCartVO;

public interface ShoppingCartDAO {
	List<ShowShoppingCartVO> getAll();

	public ShowShoppingCartVO getByShoppingCartNo(Integer shoppingCartNo);
	
	public ShoppingCartVO getByPrimaryKey(Integer shoppingCartNo);

	public List<ShowShoppingCartVO> getByMemberNumber(Integer number);

	public ShoppingCartVO insert(ShoppingCartVO shoppingCartVO);

	public ShoppingCartVO update(ShoppingCartVO shoppingCartVO);

	public boolean delete(Integer shoppingCartNo);

}
