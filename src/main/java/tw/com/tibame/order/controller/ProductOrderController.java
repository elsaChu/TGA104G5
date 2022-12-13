package tw.com.tibame.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.tibame.order.service.OrderDetailService;
import tw.com.tibame.order.service.ProductOrderService;
import tw.com.tibame.order.vo.OrderDetailVO;
import tw.com.tibame.order.vo.ProductOrderVO;

@RestController
@RequestMapping("member/product")
public class ProductOrderController {
	@Autowired
	private ProductOrderService productOrderService;
	@Autowired
	private OrderDetailService orderDetailService;
	
    @GetMapping("orderlist")
	public List<ProductOrderVO> memberOrder() {
//    	Integer number =  (Integer) request.getSession().getAttribute("number");
		Integer number = 5;  // 先寫死之後要串會員登入唷!!					// 要去看會員功能寫甚麼字串唷!!
		List<ProductOrderVO> list = productOrderService.getByNumberOrder(number);
    	return list;
	}
    
    @GetMapping("orderdetail")
    public List<OrderDetailVO> memberOrderDetail() {
//    	Integer prodOrderNo =  (Integer) request.getSession().getAttribute("prodOrderNo");
    	Integer prodOrderNo = 2; // 2是先寫死的唷!!
    	List<OrderDetailVO> list = orderDetailService.getByProdOrderNo(prodOrderNo);
    	return list;
	}
    
//    @PostMapping("order")
//    public ProductOrderVO addProdOrder() {
//    	ProductOrderVO productOrderVO = 
//    	return productOrderService.addOrder(productOrderVO);
//	}
    
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
