package tw.com.tibame.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.tibame.order.service.ShoppingCartService;
import tw.com.tibame.order.vo.ShoppingCartVO;

/*
 * 結帳之後要刪除購物車內容
 * 
 */


@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("selectAll")
	public List<ShoppingCartVO> selectAll() {
		return shoppingCartService.getAll();
	}
	
	@GetMapping("memberCart")
	public List<ShoppingCartVO> getByMemberNumber(@RequestParam Integer number) {
		return shoppingCartService.getByMemberNumber(number);
	}
	
	@PostMapping("addToCart")
	public ShoppingCartVO addToCart(@RequestBody Integer number, Integer prodNo, Integer shoppingQty) {
		ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
//		Integer number =  (Integer) request.getSession().getAttribute("number");
//		Integer number = 5;  // 這些是先寫死的唷!
//		Integer prodNo = 9;
//		Integer shoppingQty = 4;
		
//		shoppingCartVO.setNumber(3);
//		shoppingCartVO.setProdNo(14);
//		shoppingCartVO.setShoppingQty(5);
//		
//			
		return shoppingCartService.insert(shoppingCartVO);
//		}
//		return null;
	}
	
	@PutMapping("updateCart")
	public ShoppingCartVO updateQty() {
//		Integer shoppingCartNo =  (Integer) request.getSession().getAttribute("shoppingCartNo");
		Integer shoppingCartNo = 2;		// 購物車編號是先寫死的唷!!
		Integer shoppingQty = 100;		// 這些是先寫死的唷要從表單得到資料唷!
    	
		ShoppingCartVO shoppingCartVO = shoppingCartService.updateQty(shoppingCartNo, shoppingQty);
		return shoppingCartVO;
	}
	
	@GetMapping("removeFromCart")
	public boolean removeFromCart(Integer shoppingCartNo) {
		if(shoppingCartNo != null) {
			shoppingCartService.delete(shoppingCartNo);
			return true;
		}
		return false;
	}
	
}
