package tw.com.tibame.event.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import tw.com.tibame.util.common.Common;

public class OrderJDBCDAO implements OrderDAO_interface {

    private static final String insertSQL = 
            " INSERT INTO `order` "
            + " ( "
            + "    eventNumber    "
            + "  , number         "
            + "  , orderDate      "
            + "  , orderType      "
            + "  , total          "
            + "  , totalTicket    "
            + "  , pData          "
            + "  , reason         "
            + "  , reasonMoney    "
            + "  , eventScore     "
            + "  , eventSContent  "
            + "  , eventSDate     "
            + "  ) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, null, NULL, NULL)"
            ;
    private static final String queryByOrderIdSQL = "SELECT * from `ORDER` where orderID = ? ";
    
    private static final String updateSQL = 
            " UPDATE `ORDER` set %s where orderID = ? " ;
    
    @Override
    public int insert(OrderVO vo,List<SoldTicketsVO> solList) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int orderId = 0;
        try {
            Class.forName(Common.driver);
            conn = DriverManager.getConnection(Common.URL,Common.USER,Common.PASSWORD);
            
            conn.setAutoCommit(false);
            String[] c = {"orderID"};
            ps = conn.prepareStatement(insertSQL,c);
            
            
            ps.setInt(1,vo.getEventNumber());
            ps.setInt(2,vo.getNumber());
            ps.setTimestamp(3, vo.getOrderDate());
            ps.setString(4, vo.getOrderType());
            ps.setInt(5,vo.getTotal());
            ps.setInt(6,vo.getTotalTicket());
            ps.setString(7,vo.getpData());
            ps.setString(8,vo.getReason());
            ps.setInt(9,vo.getReasonMoney());
            
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            if(rs.next()) {
                orderId = rs.getInt(1);
                System.out.println("自增主鍵值= " + orderId);
            }else {
				System.out.println("未取得自增主鍵值");
			}
            System.out.println(" order inserted!!");
            rs.close();
            
            SoldTicketsJDBCDAO soDao = new SoldTicketsJDBCDAO();
            for( SoldTicketsVO sovo : solList) {
            	sovo.setOrderID(orderId);
            	soDao.insert(sovo, conn);
            }
            
            conn.commit();
			conn.setAutoCommit(true);
            
        // Handle any driver errors
        } catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (conn != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-Order");
					conn.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
        return orderId;
    }

    @Override
    public int update(OrderVO vo,List<SoldTicketsVO> solList) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rowCount = 0;
        String setSQL = "";
        try {
        	
            if(vo.getOrderType() != null) {
                setSQL += " orderType = ? ,";
            }
            if(vo.getTotal() != null) {
                setSQL += " total = ? ,";
            }
            if(vo.getTotalTicket() != null) {
                setSQL += " totalTicket = ? ,";
            }
            if(vo.getpData() != null) {
                setSQL += " pData = ? ,";
            }
            
            setSQL = setSQL.substring(0,setSQL.lastIndexOf(","));
            
            Class.forName(Common.driver);
            conn = DriverManager.getConnection(Common.URL,Common.USER,Common.PASSWORD);
            
            conn.setAutoCommit(false);
            
            ps = conn.prepareStatement(String.format(updateSQL, setSQL));
            int idx = 1;
            if(vo.getOrderType() != null) {
                ps.setString(idx, vo.getOrderType());
                idx++;
            }
            if(vo.getTotal() != null) {
                ps.setInt(idx,vo.getTotal());
                idx++;
            }
            if(vo.getTotalTicket() != null) {
                ps.setInt(idx,vo.getTotalTicket());
                idx++;
            }
            if(vo.getpData() != null) {
                ps.setString(idx, vo.getpData());
                idx++;
            }
            
            ps.setInt(idx,vo.getOrderID());
            
            rowCount = ps.executeUpdate();
            
            SoldTicketsJDBCDAO soDao = new SoldTicketsJDBCDAO();
            for( SoldTicketsVO sovo : solList) {
            	sovo.setOrderID(vo.getOrderID());
            	soDao.insert(sovo, conn);
            }
            
            conn.commit();
			conn.setAutoCommit(true);
            
        // Handle any driver errors
        } catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (conn != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-Order");
					conn.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
        return rowCount;
    }
    
    
    
