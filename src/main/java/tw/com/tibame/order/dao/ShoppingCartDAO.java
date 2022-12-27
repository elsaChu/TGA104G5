package tw.com.tibame.order.dao;

import java.util.List;

import tw.com.tibame.order.vo.ShoppingCartVO;
import tw.com.tibame.order.vo.ShowShoppingCartVO;

public interface ShoppingCartDAO {
	List<ShowShoppingCartVO> getAll();

	ShowShoppingCartVO getByShoppingCartNo(Integer shoppingCartNo);
	
	ShoppingCartVO getByPrimaryKey(Integer shoppingCartNo);
	
	ShoppingCartVO getByMemberNoAndProdNo(Integer number, Integer prodNo);

	List<ShowShoppingCartVO> getByMemberNumber(Integer number);
	
	List<ShoppingCartVO> findByMemberNumber(Integer number);

	ShoppingCartVO insert(ShoppingCartVO shoppingCartVO);

	ShoppingCartVO update(ShoppingCartVO shoppingCartVO);

	boolean delete(Integer shoppingCartNo);

}
