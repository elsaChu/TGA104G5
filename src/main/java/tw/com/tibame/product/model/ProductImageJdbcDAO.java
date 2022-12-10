package tw.com.tibame.product.model;

import static tw.com.tibame.util.common.Common.PASSWORD;
import static tw.com.tibame.util.common.Common.URL;
import static tw.com.tibame.util.common.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductImageJdbcDAO implements ProductImageDAO{
	
	private static final String SELECT_ALL = 
			"select prodIMGID, prodNo, prodIMGName, prodIMG from PRODUCT_IMG";
	@Override
	public List<ProductImageVO> getAll() {
		List<ProductImageVO> result = new ArrayList<>();
		try (
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
			/*
			 * 當Statement關閉，ResultSet也會自動關閉，可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductImageVO productImageVO = new ProductImageVO();
				productImageVO.setProdIMGID(rs.getInt("prodIMGID"));
				productImageVO.setProdNo(rs.getInt("prodNo"));
				productImageVO.setProdIMGName(rs.getString("prodIMGName"));
				productImageVO.setProdIMG(rs.getBytes("prodIMG"));  
				
				result.add(productImageVO);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
		
	
	private static final String SELECT_BY_ID = 
			"select prodIMGID, prodNo, prodIMGName, prodIMG from PRODUCT_IMG where prodIMGID=?";
	@Override
	public ProductImageVO getPrimaryKey(Integer prodIMGID) {

		ProductImageVO result = null;
		
		if(prodIMGID != null) {
			ResultSet rs = null;
			try(
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
				
				ps.setInt(1, prodIMGID);
				rs = ps.executeQuery();
				if(rs.next()) {
					result = new ProductImageVO();
					result.setProdIMGID(rs.getInt("prodIMGID"));
					result.setProdNo(rs.getInt("prodNo"));
					result.setProdIMGName(rs.getString("prodIMGName"));
					result.setProdIMG(rs.getBytes("prodIMG"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	
	
	private static final String INSERT = 
			  "insert into PRODUCT_IMG (prodNo, prodIMGName, prodIMG) values (?, ?, ?)";
	@Override
	public void insert(ProductImageVO productImageVO) {
		
		if(productImageVO != null && productImageVO.getProdNo() != null) {
			try (
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(INSERT)) {
				ps.setInt(1, productImageVO.getProdNo());
				ps.setString(2, productImageVO.getProdIMGName());
				ps.setBytes(3, productImageVO.getProdIMG());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}

	
	
	private static final String UPDATE =
			"update PRODUCT_IMG set prodNo=?, prodIMGName=?, prodIMG=? where prodIMGID=?";
	@Override
	public void update(ProductImageVO productImageVO) {
		if(productImageVO != null && productImageVO.getProdNo() != null) {
			try (
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(UPDATE)) {
				ps.setInt(1, productImageVO.getProdNo());
				ps.setString(2, productImageVO.getProdIMGName());
				ps.setBytes(3, productImageVO.getProdIMG());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}

	
	
	private static final String DELETE = "delete from PRODUCT_IMG where prodIMGID=?";
	@Override
	public boolean delete(Integer prodIMGID) {
		if(prodIMGID != null) {
			try (
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(DELETE)) {
				ps.setInt(1, prodIMGID);
				
				int rowCount = ps.executeUpdate();
				if(rowCount == 1) {
					return true;
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return false;
	}
}
