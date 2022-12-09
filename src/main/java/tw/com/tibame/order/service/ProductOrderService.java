package tw.com.tibame.order.service;

import java.util.List;

import tw.com.tibame.order.vo.ProductOrderVO;

public interface ProductOrderService {

	// 會員中心 - 查詢該會員所有訂單
	List<ProductOrderVO> getByNumberOrder(Integer number);

	// 會員中心 - 查詢單筆訂單
	ProductOrderVO getOneOrder(Integer prodOrderNo);

	// 會員中心 - 修改收件資訊
	ProductOrderVO updateReceiverInfo(Integer prodOrderNo, String receiverName, String receiverTel, String shippingAdd);

	// 新增訂單
	ProductOrderVO addOrder(Integer number, Integer amountPrice, Integer prodTotal, String receiverName,
			String receiverTel, String shippingAdd);

}