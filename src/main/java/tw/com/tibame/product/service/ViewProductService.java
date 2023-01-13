package tw.com.tibame.product.service;

import java.util.List;

import tw.com.tibame.product.vo.FilterProducts;
import tw.com.tibame.product.vo.ProductImage;
import tw.com.tibame.product.vo.ViewProductVO;

public interface ViewProductService {

	List<ViewProductVO> findAll();

	List<FilterProducts> findProductByEventClassName(String eventClassName);
	
	List<ViewProductVO> findProductLaunch(boolean isPOn);

	ViewProductVO findProductByPrimaryKey(Integer prodNo);
	
	List<ProductImage> findAllPic();
	
	ProductImage findPicByProdIMGID(Integer prodIMGID);
	
	ProductImage findMainPic(Integer prodNo);
	
	ProductImage update(ProductImage productImage);

	List<Integer> findProdImageIdByProdNo(Integer prodNo);
	
	List<String> findAllEventType();

}