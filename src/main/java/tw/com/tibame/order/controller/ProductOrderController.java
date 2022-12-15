package tw.com.tibame.order.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.tibame.order.service.OrderDetailService;
import tw.com.tibame.order.service.ProductOrderService;
import tw.com.tibame.order.vo.OrderDetailVO;
import tw.com.tibame.order.vo.ProductOrderVO;
import tw.com.tibame.order.vo.ViewOrderDetailVO;

@RestController
@RequestMapping("order")
public class ProductOrderController {
	@Autowired
	private ProductOrderService productOrderService;
	@Autowired
	private OrderDetailService orderDetailService;
	
//    @GetMapping("orderlist") // 這是對的唷可以從session拿資料!
//	public List<ProductOrderVO> memberOrder(HttpSession session) {
//    	Integer number =  (Integer) session.getAttribute("number");
//		List<ProductOrderVO> list = productOrderService.getByNumberOrder(number);
//    	return list;
//	}
    
    @GetMapping("orderlist") 
	public List<ProductOrderVO> memberOrder(HttpSession session) {
		List<ProductOrderVO> list = productOrderService.getByNumberOrder(2); // 這是先寫死測試用的唷!
    	return list;
	}
    
    @GetMapping("orderdetail") // 跳轉訂單時需要檢查會員編號
    public List<ViewOrderDetailVO> memberOrderDetail(@RequestParam Integer prodOrderNo) {
    	List<ViewOrderDetailVO> list = orderDetailService.findByProdOrderNo(prodOrderNo);
    	return list;
	}
    
    @PostMapping("addProdOrder")
    public ProductOrderVO addProdOrder() {
    	ProductOrderVO productOrderVO = new ProductOrderVO();
    	productOrderVO.setNumber(5);			// 這些是先寫死的唷要從表單得到資料唷!
		productOrderVO.setAmountPrice(1000);
		productOrderVO.setProdTotal(1);
		productOrderVO.setReceiverName("達子1");
		productOrderVO.setReceiverTel("8888888888");
		productOrderVO.setShippingAdd("ppp");
		
    	return productOrderService.addOrder(productOrderVO);
	}
    
    @PutMapping("updateReceiverInfo")
    public ProductOrderVO updateReceiverInfo() {
//    	Integer prodOrderNo =  (Integer) request.getSession().getAttribute("prodOrderNo");
    	Integer prodOrderNo = 2;		// 訂單編號是先寫死的唷!!
    	String receiverName = "測試1";	// 這些是先寫死的唷要從表單得到資料唷!
    	String receiverTel = "測試1";
    	String shippingAdd = "測試1";
    	ProductOrderVO productOrderVO = productOrderService.updateReceiverInfo(prodOrderNo, receiverName, receiverTel, shippingAdd);
		return productOrderVO;
		
	}
    
    @PutMapping("comment")
    public OrderDetailVO updateComment() {
//    	Integer itemNo =  (Integer) request.getSession().getAttribute("itemNo");
    	Integer itemNo = 12;       // 這些是先寫死的唷要從表單得到資料唷!
    	Float commentRanking = 4F;
    	String commentContent = "いいね！";
    	
    	OrderDetailVO orderDetailVO = orderDetailService.updateComment(itemNo, commentRanking, commentContent);
    	return orderDetailVO;
	}
    
    @PutMapping("return")
    public OrderDetailVO updateReturn() {
    	Integer itemNo = 11;		// 這些是先寫死的唷要從表單得到資料唷!
    	String returnReason = "寄錯了";
    	
    	OrderDetailVO orderDetailVO = orderDetailService.updateReturn(itemNo, returnReason);
    	return orderDetailVO;
	}
	
		
	

}
