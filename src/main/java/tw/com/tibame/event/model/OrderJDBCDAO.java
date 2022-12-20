package tw.com.tibame.event.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import tw.com.tibame.staff.model.StaffVO;
import tw.com.tibame.util.common.Common;

public class OrderJDBCDAO implements OrderDAO_interface {

	private static final String INSERT = "";
			
	private static final String UPDATE = "";
			
	private static final String GET_ORDER_BY_EVENT_NUMBER = 
			"SELECT orderID , number , orderDate, orderType, total , totalTicket , pData ,reason \r\n"
			+ "FROM `ORDER` o , `EVENT` e\r\n"
			+ "WHERE e.eventNumber = 2;";
	private static final String GET_ORDER_BY_ORDER_DATE = "";
	private static final String GET_ORDER_BY_ORDER_TYPE = "";
	private static final String GET_ORDER_BY_NUMBER = "";
	private static final String GET_BY_ORDERID =
			"select o.orderID,o.orderType,o.totalTicket,o.total,\r\n"
			+ "			e.eventName,e.eventPlace,e.bigImg,e.eventStartDate,\r\n"
			+ "			g.organizerName,\r\n"
			+ "			m.`number`\r\n"
			+ "			from `MEMBER` m,`ORDER` o ,`EVENT` e,`ORGANIZER` g\r\n"
			+ "			where m.`number` = 1\r\n"
			+ "			and m.`number` = o.`number`\r\n"
			+ "			and o.eventNumber = e.eventNumber\r\n"
			+ "			and e.organizerNumber = g.organizerNumber\r\n"
			+ "			and o.eventNumber = e.eventNumber;";
	private static final String GET_ORGANIZER_BY_NUMBER=
			"SELECT eventNumber,eventName,eventType,eventStartDate,eventEndDate \r\n"
			+ "\r\n"
			+ "FROM TICK_IT_TEST.`EVENT` e,ORGANIZER o\r\n"
			+ "where o.organizerNumber = 1\r\n"
			+ "and e.organizerNumber = o.organizerNumber;";
	
	
	@Override
	public int insert(OrderVO orderVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(OrderVO orderVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderVO> selectByEventNumber(Integer eventNumber) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		// 初值
		OrderVO OrderVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			System.out.println("selectByEventNumber jdbc");
			Class.forName(Common.driver);

			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			pstmt = conn.prepareStatement(GET_ORDER_BY_EVENT_NUMBER);
//			pstmt.setInt(1, eventNumber);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderVO = new OrderVO();

				OrderVO.setOrderID(rs.getInt("orderID"));
				OrderVO.setNumber(rs.getInt("number"));
				OrderVO.setOrderDate(rs.getTimestamp("orderdate"));
				OrderVO.setOrderType(rs.getString("orderType"));
				OrderVO.setTotal(rs.getInt("total"));
				OrderVO.setTotalTicket(rs.getInt("totalTicket"));
				OrderVO.setpData(rs.getString("pData"));
				OrderVO.setReason(rs.getString("reason"));

				list.add(OrderVO);
				// 將上述SET完成的vo，塞進去這個集合。Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

		}

		return list;

	}

	@Override
	public List<OrderVO> selectByOrderDate(Timestamp orderDate) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		// 初值
		OrderVO OrderVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			System.out.println("selectByorderDate jdbc");
			Class.forName(Common.driver);

			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			pstmt = conn.prepareStatement(GET_ORDER_BY_ORDER_DATE);
			pstmt.setTimestamp(1, orderDate);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderVO = new OrderVO();

//				eventNumber, `number`, orderDate, orderType, total, totalTicket, pData, reason, reasonMoney, eventScore, eventSContent, eventSDate
				OrderVO.setEventNumber(rs.getInt("eventNumber"));
				OrderVO.setNumber(rs.getInt("number"));
				OrderVO.setOrderDate(rs.getTimestamp("orderdate"));
				OrderVO.setOrderType(rs.getString("orderType"));
				OrderVO.setTotal(rs.getInt("total"));
				OrderVO.setTotalTicket(rs.getInt("totalTicket"));
				OrderVO.setpData(rs.getString("pData"));
				OrderVO.setReason(rs.getString("reason"));
				OrderVO.setReasonMoney(rs.getInt("reasonMoney"));
				OrderVO.setEventScore(rs.getDouble("eventScore"));
				OrderVO.setEventSContent(rs.getString("eventSContent"));
				OrderVO.setEventSDate(rs.getTimestamp("eventSDate"));

				list.add(OrderVO);
				// 將上述SET完成的vo，塞進去這個集合。Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

		}

		return list;

	}

	@Override
	public List<OrderVO> selectByOrderType(String orderType) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		// 初值
		OrderVO OrderVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			System.out.println("selectByorderType jdbc");
			Class.forName(Common.driver);

			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			pstmt = conn.prepareStatement(GET_ORDER_BY_ORDER_TYPE);
			pstmt.setString(1, orderType);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderVO = new OrderVO();

