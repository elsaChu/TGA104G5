package tw.com.tibame.order.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.tibame.order.vo.ShoppingCartVO;

@SpringBootTest
public class ShoppingCartDAOHibernateTests {
	@Autowired
	private ShoppingCartDAO shoppingCartDAO;
	
	@Test
	public void testGetAll() {
		System.out.println(shoppingCartDAO.getAll());
	}
	
	@Test
	public void testGetByPrimaryKey() {
		System.out.println(shoppingCartDAO.getByPrimaryKey(2));
	}
	
	@Test
	public void testInsert() {
		ShoppingCartVO vo = new ShoppingCartVO();
		vo.setNumber(1);
		vo.setProdNo(10);
		vo.setShoppingQty(2);
		System.out.println(shoppingCartDAO.insert(vo));
	}
	
	@Test
	public void testUpdate() {
		ShoppingCartVO vo = new ShoppingCartVO();
		vo.setShoppingCartNo(7);
		vo.setNumber(1);
		vo.setProdNo(10);
		vo.setShoppingQty(5);
		System.out.println(shoppingCartDAO.update(vo));
	}
	
	@Test
	public void testDelete() {
		System.out.println(shoppingCartDAO.delete(7));
	}
}
