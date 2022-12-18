package tw.com.tibame.product.service;

import java.util.List;

import tw.com.tibame.product.vo.ViewProductVO;

public interface ViewProductService {

	List<ViewProductVO> findAll();

	List<ViewProductVO> findProductByEventType(String eventType, boolean isPOn);
	
	List<ViewProductVO> findProductLaunch(boolean isPOn);

	ViewProductVO findProductByPrimaryKey(Integer prodNo);

}