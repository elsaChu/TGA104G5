package tw.com.tibame.product.dao;

import java.util.List;

import tw.com.tibame.product.vo.FilterProducts;
import tw.com.tibame.product.vo.ProductImage;
import tw.com.tibame.product.vo.ViewProductVO;

public interface ViewProductDAO {
	List<ViewProductVO> findAll();
	
	List<String> findAllEventType();

	List<FilterProducts> findByEventClassName(String eventClassName);

	List<ViewProductVO> findProductLaunch(boolean isPOn);

	ViewProductVO findByPrimaryKey(Integer prodNo);

	List<ProductImage> findAllPic();

	ProductImage findPicByProdIMGID(Integer prodIMGID);

	ProductImage findMainPic(Integer prodNo);
	
	List<Integer> findProdImageIdByProdNo(Integer prodNo);

	ProductImage update(ProductImage productImage);
	
	Integer findStock(ViewProductVO viewProductVO);
	
	
}
