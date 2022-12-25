package tw.com.tibame.product.dao;

import java.util.List;

import tw.com.tibame.product.vo.ProductImage;
import tw.com.tibame.product.vo.ViewProductVO;

public interface ViewProductDAO {
	List<ViewProductVO> findAll();
	
	public List<ViewProductVO> findAllEventType();

	public List<ViewProductVO> findByEventType(ViewProductVO vo);

	public List<ViewProductVO> findProductLaunch(boolean isPOn);

	public ViewProductVO findByPrimaryKey(Integer prodNo);

	List<ProductImage> findAllPic();

	public ProductImage findPicByProdIMGID(Integer prodIMGID);

	public ProductImage findMainPic(Integer prodNo);
	
	public List<Integer> findProdImageIdByProdNo(Integer prodNo);

	public ProductImage update(ProductImage productImage);
	
	public Integer findStock(ViewProductVO viewProductVO);
	
	
}
