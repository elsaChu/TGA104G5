package tw.com.tibame.order.service;

import java.util.List;

import tw.com.tibame.order.vo.ShowShoppingCartVO;

public interface ShoppingCartService {

	List<ShowShoppingCartVO> getAll();

	ShowShoppingCartVO getByPrimaryKey(Integer shoppingCartNo);
	
	List<ShowShoppingCartVO> getByMemberNumber(Integer number);

	ShowShoppingCartVO insert(ShowShoppingCartVO shoppingCartVO);

	ShowShoppingCartVO update(ShowShoppingCartVO shoppingCartVO);
	
	ShowShoppingCartVO updateQty(Integer shoppingCartNo, Integer shoppingQty);

	boolean delete(Integer shoppingCartNo);

}