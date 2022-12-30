package tw.com.tibame.product.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.tibame.product.vo.ViewProductVO;

@SpringBootTest
public class ViewProductDAOHibernateTests {
	@Autowired
	private ViewProductDAO viewProductDAO;
	
	@Test
	public void testFindAllProduct() {
		List<ViewProductVO> result = viewProductDAO.findAll();
		System.out.println(result);
	}

//	@Test 
//	public void testFindByEventType() {
//		ViewProductVO vo = new ViewProductVO();
//		vo.setEventType("展覽");
//		vo.setIsPOn(true);
//		List<ViewProductVO> result = viewProductDAO.findByEventType(String eventType);
//		System.out.println(result);
//	}	
	
	@Test 
	public void testFindByPrimaryKey() {
		// 會員中心 - 查詢單筆訂單
		ViewProductVO viewProductVO = new ViewProductVO();
		viewProductVO = viewProductDAO.findByPrimaryKey(3);
		System.out.println(viewProductVO);
	}	
	
	@Test
	public void testFindProductLaunch() {
		List<ViewProductVO> result = viewProductDAO.findProductLaunch(true);
		System.out.println(result);
	}

	
	
}
