package tw.com.tibame.order.service.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tibame.order.dao.ProductOrderDAO;
import tw.com.tibame.order.service.ProductOrderService;
import tw.com.tibame.order.vo.ProductOrderVO;
import tw.com.tibame.order.vo.ViewProductOrderVO;

@Service
@Transactional
public class ProductOrderServiceImpl implements ProductOrderService {
	@Autowired
	private ProductOrderDAO productOrderDAO;
	

	public ProductOrderServiceImpl(ProductOrderDAO productOrderDAO) {
		super();
		this.productOrderDAO = productOrderDAO;
	}

	// 會員中心 - 查詢該會員所有訂單
	@Override
	public List<ProductOrderVO> getByNumberOrder(Integer number){
		return productOrderDAO.getByNumber(number);
	}
	
	// 會員中心 - 查詢單筆訂單
	@Override
	public ProductOrderVO getOneOrder(Integer prodOrderNo) {
		return productOrderDAO.getByPrimaryKey(prodOrderNo);
		
	}

	// 會員中心 - 修改收件資訊
	@Override
	public ProductOrderVO updateReceiverInfo(Integer prodOrderNo, String receiverName, String receiverTel, String shippingAdd) {
		ProductOrderVO  productOrderVO = productOrderDAO.getByPrimaryKey(prodOrderNo);
		productOrderVO.setReceiverName(receiverName);
		productOrderVO.setReceiverTel(receiverTel);
		productOrderVO.setShippingAdd(shippingAdd);
		
		productOrderDAO.update(productOrderVO);
		return productOrderVO;
	}
	
	// 新增訂單
	@Override
	public ProductOrderVO addOrder(ProductOrderVO productOrderVO) {
		if(productOrderVO != null) {
			productOrderVO.setPaymentDate(new java.sql.Timestamp(new GregorianCalendar().getTimeInMillis()));
			productOrderVO.setProdOrderStatus("訂單成立");
			productOrderVO.setDeliveryStatus("處理中");
			
			return productOrderDAO.insert(productOrderVO);
		}
		return null;		
	}

	// 會員中心 - 查詢該會員所有訂單
	@Override
	public List<ViewProductOrderVO> findByNumberOrder(Integer number) {
		return productOrderDAO.findByNumber(number);
	}

	// 會員中心 - 查詢單筆訂單
	@Override
	public ViewProductOrderVO findOneOrder(Integer prodOrderNo) {
		return productOrderDAO.findByPrimaryKey(prodOrderNo);
	}
	
	
	
	
}
