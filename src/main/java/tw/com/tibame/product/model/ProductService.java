package tw.com.tibame.product.model;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class ProductService {

	private ProductDAO_interface dao;
	private ProductImageDAO imagedao;

	public ProductService() {
		dao = new ProductJDBCDAO();
		imagedao = new ProductImageJdbcDAO();
	}

	public ProductVO addProduct(Integer eventNumber, Integer organizerNumber, String prodName, String prodSpec,
			Integer unitPrice, Integer prodStock, String prodDetails, Boolean isPOn, List<ProductImageVO> imglist ) {

		ProductVO prodVo = new ProductVO();

		prodVo.setEventNumber(eventNumber);
		prodVo.setOrganizerNumber(organizerNumber);
		prodVo.setProdName(prodName);
		prodVo.setProdSpec(prodSpec);
		prodVo.setUnitPrice(unitPrice);
		prodVo.setProdStock(prodStock);
		prodVo.setProdDetails(prodDetails);
		prodVo.setIsPOn(isPOn);

		dao.insert(prodVo, imglist);

		return prodVo;
	}

	
	public ProductVO updateProduct(Integer eventNumber, Integer organizerNumber, String prodName, String prodSpec,
			Integer unitPrice, Integer prodStock, String prodDetails, Boolean isPOn, Integer prodNo, List<ProductImageVO> imglist) {

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
		dao.update(prodVo, imglist);

		return prodVo;
	}

//	public void deleteProduct(Integer prodNo) {
//		dao.delete(prodNo);
//	}

	public ProductVO getOneProduct(Integer prodNo, Integer on) {
		return dao.findByPrimaryKey(prodNo, on);
	}
	
//	public ProductVO getOneProductByProductName(String prodName) {
//		return dao.findByProductName(prodName);
//	}
	
	public List<ProductVO> findByProductName(String pdname, Integer on) {
		return dao.findByProductName(pdname, on);
	}
	
	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
	public List<ProductVO> getAllByOrganizer(Integer OrganizerNumber) {
		return dao.getAllByOrganizer(OrganizerNumber);
	}
	
	public String showImage(Integer prodNo){
		List<ProductImageVO> prodimglist = imagedao.selectProdImage(prodNo);
		List<Map> base64image = new ArrayList<Map>();
		for(ProductImageVO onedata : prodimglist) {
			Base64.Encoder encoder = Base64.getEncoder();
			String bigImg64 = "data:image/jpeg;base64," + encoder.encodeToString(onedata.getProdIMG());
			Map map = new HashMap();
			map.put("BigImg64", bigImg64);
			base64image.add(map);
		};
		
		Gson gson = new Gson();
		String jsonimg = gson.toJson(base64image);
		System.out.println("SERVICE="+jsonimg.length());
		return jsonimg;
	}
}