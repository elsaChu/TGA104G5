package tw.com.tibame.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tibame.product.dao.ViewProductDAO;
import tw.com.tibame.product.vo.ProductImage;
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
	public List<ViewProductVO> findProductByEventType(String eventType) {
		if(eventType != null && !eventType.trim().isEmpty()) {
			return viewProductDAO.findByEventType(eventType);
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
	

	@Override
	public List<ProductImage> findAllPic() {
		return viewProductDAO.findAllPic();
	}

	@Override
	public ProductImage findPicByProdIMGID(Integer prodIMGID) {
		if (prodIMGID != null && prodIMGID > 0) {
			return viewProductDAO.findPicByProdIMGID(prodIMGID);
		}
		return null;
	}

	@Override
	public ProductImage findMainPic(Integer prodNo) {
		if (prodNo != null && prodNo > 0) {
			return viewProductDAO.findMainPic(prodNo);
		}
		return null;
	}

	@Override
	public ProductImage update(ProductImage productImage) {
		Integer prodNo = productImage.getProdNo();
		if(prodNo != null) {
			return viewProductDAO.update(productImage);
		}
		return null;
	}
	
	@Override
	public List<Integer> findProdImageIdByProdNo(Integer prodNo) {
		if(prodNo != null && prodNo > 0) {
			return viewProductDAO.findProdImageIdByProdNo(prodNo);
		}
		return null;
	}
	
	@Override
	public List<String> findAllEventType(){
		return viewProductDAO.findAllEventType();
	}

}
