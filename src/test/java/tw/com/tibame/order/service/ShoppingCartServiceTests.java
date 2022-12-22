package tw.com.tibame.order.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.tibame.order.service.impl.ShoppingCartServiceImpl;
import tw.com.tibame.order.vo.ShoppingCartVO;
import tw.com.tibame.order.vo.ShowShoppingCartVO;

@SpringBootTest
public class ShoppingCartServiceTests {
	@Autowired
	private ShoppingCartServiceImpl service;
	
	@Test
	public void testGetAll() {
		System.out.println(service.getAll());
	}
	
	@Test
	public void testGetByPrimaryKey() {
		System.out.println(service.getByPrimaryKey(2));
	}
	
	@Test
	public void testInsert() {
		ShowShoppingCartVO shoppingCartVO = new ShowShoppingCartVO();
		shoppingCartVO.setNumber(3);
		shoppingCartVO.setProdNo(14);
		shoppingCartVO.setShoppingQty(5);
		System.out.println(service.insert(shoppingCartVO));
	}
	
	@Test
	public void testUpdate() {
		ShowShoppingCartVO shoppingCartVO = new ShowShoppingCartVO();
		shoppingCartVO.setShoppingCartNo(9);
		shoppingCartVO.setNumber(4);
		shoppingCartVO.setProdNo(14);
		shoppingCartVO.setShoppingQty(20);
		System.out.println(service.update(shoppingCartVO));
	}
	
	@Test
	public void testDelete() {
		System.out.println(service.delete(10));
	}
}
