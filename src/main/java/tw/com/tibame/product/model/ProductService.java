package tw.com.tibame.product.model;

import java.util.List;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductJDBCDAO();
	}

	public ProductVO addProduct(Integer eventNumber, Integer organizerNumber, String prodName, String prodSpec,
			Integer unitPrice, Integer prodStock, String prodDetails, Boolean isPOn, ProductImageVO prodimgvo) {

		ProductVO prodVo = new ProductVO();

		prodVo.setEventNumber(eventNumber);
		prodVo.setOrganizerNumber(organizerNumber);
		prodVo.setProdName(prodName);
		prodVo.setProdSpec(prodSpec);
		prodVo.setUnitPrice(unitPrice);
		prodVo.setProdStock(prodStock);
		prodVo.setProdDetails(prodDetails);
		prodVo.setIsPOn(isPOn);

		dao.insert(prodVo, prodimgvo);

		return prodVo;
	}

	
	public ProductVO updateProduct(Integer eventNumber, Integer organizerNumber, String prodName, String prodSpec,
			Integer unitPrice, Integer prodStock, String prodDetails, Boolean isPOn, Integer prodNo) {

		ProductVO prodVo = new ProductVO();

		prodVo.setEventNumber(eventNumber);
		prodVo.setOrganizerNumber(organizerNumber);
		prodVo.setProdName(prodName);
		prodVo.setProdSpec(prodSpec);
		prodVo.setUnitPrice(unitPrice);
		prodVo.setProdStock(prodStock);
		prodVo.setProdDetails(prodDetails);
		prodVo.setIsPOn(isPOn);
		prodVo.setProdNo(prodNo);
		dao.update(prodVo);

		return prodVo;
	}

//	public void deleteProduct(Integer prodNo) {
//		dao.delete(prodNo);
//	}

	public ProductVO getOneProduct(Integer prodNo) {
		return dao.findByPrimaryKey(prodNo);
	}
	
	public ProductVO getOneProductByProductName(String prodName) {
		return dao.findByProductName(prodName);
	}
	
	public List<ProductVO> getAll() {
		return dao.getAll();
	}
}