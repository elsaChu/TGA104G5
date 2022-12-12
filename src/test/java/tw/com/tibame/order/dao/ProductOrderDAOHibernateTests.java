package tw.com.tibame.order.dao;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.tibame.order.vo.ProductOrderVO;

@SpringBootTest
public class ProductOrderDAOHibernateTests {
	@Autowired
	private ProductOrderDAO productOrderDAO;

	@Test
	public void testGetByNumber() {
		// 會員中心 - 查詢所有訂單
		List<ProductOrderVO> result = productOrderDAO.getByNumber(5);
		System.out.println(result);
	}	
	@Test 
	public void testGetPrimaryKey() {
		// 會員中心 - 查詢單筆訂單
		ProductOrderVO productOrderVO = new ProductOrderVO();
		productOrderVO = productOrderDAO.getPrimaryKey(3);
		System.out.println(productOrderVO);
	}	
	
	@Test
	public void testInsert() {
		// 新增訂單
		ProductOrderVO productOrderVO = new ProductOrderVO();
		productOrderVO.setNumber(2);
		productOrderVO.setAmountPrice(5000);
		productOrderVO.setProdTotal(1);
		productOrderVO.setReceiverName("測試測試");
		productOrderVO.setReceiverTel("0911111111");
		productOrderVO.setShippingAdd("ppp");
		productOrderVO.setDeliveryStatus("hh");
		productOrderVO.setProdOrderStatus("kk");
		productOrderVO.setPaymentDate(new java.sql.Timestamp(new GregorianCalendar().getTimeInMillis()));
		productOrderVO = productOrderDAO.insert(productOrderVO);
		System.out.println(productOrderVO);
	}	
	
	@Test
	public void testUpdate() {
		ProductOrderVO productOrderVO = new ProductOrderVO();
		productOrderVO = productOrderDAO.getPrimaryKey(4);
		productOrderVO.setReceiverName("更改收件人姓名");
		
		System.out.println(productOrderDAO.update(productOrderVO));
		
	}
	
	
}
