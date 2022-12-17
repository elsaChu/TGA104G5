package tw.com.tibame.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tibame.product.dao.ViewProductDAO;
import tw.com.tibame.product.vo.ViewProductVO;

@Service
@Transactional
public class ViewProductServiceImpl implements ViewProductService {
	
	@Autowired
	private ViewProductDAO viewProductDAO;

	public ViewProductServiceImpl(ViewProductDAO viewProductDAO) {
		super();
		this.viewProductDAO = viewProductDAO;
	}
	
	@Override
	public List<ViewProductVO> findAll() {
		return viewProductDAO.findAll();
	}
	
	@Override	// 已上架所有商品
	public List<ViewProductVO> findProductLaunch(boolean isPOn) {
		if(isPOn == true) {
			return viewProductDAO.findProductLaunch(isPOn);
		}
		return null;
	}
	
	
	@Override	// 依活動分類篩選已上架商品
	public List<ViewProductVO> findProductByEventType(String eventType, boolean isPOn) {
		if(eventType != null && !eventType.trim().isEmpty()) {
			if(isPOn == true) {
				return viewProductDAO.findByEventType(eventType, isPOn);
			}
		}
		return null;
	}
	
	
	@Override
	public ViewProductVO findProductByPrimaryKey(Integer prodNo) {
		if(prodNo != null && prodNo > 0) {
			return viewProductDAO.findByPrimaryKey(prodNo);
		}
		return null;
	}

}
