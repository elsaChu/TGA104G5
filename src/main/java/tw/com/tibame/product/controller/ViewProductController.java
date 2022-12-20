package tw.com.tibame.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.tibame.product.service.ViewProductService;
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
	
	@PostMapping("eventType")
	public List<ViewProductVO> findProductByEventType(@RequestBody ViewProductVO vo) {
		List<ViewProductVO> list = viewProductService.findProductByEventType(vo);
    	return list;
	}
	
	@GetMapping("detail")
	public ViewProductVO findAnProduct(@RequestParam Integer prodNo) {
		ViewProductVO viewProductVO = viewProductService.findProductByPrimaryKey(prodNo);
		return viewProductVO;
	}
	
	@GetMapping("all")
    public List<ViewProductVO> findAllProduct() {
    	List<ViewProductVO> list = viewProductService.findAll();
    	return list;
	}
}