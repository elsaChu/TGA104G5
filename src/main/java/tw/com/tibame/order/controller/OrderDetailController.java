package tw.com.tibame.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.tibame.order.service.OrderDetailService;
import tw.com.tibame.order.service.OrderDetailServiceImpl;
import tw.com.tibame.order.vo.OrderDetailVO;


@WebServlet("/ProductOrder/detail")
public class OrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderDetailService service;
	
	@Override
	public void init() throws ServletException {
		service = new OrderDetailServiceImpl();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//		Integer prodOrderNo = (Integer) request.getSession().getAttribute("prodOrderNo");
		Integer prodOrderNo = 2; // 先寫死測試
		List<OrderDetailVO> list = service.getByProdOrderNo(prodOrderNo);
		request.setAttribute("detaillist", list);
		request.getRequestDispatcher("/front-end/order/selectByProdOrderNo.jsp").forward(request, response);
		
	}

}
