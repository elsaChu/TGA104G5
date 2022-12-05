package com.product.model;

import java.util.List;

public class ProductOrderService {
	private ProductOrderDAO dao;

	public ProductOrderService() {
		dao = new ProductOrderDAOJndi();
	}
	
	// 會員中心 - 查詢該會員所有訂單
	public List<ProductOrderVO> getByNumberOrder(Integer number){
		return dao.getByNumber(number);
	}
	
	// 會員中心 - 查詢單筆訂單
	public ProductOrderVO getOneOrder(Integer prodOrderNo) {
		return dao.getPrimaryKey(prodOrderNo);
		
	}

	// 會員中心 - 修改收件資訊
	public ProductOrderVO updateReceiverInfo(Integer prodOrderNo, String receiverName, String receiverTel, String shippingAdd) {
		ProductOrderVO productOrderVO = new ProductOrderVO();
		if(prodOrderNo != null) {
			productOrderVO.setReceiverName(receiverName);
			productOrderVO.setReceiverTel(receiverTel);
			productOrderVO.setShippingAdd(shippingAdd);
			
			dao.update(productOrderVO);
		}
		return productOrderVO;
		
	}
	
	// 新增訂單
	public ProductOrderVO addOrder(Integer number, Integer amountPrice, Integer prodTotal, String receiverName, String receiverTel, String shippingAdd) {
		ProductOrderVO productOrderVO = new ProductOrderVO();
		
		productOrderVO.setNumber(number);
		productOrderVO.setAmountPrice(amountPrice);
		productOrderVO.setProdTotal(prodTotal);
		productOrderVO.setReceiverName(receiverName);
		productOrderVO.setReceiverTel(receiverTel);
		productOrderVO.setShippingAdd(shippingAdd);
		dao.insert(productOrderVO);
		return productOrderVO;
	}
	
	
	
	
}
