package tw.com.tibame.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.tibame.order.service.ShoppingCartService;
import tw.com.tibame.order.vo.ShoppingCartVO;

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@PostMapping("addToCart")
	public ShoppingCartVO addToCart() {
		ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
//		Integer number =  (Integer) request.getSession().getAttribute("number");
//		Integer number = 5;  // 這些是先寫死的唷!
//		Integer prodNo = 9;
//		Integer shoppingQty = 4;
		
		shoppingCartVO.setNumber(3);
		shoppingCartVO.setProdNo(14);
		shoppingCartVO.setShoppingQty(5);
		
		//同會員同商品再按加入購物車時數量需要合併怎麼寫??
//		if() {
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
	
	@DeleteMapping("removeFromCart")
	public boolean removeFromCart(Integer shoppingCartNo) {
		if(shoppingCartNo != null) {
			shoppingCartService.delete(shoppingCartNo);
			return true;
		}
		return false;
	}
	
}
