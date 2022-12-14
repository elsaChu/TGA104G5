package tw.com.tibame.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.tibame.product.service.ViewProductService;
import tw.com.tibame.product.vo.FilterProducts;
import tw.com.tibame.product.vo.ProductImage;
import tw.com.tibame.product.vo.ViewProductVO;

@RestController
@RequestMapping("product")
public class ViewProductController {
	@Autowired
	private ViewProductService viewProductService;
	
	
	@GetMapping("launch")
    public List<ViewProductVO> productLaunch(@RequestParam boolean isPOn) {
    	List<ViewProductVO> list = viewProductService.findProductLaunch(isPOn);
    	return list;
	}
	
	@GetMapping("categories")
	public List<String> findAllEventType() {
		List<String> list = viewProductService.findAllEventType();
		return list;
	}
	
	@GetMapping("eventType")
	public List<FilterProducts> findProductByEventClassName(@RequestParam String eventClassName) {
		System.out.println(eventClassName);
		List<FilterProducts> list = viewProductService.findProductByEventClassName(eventClassName);
    	return list;
	}
	
	@GetMapping("detail")
	public ViewProductVO findAnProduct(@RequestParam Integer prodNo) {
		ViewProductVO viewProductVO = viewProductService.findProductByPrimaryKey(prodNo);
		return viewProductVO;
	}
	
	@GetMapping("all")
    public List<ProductImage> findAllPic() {
    	List<ProductImage> list = viewProductService.findAllPic();
    	return list;
	}
	
	@GetMapping("findPictureById")
	public byte[] findPicByProdIMGID(@RequestParam Integer prodIMGID) {
		ProductImage productImage = viewProductService.findPicByProdIMGID(prodIMGID);
		return productImage != null ? productImage.getProdIMG() : null;
	}

	@GetMapping("mainPic")
	public byte[] findMainImage(@RequestParam Integer prodNo) {
		ProductImage productImage = viewProductService.findMainPic(prodNo);
		return productImage != null ? productImage.getProdIMG() : null;
	}
	
	@GetMapping("findImageId")
	public List<Integer> findProdImageIdByProdNo(@RequestParam Integer prodNo){
		List<Integer> list = viewProductService.findProdImageIdByProdNo(prodNo);
		if(list != null) {
			return list;
		}
		return null;
	}
	
}
