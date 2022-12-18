package tw.com.tibame.product.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ViewProductServiceTests {
	@Autowired
	private ViewProductServiceImpl service;

	@Test
	public void testFindAll() {
		System.out.println(service.findAll());
	}
	
	@Test
	public void findProductLaunch() {
		System.out.println(service.findProductLaunch(true));
	}
	
	@Test
	public void testFindProductByEventType() {
		System.out.println(service.findProductByEventType("展覽", true));
	}
	
	
	
}
