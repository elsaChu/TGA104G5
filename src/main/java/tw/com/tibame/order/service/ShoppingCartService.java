package tw.com.tibame.order.service;

import java.util.List;

import tw.com.tibame.order.vo.ShoppingCartVO;

public interface ShoppingCartService {

	List<ShoppingCartVO> getAll();

	ShoppingCartVO getByPrimaryKey(Integer shoppingCartNo);
	
	List<ShoppingCartVO> getByMemberNumber(Integer number);

	ShoppingCartVO insert(ShoppingCartVO shoppingCartVO);

	ShoppingCartVO update(ShoppingCartVO shoppingCartVO);
	
	ShoppingCartVO updateQty(Integer shoppingCartNo, Integer shoppingQty);

	boolean delete(Integer shoppingCartNo);

}