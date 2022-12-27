package tw.com.tibame.order.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.tibame.order.service.impl.OrderDetailServiceImpl;
import tw.com.tibame.order.vo.OrderDetailVO;

@SpringBootTest
public class OrderDetailServiceTests {
	@Autowired
	private OrderDetailServiceImpl service;
	
	@Test
	public void testGetByProdOrderNo() {
		System.out.println(service.getByProdOrderNo(2));
	}
	
	@Test
	public void teatGetOneOrderDetail() {
		System.out.println(service.getByProdOrderNo(1));
	}
	
	@Test
	public void testUpdateComment() {
		System.out.println(service.updateComment(12, 3.5F, "ookk"));
	}
	
	@Test
	public void testUpdateReturn() {
		System.out.println(service.updateReturn(13, "有破洞"));
	}
	
	@Test
	public void testAddDetail() {
		OrderDetailVO vo = new OrderDetailVO();
		vo.setProdOrderNo(4);
		vo.setProdNo(8);
		vo.setProdQty(2);
		vo.setSubtotal(2160);
		
		System.out.println(service.addDetail(vo));
	}
	
}
