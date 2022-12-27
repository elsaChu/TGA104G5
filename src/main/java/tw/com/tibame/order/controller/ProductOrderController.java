package tw.com.tibame.order.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.tibame.member.model.MemberVO;
import tw.com.tibame.order.service.OrderDetailService;
import tw.com.tibame.order.service.ProductOrderService;
import tw.com.tibame.order.service.ShoppingCartService;
import tw.com.tibame.order.vo.OrderDetailVO;
import tw.com.tibame.order.vo.ProductOrderVO;
import tw.com.tibame.order.vo.ViewOrderDetailVO;
import tw.com.tibame.order.vo.ViewProductOrderVO;
import tw.com.tibame.product.service.ProductService;
import tw.com.tibame.product.vo.OrderWrapper;
import tw.com.tibame.product.vo.ProductVO;

@RestController
@RequestMapping("order")
public class ProductOrderController {
	@Autowired
	private ProductOrderService productOrderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ProductService productService;
	
    @GetMapping("orderlist") // 這是對的唷可以從session拿資料!
	public List<ProductOrderVO> memberOrder(HttpSession session) {
    	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
    	Integer number =  memberVO.getNumber();
		List<ProductOrderVO> list = productOrderService.getByNumberOrder(number);
    	return list;
	}
    
//    @GetMapping("orderlist") 
//	public List<ProductOrderVO> memberOrder(HttpSession session) {
//		List<ProductOrderVO> list = productOrderService.getByNumberOrder(2); // 這是先寫死測試用的唷!
//    	return list;
//	}
    
    @GetMapping("orderdetail") // 跳轉訂單時需要檢查會員編號，避免看到別的會員訂單
    public List<ViewOrderDetailVO> memberOrderDetail(@RequestParam Integer prodOrderNo) {
    	List<ViewOrderDetailVO> list = orderDetailService.findByProdOrderNo(prodOrderNo);
    	return list;
	}
    
    @GetMapping("info") //不知道是不是這樣寫
    public ViewProductOrderVO findAnOrder(@RequestParam Integer prodOrderNo) {
    	ViewProductOrderVO viewProductOrderVO = productOrderService.findAnOrder(prodOrderNo);
		return viewProductOrderVO;
    }
    
    
    @PostMapping("addProdOrder") 
    public boolean addProdOrder(HttpSession session, @RequestBody OrderWrapper orderWrapper) {
    	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
    	Integer number = memberVO.getNumber();
    	
    	orderWrapper.getProductOrderVO().setNumber(number);
    	// 新增訂單
    	if(orderWrapper.getProductOrderVO() != null) {
    		ProductOrderVO newOrder = productOrderService.addOrder(orderWrapper.getProductOrderVO());
    		
    	// 新增訂單明細
	    	for(OrderDetailVO orderDetailVO : orderWrapper.getOrderDetailList()) {
	    		orderDetailVO.setProdOrderNo(newOrder.getProdOrderNo());
	    		OrderDetailVO newOrderDetail = orderDetailService.addDetail(orderDetailVO);
	    		
	    // 更新商品庫存
	    // 新的庫存如果小於0怎麼辦?__? 如果放進購物車時就先扣庫存，這裡就沒這問題了 ^__^
	    		Integer prodNo = newOrderDetail.getProdNo();
	    		Integer prodQty = newOrderDetail.getProdQty();
	    		
	    		ProductVO productVO = productService.findByProdNo(prodNo); 
	    		Integer newStock = productVO.getProdStock() - prodQty;
	    		if(newStock >= 0) {
	    			productVO.setProdStock(newStock);
	    			productService.update(productVO);
	    		}
	    		
	    	}
	    	
	    // 清空會員購物車
	    	shoppingCartService.deleteAllByMemberNumber(newOrder.getNumber());
	    	return true;
    	}
    	return false;
	}
        
    @PostMapping("updateReceiverInfo")
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