//				eventNumber, `number`, orderDate, orderType, total, totalTicket, pData, reason, reasonMoney, eventScore, eventSContent, eventSDate
				OrderVO.setEventNumber(rs.getInt("eventNumber"));
				OrderVO.setNumber(rs.getInt("number"));
				OrderVO.setOrderDate(rs.getTimestamp("orderdate"));
				OrderVO.setOrderType(rs.getString("orderType"));
				OrderVO.setTotal(rs.getInt("total"));
				OrderVO.setTotalTicket(rs.getInt("totalTicket"));
				OrderVO.setpData(rs.getString("pData"));
				OrderVO.setReason(rs.getString("reason"));
				OrderVO.setReasonMoney(rs.getInt("reasonMoney"));
				OrderVO.setEventScore(rs.getDouble("eventScore"));
				OrderVO.setEventSContent(rs.getString("eventSContent"));
				OrderVO.setEventSDate(rs.getTimestamp("eventSDate"));

				list.add(OrderVO);
				// 將上述SET完成的vo，塞進去這個集合。Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

		}

		return list;

	}

	@Override
	public List<OrderVO> selectByNumber(Integer number) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		// 初值
		OrderVO OrderVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			System.out.println("selectBynumber jdbc");
			Class.forName(Common.driver);

			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			pstmt = conn.prepareStatement(GET_ORDER_BY_NUMBER);
			pstmt.setInt(1, number);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderVO = new OrderVO();

//				eventNumber, `number`, orderDate, orderType, total, totalTicket, pData, reason, reasonMoney, eventScore, eventSContent, eventSDate
				OrderVO.setEventNumber(rs.getInt("eventNumber"));
				OrderVO.setNumber(rs.getInt("number"));
				OrderVO.setOrderDate(rs.getTimestamp("orderdate"));
				OrderVO.setOrderType(rs.getString("orderType"));
				OrderVO.setTotal(rs.getInt("total"));
				OrderVO.setTotalTicket(rs.getInt("totalTicket"));
				OrderVO.setpData(rs.getString("pData"));
				OrderVO.setReason(rs.getString("reason"));
				OrderVO.setReasonMoney(rs.getInt("reasonMoney"));
				OrderVO.setEventScore(rs.getDouble("eventScore"));
				OrderVO.setEventSContent(rs.getString("eventSContent"));
				OrderVO.setEventSDate(rs.getTimestamp("eventSDate"));

				list.add(OrderVO);
				// 將上述SET完成的vo，塞進去這個集合。Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

		}

		return list;

	}

	@Override
	public List<OrderEventVO> findByNumber() {
		List<OrderEventVO> list = new ArrayList<OrderEventVO>();
		// 初值
		OrderVO OrderVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(Common.driver);

			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			pstmt = conn.prepareStatement(GET_BY_ORDERID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderEventVO orderEventVO = new OrderEventVO();
				
				orderEventVO.setNumber(rs.getInt("number"));
				orderEventVO.setOrderID(rs.getInt("orderID"));
				orderEventVO.setOrderType(rs.getString("orderType"));
				orderEventVO.setTotalTicket(rs.getInt("totalTicket"));
				orderEventVO.setTotal(rs.getInt("total"));
				orderEventVO.setEventName(rs.getString("eventName"));
				orderEventVO.setEventPlace(rs.getString("eventPlace"));
				orderEventVO.setBigImg(rs.getBlob("bigimg"));
				orderEventVO.setEventStartDate(rs.getTimestamp("eventStartDate"));
				orderEventVO.setOrganizerName(rs.getString("organizerName"));
//System.out.println("getTimestamp="+ rs.getTimestamp("eventStartDate"));
				list.add(orderEventVO);
				
//				System.out.println("getEventStartDate ="+orderEventVO.getEventStartDate() );
						
				// 將上述SET完成的vo，塞進去這個集合。Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

		}

		return list;

	}

	@Override
	public List<EventVO> findByOrganizerNumber() {
		List<EventVO> list = new ArrayList<EventVO>();
		// 初值
//		EventVO eventVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(Common.driver);

			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			pstmt = conn.prepareStatement(GET_ORGANIZER_BY_NUMBER);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				EventVO eventVO = new EventVO();
				
				eventVO.setEventNumber(rs.getInt("eventNumber"));
				eventVO.setEventName(rs.getString("eventName"));
				eventVO.setEventType(rs.getString("eventType"));
				eventVO.setEventStartDate(rs.getTimestamp("eventStartDate"));
				eventVO.setEventEndDate(rs.getTimestamp("eventEndDate"));
				
				list.add(eventVO);					
				// 將上述SET完成的vo，塞進去這個集合。Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

		}
		EventVO s = list.get(0);
		return list;

	}
		

}