package tw.com.tibame.order.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.tibame.order.service.impl.ProductOrderServiceImpl;
import tw.com.tibame.order.vo.OrderDetailVO;
import tw.com.tibame.order.vo.ProductOrderVO;

@SpringBootTest
public class ProductOrderServiceTests {
	@Autowired
	private ProductOrderServiceImpl service;
	
	@Test
	public void testGetByNumberOrder() {
		System.out.println(service.getByNumberOrder(2));
	}
	
	@Test
	public void testGetOneOrder() {
		System.out.println(service.getOneOrder(1));
	}
	
	@Test
	public void testAddOrder() {
		ProductOrderVO productOrderVO = new ProductOrderVO();
		productOrderVO.setNumber(1);
		productOrderVO.setAmountPrice(1000);
		productOrderVO.setProdTotal(1);
		productOrderVO.setReceiverName("達子1");
		productOrderVO.setReceiverTel("8888888888");
		productOrderVO.setShippingAdd("ppp");
		System.out.println(service.addOrder(productOrderVO));
	}
	
	@Test
	public void testUpdateReceiverInfo() {
		System.out.println(service.updateReceiverInfo(4, "測試改名", "改電話", "hk4g42"));
	}
	
	
}
