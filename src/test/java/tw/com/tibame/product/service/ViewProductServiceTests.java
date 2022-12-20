package tw.com.tibame.product.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.tibame.product.vo.ViewProductVO;

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
		ViewProductVO vo = new ViewProductVO();
		vo.setEventType("展覽");
		vo.setIsPOn(true);
		System.out.println(service.findProductByEventType(vo));
	}
	
	
	
}
