package tw.com.tibame.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.tibame.order.service.ProductOrderService;
import tw.com.tibame.order.service.ProductOrderServiceImpl;
import tw.com.tibame.order.vo.ProductOrderVO;

@WebServlet("/ProductOrder/addOrder")
public class addProductOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductOrderService service;
	
	@Override
	public void init() throws ServletException {
		service = new ProductOrderServiceImpl();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Integer number =  (Integer) request.getSession().getAttribute("number");
		Integer amountPrice = (Integer) request.getSession().getAttribute("amountPrice");
		Integer prodTotal = (Integer) request.getSession().getAttribute("prodTotal");
		String receiverName = (String) request.getSession().getAttribute("receiverName");
		String receiverTel = (String) request.getSession().getAttribute("receiverTel");
		String shippingAdd = (String) request.getSession().getAttribute("shippingAdd");
		
		ProductOrderVO vo = service.addOrder(number, amountPrice, prodTotal, receiverName, receiverTel, shippingAdd);
		
//		request.setAttribute("", vo);
//		request.getRequestDispatcher(".jsp").forward(request, response);;
	}

}
