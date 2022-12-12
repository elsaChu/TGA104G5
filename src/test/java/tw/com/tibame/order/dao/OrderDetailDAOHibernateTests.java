package tw.com.tibame.order.dao;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.tibame.order.vo.OrderDetailVO;

@SpringBootTest
public class OrderDetailDAOHibernateTests {
	@Autowired
	private OrderDetailDAO oderDetailDAO;

	@Test
	public void testGetAll() {
		List<OrderDetailVO> result = oderDetailDAO.getAll();
		System.out.println(result);
	}
	
	@Test 
	public void testGetPrimaryKey() {
		OrderDetailVO oderDetailVO = new OrderDetailVO();
		oderDetailVO = oderDetailDAO.getPrimaryKey(3);
		System.out.println(oderDetailVO);
	}
	
	@Test
	public void testGetByProdOrderNo() {
		List<OrderDetailVO> result = oderDetailDAO.getByProdOrderNo(2);
		System.out.println(result);
	}
	
	@Test
	public void testInsert() {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		orderDetailVO.setProdNo(4);
		orderDetailVO.setProdOrderNo(2);
		orderDetailVO.setProdQty(3);
		orderDetailVO.setSubtotal(1000);
		
		System.out.println(oderDetailDAO.insert(orderDetailVO));
	}
	
	@Test 
	public void testUpdate() {
		OrderDetailVO orderDetailVO = oderDetailDAO.getPrimaryKey(10);
		orderDetailVO.setCommentRanking(4.5F);
		orderDetailVO.setCommentContent("讚讚^__^");
		orderDetailVO.setCommentDate(new java.sql.Timestamp(new GregorianCalendar().getTimeInMillis()));
		System.out.println(oderDetailDAO.update(orderDetailVO));
	}
	
	
}
