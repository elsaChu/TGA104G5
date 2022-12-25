package tw.com.tibame.product.vo;

import java.util.List;

import tw.com.tibame.order.vo.OrderDetailVO;
import tw.com.tibame.order.vo.ProductOrderVO;

public class OrderWrapper {
	private ProductOrderVO productOrderVO;
	
	private List<OrderDetailVO> orderDetailList;

	public ProductOrderVO getProductOrderVO() {
		return productOrderVO;
	}

	public void setProductOrderVO(ProductOrderVO productOrderVO) {
		this.productOrderVO = productOrderVO;
	}

	public List<OrderDetailVO> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetailVO> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	
	
	
}
