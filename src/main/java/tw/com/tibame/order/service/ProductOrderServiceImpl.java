package tw.com.tibame.order.service;

import java.util.List;

import tw.com.tibame.order.dao.ProductOrderDAO;
import tw.com.tibame.order.vo.ProductOrderVO;

public class ProductOrderServiceImpl implements ProductOrderService {
	private ProductOrderDAO dao;
	
	// 會員中心 - 查詢該會員所有訂單
	@Override
	public List<ProductOrderVO> getByNumberOrder(Integer number){
		return dao.getByNumber(number);
	}
	
	// 會員中心 - 查詢單筆訂單
	@Override
	public ProductOrderVO getOneOrder(Integer prodOrderNo) {
		return dao.getPrimaryKey(prodOrderNo);
		
	}

	// 會員中心 - 修改收件資訊
	@Override
	public ProductOrderVO updateReceiverInfo(Integer prodOrderNo, String receiverName, String receiverTel, String shippingAdd) {
		ProductOrderVO productOrderVO = new ProductOrderVO();
		if(!receiverName.trim().isEmpty() && !receiverTel.trim().isEmpty() && !shippingAdd.trim().isEmpty()) {
			productOrderVO.setReceiverName(receiverName);
			productOrderVO.setReceiverTel(receiverTel);   //需要驗證格式
			productOrderVO.setShippingAdd(shippingAdd);   //需要驗證格式
			
			dao.update(productOrderVO);
		}
		return productOrderVO;
		
	}
	
	// 新增訂單
	@Override
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
