package tw.com.tibame.order.dao;

import java.util.List;

import tw.com.tibame.order.vo.ShowShoppingCartVO;

public interface ShoppingCartDAO {
	List<ShowShoppingCartVO> getAll();

	public ShowShoppingCartVO getByPrimaryKey(Integer shoppingCartNo);

	public List<ShowShoppingCartVO> getByMemberNumber(Integer number);

	public ShowShoppingCartVO insert(ShowShoppingCartVO shoppingCartVO);

	public ShowShoppingCartVO update(ShowShoppingCartVO shoppingCartVO);

	public boolean delete(Integer shoppingCartNo);

}