    @Override
	public int updateOrder(OrderVO vo) {

        Connection conn = null;
        PreparedStatement ps = null;
        int rowCount = 0;
        String setSQL = "";
        try {
        	
            if(vo.getOrderType() != null) {
                setSQL += " orderType = ? ,";
            }
            if(vo.getTotal() != null) {
                setSQL += " total = ? ,";
            }
            if(vo.getTotalTicket() != null) {
                setSQL += " totalTicket = ? ,";
            }
            if(vo.getpData() != null) {
                setSQL += " pData = ? ,";
            }
            
            setSQL = setSQL.substring(0,setSQL.lastIndexOf(","));
            
            Class.forName(Common.driver);
            conn = DriverManager.getConnection(Common.URL,Common.USER,Common.PASSWORD);
            
            conn.setAutoCommit(false);
            
            ps = conn.prepareStatement(String.format(updateSQL, setSQL));
            int idx = 1;
            if(vo.getOrderType() != null) {
                ps.setString(idx, vo.getOrderType());
                idx++;
            }
            if(vo.getTotal() != null) {
                ps.setInt(idx,vo.getTotal());
                idx++;
            }
            if(vo.getTotalTicket() != null) {
                ps.setInt(idx,vo.getTotalTicket());
                idx++;
            }
            if(vo.getpData() != null) {
                ps.setString(idx, vo.getpData());
                idx++;
            }
            
            ps.setInt(idx,vo.getOrderID());
            
            rowCount = ps.executeUpdate();
            
            
            conn.commit();
			conn.setAutoCommit(true);
            
        // Handle any driver errors
        } catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (conn != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-Order");
					conn.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
        return rowCount;
    
	}



	private static final String selectByEventNumberSQL =
            "select * from `ORDER` where eventNumber = ?";

    @Override
    public List<OrderVO> selectByEventNumber(int eventNumber) {
        
        List<OrderVO>  list = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(Common.driver);
            conn = DriverManager.getConnection(Common.URL,Common.USER,Common.PASSWORD);
            ps = conn.prepareStatement(selectByEventNumberSQL);
            ps.setInt(1, eventNumber);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                OrderVO vo = buildVO(rs);
                list.add(vo);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (Exception e) {}
            try {
                ps.close();
            } catch (Exception e) {}
            try {
                conn.close();
            } catch (Exception e) {}
        }
        
        return list;
    
        
    }
    private OrderVO buildVO (ResultSet rs) throws Exception {
        OrderVO vo = new OrderVO(
                rs.getInt("orderID"),
                rs.getInt("eventNumber"), 
                rs.getInt("number"),
                rs.getTimestamp("orderDate"),
                rs.getString("orderType"), 
                rs.getInt("total"), 
                rs.getInt("totalTicket"),
                rs.getString("pData"),
                rs.getString("reason"),
                rs.getInt("reasonMoney"), 
                rs.getFloat("eventScore"), 
                rs.getString("eventSContent"), 
                rs.getTimestamp("eventSDate"));
        return vo;
    }
    

    @Override
    public OrderVO queryByOrderId(int OrderId) {
        OrderVO vo = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(Common.driver);
            conn = DriverManager.getConnection(Common.URL,Common.USER,Common.PASSWORD);
            ps = conn.prepareStatement(queryByOrderIdSQL);
            ps.setInt(1, OrderId);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                vo = buildVO(rs);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (Exception e) {}
            try {
                ps.close();
            } catch (Exception e) {}
            try {
                conn.close();
            } catch (Exception e) {}
        }
        
        return vo;
    }

    @Override
    public List<OrderVO> selectByDate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrderVO> selectByOrderType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrderVO> selectByNumber(int number) {
        // TODO Auto-generated method stub
        return null;
    }

    
    
    
}
