package common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.vo.ProductOrderVO;

@WebServlet("/ConnectionTest_JNDI")
public class ConnectionTest_JNDI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ConnectionTest_JNDI() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ProductOrderVO vo = null;
		
		String sql = "select * from PROD_ORDER where number = ?";
		
		try(Connection conn = ServiceLocator.getInstance().getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);){
			
			ps.setInt(1, 5);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				vo = new ProductOrderVO();
				
				vo.setProdOrderNo(rs.getInt(1));
				vo.setNumber(rs.getInt(2));
				vo.setAmountPrice(rs.getInt(3));
				vo.setProdTotal(rs.getInt(4));
				vo.setPaymentDate(rs.getTimestamp(5));
				vo.setReceiverName(rs.getString(6));
				vo.setReceiverTel(rs.getString(7));
				vo.setShippingAdd(rs.getString(8));
				vo.setProdOrderStatus(rs.getString(9));
				vo.setDeliveryStatus(rs.getString(10));
			
			}
			
			System.out.println(vo.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
