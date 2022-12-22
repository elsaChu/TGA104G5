package tw.com.tibame.order.service;

import java.util.List;

import tw.com.tibame.order.vo.ShoppingCartVO;
import tw.com.tibame.order.vo.ShowShoppingCartVO;

public interface ShoppingCartService {

	List<ShowShoppingCartVO> getAll();

	ShowShoppingCartVO getByPrimaryKey(Integer shoppingCartNo);
	
	List<ShowShoppingCartVO> getByMemberNumber(Integer number);

	ShoppingCartVO insert(ShoppingCartVO shoppingCartVO);

	ShoppingCartVO update(ShoppingCartVO shoppingCartVO);
	
	ShoppingCartVO updateQty(Integer shoppingCartNo, Integer shoppingQty);

	boolean delete(Integer shoppingCartNo);

}